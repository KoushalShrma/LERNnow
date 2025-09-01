# LEARNnow — Smart Learning Web App

Transform scattered YouTube learning into a structured, personalized, and engaging journey.

## Why
- Turn “random tutorials” into a goal‑oriented path.
- Practice real-world stack: Spring Boot + MySQL + React.
- Build an interview‑ready, portfolio‑quality project.

## Vision
- Personalized learning paths by topic, purpose (Job Interview / Upskill / Exams), and language.
- Curated YouTube videos in the right sequence; chapters and alternatives.
- Adaptive quizzes with explanations and progress tracking.
- Scorecard measuring accuracy, consistency, discipline, dedication; shareable.
- AI mentor mode for summaries, doubts, and next‑step recommendations.
- Gamification: XP, streaks, badges, leaderboards; career goals and peer discussions.

## Tech Stack
- Backend: Java 21+, Spring Boot 3, Spring Data JPA, (Flyway soon), OpenAPI (soon)
- DB: MySQL (prod), H2 (dev/test planned)
- Frontend: React + TypeScript (planned)
- Infra: Docker (DB), GitHub Actions CI (planned)

## Current Status (MVP foundation)
- Domain models defined with clean JPA mappings:
  - Topic, Video, Quiz, User, UserProgress, ScoreCard
  - Enums: Difficulty (EASY/MEDIUM/HARD), ProgressStatus (NOT_STARTED/IN_PROGRESS/COMPLETED)
  - Repositories: JpaRepository for all entities
- Inline comments explain relationships and design choices.
- Next: DTOs + validation, global error handling, Flyway migrations, Swagger, Topic endpoints.

## API (planned v1)
- Topics: list/create with filters
- Learning Path: create + fetch
- Videos: list per topic
- Quizzes: adaptive attempt flow
- Progress: update & fetch
- Scorecard: fetch + shareable

## Local Development
1) Prereqs: JDK 21+, Maven, Docker (optional for MySQL)
2) Build backend
```bash
./mvnw -q -DskipTests clean package
./mvnw spring-boot:run
```
3) Swagger (soon): http://localhost:8080/swagger-ui

## Project Structure
```
src/main/java/me/learn/now
  ├─ controller/        # REST controllers (scaffolded)
  ├─ dto/               # Request/response DTOs (scaffolded)
  ├─ model/             # JPA entities + enums
  ├─ repository/        # Spring Data repositories
  ├─ service/           # Business services (scaffolded)
  └─ LearNnowApplication.java
Track/
  └─ DAY-1              # Daily progress log (Hinglish)
```

## Roadmap
- Day 1: Entities + mappings + repos (DONE)
- Day 2: H2/Flyway + Topic CRUD + Swagger
- Day 3: Learning Path MVP + seed data
- Day 4: Adaptive quiz v1 + scoring
- Day 5: Scorecard + shareable summary
- Day 6+: Gamification + AI mentor mode + frontend flows

## Contributing
- Small, focused PRs welcome. Keep endpoints documented and add tests.
- Naming: prefer clear, consistent names; enums persisted as string.

## Notes
- This repo is under active development; APIs may change.
- Aim: clean, testable, deployment-ready code you’re proud to show.

