# COMPLETE LEARNnow API Testing Guide
## Base URL: http://localhost:8080

## AUTHENTICATION FLOW
### 1. Register User
**POST** `/api/auth/register`
```json
{
  "name": "newuser123",
  "email": "newuser@example.com",
  "password": "password123",
  "preferredLanguage": "en"
}
```

### 2. Login User
**POST** `/api/auth/login`
```json
{
  "email": "newuser@example.com",
  "password": "password123"
}
```
**Response Example:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "id": 1,
  "email": "newuser@example.com",
  "name": "newuser123"
}
```

### 3. Get Current User (Protected)
**GET** `/api/auth/me`
**Headers:** `Authorization: Bearer YOUR_JWT_TOKEN`

---

## TOPIC MANAGEMENT
### 1. Create Topic
**POST** `/api/topics`
**Headers:** `Authorization: Bearer YOUR_JWT_TOKEN`
```json
{
  "name": "Advanced JavaScript",
  "description": "Deep dive into JavaScript concepts",
  "purpose": "Professional development",
  "language": "English",
  "level": "Advanced"
}
```

### 2. Get All Topics
**GET** `/api/topics`

### 3. Get Topic by ID
**GET** `/api/topics/1`

### 4. Update Topic
**PUT** `/api/topics/1`
```json
{
  "name": "Advanced JavaScript - Updated",
  "description": "Updated JavaScript concepts with ES6+",
  "purpose": "Professional development",
  "language": "English",
  "level": "Expert"
}
```

### 5. Delete Topic
**DELETE** `/api/topics/1`

### 6. Get Videos for Topic
**GET** `/api/topics/1/videos`

### 7. Get Quizzes for Topic
**GET** `/api/topics/1/quizzes`

### 8. Reorder Topic Videos
**PATCH** `/api/topics/1/videos/reorder`
```json
[3, 1, 2, 4]
```

---

## VIDEO MANAGEMENT
### 1. Create Video
**POST** `/api/videos`
```json
{
  "youtubeId": "abc123xyz",
  "title": "JavaScript Closures Explained",
  "channel": "JS Mastery",
  "duration": 1500,
  "language": "English",
  "position": 1,
  "chaptersJson": "{\"chapters\": [{\"title\": \"Introduction\", \"time\": 0}, {\"title\": \"Closures Basics\", \"time\": 300}, {\"title\": \"Advanced Examples\", \"time\": 900}]}",
  "topic": {
    "id": 1
  },
  "quiz": {
    "id": 2
  }
}
```

### 2. Get All Videos
**GET** `/api/videos`

### 3. Get Video by ID
**GET** `/api/videos/1`

### 4. Update Video
**PUT** `/api/videos/1`
```json
{
  "youtubeId": "updated123",
  "title": "Updated Video Title",
  "channel": "Updated Channel",
  "duration": 1800,
  "language": "English",
  "position": 2,
  "chaptersJson": "{\"chapters\": [{\"title\": \"New Chapter\", \"time\": 0}]}",
  "topic": {
    "id": 1
  }
}
```

### 5. Delete Video
**DELETE** `/api/videos/1`

---

## QUIZ MANAGEMENT
### 1. Create Quiz
**POST** `/api/quizzes`
```json
{
  "subTopic": "JavaScript Arrays",
  "difficulty": "MEDIUM",
  "language": "English",
  "purpose": "Practice",
  "active": true,
  "topic": {
    "id": 1
  },
  "questionsJson": "{\"questions\": [{\"question\": \"Which method adds an element to the end of an array?\", \"options\": [\"push()\", \"pop()\", \"shift()\", \"unshift()\"], \"correct\": 0}, {\"question\": \"What does array.length return?\", \"options\": [\"Last element\", \"Number of elements\", \"First element\", \"Array type\"], \"correct\": 1}]}"
}
```

### 2. Get All Quizzes
**GET** `/api/quizzes`

### 3. Get Quiz by ID
**GET** `/api/quizzes/1`

### 4. Update Quiz
**PUT** `/api/quizzes/1`
```json
{
  "subTopic": "JavaScript Objects - Updated",
  "difficulty": "HARD",
  "language": "English",
  "purpose": "Assessment",
  "active": true,
  "topic": {
    "id": 1
  },
  "questionsJson": "{\"questions\": [{\"question\": \"How do you create an object in JavaScript?\", \"options\": [\"var obj = {}\", \"var obj = []\", \"var obj = ()\"], \"correct\": 0}]}"
}
```

### 5. Delete Quiz
**DELETE** `/api/quizzes/1`

### 6. Toggle Quiz Active Status
**PATCH** `/api/quizzes/1/active?active=false`

### 7. Get Quizzes by Topic
**GET** `/api/quizzes/topic/1`

### 8. Get Quizzes by Difficulty
**GET** `/api/quizzes/difficulty/MEDIUM`

### 9. Generate Quiz for Topic
**POST** `/api/quizzes/generate?topicId=1&difficulty=EASY&language=English`

### 10. Submit Quiz Answers
**POST** `/api/quizzes/1/submit`
```json
{
  "question1": "0",
  "question2": "1",
  "question3": "2"
}
```

---

## USER PROGRESS MANAGEMENT
### 1. Get User's All Progress
**GET** `/api/users/1/progress`

### 2. Create User Progress
**POST** `/api/users/1/progress`
```json
{
  "topic": {
    "id": 2
  },
  "status": "NOT_STARTED",
  "secondsWatched": 0
}
```

### 3. Get Specific Progress
**GET** `/api/users/1/progress/1`

### 4. Update Progress
**PATCH** `/api/users/1/progress/1`
```json
{
  "status": "IN_PROGRESS",
  "secondsWatched": 450
}
```

### 5. Delete Progress
**DELETE** `/api/users/1/progress/1`

### 6. Get Progress by Topic
**GET** `/api/users/1/progress/by-topic/1`

### 7. Update Status by Topic
**PUT** `/api/users/1/progress/by-topic/1/status`
```json
{
  "value": "COMPLETED"
}
```

### 8. Update Watch Seconds by Topic
**PUT** `/api/users/1/progress/by-topic/1/watch-seconds`
```json
{
  "value": 900
}
```

---

## SCORECARD MANAGEMENT
### 1. Create Scorecard
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

### 2. Get All Scorecards
**GET** `/api/scorecards`

### 3. Get Scorecard by ID
**GET** `/api/scorecards/1`

### 4. Get User's Scorecard
**GET** `/api/users/1/scorecard`

### 5. Update Scorecard
**PUT** `/api/scorecards/1`
```json
{
  "accuracy": 90.0,
  "consistency": 85.0,
  "discipline": 95.0,
  "dedication": 92.0,
  "streakDays": 20
}
```

### 6. Delete Scorecard
**DELETE** `/api/scorecards/1`

### 7. Generate/Refresh User Scorecard
**POST** `/api/users/1/scorecard/generate`

### 8. Get Performance Summary
**GET** `/api/users/1/performance-summary`

---

## LEARNING PATH MANAGEMENT
### 1. Create Learning Path
**POST** `/api/learning-paths/user/1`
```json
{
  "name": "Complete Web Developer",
  "description": "Full-stack web development path",
  "purpose": "UPSKILL",
  "language": "English",
  "level": "INTERMEDIATE",
  "estimatedHours": 150,
  "isPublic": true
}
```

### 2. Get User's Learning Paths
**GET** `/api/learning-paths/user/1`

### 3. Get Learning Path by ID
**GET** `/api/learning-paths/1`

### 4. Get Public Learning Paths
**GET** `/api/learning-paths/public`

### 5. Get Popular Learning Paths
**GET** `/api/learning-paths/popular`

### 6. Update Learning Path
**PUT** `/api/learning-paths/1`
```json
{
  "name": "Complete Web Developer - Updated",
  "description": "Updated full-stack development path",
  "purpose": "JOB_INTERVIEW",
  "language": "English",
  "level": "ADVANCED",
  "estimatedHours": 180,
  "isPublic": false
}
```

### 7. Delete Learning Path
**DELETE** `/api/learning-paths/1`

### 8. Add Topic to Learning Path
**POST** `/api/learning-paths/1/topics/2`

### 9. Remove Topic from Learning Path
**DELETE** `/api/learning-paths/1/topics/2`

### 10. Filter by Purpose
**GET** `/api/learning-paths/filter/purpose/UPSKILL`

### 11. Filter by Language
**GET** `/api/learning-paths/filter/language/English`

---

## USER MANAGEMENT
### 1. Get All Users (Admin)
**GET** `/api/users`

### 2. Get User by ID
**GET** `/api/users/1`

### 3. Update User
**PUT** `/api/users/1`
```json
{
  "name": "updated_username",
  "email": "updated@example.com",
  "preferredLanguage": "hi"
}
```

### 4. Delete User
**DELETE** `/api/users/1`

### 5. Update Password
**PUT** `/api/users/pass/1`
```json
{
  "uPass": "newSecurePassword123"
}
```

### 6. Create User (Public)
**POST** `/api/public/users`
```json
{
  "name": "publicuser",
  "email": "public@example.com",
  "password": "password123",
  "preferredLanguage": "en",
  "role": "USER"
}
```

---

## TESTING WORKFLOW

### Step 1: Authentication
1. Register a new user with `/api/auth/register`
2. Login with `/api/auth/login` to get JWT token
3. Use token in `Authorization: Bearer TOKEN` header for protected endpoints

### Step 2: Create Base Data
1. Create topics using `/api/topics`
2. Create videos for topics using `/api/videos`
3. Create quizzes for topics using `/api/quizzes`

### Step 3: Test Learning Features
1. Create user progress using `/api/users/{userId}/progress`
2. Update progress as user watches videos
3. Submit quiz answers using `/api/quizzes/{id}/submit`
4. Generate scorecard using `/api/users/{userId}/scorecard/generate`

### Step 4: Test Learning Paths
1. Create learning paths using `/api/learning-paths/user/{userId}`
2. Add topics to learning paths
3. Test filtering and discovery features

## IMPORTANT NOTES

- **Authentication:** Most endpoints require JWT token in Authorization header
- **Status Values:** `NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`
- **Difficulty Values:** `EASY`, `MEDIUM`, `HARD`
- **Role Values:** `USER`, `ADMIN`
- **Purpose Values:** `UPSKILL`, `JOB_INTERVIEW`, `CAREER_CHANGE`, `LEARNING`
- **Level Values:** `BEGINNER`, `INTERMEDIATE`, `ADVANCED`, `EXPERT`

## ERROR HANDLING
- **401:** Unauthorized - Missing/invalid JWT token
- **404:** Not Found - Resource doesn't exist
- **400:** Bad Request - Invalid JSON or validation errors
- **403:** Forbidden - Insufficient permissions
