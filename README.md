<p align="center">
  <img src="docs/banner.svg" alt="LEARNnow banner" width="100%"/>
</p>

<h1 align="center">LEARNnow — Smart Learning Web App</h1>

<p align="center">
  <b>Transform scattered YouTube learning into structured, goal‑oriented journeys.</b>
</p>

<p align="center">
  <a href="https://www.oracle.com/java/technologies/downloads/"><img alt="Java" src="https://img.shields.io/badge/Java-21-0b1220?logo=openjdk&logoColor=white"></a>
  <a href="https://spring.io/projects/spring-boot"><img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.5-green?logo=springboot&logoColor=white"></a>
  <a href="https://www.mysql.com/"><img alt="MySQL" src="https://img.shields.io/badge/MySQL-8-005e8a?logo=mysql&logoColor=white"></a>
  <img alt="Status" src="https://img.shields.io/badge/Status-WIP-orange">
  <img alt="PRs welcome" src="https://img.shields.io/badge/PRs-welcome-blue">
</p>

---

## TL;DR
LEARNnow turns random tutorials into a personalized path with curated YouTube videos, adaptive quizzes, progress scorecards, and light gamification.

## Demo (Dev)
- Start backend (MySQL running; dev profile uses `application-dev.yml`):
  - Windows PowerShell:
    - `./mvnw -Dspring-boot.run.profiles=dev spring-boot:run`
- Open Swagger UI (auto docs): http://localhost:8080/swagger-ui/index.html
- Try these quickly:
  - POST /api/users (create user)
  - GET  /api/topics
  - GET  /api/topics/{id}/videos
  - PATCH /api/topics/{id}/videos/reorder
  - GET  /api/users/{userId}/progress
  - PUT  /api/users/{userId}/progress/by-topic/{topicId}/status?value=IN_PROGRESS

Note: Dev security is relaxed for speed (API + Swagger are open). Passwords are hashed with BCrypt.

## Why
- Turn “random tutorials” into a goal‑oriented path.
- Practice real-world stack: Spring Boot + MySQL + React (to be added).
- Build an interview‑ready, portfolio‑quality project.

## Vision
- Personalized learning paths by topic, purpose (Job Interview / Upskill / Exams), and language.
- Curated YouTube videos in sequence; chapter awareness; alternatives for coverage.
- Adaptive quizzes with explanations and progress tracking.
- Scorecard measuring accuracy, consistency, discipline, dedication; shareable.
- AI mentor mode for summaries, doubts, and next‑step recommendations.
- Gamification: XP, streaks, badges, leaderboards; career goals and peer discussions.

## Tech Stack
- Backend: Java 21, Spring Boot 3, Spring Data JPA
- Database: MySQL (dev/prod)
- Dev/Tooling: Maven Wrapper, Lombok, Swagger UI (springdoc)
- Planned: Flyway (migrations), OpenAPI polish, React + TypeScript frontend

---

## Contents
- Features (coming up)
- Architecture (coming up)
- Quickstart (updated)
- API Preview (updated)
- Project Structure
- Roadmap
- Track Logs

## Project Structure
```
src/main/java/me/learn/now
  ├─ config/           # SecurityConfig, etc.
  ├─ controller/       # REST controllers (Users, Topics, Videos, Quizzes, Progress, ScoreCard)
  ├─ dto/              # (reserved for later)
  ├─ exception/        # ApiError + GlobalExceptionHandler (clean 400/404 messages)
  ├─ integration/      # youtube/ → YoutubeClient (stub; plug real API later)
  ├─ model/            # JPA entities + enums
  ├─ repository/       # Spring Data repositories
  ├─ service/          # Business services
  └─ LearNnowApplication.java
```

## Quickstart (Backend)
1) Prereqs
   - JDK 21+, Maven, MySQL running (dev DB configured in `application-dev.yml`)
2) Run in dev profile
```bash
./mvnw -Dspring-boot.run.profiles=dev spring-boot:run
```
3) Docs + Try it
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- JSON in/out; dev security is open for speed.

## API Preview (MVP slice)
- Users
  - POST /api/users → create user (password stored as BCrypt hash)
  - GET  /api/users → list users
  - GET  /api/users/{id} → get user (404 if not found)
  - PUT  /api/users/{id} → update name/email
  - PUT  /api/users/pass/{id} → update password (disallows old password)
- Topics
  - CRUD on /api/topics
  - GET  /api/topics/{id}/videos (ordered by vPosition)
  - GET  /api/topics/{id}/quizzes
  - PATCH /api/topics/{id}/videos/reorder (body: [videoId...])
- Videos
  - CRUD on /api/videos
- Quizzes
  - CRUD on /api/quizzes
  - PATCH /api/quizzes/{id}/active?active=true|false
- Progress
  - Base: /api/users/{userId}/progress (list/create/get/patch/delete)
  - Helpers: by-topic get + set status/watch seconds
- Scorecard
  - GET /api/users/{userId}/scorecard, plus basic CRUD under /api/scorecards

## YouTube Plan
- `integration/youtube/YoutubeClient` is a stub right now (Hinglish comments inside).
- When API key ready: implement search/list/details, normalize durations, language, and chapters.

## Roadmap
- [x] Day 1: Entities + mappings + repos
- [x] Day 2-3: Users API + Topic/Videos + basic Quizzes/Progress/ScoreCard
- [x] Swagger UI + Global error handler (ApiError)
- [ ] Flyway V1 (Topic/Video) + seed data
- [ ] DTOs + validation + better errors per field
- [ ] Frontend (React + TS) pages + simple path builder
- [ ] Caching + rate-limit for YouTube integration

## Track Logs
- See `Track/` folder for day-wise Hinglish recaps
