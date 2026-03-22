# 🛠 Config Server (wooricard-config)

Woori Card 마이크로서비스들의 설정 정보를 중앙에서 관리하고 배포하는 서버입니다. 이 서버는 **Eureka Client**로 동작하며, 설정 정보를 외부 **Git Repository**에서 로드하여 각 서비스에 전달합니다.

## 🏗 System Architecture


* **Port**: 8888
* **Service Name**: `wooricard-config`
* **Eureka Registration**: 필수 (Eureka Client)
* **Config Source**: External Git Repository (`wooricard-config-repo`)

---

## 🚦 실행 순서 (Execution Order)

본 시스템은 서비스 디스커버리 기반으로 동작하므로 **반드시 다음 순서대로 실행**해야 합니다.

1. **Eureka Server (`port: 8761`)**: 서비스 레지스트리 기동
2. **Config Server (`port: 8888`)**: (본 서비스) Eureka에 등록 후 설정 제공 준비
3. **Other Microservices**: Gateway, Approval, Settlement, Billing 순차 실행

---

## ⚙️ 주요 설정 (Configuration)

본 서버는 별도의 `.env` 파일 대신 `src/main/resources/application.yml` (또는 `bootstrap.yml`) 내에 설정 저장소 정보를 관리합니다.

### 1. Eureka 연동
```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/ # Eureka 서버 주소
```

### 2. Config 저장소 (Git Repo) 연결
다이어그램에 명시된 `config-repo`의 파일들을 참조합니다.
* **대상 파일**: `application.yml`, `wooricard-approval-service.yml` 등 5종

---

## 📁 서빙 중인 설정 파일 목록
`config-git-repo` 내에서 관리되는 설정 파일들입니다:

| 파일명 | 대상 서비스 |
| :--- | :--- |
| `application.yml` | 전 서비스 공통 설정 |
| `wooricard-gateway.yml` | API Gateway (Port: 8080) |
| `wooricard-approval-service.yml` | 승인/결제 서비스 (Port: 8081/8084) |
| `wooricard-settlement-service.yml` | 정산 서비스 (Port: 8082) |
| `wooricard-billing-service.yml` | 매입 청구 서비스 (Port: 8083) |

---

## 🔗 Endpoint 확인
Config Server가 정상적으로 설정을 읽어오는지 확인하려면 브라우저에서 아래 주소로 접속하세요.
* `{SERVER_URL}/wooricard-approval-service/default`


