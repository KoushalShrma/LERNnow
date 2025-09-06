# LEARNnow Platform

A fully integrated learning platform with AI-powered course generation, interactive learning experiences, and progress tracking.

## Project Structure

The LEARNnow platform consists of two main components:

1. **Backend (Spring Boot)**
   - REST API endpoints in `src/main/java/me/learn/now/controller`
   - Database models in `src/main/java/me/learn/now/model`
   - Business logic in `src/main/java/me/learn/now/service`
   - Data repositories in `src/main/java/me/learn/now/repository`

2. **Frontend (Next.js)**
   - Located in the `frontend` directory
   - Modern app router architecture
   - TailwindCSS for styling
   - API routes for server-side data fetching

## Getting Started

### Prerequisites

- Java 17 or higher
- Node.js 18 or higher
- npm 9 or higher
- PostgreSQL database (configured in application.yml)

### Database Setup

Run the database setup script to create the necessary tables and initial data:

```bash
psql -U your_username -d your_database -f database_setup.sql
```

### Configuration

1. **Backend Configuration**:
   - Update `src/main/resources/application.yml` with your database settings
   - If needed, create `application-secret.yml` for sensitive information like API keys

2. **Frontend Configuration**:
   - The frontend is already configured to connect to the backend at `http://localhost:8080/api`
   - Environment variables are set in `.env.local`

### Running the Platform

#### Option 1: Using the Start Script (Recommended)

Run the provided batch script to start both the backend and frontend:

```bash
./start-learnnow.bat
```

This will:
- Start the Spring Boot backend on port 8080
- Start the Next.js frontend on port 3000
- Open browser windows for both

#### Option 2: Manual Start

**Backend:**
```bash
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd "frontend"
npm install
npm run dev
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `GET /api/auth/me` - Get current user info

### Topics/Courses
- `GET /api/topics` - List all topics
- `GET /api/topics/{id}` - Get topic by ID
- `POST /api/topics` - Create new topic
- `PUT /api/topics/{id}` - Update topic
- `DELETE /api/topics/{id}` - Delete topic
- `GET /api/topics/{id}/videos` - Get videos for a topic

### User Progress
- `GET /api/user-progress` - Get user's learning progress
- `POST /api/user-progress` - Update user's progress
- `GET /api/user-progress/course/{courseId}` - Get progress for specific course

### Quizzes
- `GET /api/quizzes` - List all quizzes
- `GET /api/quizzes/{id}` - Get quiz by ID
- `POST /api/quizzes` - Create new quiz
- `PUT /api/quizzes/{id}` - Update quiz
- `DELETE /api/quizzes/{id}` - Delete quiz

### Videos
- `GET /api/videos` - List all videos
- `GET /api/videos/{id}` - Get video by ID
- `POST /api/videos` - Create new video
- `PUT /api/videos/{id}` - Update video
- `DELETE /api/videos/{id}` - Delete video

## Architecture

The LEARNnow platform follows a modern architecture with complete separation between the frontend and backend:

1. **Backend**: Spring Boot REST API providing:
   - Secure authentication with JWT
   - Database operations via JPA/Hibernate
   - Business logic in service layer
   - YouTube API integration for video content

2. **Frontend**: Next.js application with:
   - Server components for better SEO and performance
   - Client components for interactive elements
   - API routes to proxy requests to the backend
   - Tailwind CSS for responsive design

3. **Integration**: The frontend communicates with the backend via:
   - API client in `lib/api-client.js`
   - Fetch API for data retrieval
   - JWT token-based authentication

## Contributing

To contribute to the LEARNnow platform:

1. Create a feature branch from `main`
2. Make your changes
3. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For questions or issues, please contact the development team.
