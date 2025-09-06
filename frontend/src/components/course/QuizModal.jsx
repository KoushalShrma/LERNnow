import { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { X, CheckCircle, XCircle, Trophy, RotateCcw } from 'lucide-react';
import Modal from '@/components/ui/Modal';
import Button from '@/components/ui/Button';
import { useApiMutation } from '@/hooks/useApi';
import { quizzesAPI } from '@/lib/api';

const QuizModal = ({ quiz, isOpen, onClose, onComplete }) => {
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [selectedAnswers, setSelectedAnswers] = useState({});
  const [showResults, setShowResults] = useState(false);
  const [quizResults, setQuizResults] = useState(null);
  const [questions, setQuestions] = useState([]);

  const submitQuizMutation = useApiMutation(
    ({ quizId, answers }) => quizzesAPI.submit(quizId, answers),
    {
      onSuccess: (data) => {
        setQuizResults(data.data);
        setShowResults(true);
      }
    }
  );

  useEffect(() => {
    if (quiz && quiz.questionsJson) {
      try {
        const parsedQuestions = JSON.parse(quiz.questionsJson);
        setQuestions(parsedQuestions.questions || parsedQuestions || []);
      } catch (error) {
        console.error('Error parsing quiz questions:', error);
        setQuestions([]);
      }
    }
  }, [quiz]);

  const handleAnswerSelect = (answerIndex) => {
    setSelectedAnswers({
      ...selectedAnswers,
      [`q${currentQuestion}`]: answerIndex.toString()
    });
  };

  const handleNext = () => {
    if (currentQuestion < questions.length - 1) {
      setCurrentQuestion(currentQuestion + 1);
    } else {
      handleSubmitQuiz();
    }
  };

  const handlePrevious = () => {
    if (currentQuestion > 0) {
      setCurrentQuestion(currentQuestion - 1);
    }
  };

  const handleSubmitQuiz = () => {
    submitQuizMutation.mutate({
      quizId: quiz.id,
      answers: selectedAnswers
    });
  };

  const handleRetry = () => {
    setCurrentQuestion(0);
    setSelectedAnswers({});
    setShowResults(false);
    setQuizResults(null);
  };

  const handleComplete = () => {
    onComplete(quizResults?.percentage || 0);
    onClose();
  };

  const currentQuestionData = questions[currentQuestion];
  const isLastQuestion = currentQuestion === questions.length - 1;
  const hasSelectedAnswer = selectedAnswers[`q${currentQuestion}`] !== undefined;

  if (!quiz || questions.length === 0) {
    return null;
  }

  return (
    <Modal isOpen={isOpen} onClose={onClose} size="lg">
      <div className="p-6">
        <AnimatePresence mode="wait">
          {!showResults ? (
            <motion.div
              key="quiz"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -20 }}
            >
              {/* Quiz Header */}
              <div className="flex items-center justify-between mb-6">
                <div>
                  <h2 className="text-2xl font-semibold text-neutral-900">
                    {quiz.subTopic} Quiz
                  </h2>
                  <p className="text-neutral-600">
                    Question {currentQuestion + 1} of {questions.length}
                  </p>
                </div>
                <div className="flex items-center space-x-2">
                  <div className={`px-3 py-1 rounded-full text-xs font-medium ${
                    quiz.difficulty === 'EASY' ? 'bg-accent-100 text-accent-700' :
                    quiz.difficulty === 'MEDIUM' ? 'bg-warning-100 text-warning-700' :
                    'bg-red-100 text-red-700'
                  }`}>
                    {quiz.difficulty}
                  </div>
                </div>
              </div>

              {/* Progress Bar */}
              <div className="w-full bg-neutral-200 rounded-full h-2 mb-8">
                <motion.div
                  className="bg-primary-500 h-2 rounded-full"
                  initial={{ width: 0 }}
                  animate={{ width: `${((currentQuestion + 1) / questions.length) * 100}%` }}
                  transition={{ duration: 0.3 }}
                />
              </div>

              {/* Question */}
              <div className="mb-8">
                <h3 className="text-xl font-medium text-neutral-900 mb-6">
                  {currentQuestionData?.question}
                </h3>

                <div className="space-y-3">
                  {currentQuestionData?.options?.map((option, index) => (
                    <motion.button
                      key={index}
                      initial={{ opacity: 0, x: -20 }}
                      animate={{ opacity: 1, x: 0 }}
                      transition={{ delay: index * 0.1 }}
                      onClick={() => handleAnswerSelect(index)}
                      className={`w-full p-4 text-left rounded-lg border-2 transition-all duration-200 ${
                        selectedAnswers[`q${currentQuestion}`] === index.toString()
                          ? 'border-primary-500 bg-primary-50 text-primary-900'
                          : 'border-neutral-200 hover:border-neutral-300 hover:bg-neutral-50'
                      }`}
                    >
                      <div className="flex items-center space-x-3">
                        <div className={`w-6 h-6 rounded-full border-2 flex items-center justify-center ${
                          selectedAnswers[`q${currentQuestion}`] === index.toString()
                            ? 'border-primary-500 bg-primary-500'
                            : 'border-neutral-300'
                        }`}>
                          {selectedAnswers[`q${currentQuestion}`] === index.toString() && (
                            <div className="w-2 h-2 bg-white rounded-full" />
                          )}
                        </div>
                        <span className="font-medium">{option}</span>
                      </div>
                    </motion.button>
                  ))}
                </div>
              </div>

              {/* Navigation */}
              <div className="flex justify-between">
                <Button
                  variant="ghost"
                  onClick={handlePrevious}
                  disabled={currentQuestion === 0}
                >
                  Previous
                </Button>
                <Button
                  onClick={handleNext}
                  disabled={!hasSelectedAnswer}
                  loading={submitQuizMutation.isLoading}
                >
                  {isLastQuestion ? 'Submit Quiz' : 'Next Question'}
                </Button>
              </div>
            </motion.div>
          ) : (
            <motion.div
              key="results"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              className="text-center"
            >
              {/* Results Header */}
              <div className="mb-8">
                <div className={`w-20 h-20 rounded-full flex items-center justify-center mx-auto mb-4 ${
                  quizResults?.percentage >= 80 ? 'bg-accent-100' :
                  quizResults?.percentage >= 60 ? 'bg-warning-100' :
                  'bg-red-100'
                }`}>
                  {quizResults?.percentage >= 60 ? (
                    <Trophy className={`h-10 w-10 ${
                      quizResults?.percentage >= 80 ? 'text-accent-600' :
                      'text-warning-600'
                    }`} />
                  ) : (
                    <XCircle className="h-10 w-10 text-red-600" />
                  )}
                </div>
                
                <h2 className="text-3xl font-bold text-neutral-900 mb-2">
                  {quizResults?.percentage}%
                </h2>
                
                <p className="text-lg text-neutral-600 mb-4">
                  {quizResults?.percentage >= 80 ? 'Excellent work!' :
                   quizResults?.percentage >= 60 ? 'Good job!' :
                   'Keep practicing!'}
                </p>

                <div className="bg-neutral-50 rounded-lg p-6 mb-6">
                  <div className="grid grid-cols-2 gap-4 text-center">
                    <div>
                      <p className="text-2xl font-bold text-accent-600">
                        {quizResults?.correctAnswers || 0}
                      </p>
                      <p className="text-sm text-neutral-600">Correct</p>
                    </div>
                    <div>
                      <p className="text-2xl font-bold text-red-600">
                        {(quizResults?.totalQuestions || 0) - (quizResults?.correctAnswers || 0)}
                      </p>
                      <p className="text-sm text-neutral-600">Incorrect</p>
                    </div>
                  </div>
                </div>
              </div>

              {/* Action Buttons */}
              <div className="flex justify-center space-x-4">
                {quizResults?.percentage < 60 && (
                  <Button
                    variant="outline"
                    onClick={handleRetry}
                    className="flex items-center space-x-2"
                  >
                    <RotateCcw className="h-4 w-4" />
                    <span>Retry Quiz</span>
                  </Button>
                )}
                <Button
                  onClick={handleComplete}
                  className="flex items-center space-x-2"
                >
                  <CheckCircle className="h-4 w-4" />
                  <span>Continue</span>
                </Button>
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </div>
    </Modal>
  );
};

export default QuizModal;