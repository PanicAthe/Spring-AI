
# 🧠 Spring AI with GenAI Lab

이 레포지토리는 **Spring AI와 Google GenAI 기반 프로젝트를 위해 직접 구성한 Lab 환경**입니다.

학습 과정에서 서적 **[이것이 Spring AI다](https://product.kyobobook.co.kr/detail/S000217369517)** 를 참고하였으며, 교재의 흐름을 기반으로 하되 **GenAI 환경에 맞게 구조를 재해석하고 확장**하는 데에 초점을 두었습니다.

---

## 📘 프로젝트 개요

* Spring AI 1.1 이후의 구조와 기능을 **실전적으로 익히기 위한 실습 공간**
* 향후 개발할 **문서 기반 질의응답, RAG, Tool Calling 프로젝트**의 기반 코드 마련

---

## 🏗️ 주요 구성 및 설계 방향

* 이 Lab에서는 모든 기능이 **실제 서비스 개발에 가까운 단일 프로젝트 구조로 구현**
* Google GenAI API 흐름에 맞춰 컨트롤러·서비스 구조를 재설계
* 단순 기능 구현(ch01, 02)과 현재 GenAI 모델에서 지원되지 않는 이미지·오디오 기능(ch05, 06)은 제외 됨

* **OpenAI 구성도 참고용으로 보존**

  * 모델 교체 실습 및 Spring AI의 유연성 확인 목적
  * `openai_` 접두사 파일을 통해 비교 가능

---

## ▶️ 실행 방법

```bash
cd demo
./gradlew build
./gradlew bootRun
```
---

## 📌 참고 자료

- **이것이 Spring AI다** — 교보: https://product.kyobobook.co.kr/detail/S000217369517
- **이것이 Spring AI다(예제 코드 확인 가능)** — 한빛: https://www.hanbit.co.kr/store/books/look.php?p_code=B1665872775
