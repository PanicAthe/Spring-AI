package com.example.demo.controller.ch03_prompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ch03_prompt.AiServiceChainOfThoughtPrompt;
import com.example.demo.service.ch03_prompt.AiServiceDefaultMethod;
import com.example.demo.service.ch03_prompt.AiServiceFewShotPrompt;
import com.example.demo.service.ch03_prompt.AiServiceMultiMessages;
import com.example.demo.service.ch03_prompt.AiServicePromptTemplate;
import com.example.demo.service.ch03_prompt.AiServiceRoleAssignmentPrompt;
import com.example.demo.service.ch03_prompt.AiServiceSelfConsistency;
import com.example.demo.service.ch03_prompt.AiServiceStepBackPrompt;
import com.example.demo.service.ch03_prompt.AiServiceZeroShotPrompt;
import com.example.demo.service.ch03_prompt.PromptService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai/prompt")
@Slf4j
public class PromptController {
  // ##### 필드 #####
  @Autowired
  private PromptService aiService;

  @Autowired
  private AiServiceZeroShotPrompt aiServiceZeroShot;

  @Autowired
  private AiServiceStepBackPrompt aiServiceStepBack;

  @Autowired
  private AiServiceSelfConsistency aiServiceSelfConsistency;

  @Autowired
  private AiServiceRoleAssignmentPrompt aiServiceRoleAssignment;

  @Autowired
  private AiServicePromptTemplate aiServicePromptTemplate;

  @Autowired
  private AiServiceMultiMessages aiServiceMultiMessages;

  @Autowired
  private AiServiceFewShotPrompt aiServiceFewShot;

  @Autowired
  private AiServiceDefaultMethod aiServiceDefaultMethod;

  @Autowired
  private AiServiceChainOfThoughtPrompt aiServiceChainOfThought;

  // ##### 요청 매핑 메소드 #####
  @PostMapping(value = "/chat-model", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String chatModel(@RequestParam("question") String question) {
    String answerText = aiService.generateText(question);
    return answerText;
  }

  @PostMapping(value = "/chat-model-stream", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_NDJSON_VALUE // 라인으로
                                                                                                                                                   // 구분된
                                                                                                                                                   // 청크
                                                                                                                                                   // 텍스트
  )
  public Flux<String> chatModelStream(@RequestParam("question") String question) {
    Flux<String> answerStreamText = aiService.generateStreamText(question);
    return answerStreamText;
  }

  @PostMapping(value = "/zero-shot-prompt", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String zeroShotPrompt(@RequestParam("review") String review) {
    String reviewSentiment = aiServiceZeroShot.zeroShotPrompt(review);
    return reviewSentiment;
  }

@PostMapping(value = "/step-back-prompt", 
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
        produces = MediaType.TEXT_EVENT_STREAM_VALUE) // 핵심: Event Stream
    public Flux<String> stepBackPrompt(@RequestParam("question") String question) throws Exception {
        return aiServiceStepBack.stepBackPrompt(question);
    }

  @PostMapping(value = "/self-consistency", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String selfConsistency(@RequestParam("content") String content) {
    String answer = aiServiceSelfConsistency.selfConsistency(content);
    return answer;
  }

  @PostMapping(value = "/role-assignment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_NDJSON_VALUE)
  public Flux<String> roleAssignment(@RequestParam("requirements") String requirements) {
    Flux<String> travelSuggestions = aiServiceRoleAssignment.roleAssignment(requirements);
    return travelSuggestions;
  }

  @PostMapping(value = "/prompt-template", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_NDJSON_VALUE)
  public Flux<String> promptTemplate(
      @RequestParam("statement") String statement,
      @RequestParam("language") String language) {
    Flux<String> response = aiServicePromptTemplate.promptTemplate1(statement, language);
    return response;
  }

  @PostMapping(value = "/multi-messages", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  @SuppressWarnings("unchecked")
  public String multiMessages(
      @RequestParam("question") String question, HttpSession session) {
    List<Message> chatMemory = (List<Message>) session.getAttribute("chatMemory");
    if (chatMemory == null) {
      chatMemory = new ArrayList<Message>();
      session.setAttribute("chatMemory", chatMemory);
    }
    String answer = aiServiceMultiMessages.multiMessages(question, chatMemory);
    return answer;
  }

  @PostMapping(value = "/few-shot-prompt", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
  public String fewShotPrompt(@RequestParam("order") String order) {
    return aiServiceFewShot.fewShotPrompt(order);
  }

  @PostMapping(value = "/default-method", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_NDJSON_VALUE)
  public Flux<String> defaultMethod(@RequestParam("question") String question) {
    Flux<String> response = aiServiceDefaultMethod.defaultMethod(question);
    return response;
  }

  @PostMapping(value = "/chain-of-thought", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_NDJSON_VALUE)
  public Flux<String> chainOfThought(@RequestParam("question") String question) {
    Flux<String> answer = aiServiceChainOfThought.chainOfThought(question);
    return answer;
  }

}
