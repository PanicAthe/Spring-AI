package com.example.demo.service.ch03_prompt;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AiServiceSelfConsistency {
  // ##### 필드 #####
  private ChatClient chatClient;
  private PromptTemplate promptTemplate = PromptTemplate.builder()
      .template("""
          다음 내용을 [IMPORTANT, NOT_IMPORTANT] 둘 중 하나로 분류해 주세요.
          레이블만 반환하세요. 
          내용: {content}
          """)
      .build();

  // ##### 생성자 #####
  public AiServiceSelfConsistency(ChatClient.Builder chatClientBuilder) {
    chatClient = chatClientBuilder.build();
  }

  // ##### 메소드 #####
  public String selfConsistency(String content) {
    int importantCount = 0;
    int notImportantCount = 0;
    
    String userText = promptTemplate.render(Map.of("content", content));
  
    // 다섯번에 걸쳐 응답 받아 보기
    for (int i = 0; i < 5; i++) {
      String output = chatClient.prompt()
          .user(userText)
          .options(ChatOptions.builder()
              .temperature(1.0) // 창의성 최대 (다양한 답변 유도)
              .build())
          .call()
          .content();
  
      log.info("{}: [{}]", i, output); // 로그에 대괄호를 쳐서 공백이 있는지 확인하세요
  
      // 공백 제거 및 대소문자 무시 후 포함 여부 확인
      String cleanedOutput = output.trim().toUpperCase();

      if (cleanedOutput.contains("IMPORTANT") && !cleanedOutput.contains("NOT")) {
        // "IMPORTANT"는 포함하지만 "NOT IMPORTANT"는 아닐 때
        importantCount++;
      } else if (cleanedOutput.contains("NOT_IMPORTANT") || cleanedOutput.contains("NOT IMPORTANT")) {
        notImportantCount++;
      } else {
        // AI가 이상한 말을 했을 경우 (예: "모르겠습니다")
        // 안전하게 중요하지 않음으로 분류하거나, 로그만 남김
        log.warn("분류 실패: {}", output);
        notImportantCount++; 
      }
    }
  
    String finalClassification = importantCount > notImportantCount ?
            "중요함" : "중요하지 않음";
    
    // 결과 로그 추가
    log.info("투표 결과 - 중요: {}, 안중요: {} -> 최종: {}", 
             importantCount, notImportantCount, finalClassification);

    return finalClassification;
  }
}
