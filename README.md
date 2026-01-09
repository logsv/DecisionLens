# DecisionLens

**DecisionLens** is an intelligent Engineering Decision Management System designed to capture, review, and evaluate technical decisions (ADRs, RFCs). It combines traditional rule-based policy enforcement with AI-driven critiques to help engineering teams avoid costly mistakes and blind spots.

## 🏗 Architecture

DecisionLens is built as a **Maven Multi-Module** Spring Boot project, enforcing a clean separation of concerns:

| Module | Description | Tech Stack |
|--------|-------------|------------|
| **[decision-service](decision-service)** | Core System of Record. Manages decision lifecycle, versioning, and storage. | Spring Web, Data JPA, PostgreSQL, OpenAPI |
| **[review-engine](review-engine)** | Deterministic rule engine. Validates decisions against defined engineering standards (e.g., "Must have rollback plan"). | Jackson YAML, Custom DSL |
| **[policy-engine](policy-engine)** | Governance layer. Handles RBAC, approval workflows, and state transitions (DRAFT → APPROVED). | Spring Security (Concepts), Domain Logic |
| **[ai-review-service](ai-review-service)** | AI integration layer. Generates semantic critiques, identifies trade-offs, and scores confidence using LLMs. | LangChain4j (planned), Vendor-Agnostic Interfaces |
| **[evaluation-harness](evaluation-harness)** | Quality assurance framework. Compares AI outputs against "Golden Decisions" (ground truth) to measure Precision/Recall. | Statistical Logic, No-ML Verification |
| **[common](common)** | Shared domain models, utilities, and exceptions. | POJOs, Lombok |
| **audit-service** | *Planned*. Immutable log of all changes and approvals. | - |

## 🚀 Key Features

*   **Versioned Decisions**: Treat decisions as code with full version history.
*   **Hybrid Review Pipeline**:
    *   **Deterministic**: "Does this have an SLA?" (Rule Engine)
    *   **Probabilistic**: "Are we missing a scalability risk?" (AI Service)
*   **Policy Workflows**: Strict state transitions (Draft -> Review -> Approved/Rejected) enforced by policy.
*   **Vendor-Agnostic AI**: Plug-and-play LLM providers with prompt versioning.
*   **Quality Evaluation**: Built-in harness to benchmark AI critique quality against historical post-mortems.

## 🛠 Getting Started

### Prerequisites

*   **Java 21+**
*   **Maven 3.9+**
*   **Docker** (Required for PostgreSQL)

### 1. Start Database

Start a PostgreSQL instance using Docker:

```bash
docker run --name decision-postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=decision_db \
  -p 5432:5432 \
  -d postgres:15
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run Decision Service

The service runs on port `8081`.

```bash
cd decision-service
mvn spring-boot:run
```

## 📡 API Usage

You can interact with the API using `curl` or the Swagger UI (when enabled).

### Create a Decision

```bash
curl -X POST http://localhost:8081/decisions \
-H "Content-Type: application/json" \
-d '{
  "title": "Database Migration",
  "content": "Switching from H2 to PostgreSQL for production readiness",
  "author": "System Admin",
  "version": "1.0.0"
}'
```

### Get a Decision

```bash
curl -X GET http://localhost:8081/decisions/{decision_id}
```

### Update Decision Status

```bash
curl -X PATCH http://localhost:8081/decisions/{decision_id} \
-H "Content-Type: application/json" \
-d '{
  "status": "ACCEPTED"
}'
```

## 📝 Configuration

### Rule Engine
Rules are defined in `review-engine/src/main/resources/rules.yaml`. Example:
```yaml
rules:
  - id: "must-have-rollback"
    name: "Missing Rollback Plan"
    condition: "content contains 'rollback'"
    severity: "HIGH"
```

## 🤝 Contributing

1.  Fork the repository.
2.  Create a feature branch (`git checkout -b feature/amazing-feature`).
3.  Commit your changes (`git commit -m 'Add some amazing feature'`).
4.  Push to the branch (`git push origin feature/amazing-feature`).
5.  Open a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
