# LEARNnow API Testing Guide

## Base URL: http://localhost:8080

## 1. AUTHENTICATION ENDPOINTS

### Register User
**POST** `/api/auth/register`
```json
{
  "name": "testuser123",
  "email": "testuser123@example.com",
  "password": "password123",
  "preferredLanguage": "en"
}
```

### Login User
**POST** `/api/auth/login`
```json
{
  "email": "testuser123@example.com",
  "password": "password123"
}
```
**Response will include JWT token - save this for authorized requests**

### Get Current User (Requires JWT Token)
**GET** `/api/auth/me`
**Headers:** `Authorization: Bearer YOUR_JWT_TOKEN`

---

## 2. TOPIC ENDPOINTS

### Create Topic
**POST** `/api/topics`
```json
{
  "name": "Advanced JavaScript",
  "description": "Deep dive into JavaScript concepts",
  "purpose": "Professional development",
  "language": "English",
  "level": "Advanced"
}
```

### Get All Topics
**GET** `/api/topics`

### Get Topic by ID
**GET** `/api/topics/1`

### Update Topic
**PUT** `/api/topics/1`
```json
{
  "name": "Advanced JavaScript - Updated",
  "description": "Updated description for JavaScript concepts",
  "purpose": "Professional development",
  "language": "English",
  "level": "Expert"
}
```

### Delete Topic
**DELETE** `/api/topics/1`

### Get Videos by Topic
**GET** `/api/topics/1/videos`

### Get Quizzes by Topic
**GET** `/api/topics/1/quizzes`

---

## 3. USER ENDPOINTS

### Get All Users (Admin)
**GET** `/api/users`

### Get User by ID
**GET** `/api/users/1`

### Update User
**PUT** `/api/users/1`
```json
{
  "name": "updated_username",
  "email": "updated@example.com"
}
```

### Delete User
**DELETE** `/api/users/1`

### Update Password
**PATCH** `/api/users/1/password`
```json
{
  "newPassword": "newSecurePassword123"
}
```

---

## 4. VIDEO ENDPOINTS

### Create Video
**POST** `/api/videos`
```json
{
  "youtubeId": "newVideoId123",
  "title": "JavaScript Closures Explained",
  "channel": "JS Mastery",
  "duration": 1500,
  "language": "English",
  "position": 3,
  "chaptersJson": "{\"chapters\": [{\"title\": \"Introduction\", \"time\": 0}, {\"title\": \"Closures Basics\", \"time\": 300}, {\"title\": \"Advanced Examples\", \"time\": 900}]}",
  "topic": {
    "id": 1
  }
}
```

### Get All Videos
**GET** `/api/videos`

### Get Video by ID
**GET** `/api/videos/1`

### Update Video
**PUT** `/api/videos/1`
```json
{
  "youtubeId": "updatedVideoId",
  "title": "Updated Video Title",
  "channel": "Updated Channel",
  "duration": 1800,
  "language": "English",
  "position": 1,
  "chaptersJson": "{\"chapters\": [{\"title\": \"New Chapter 1\", \"time\": 0}]}",
  "topic": {
    "id": 1
  }
}
```

### Delete Video
**DELETE** `/api/videos/1`

---

## 5. QUIZ ENDPOINTS

### Create Quiz
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

### Get All Quizzes
**GET** `/api/quizzes`

### Get Quiz by ID
**GET** `/api/quizzes/1`

### Update Quiz
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
  "questionsJson": "{\"questions\": [{\"question\": \"Updated question?\", \"options\": [\"Option 1\", \"Option 2\"], \"correct\": 0}]}"
}
```

### Delete Quiz
**DELETE** `/api/quizzes/1`

### Toggle Quiz Active Status
**PATCH** `/api/quizzes/1/active?active=false`

### Get Quizzes by Topic
**GET** `/api/quizzes/topic/1`

### Get Quizzes by Difficulty
**GET** `/api/quizzes/difficulty/MEDIUM`

### Get Quizzes by Language
**GET** `/api/quizzes/language/English`

### Get Quizzes by Purpose
**GET** `/api/quizzes/purpose/Practice`

---

## 6. USER PROGRESS ENDPOINTS

### Get User's All Progress
**GET** `/api/users/1/progress`

### Create User Progress
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

### Get Specific Progress
**GET** `/api/users/1/progress/1`

### Update Progress
**PATCH** `/api/users/1/progress/1`
```json
{
  "status": "IN_PROGRESS",
  "secondsWatched": 450
}
```

### Delete Progress
**DELETE** `/api/users/1/progress/1`

### Get Progress by Status
**GET** `/api/users/1/progress/status/IN_PROGRESS`

### Get Progress for Topic
**GET** `/api/users/1/progress/topic/1`

---

## 7. SCORECARD ENDPOINTS

### Get User's Scorecard
**GET** `/api/users/1/scorecard`

### Create/Update Scorecard
**POST** `/api/users/1/scorecard`
```json
{
  "accuracy": 85.5,
  "consistency": 78.2,
  "discipline": 92.0,
  "dedication": 88.5,
  "streakDays": 15
}
```

### Update Scorecard
**PUT** `/api/users/1/scorecard`
```json
{
  "accuracy": 90.0,
  "consistency": 85.0,
  "discipline": 95.0,
  "dedication": 92.0,
  "streakDays": 20
}
```

---

## 8. LEARNING PATH ENDPOINTS (if implemented)

### Create Learning Path
**POST** `/api/learning-paths`
```json
{
  "name": "Complete Web Developer",
  "description": "Full-stack web development path",
  "difficultyLevel": "INTERMEDIATE",
  "estimatedDuration": 150,
  "active": true
}
```

### Get All Learning Paths
**GET** `/api/learning-paths`

### Get Learning Path by ID
**GET** `/api/learning-paths/1`

### Update Learning Path
**PUT** `/api/learning-paths/1`
```json
{
  "name": "Complete Web Developer - Updated",
  "description": "Updated full-stack development path",
  "difficultyLevel": "ADVANCED",
  "estimatedDuration": 180,
  "active": true
}
```

### Delete Learning Path
**DELETE** `/api/learning-paths/1`

---

## TESTING WORKFLOW

1. **First, run your Spring Boot application**
2. **Execute the database setup SQL script** to populate test data
3. **Start testing with authentication:**
   - Register a new user
   - Login to get JWT token
   - Use token in Authorization header for protected endpoints

4. **Test CRUD operations for each entity:**
   - Create new records
   - Read/list records
   - Update existing records
   - Delete records

5. **Test relationships:**
   - Create videos linked to topics
   - Create quizzes linked to topics
   - Create user progress for specific topics
   - Test filtering endpoints

## IMPORTANT NOTES

- **JWT Token:** Most endpoints require authentication. Include `Authorization: Bearer YOUR_JWT_TOKEN` in headers
- **User IDs:** Use the user IDs from your database (1, 2, 3, 4 from the setup script)
- **Topic IDs:** Reference existing topic IDs when creating videos, quizzes, or progress
- **Status Values:** For UserProgress, use: `NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`
- **Difficulty Values:** For Quiz, use: `EASY`, `MEDIUM`, `HARD`
- **Role Values:** For User, use: `USER`, `ADMIN`

## ERROR RESPONSES TO EXPECT

- **401 Unauthorized:** Missing or invalid JWT token
- **404 Not Found:** Resource doesn't exist
- **400 Bad Request:** Invalid JSON or missing required fields
- **403 Forbidden:** Insufficient permissions
