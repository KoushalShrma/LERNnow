package me.learn.now.service;

import me.learn.now.model.Difficulty;
import me.learn.now.model.Quiz;
import me.learn.now.model.Topic;
import me.learn.now.repository.QuizRepo;
import me.learn.now.repository.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class QuizService {

    @Autowired
    private QuizRepo qr; // repo se DB baat karega

    @Autowired
    private TopicRepo topicRepo;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Quiz add(Quiz q){
        return qr.save(q);
    }

    public List<Quiz> list(){
        return qr.findAll();
    }

    public Optional<Quiz> get(Long id){
        return qr.findById(id);
    }

    public Quiz update(Long id, Quiz input){
        Quiz q = qr.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        q.setSubTopic(input.getSubTopic());
        q.setDifficulty(input.getDifficulty());
        q.setLanguage(input.getLanguage());
        q.setPurpose(input.getPurpose());
        q.setActive(input.isActive());
        q.setTopic(input.getTopic());
        q.setQuestionsJson(input.getQuestionsJson());
        return qr.save(q);
    }

    public Optional<Quiz> delete(Long id){
        Optional<Quiz> q = qr.findById(id);
        q.ifPresent(qr::delete);
        return q;
    }

    public Quiz setActive(Long id, boolean active){
        Quiz q = qr.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        q.setActive(active);
        return qr.save(q);
    }

    // Hinglish: topic ke basis pe active quizzes dhoondhne ke liye
    public List<Quiz> getQuizzesByTopic(Long topicId) {
        return qr.findByTopicIdAndIsActiveTrue(topicId);
    }

    // Hinglish: difficulty level ke basis pe quiz filter karne ke liye
    public List<Quiz> getQuizzesByDifficulty(Difficulty difficulty) {
        return qr.findByDifficultyAndIsActiveTrue(difficulty);
    }

    // Hinglish: auto quiz generation - topic aur difficulty ke basis pe questions banate hai
    public Quiz generateQuizForTopic(Long topicId, Difficulty difficulty, String language) {
        Topic topic = topicRepo.findById(topicId)
            .orElseThrow(() -> new RuntimeException("Topic not found"));

        // Hinglish: sample questions generate karte hai - real me AI API use kar sakte hai
        List<Map<String, Object>> questions = generateSampleQuestions(topic.getName(), difficulty, language);

        Quiz quiz = new Quiz();
        quiz.setTopic(topic);
        quiz.setSubTopic(topic.getName());
        quiz.setDifficulty(difficulty);
        quiz.setLanguage(language);
        quiz.setPurpose("LEARNING");
        quiz.setActive(true);

        try {
            quiz.setQuestionsJson(objectMapper.writeValueAsString(questions));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize questions: " + e.getMessage());
        }

        return qr.save(quiz);
    }

    // Hinglish: quiz answers evaluate karne ke liye
    public Map<String, Object> evaluateQuizAnswers(Long quizId, Map<String, String> userAnswers) {
        Quiz quiz = qr.findById(quizId)
            .orElseThrow(() -> new RuntimeException("Quiz not found"));

        try {
            // Hinglish: stored questions ko parse karte hai
            List<Map<String, Object>> questions = objectMapper.readValue(
                quiz.getQuestionsJson(), List.class);

            int totalQuestions = questions.size();
            int correctAnswers = 0;

            // Hinglish: har question ka answer check karte hai
            for (int i = 0; i < questions.size(); i++) {
                Map<String, Object> question = questions.get(i);
                String correctAnswer = (String) question.get("correctAnswer");
                String userAnswer = userAnswers.get("q" + i);

                if (correctAnswer != null && correctAnswer.equalsIgnoreCase(userAnswer)) {
                    correctAnswers++;
                }
            }

            // Hinglish: result calculate karte hai
            double percentage = (double) correctAnswers / totalQuestions * 100;
            String grade = calculateGrade(percentage);

            Map<String, Object> result = new HashMap<>();
            result.put("totalQuestions", totalQuestions);
            result.put("correctAnswers", correctAnswers);
            result.put("percentage", Math.round(percentage * 100.0) / 100.0);
            result.put("grade", grade);
            result.put("passed", percentage >= 60); // 60% passing criteria

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Failed to evaluate quiz: " + e.getMessage());
        }
    }

    // Hinglish: sample questions generate karne ke liye - placeholder implementation
    private List<Map<String, Object>> generateSampleQuestions(String topicName, Difficulty difficulty, String language) {
        List<Map<String, Object>> questions = new ArrayList<>();
        Random random = new Random();

        // Hinglish: difficulty ke basis pe questions ki complexity decide karte hai
        int numQuestions = difficulty == Difficulty.EASY ? 5 : difficulty == Difficulty.MEDIUM ? 7 : 10;

        for (int i = 0; i < numQuestions; i++) {
            Map<String, Object> question = new HashMap<>();
            question.put("id", i);
            question.put("question", "Sample question " + (i + 1) + " about " + topicName);
            question.put("type", "multiple_choice");

            List<String> options = new ArrayList<>();
            options.add("Option A");
            options.add("Option B");
            options.add("Option C");
            options.add("Option D");
            question.put("options", options);

            // Hinglish: random correct answer choose kar lete hai
            question.put("correctAnswer", options.get(random.nextInt(options.size())));

            questions.add(question);
        }

        return questions;
    }

    // Hinglish: percentage ke basis pe grade calculate karne ke liye
    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else return "F";
    }
}
