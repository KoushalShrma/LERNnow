import { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { SignedIn, SignedOut, SignInButton, SignUpButton } from '@clerk/clerk-react';
import { Navigate } from 'react-router-dom';
import { BookOpen, ArrowRight, Users, Trophy, Target } from 'lucide-react';
import Button from '@/components/ui/Button';
import Card from '@/components/ui/Card';

const AuthPage = () => {
  const [activeTab, setActiveTab] = useState('signin');

  const features = [
    {
      icon: BookOpen,
      title: 'Personalized Learning Paths',
      description: 'AI-curated courses tailored to your goals'
    },
    {
      icon: Trophy,
      title: 'Interactive Assessments',
      description: 'Dynamic quizzes that adapt to your progress'
    },
    {
      icon: Target,
      title: 'Progress Tracking',
      description: 'Detailed analytics and shareable scorecards'
    },
    {
      icon: Users,
      title: 'Expert Content',
      description: 'Curated from top educational channels'
    }
  ];

  return (
    <>
      <SignedIn>
        <Navigate to="/dashboard" replace />
      </SignedIn>
      
      <SignedOut>
        <div className="min-h-screen bg-gradient-to-br from-neutral-50 via-surface to-primary-50 flex">
          {/* Left Side - Features */}
          <div className="hidden lg:flex lg:w-1/2 bg-gradient-to-br from-primary-500 to-secondary-500 p-12 flex-col justify-center">
            <motion.div
              initial={{ opacity: 0, x: -50 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ duration: 0.8 }}
            >
              <div className="flex items-center space-x-3 mb-8">
                <div className="w-12 h-12 bg-white/20 rounded-xl flex items-center justify-center">
                  <span className="text-white font-bold text-xl">L</span>
                </div>
                <span className="text-3xl font-bold text-white">LEARNnow</span>
              </div>
              
              <h1 className="text-4xl font-bold text-white mb-6">
                Transform Your Learning Journey
              </h1>
              <p className="text-xl text-white/90 mb-12">
                Create personalized learning paths, track your progress, and achieve your goals with AI-powered education.
              </p>
              
              <div className="space-y-6">
                {features.map((feature, index) => {
                  const Icon = feature.icon;
                  return (
                    <motion.div
                      key={feature.title}
                      initial={{ opacity: 0, y: 20 }}
                      animate={{ opacity: 1, y: 0 }}
                      transition={{ duration: 0.6, delay: index * 0.1 }}
                      className="flex items-start space-x-4"
                    >
                      <div className="w-10 h-10 bg-white/20 rounded-lg flex items-center justify-center flex-shrink-0">
                        <Icon className="h-5 w-5 text-white" />
                      </div>
                      <div>
                        <h3 className="font-semibold text-white mb-1">{feature.title}</h3>
                        <p className="text-white/80 text-sm">{feature.description}</p>
                      </div>
                    </motion.div>
                  );
                })}
              </div>
            </motion.div>
          </div>

          {/* Right Side - Auth */}
          <div className="w-full lg:w-1/2 flex items-center justify-center p-8">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.6 }}
              className="w-full max-w-md"
            >
              {/* Mobile Logo */}
              <div className="lg:hidden text-center mb-8">
                <div className="inline-flex items-center space-x-2">
                  <div className="w-10 h-10 bg-gradient-to-br from-primary-500 to-secondary-500 rounded-xl flex items-center justify-center">
                    <span className="text-white font-bold">L</span>
                  </div>
                  <span className="text-2xl font-bold text-neutral-900">LEARNnow</span>
                </div>
              </div>

              <Card className="p-8">
                <div className="text-center mb-8">
                  <h2 className="text-2xl font-bold text-neutral-900 mb-2">
                    Welcome to LEARNnow
                  </h2>
                  <p className="text-neutral-600">
                    Start your personalized learning journey today
                  </p>
                </div>

                {/* Tab Navigation */}
                <div className="flex bg-neutral-100 rounded-lg p-1 mb-6">
                  <button
                    onClick={() => setActiveTab('signin')}
                    className={`flex-1 py-2 px-4 rounded-md text-sm font-medium transition-all duration-200 ${
                      activeTab === 'signin'
                        ? 'bg-white text-primary-600 shadow-soft'
                        : 'text-neutral-600 hover:text-neutral-900'
                    }`}
                  >
                    Sign In
                  </button>
                  <button
                    onClick={() => setActiveTab('signup')}
                    className={`flex-1 py-2 px-4 rounded-md text-sm font-medium transition-all duration-200 ${
                      activeTab === 'signup'
                        ? 'bg-white text-primary-600 shadow-soft'
                        : 'text-neutral-600 hover:text-neutral-900'
                    }`}
                  >
                    Sign Up
                  </button>
                </div>

                <AnimatePresence mode="wait">
                  <motion.div
                    key={activeTab}
                    initial={{ opacity: 0, x: 20 }}
                    animate={{ opacity: 1, x: 0 }}
                    exit={{ opacity: 0, x: -20 }}
                    transition={{ duration: 0.3 }}
                  >
                    {activeTab === 'signin' ? (
                      <div className="space-y-4">
                        <p className="text-sm text-neutral-600 text-center mb-6">
                          Welcome back! Sign in to continue your learning journey.
                        </p>
                        <SignInButton mode="modal">
                          <Button className="w-full" size="lg">
                            Sign In
                            <ArrowRight className="ml-2 h-4 w-4" />
                          </Button>
                        </SignInButton>
                      </div>
                    ) : (
                      <div className="space-y-4">
                        <p className="text-sm text-neutral-600 text-center mb-6">
                          Create your account and start learning today.
                        </p>
                        <SignUpButton mode="modal">
                          <Button className="w-full" size="lg">
                            Create Account
                            <ArrowRight className="ml-2 h-4 w-4" />
                          </Button>
                        </SignUpButton>
                      </div>
                    )}
                  </motion.div>
                </AnimatePresence>

                <div className="mt-8 pt-6 border-t border-neutral-200">
                  <p className="text-xs text-neutral-500 text-center">
                    By continuing, you agree to our Terms of Service and Privacy Policy
                  </p>
                </div>
              </Card>
            </motion.div>
          </div>
        </div>
      </SignedOut>
    </>
  );
};

export default AuthPage;