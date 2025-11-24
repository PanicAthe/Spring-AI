package com.example.demo.service.ch03_prompt;

import java.util.List;
import java.util.Objects;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class AiServiceStepBackPrompt {
    
    private final ChatClient chatClient;

    public AiServiceStepBackPrompt(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // Return Type을 Flux<String>으로 변경
    public Flux<String> stepBackPrompt(String question) {
        return Flux.create(sink -> {
            try {
                // 1. 단계별 질문 계획 생성
                String questions = chatClient.prompt()
                    .user("""
                        사용자 질문을 처리하기 위해 Step-Back 프롬프트 기법을 사용하려고 합니다.
                        사용자 질문을 단계별 질문들로 재구성해주세요. 
                        맨 마지막 질문은 사용자 질문과 일치해야 합니다.
                        단계별 질문을 항목으로 하는 JSON 배열로 출력해 주세요.
                        예시: ["...", "...", "...", ...]
                        사용자 질문: %s
                        """.formatted(question))
                    .call()
                    .content();

                // JSON 파싱을 위해 대괄호 부분만 추출
                String json = questions.substring(questions.indexOf("["), questions.indexOf("]") + 1);
                
                // [스트림 전송 1] 계획 알림
                sink.next("**[생성된 질문 계획]**\n" + json + "\n\n---\n");

                ObjectMapper objectMapper = new ObjectMapper();
                List<String> listQuestion = objectMapper.readValue(json, new TypeReference<List<String>>() {});

                String[] answerArray = new String[listQuestion.size()];

                // 2. 단계별 순회 및 답변 생성
                for (int i = 0; i < listQuestion.size(); i++) {
                    String stepQuestion = listQuestion.get(i);
                    // [스트림 전송 2] 현재 단계 질문 알림
                    sink.next(String.format("### 단계 %d\n**질문:** %s\n\n", i + 1, stepQuestion));
                    
                    String stepAnswer = getStepAnswer(stepQuestion, answerArray);
                    answerArray[i] = stepAnswer;

                    // [스트림 전송 3] 현재 단계 답변 알림
                    sink.next(String.format("**답변:** %s\n\n", stepAnswer));
                }

                // 스트림 종료
                sink.complete();
                
            } catch (Exception e) {
                log.error("Step-back Prompt Error", e);
                sink.error(e);
            }
        });
    }

    public String getStepAnswer(String question, String... prevStepAnswers) {
        String context = "";
        for (String prevStepAnswer : prevStepAnswers) {
            context += Objects.requireNonNullElse(prevStepAnswer, "");
        }
        return chatClient.prompt()
            .user("""
                %s
                문맥: %s
                """.formatted(question, context))
            .call()
            .content();
    }
}