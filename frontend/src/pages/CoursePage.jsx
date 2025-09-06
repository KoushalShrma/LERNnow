import { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { 
  ArrowLeft, 
  Play, 
  CheckCircle, 
  Clock, 
  BookOpen, 
  Trophy,
  ChevronRight,
  Youtube
} from 'lucide-react';
import { useApiQuery, useApiMutation } from '@/hooks/useApi';
import { topicsAPI, progressAPI, quizzesAPI } from '@/lib/api';
import { useAuth } from '@/hooks/useAuth.jsx';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import LoadingSpinner from '@/components/ui/LoadingSpinner';
import VideoPlayer from '@/components/course/VideoPlayer';
import QuizModal from '@/components/course/QuizModal';
import toast from 'react-hot-toast';

const CoursePage = () => {
  const { courseId } = useParams();
  const { user } = useAuth();
  const [currentVideoIndex, setCurrentVideoIndex] = useState(0);
  const [showQuiz, setShowQuiz] = useState(false);
  const [currentQuiz, setCurrentQuiz] = useState(null);

  // Fetch course details
  const { data: course, isLoading: courseLoading } = useApiQuery(
    ['topic', courseId],
    () => topicsAPI.getById(courseId),
    { enabled: !!courseId }
  );

  // Fetch course videos
  const { data: videos, isLoading: videosLoading } = useApiQuery(
    ['topic-videos', courseId],
    () => topicsAPI.getVideos(courseId),
    { enabled: !!courseId }
  );

  // Fetch course quizzes
  const { data: quizzes, isLoading: quizzesLoading } = useApiQuery(
    ['topic-quizzes', courseId],
    () => topicsAPI.getQuizzes(courseId),
    { enabled: !!courseId }
  );

  // Fetch user progress
  const { data: userProgress, refetch: refetchProgress } = useApiQuery(
    ['user-progress-topic', user?.id, courseId],
    () => progressAPI.getByTopic(user?.id, courseId),
    { enabled: !!user?.id && !!courseId }
  );

  // Update progress mutation
  const updateProgressMutation = useApiMutation(
    ({ userId, topicId, data }) => progressAPI.setWatchSeconds(userId, topicId, data),
    {
      onSuccess: () => {
        refetchProgress();
      }
    }
  );

  const currentVideo = videos?.[currentVideoIndex];

  const handleVideoComplete = async () => {
    if (!user?.id || !courseId) return;

    try {
      // Update watch time
      await updateProgressMutation.mutateAsync({
        userId: user.id,
        topicId: courseId,
        data: (currentVideo?.duration || 600)
      });

      // Check if there's a quiz for this video
      const videoQuiz = quizzes?.find(quiz => 
        quiz.subTopic?.toLowerCase().includes(currentVideo?.title?.toLowerCase().split(' ')[0] || '')
      );

      if (videoQuiz) {
        setCurrentQuiz(videoQuiz);
        setShowQuiz(true);
      } else {
        // Move to next video if no quiz
        handleNextVideo();
      }
    } catch (error) {
      console.error('Error updating progress:', error);
      toast.error('Failed to update progress');
    }
  };

  const handleQuizComplete = (score) => {
    setShowQuiz(false);
    setCurrentQuiz(null);
    
    if (score >= 60) {
      toast.success(`Great job! You scored ${score}%`);
      handleNextVideo();
    } else {
      toast.error(`You scored ${score}%. Consider reviewing the material.`);
    }
  };

  const handleNextVideo = () => {
    if (videos && currentVideoIndex < videos.length - 1) {
      setCurrentVideoIndex(currentVideoIndex + 1);
    } else {
      // Course completed
      toast.success('Congratulations! You completed the course!');
      // Update course status to completed
      if (user?.id && courseId) {
        progressAPI.setStatus(user.id, courseId, 'COMPLETED');
      }
    }
  };

  const handleVideoSelect = (index) => {
    setCurrentVideoIndex(index);
  };

  if (courseLoading || videosLoading) {
    return (
      <div className="min-h-screen bg-surface flex items-center justify-center">
        <LoadingSpinner size="lg" />
      </div>
    );
  }

  if (!course) {
    return (
      <div className="min-h-screen bg-surface flex items-center justify-center">
        <div className="text-center">
          <h2 className="text-2xl font-semibold text-neutral-900 mb-4">Course Not Found</h2>
          <Link to="/dashboard">
            <Button>Back to Dashboard</Button>
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-surface">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {/* Header */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="mb-8"
        >
          <Link 
            to="/dashboard"
            className="inline-flex items-center text-primary-600 hover:text-primary-700 mb-4"
          >
            <ArrowLeft className="h-4 w-4 mr-2" />
            Back to Dashboard
          </Link>
          
          <div className="flex items-start justify-between">
            <div>
              <h1 className="text-3xl font-bold text-neutral-900 mb-2">
                {course.name}
              </h1>
              <p className="text-neutral-600 mb-4">
                {course.description}
              </p>
              <div className="flex items-center space-x-4 text-sm text-neutral-500">
                <div className="flex items-center space-x-1">
                  <Clock className="h-4 w-4" />
                  <span>{course.estimatedDuration || '2-3 hours'}</span>
                </div>
                <div className="flex items-center space-x-1">
                  <BookOpen className="h-4 w-4" />
                  <span>{course.level}</span>
                </div>
                <div className="flex items-center space-x-1">
                  <Play className="h-4 w-4" />
                  <span>{videos?.length || 0} videos</span>
                </div>
              </div>
            </div>
          </div>
        </motion.div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Video Player */}
          <motion.div
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: 1, x: 0 }}
            className="lg:col-span-2"
          >
            <Card className="overflow-hidden">
              {currentVideo ? (
                <VideoPlayer
                  video={currentVideo}
                  onVideoComplete={handleVideoComplete}
                />
              ) : (
                <div className="aspect-video bg-neutral-100 flex items-center justify-center">
                  <div className="text-center">
                    <Youtube className="h-12 w-12 text-neutral-400 mx-auto mb-4" />
                    <p className="text-neutral-600">No videos available</p>
                  </div>
                </div>
              )}
            </Card>
          </motion.div>

          {/* Course Outline */}
          <motion.div
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
          >
            <Card>
              <div className="p-6">
                <h3 className="text-lg font-semibold text-neutral-900 mb-4">
                  Course Content
                </h3>
                
                {videos && videos.length > 0 ? (
                  <div className="space-y-2">
                    {videos.map((video, index) => (
                      <motion.div
                        key={video.id}
                        initial={{ opacity: 0, y: 10 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ delay: index * 0.1 }}
                        className={`p-3 rounded-lg cursor-pointer transition-all duration-200 ${
                          index === currentVideoIndex
                            ? 'bg-primary-50 border border-primary-200'
                            : 'hover:bg-neutral-50'
                        }`}
                        onClick={() => handleVideoSelect(index)}
                      >
                        <div className="flex items-center space-x-3">
                          <div className={`w-8 h-8 rounded-full flex items-center justify-center ${
                            index < currentVideoIndex
                              ? 'bg-accent-500 text-white'
                              : index === currentVideoIndex
                              ? 'bg-primary-500 text-white'
                              : 'bg-neutral-200 text-neutral-600'
                          }`}>
                            {index < currentVideoIndex ? (
                              <CheckCircle className="h-4 w-4" />
                            ) : (
                              <span className="text-sm font-medium">{index + 1}</span>
                            )}
                          </div>
                          <div className="flex-1">
                            <h4 className="font-medium text-neutral-900 text-sm">
                              {video.title}
                            </h4>
                            <p className="text-xs text-neutral-500">
                              {Math.floor((video.duration || 600) / 60)} min
                            </p>
                          </div>
                          {index === currentVideoIndex && (
                            <ChevronRight className="h-4 w-4 text-primary-600" />
                          )}
                        </div>
                      </motion.div>
                    ))}
                  </div>
                ) : (
                  <div className="text-center py-8">
                    <BookOpen className="h-8 w-8 text-neutral-400 mx-auto mb-2" />
                    <p className="text-neutral-600 text-sm">No content available</p>
                  </div>
                )}
              </div>
            </Card>
          </motion.div>
        </div>
      </div>

      {/* Quiz Modal */}
      {showQuiz && currentQuiz && (
        <QuizModal
          quiz={currentQuiz}
          isOpen={showQuiz}
          onClose={() => setShowQuiz(false)}
          onComplete={handleQuizComplete}
        />
      )}
    </div>
  );
};

export default CoursePage;