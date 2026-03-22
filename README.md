# 🛠 Woori Card Config Server

우리카드 MSA(Microservice Architecture) 프로젝트의 **중앙 설정 관리(Centralized Configuration)** 를 담당하는 서버입니다. 



모든 마이크로서비스(Gateway, 승인, 정산 등)의 설정 정보(`YAML`)를 외부 Git 저장소에서 로드하여 배포하며, **Eureka Client**로 동작하여 다른 서비스들이 설정 서버의 위치를 동적으로 찾을 수 있게 합니다.

---

## 🏗 주요 역할
* **Centralized Configuration**: 여러 서비스의 설정 파일을 한 곳에서 통합 관리
* **Environment Isolation**: 개발(dev), 운영(prod) 등 환경별 설정 분리 및 제공
* **Dynamic Refresh**: 서버 재시작 없이 설정 변경 사항을 각 서비스에 전파 (Actuator 연동 시)

---

## 🚀 실행 방법 (Getting Started)

### 1. 사전 요구 사항
* **Java 17** 이상
* **Gradle**
* **Git Repository 권한**: 설정 파일이 담긴 [config-repo](https://github.com/woori-card-msa/wooricard-config-repo?tab=readme-ov-file#-woori-card-config-repository) 접근 권한 필요

### 2. 서버 기동
이 서버는 Eureka 서버가 실행된 이후 **두 번째로 실행**되어야 합니다.

```bash
./gradlew bootRun
```

* **Default Port**: `8888`
* **Service Name**: `wooricard-config`

---

## 🚦 인프라 실행 순서 (Dependency)

MSA 구조의 안정적인 구동을 위해 아래 순서를 반드시 지켜주세요.

1. **[Eureka Server](https://github.com/woori-card-msa/wooricard-eureka?tab=readme-ov-file#-%EC%8B%A4%ED%96%89-%EB%B0%A9%EB%B2%95-getting-started)**: 서비스 레지스트리 선행 기동
2. **Config Server** (본 서비스) ◀ **NOW**
3. **Microservices**: Gateway 및 도메인 서비스(Approval, Settlement 등) 실행

---

## 📁 관리 중인 설정 파일 목록
본 서버는 아래의 서비스별 설정 파일을 Git 저장소에서 읽어와 제공합니다.

| 서비스 이름 | 포트 | 참조 파일명 |
| :--- | :--- | :--- |
| **공통 설정** | - | `application.yml` |
| **API Gateway** | 8080 | `wooricard-gateway.yml` |
| **승인/결제 서비스** | 8081 | `wooricard-approval-service.yml` |
| **정산 서비스** | 8082 | `wooricard-settlement-service.yml` |
| **매입 청구 서비스** | 8083 | `wooricard-billing-service.yml` |


