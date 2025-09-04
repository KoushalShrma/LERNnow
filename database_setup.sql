-- LEARNnow Database Setup Script
-- Execute this after your Spring Boot app creates the tables

-- Insert Topics
INSERT INTO topic (name, description, purpose, language, level, create_at, update_at) VALUES
('Java Basics', 'Introduction to Java programming language', 'Learning fundamentals', 'English', 'Beginner', NOW(), NOW()),
('Spring Boot', 'Spring Boot framework for web development', 'Web development', 'English', 'Intermediate', NOW(), NOW()),
('React JS', 'Frontend development with React', 'Frontend development', 'English', 'Intermediate', NOW(), NOW()),
('Python Basics', 'Introduction to Python programming', 'Learning fundamentals', 'English', 'Beginner', NOW(), NOW()),
('Data Structures', 'Algorithms and Data Structures', 'Interview preparation', 'English', 'Advanced', NOW(), NOW());

-- Insert Users (passwords will be hashed by the application)
-- Note: Column names are in snake_case as created by Hibernate
INSERT INTO user (u_name, u_email, u_pass, role, u_preferred_language, u_create_at, u_update_at) VALUES
('john_doe', 'john@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'USER', 'en', NOW(), NOW()),
('jane_smith', 'jane@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'USER', 'en', NOW(), NOW()),
('admin_user', 'admin@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'ADMIN', 'en', NOW(), NOW()),
('test_user', 'test@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'USER', 'hi', NOW(), NOW());

-- Insert Videos
-- Note: Hibernate converts tId to t_id, qId to q_id, etc.
INSERT INTO video (youtube_id, title, channel, duration, language, position, chapters_json, create_at, update_at, t_id) VALUES
('dQw4w9WgXcQ', 'Java Variables and Data Types', 'JavaTutorials', 900, 'English', 1, '{"chapters": [{"title": "Introduction", "time": 0}, {"title": "Variables", "time": 300}]}', NOW(), NOW(), 1),
('abc123xyz', 'Java Control Structures', 'JavaTutorials', 1200, 'English', 2, '{"chapters": [{"title": "If-else", "time": 0}, {"title": "Loops", "time": 600}]}', NOW(), NOW(), 1),
('spring101', 'Spring Boot Hello World', 'SpringChannel', 800, 'English', 1, '{"chapters": [{"title": "Setup", "time": 0}, {"title": "First App", "time": 400}]}', NOW(), NOW(), 2),
('react101', 'React Components', 'ReactChannel', 1000, 'English', 1, '{"chapters": [{"title": "JSX", "time": 0}, {"title": "Props", "time": 500}]}', NOW(), NOW(), 3),
('python101', 'Python Syntax', 'PythonTutorials', 700, 'English', 1, '{"chapters": [{"title": "Variables", "time": 0}, {"title": "Functions", "time": 350}]}', NOW(), NOW(), 4);

-- Insert Quizzes
-- Note: All q-prefixed columns become q_* in snake_case
INSERT INTO quiz (q_sub_topic, q_difficulty, q_language, q_purpose, q_is_active, q_create_at, q_update_at, t_id, q_questions_json) VALUES
('Java Variables', 'EASY', 'English', 'Learning assessment', true, NOW(), NOW(), 1, '{"questions": [{"question": "What is a variable?", "options": ["A container", "A method", "A class"], "correct": 0}]}'),
('Spring Boot Basics', 'MEDIUM', 'English', 'Skill testing', true, NOW(), NOW(), 2, '{"questions": [{"question": "What is Spring Boot?", "options": ["Framework", "Language", "Database"], "correct": 0}]}'),
('React Components', 'MEDIUM', 'English', 'Practice', true, NOW(), NOW(), 3, '{"questions": [{"question": "What is JSX?", "options": ["JavaScript XML", "Java XML", "JSON XML"], "correct": 0}]}'),
('Python Basics', 'EASY', 'English', 'Learning check', true, NOW(), NOW(), 4, '{"questions": [{"question": "Python is?", "options": ["Interpreted", "Compiled", "Both"], "correct": 0}]}'),
('Data Structures', 'HARD', 'English', 'Interview prep', true, NOW(), NOW(), 5, '{"questions": [{"question": "Time complexity of binary search?", "options": ["O(n)", "O(log n)", "O(n²)"], "correct": 1}]}');

-- Update Videos to link with Quizzes (q_id references)
UPDATE video SET q_id = 1 WHERE id = 1;
UPDATE video SET q_id = 2 WHERE id = 3;
UPDATE video SET q_id = 3 WHERE id = 4;

-- Insert User Progress
-- Note: uId becomes u_id, tId becomes t_id in snake_case
INSERT INTO user_progress (u_id, t_id, status, seconds_watched, create_at, update_at, last_seen_at) VALUES
(1, 1, 'IN_PROGRESS', 450, NOW(), NOW(), NOW()),
(1, 2, 'NOT_STARTED', 0, NOW(), NOW(), NULL),
(2, 1, 'COMPLETED', 900, NOW(), NOW(), NOW()),
(2, 3, 'IN_PROGRESS', 300, NOW(), NOW(), NOW()),
(3, 1, 'COMPLETED', 900, NOW(), NOW(), NOW()),
(4, 4, 'IN_PROGRESS', 200, NOW(), NOW(), NOW());

-- Insert Score Cards
-- Note: uId becomes u_id in snake_case
INSERT INTO score_card (u_id, accuracy, consistency, discipline, dedication, streak_days, update_at) VALUES
(1, 75.5, 80.0, 65.0, 90.0, 5, NOW()),
(2, 88.2, 92.0, 78.5, 85.0, 12, NOW()),
(3, 95.0, 88.0, 95.0, 100.0, 30, NOW()),
(4, 60.0, 55.0, 70.0, 75.0, 2, NOW());

-- Insert Learning Paths with correct column names
-- Note: u_id is required (creator of the learning path), using existing user IDs
INSERT INTO learning_path (name, description, purpose, language, level, u_id, estimated_hours, is_public, create_at, update_at) VALUES
('Full Stack Java Developer', 'Complete path for Java full-stack development', 'UPSKILL', 'English', 'INTERMEDIATE', 1, 120, true, NOW(), NOW()),
('Frontend Developer', 'React and modern frontend technologies', 'JOB_INTERVIEW', 'English', 'BEGINNER', 2, 80, true, NOW(), NOW()),
('Python Developer', 'Python programming and web development', 'UPSKILL', 'English', 'BEGINNER', 3, 100, false, NOW(), NOW()),
('Data Science Path', 'Complete data science learning journey', 'CAREER_CHANGE', 'English', 'ADVANCED', 1, 200, true, NOW(), NOW()),
('Backend Developer', 'Server-side development with Java Spring', 'JOB_INTERVIEW', 'English', 'INTERMEDIATE', 2, 150, false, NOW(), NOW());
