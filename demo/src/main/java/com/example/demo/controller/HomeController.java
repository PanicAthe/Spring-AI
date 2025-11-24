package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/ch10_rag/rag")
  public String rag() {
    return "ch10_rag/rag";
  }

  @GetMapping("/ch10_rag/compression-query-transformer")
  public String compressionQueryTransformer() {
    return "ch10_rag/compression-query-transformer";
  }  

  @GetMapping("/ch10_rag/rewrite-query-transformer")
  public String rewriteQueryTransformer() {
    return "ch10_rag/rewrite-query-transformer";
  }   

  @GetMapping("/ch10_rag/translation-query-transformer")
  public String translationQueryTransformer() {
    return "ch10_rag/translation-query-transformer";
  }     

  @GetMapping("/ch10_rag/multi-query-expander")
  public String multiQueryExpander() {
    return "ch10_rag/multi-query-expander";
  }   

    @GetMapping("/ch03_prompt/prompt-template")
  public String promptTemplate() {
    return "ch03_prompt/prompt-template";
  }  
  
  @GetMapping("/ch03_prompt/multi-messages")
  public String multiMessages() {
    return "ch03_prompt/multi-messages";
  }
  
  @GetMapping("/ch03_prompt/default-method")
  public String defaultMethod() {
    return "ch03_prompt/default-method";
  }  
  
  @GetMapping("/ch03_prompt/zero-shot-prompt")
  public String zeroShotPrompt() {
    return "ch03_prompt/zero-shot-prompt";
  }  
  
  @GetMapping("/ch03_prompt/few-shot-prompt")
  public String fewShotPrompt() {
    return "ch03_prompt/few-shot-prompt";
  }  
  
  @GetMapping("/ch03_prompt/role-assignment")
  public String rollAssignment() {
    return "ch03_prompt/role-assignment";
  }   
  
  @GetMapping("/ch03_prompt/step-back-prompt")
  public String stepBackPrompt() {
    return "ch03_prompt/step-back-prompt";
  }

  @GetMapping("/ch03_prompt/chain-of-thought")
  public String chainOfThought() {
    return "ch03_prompt/chain-of-thought";
  }   

  @GetMapping("/ch03_prompt/self-consistency")
  public String selfConsistency() {
    return "ch03_prompt/self-consistency";
  } 

    @GetMapping("/ch04_structured_output/list-output-converter")
  public String listOutputConverter() {
    return "ch04_structured_output/list-output-converter";
  }    

  @GetMapping("/ch04_structured_output/bean-output-converter")
  public String beanOutputConverter() {
    return "ch04_structured_output/bean-output-converter";
  }  

  @GetMapping("/ch04_structured_output/generic-bean-output-converter")
  public String genericBeanOutputConverter() {
    return "ch04_structured_output/generic-bean-output-converter";
  }   

  @GetMapping("/ch04_structured_output/map-output-converter")
  public String mapOutputConverter() {
    return "ch04_structured_output/map-output-converter";
  }
  
  @GetMapping("/ch04_structured_output/system-message")
  public String systemMessage() {
    return "ch04_structured_output/system-message";
  }  

  @GetMapping("/ch07_advisor/advisor-chain")
  public String advisorChain() {
    return "ch07_advisor/advisor-chain";
  }
  
  @GetMapping("/ch07_advisor/advisor-context")
  public String advisorContext() {
    return "ch07_advisor/advisor-context";
  }  
  
  @GetMapping("/ch07_advisor/advisor-logging")
  public String advisorLogging() {
    return "ch07_advisor/advisor-logging";
  }  
  
  @GetMapping("/ch07_advisor/advisor-safe-guard")
  public String advisorSafeGuard() {
    return "ch07_advisor/advisor-safe-guard";
  }

  @GetMapping("/ch08_embedding_vector_store/text-embedding")
  public String textEmbedding() {
    return "ch08_embedding_vector_store/text-embedding";
  }  
  
  @GetMapping("/ch08_embedding_vector_store/add-document")
  public String addDocument() {
    return "ch08_embedding_vector_store/add-document";
  }   
  
  @GetMapping("/ch08_embedding_vector_store/search-document-1")
  public String searchDocument1() {
    return "ch08_embedding_vector_store/search-document-1";
  }   
  
  @GetMapping("/ch08_embedding_vector_store/search-document-2")
  public String searchDocument2() {
    return "ch08_embedding_vector_store/search-document-2";
  }  
  
  @GetMapping("/ch08_embedding_vector_store/delete-document")
  public String deleteDocument() {
    return "ch08_embedding_vector_store/delete-document";
  } 
  
  @GetMapping("/ch08_embedding_vector_store/image-embedding")
  public String faceRecognition() {
    return "ch08_embedding_vector_store/image-embedding";
  }

  @GetMapping("/ch09_vector_store_chat_memory/chat")
  public String vectorStoreChatMemory() {
    return "ch09_vector_store_chat_memory/vector-store-chat-memory";
  }
}
