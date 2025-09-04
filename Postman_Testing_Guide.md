# LEARNnow - Complete API Testing Guide with Postman JSONs
## Base URL: http://localhost:8080

---

## 🔐 AUTHENTICATION & USER MANAGEMENT

### 1. Register New User
**POST** `/api/auth/register`
```json
{
  "name": "rahul_coder",
  "email": "rahul@example.com",
  "password": "password123",
  "preferredLanguage": "hi"
}
```

### 2. Login User (Get JWT Token)
**POST** `/api/auth/login`
```json
{
  "email": "rahul@example.com",
  "password": "password123"
}
```
**Expected Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyYWh1bEBleGFtcGxlLmNvbSIsImlhdCI6MTY5ODM0MjAwMCwiZXhwIjoxNjk4NDI4NDAwfQ.xyz123",
  "id": 1,
  "email": "rahul@example.com",
  "name": "rahul_coder"
}
```

### 3. Get Current User Profile
**GET** `/api/auth/me`
**Headers:** `Authorization: Bearer YOUR_JWT_TOKEN`

### 4. Create Public User (Alternative Registration)
**POST** `/api/public/users`
```json
{
  "name": "priya_student",
  "email": "priya@student.com",
  "password": "student123",
  "preferredLanguage": "en",
  "role": "USER"
}
```

---

## 🎯 TOPIC MANAGEMENT

### 5. Create Learning Topic
**POST** `/api/topics`
**Headers:** `Authorization: Bearer YOUR_JWT_TOKEN`
```json
{
  "name": "Java Spring Boot Complete Course",
  "description": "Complete Spring Boot tutorial for beginners to advanced",
  "purpose": "Professional Development",
  "language": "Hindi",
  "level": "Intermediate"
}
```

### 6. Get All Topics
**GET** `/api/topics`

### 7. Update Topic
**PUT** `/api/topics/1`
```json
{
  "name": "Java Spring Boot Master Class - Updated",
  "description": "Advanced Spring Boot concepts with microservices",
  "purpose": "Career Advancement",
  "language": "English",
  "level": "Advanced"
}
```

---

## 🎥 VIDEO MANAGEMENT

### 8. Create Learning Video
**POST** `/api/videos`
```json
{
  "youtubeId": "xyz123abc",
  "title": "Spring Boot REST API Tutorial",
  "channel": "CodeWithHarry",
  "duration": 1800,
  "language": "Hindi",
  "position": 1,
  "chaptersJson": "{\"chapters\": [{\"title\": \"Introduction\", \"time\": 0}, {\"title\": \"REST Controller\", \"time\": 300}, {\"title\": \"Database Integration\", \"time\": 900}, {\"title\": \"Testing APIs\", \"time\": 1400}]}",
  "topic": {
    "id": 1
  }
}
```

### 9. Update Video
**PUT** `/api/videos/1`
```json
{
  "youtubeId": "updated456",
  "title": "Spring Boot Advanced REST API",
  "channel": "Apna College",
  "duration": 2400,
  "language": "Hindi",
  "position": 1,
  "chaptersJson": "{\"chapters\": [{\"title\": \"Advanced Concepts\", \"time\": 0}, {\"title\": \"Security\", \"time\": 600}]}",
  "topic": {
    "id": 1
  }
}
```

---

## 🧠 QUIZ MANAGEMENT

### 10. Create Quiz
**POST** `/api/quizzes`
```json
{
  "subTopic": "Spring Boot Basics",
  "difficulty": "MEDIUM",
  "language": "Hindi",
  "purpose": "Practice",
  "active": true,
  "topic": {
    "id": 1
  },
  "questionsJson": "{\"questions\": [{\"question\": \"Spring Boot mein @RestController ka kya use hai?\", \"options\": [\"REST APIs banane ke liye\", \"Database connection ke liye\", \"Security ke liye\", \"Testing ke liye\"], \"correct\": 0}, {\"question\": \"@Autowired annotation kya karta hai?\", \"options\": [\"Manual object creation\", \"Dependency Injection\", \"Database mapping\", \"Error handling\"], \"correct\": 1}, {\"question\": \"Spring Boot application run karne ke liye kya command use karte hai?\", \"options\": [\"java -jar app.jar\", \"mvn spring-boot:run\", \"Both A and B\", \"npm start\"], \"correct\": 2}]}"
}
```

### 11. Submit Quiz Answers
**POST** `/api/quizzes/1/submit`
```json
{
  "question1": "0",
  "question2": "1", 
  "question3": "2"
}
```
**Expected Response:**
```json
{
  "score": 3,
  "totalQuestions": 3,
  "percentage": 100.0,
  "correct": 3,
  "incorrect": 0,
  "results": [
    {"question": 1, "userAnswer": "0", "correctAnswer": "0", "isCorrect": true},
    {"question": 2, "userAnswer": "1", "correctAnswer": "1", "isCorrect": true},
    {"question": 3, "userAnswer": "2", "correctAnswer": "2", "isCorrect": true}
  ],
  "message": "Excellent! Perfect score! 🎉"
}
```

### 12. Generate Auto Quiz
**POST** `/api/quizzes/generate?topicId=1&difficulty=EASY&language=Hindi`

---

## 📈 USER PROGRESS TRACKING

### 13. Create User Progress
**POST** `/api/users/1/progress`
```json
{
  "topic": {
    "id": 1
  },
  "status": "NOT_STARTED",
  "secondsWatched": 0
}
```

### 14. Update Video Watch Progress
**PATCH** `/api/users/1/progress/1`
```json
{
  "status": "IN_PROGRESS",
  "secondsWatched": 450
}
```

### 15. Mark Topic as Completed
**PUT** `/api/users/1/progress/by-topic/1/status`
```json
{
  "value": "COMPLETED"
}
```

### 16. Update Watch Time
**PUT** `/api/users/1/progress/by-topic/1/watch-seconds`
```json
{
  "value": 1800
}
```

---

## 🏆 SCORECARD & ANALYTICS

### 17. Create User Scorecard
**POST** `/api/scorecards`
```json
{
  "user": {
    "id": 1
  },
  "accuracy": 85.5,
  "consistency": 78.2,
  "discipline": 92.0,
  "dedication": 88.5,
  "streakDays": 15
}
```

### 18. Generate/Update Scorecard Automatically
**POST** `/api/users/1/scorecard/generate`

### 19. Get User Performance Summary
**GET** `/api/users/1/performance-summary`

---

## 🛤️ LEARNING PATH MANAGEMENT

### 20. Create Learning Path
**POST** `/api/learning-paths/user/1`
```json
{
  "name": "Complete Full Stack Developer Path",
  "description": "Frontend se backend tak complete web development sikho",
  "purpose": "CAREER_CHANGE",
  "language": "Hindi",
  "level": "INTERMEDIATE",
  "estimatedHours": 200,
  "isPublic": true
}
```

### 21. Add Topic to Learning Path
**POST** `/api/learning-paths/1/topics/1`

### 22. Update Learning Path
**PUT** `/api/learning-paths/1`
```json
{
  "name": "Complete Full Stack Developer Path - Updated",
  "description": "React, Node.js, MongoDB ke saath complete stack",
  "purpose": "JOB_INTERVIEW",
  "language": "Hindi",
  "level": "ADVANCED",
  "estimatedHours": 250,
  "isPublic": false
}
```

---

## 🎬 YOUTUBE API INTEGRATION

### 23. Search Educational Videos
**GET** `/api/youtube/search?query=java spring boot tutorial&max=5`
**Expected Response:**
```json
[
  {
    "videoId": "abc123",
    "title": "Spring Boot Tutorial for Beginners - Complete Course",
    "description": "Learn Spring Boot from scratch with this comprehensive tutorial...",
    "channelTitle": "CodeWithHarry",
    "thumbnailUrl": "https://i.ytimg.com/vi/abc123/mqdefault.jpg",
    "videoUrl": "https://www.youtube.com/watch?v=abc123",
    "publishedAt": "2024-01-15T10:30:00Z",
    "duration": 3600,
    "viewCount": 125000,
    "category": "Education"
  }
]
```

### 24. Get Popular Programming Videos
**GET** `/api/youtube/popular?max=8`

### 25. Get Topic-Specific Videos
**GET** `/api/youtube/topic/react?max=6`

### 26. YouTube API Health Check
**GET** `/api/youtube/health`

---

## 🧪 TESTING WORKFLOW

### Phase 1: Setup & Authentication
1. **Register User** → Get user account
2. **Login** → Get JWT token
3. **Save JWT token** for subsequent requests

### Phase 2: Create Learning Content
1. **Create Topics** → Programming subjects
2. **Add Videos** → Learning materials
3. **Create Quizzes** → Assessment content

### Phase 3: Learning Journey
1. **Start Learning Path** → Structured learning
2. **Track Video Progress** → Watch time tracking
3. **Take Quizzes** → Knowledge assessment
4. **Update Progress** → Learning status

### Phase 4: YouTube Integration
1. **Search Videos** → Find learning content
2. **Get Popular Content** → Trending tutorials
3. **Topic-Specific Search** → Curated learning

### Phase 5: Analytics & Performance
1. **Generate Scorecard** → Performance metrics
2. **Track Learning Progress** → Completion rates
3. **Performance Summary** → Growth insights

---

## 🚀 DEMO SCENARIOS FOR INTERVIEWS

### Scenario 1: New Student Journey
```bash
# Register → Create learning path → Add topics → Start learning → Track progress
POST /api/auth/register (student details)
POST /api/learning-paths/user/1 (full stack path)
POST /api/learning-paths/1/topics/1 (add java topic)
POST /api/users/1/progress (start learning)
PATCH /api/users/1/progress/1 (update progress)
```

### Scenario 2: Quiz Assessment
```bash
# Create quiz → Take quiz → Get score → Update scorecard
POST /api/quizzes (java quiz)
POST /api/quizzes/1/submit (submit answers)
POST /api/users/1/scorecard/generate (update metrics)
```

### Scenario 3: YouTube Integration Demo
```bash
# Search educational content → Show real YouTube videos
GET /api/youtube/search?query=spring boot
GET /api/youtube/topic/java
GET /api/youtube/popular
```

---

## 🔧 IMPORTANT NOTES

- **JWT Token**: Copy from login response and add to Authorization header
- **User IDs**: Use actual IDs from database (1, 2, 3, etc.)
- **Topic IDs**: Reference existing topics when creating videos/quizzes
- **Status Values**: `NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`
- **Difficulty Values**: `EASY`, `MEDIUM`, `HARD`
- **YouTube API**: Works without authentication for demo

## 🎯 SUCCESS INDICATORS

✅ **Authentication Working**: JWT tokens generated
✅ **CRUD Operations**: Create, read, update, delete all entities
✅ **Relationships**: Topics ↔ Videos ↔ Quizzes ↔ Progress
✅ **YouTube Integration**: Real videos fetched from YouTube
✅ **Progress Tracking**: Learning journey recorded
✅ **Quiz System**: Assessment and scoring functional
✅ **Analytics**: Performance metrics generated

Your LEARNnow backend is now **interview-ready** and **demo-friendly**! 🚀
