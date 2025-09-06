import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { motion } from 'framer-motion';
import { X, BookOpen, Target, Globe, Sparkles } from 'lucide-react';
import { useApiMutation } from '@/hooks/useApi';
import { topicsAPI, learningPathAPI, youtubeAPI } from '@/lib/api';
import { useAuth } from '@/hooks/useAuth.jsx';
import Modal from '@/components/ui/Modal';
import Button from '@/components/ui/Button';
import Input from '@/components/ui/Input';
import LoadingSpinner from '@/components/ui/LoadingSpinner';
import toast from 'react-hot-toast';

const CreateCourseModal = ({ isOpen, onClose, onCourseCreated }) => {
  const { user } = useAuth();
  const [step, setStep] = useState(1);
  const [createdTopic, setCreatedTopic] = useState(null);
  const [isGeneratingPath, setIsGeneratingPath] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
    watch
  } = useForm();

  const courseName = watch('name');

  const createTopicMutation = useApiMutation(topicsAPI.create, {
    onSuccess: (data) => {
      setCreatedTopic(data.data);
      setStep(2);
    },
    onError: (error) => {
      toast.error('Failed to create course');
    }
  });

  const languageOptions = [
    { value: 'English', label: 'English' },
    { value: 'Hindi', label: 'Hindi' },
    { value: 'Spanish', label: 'Spanish' },
    { value: 'French', label: 'French' },
    { value: 'German', label: 'German' },
  ];

  const levelOptions = [
    { value: 'Beginner', label: 'Beginner' },
    { value: 'Intermediate', label: 'Intermediate' },
    { value: 'Advanced', label: 'Advanced' },
  ];

  const onSubmit = async (data) => {
    const topicData = {
      name: data.name,
      description: data.description,
      purpose: data.purpose,
      language: data.language,
      level: data.level,
      estimatedDurationMinutes: 120, // Default 2 hours
      enrolledUsers: 1,
      rating: 0
    };

    createTopicMutation.mutate(topicData);
  };

  const generateLearningPath = async () => {
    if (!createdTopic) return;

    setIsGeneratingPath(true);
    try {
      // Search for videos related to the topic
      const searchQuery = createdTopic.name;
      const videosResponse = await youtubeAPI.search(searchQuery, 8);
      
      if (videosResponse.data && videosResponse.data.length > 0) {
        // Create videos for the topic
        const videoPromises = videosResponse.data.slice(0, 6).map((video, index) => {
          return topicsAPI.create({
            youtubeId: video.videoId,
            title: video.title,
            channel: video.channelTitle,
            duration: video.duration || 600,
            language: createdTopic.language,
            position: index + 1,
            chaptersJson: JSON.stringify({
              chapters: [
                { title: 'Introduction', time: 0 },
                { title: 'Main Content', time: Math.floor((video.duration || 600) * 0.3) },
                { title: 'Summary', time: Math.floor((video.duration || 600) * 0.8) }
              ]
            }),
            topic: { id: createdTopic.id }
          });
        });

        await Promise.all(videoPromises);
        
        // Create a learning path
        if (user?.id) {
          await learningPathAPI.create(user.id, {
            name: `${createdTopic.name} Learning Path`,
            description: `Complete learning path for ${createdTopic.name}`,
            purpose: createdTopic.purpose,
            language: createdTopic.language,
            level: createdTopic.level,
            estimatedHours: Math.ceil(createdTopic.estimatedDurationMinutes / 60),
            isPublic: false
          });
        }
      }

      toast.success('Learning path created successfully!');
      onCourseCreated();
      handleClose();
    } catch (error) {
      console.error('Error generating learning path:', error);
      toast.error('Failed to generate learning path');
    } finally {
      setIsGeneratingPath(false);
    }
  };

  const handleClose = () => {
    setStep(1);
    setCreatedTopic(null);
    setIsGeneratingPath(false);
    reset();
    onClose();
  };

  return (
    <Modal isOpen={isOpen} onClose={handleClose} size="lg">
      <div className="p-6">
        {step === 1 ? (
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
          >
            <div className="flex items-center space-x-3 mb-6">
              <div className="w-10 h-10 bg-primary-100 rounded-lg flex items-center justify-center">
                <BookOpen className="h-5 w-5 text-primary-600" />
              </div>
              <h2 className="text-2xl font-semibold text-neutral-900">
                Create New Course
              </h2>
            </div>

            <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
              <div>
                <label className="block text-sm font-medium text-neutral-700 mb-2">
                  <Target className="inline h-4 w-4 mr-1" />
                  Course Name
                </label>
                <input
                  {...register('name', { required: 'Course name is required' })}
                  type="text"
                  placeholder="e.g., JavaScript Fundamentals, Python for Data Science"
                  className="w-full px-4 py-3 border border-neutral-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                />
                {errors.name && (
                  <p className="mt-1 text-sm text-red-600">{errors.name.message}</p>
                )}
              </div>

              <div>
                <label className="block text-sm font-medium text-neutral-700 mb-2">
                  Description (Why do you want to learn this?)
                </label>
                <textarea
                  {...register('description', { required: 'Description is required' })}
                  rows={3}
                  placeholder="Describe your learning goals and what you hope to achieve..."
                  className="w-full px-4 py-3 border border-neutral-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent resize-none"
                />
                {errors.description && (
                  <p className="mt-1 text-sm text-red-600">{errors.description.message}</p>
                )}
              </div>

              <div>
                <label className="block text-sm font-medium text-neutral-700 mb-2">
                  Learning Purpose
                </label>
                <input
                  {...register('purpose', { required: 'Purpose is required' })}
                  type="text"
                  placeholder="e.g., Career advancement, Personal interest, Job interview prep"
                  className="w-full px-4 py-3 border border-neutral-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                />
                {errors.purpose && (
                  <p className="mt-1 text-sm text-red-600">{errors.purpose.message}</p>
                )}
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-neutral-700 mb-2">
                    <Globe className="inline h-4 w-4 mr-1" />
                    Preferred Language
                  </label>
                  <select
                    {...register('language', { required: 'Language is required' })}
                    className="w-full px-4 py-3 border border-neutral-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                  >
                    <option value="">Select language</option>
                    {languageOptions.map((option) => (
                      <option key={option.value} value={option.value}>
                        {option.label}
                      </option>
                    ))}
                  </select>
                  {errors.language && (
                    <p className="mt-1 text-sm text-red-600">{errors.language.message}</p>
                  )}
                </div>

                <div>
                  <label className="block text-sm font-medium text-neutral-700 mb-2">
                    Skill Level
                  </label>
                  <select
                    {...register('level', { required: 'Level is required' })}
                    className="w-full px-4 py-3 border border-neutral-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                  >
                    <option value="">Select level</option>
                    {levelOptions.map((option) => (
                      <option key={option.value} value={option.value}>
                        {option.label}
                      </option>
                    ))}
                  </select>
                  {errors.level && (
                    <p className="mt-1 text-sm text-red-600">{errors.level.message}</p>
                  )}
                </div>
              </div>

              <div className="flex justify-end space-x-3 pt-4">
                <Button
                  type="button"
                  variant="ghost"
                  onClick={handleClose}
                >
                  Cancel
                </Button>
                <Button
                  type="submit"
                  loading={createTopicMutation.isLoading}
                  className="min-w-[120px]"
                >
                  Create Course
                </Button>
              </div>
            </form>
          </motion.div>
        ) : (
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            className="text-center"
          >
            <div className="w-16 h-16 bg-gradient-to-br from-primary-500 to-secondary-500 rounded-full flex items-center justify-center mx-auto mb-6">
              <Sparkles className="h-8 w-8 text-white" />
            </div>
            
            <h2 className="text-2xl font-semibold text-neutral-900 mb-4">
              Course Created Successfully!
            </h2>
            
            <p className="text-neutral-600 mb-8">
              Your course "{createdTopic?.name}" has been created. 
              Now let's generate a personalized learning path with curated videos.
            </p>

            <div className="bg-neutral-50 rounded-lg p-6 mb-8">
              <h3 className="font-medium text-neutral-900 mb-2">What happens next?</h3>
              <ul className="text-sm text-neutral-600 space-y-2">
                <li>• AI will break down your topic into key subtopics</li>
                <li>• Curate the best educational videos from YouTube</li>
                <li>• Create interactive quizzes for each section</li>
                <li>• Set up progress tracking and scorecard</li>
              </ul>
            </div>

            <div className="flex justify-center space-x-3">
              <Button
                variant="ghost"
                onClick={handleClose}
              >
                Skip for Now
              </Button>
              <Button
                onClick={generateLearningPath}
                loading={isGeneratingPath}
                className="min-w-[180px]"
              >
                {isGeneratingPath ? (
                  <>
                    <LoadingSpinner size="sm" className="mr-2" />
                    Generating Path...
                  </>
                ) : (
                  'Create My Learning Path'
                )}
              </Button>
            </div>
          </motion.div>
        )}
      </div>
    </Modal>
  );
};

export default CreateCourseModal;