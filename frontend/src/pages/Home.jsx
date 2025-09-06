import { Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { 
  ArrowRight, 
  BookOpen, 
  Users, 
  Trophy, 
  Star,
  Play,
  CheckCircle,
  Zap,
  Target,
  Brain
} from 'lucide-react';
import { useAuth } from '@/hooks/useAuth';
import Button from '@/components/ui/Button';
import Card from '@/components/ui/Card';

const Home = () => {
  const { isAuthenticated } = useAuth();

  const features = [
    {
      icon: Brain,
      title: 'AI-Powered Learning',
      description: 'Personalized learning paths adapted to your pace and style',
      color: 'primary',
    },
    {
      icon: Trophy,
      title: 'Interactive Quizzes',
      description: 'Test your knowledge with adaptive quizzes and instant feedback',
      color: 'secondary',
    },
    {
      icon: Target,
      title: 'Progress Tracking',
      description: 'Monitor your learning journey with detailed analytics',
      color: 'accent',
    },
    {
      icon: Users,
      title: 'Community Learning',
      description: 'Connect with learners worldwide and share your achievements',
      color: 'warning',
    },
  ];

  const stats = [
    { label: 'Active Learners', value: '50K+' },
    { label: 'Courses Available', value: '1,200+' },
    { label: 'Success Rate', value: '94%' },
    { label: 'Countries', value: '120+' },
  ];

  const testimonials = [
    {
      name: 'Sarah Johnson',
      role: 'Software Developer',
      content: 'LEARNnow transformed my career. The personalized learning paths helped me master React in just 3 months!',
      avatar: 'SJ',
      rating: 5,
    },
    {
      name: 'Michael Chen',
      role: 'Data Scientist',
      content: 'The interactive quizzes and real-time feedback made learning Python enjoyable and effective.',
      avatar: 'MC',
      rating: 5,
    },
    {
      name: 'Emily Rodriguez',
      role: 'UX Designer',
      content: 'Amazing platform! The progress tracking keeps me motivated and the community is incredibly supportive.',
      avatar: 'ER',
      rating: 5,
    },
  ];

  return (
    <div className="min-h-screen bg-surface">
      {/* Hero Section */}
      <section className="relative overflow-hidden bg-gradient-to-br from-primary-50 via-surface to-secondary-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20 lg:py-32">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
            <motion.div
              initial={{ opacity: 0, x: -50 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ duration: 0.8 }}
            >
              <h1 className="text-4xl lg:text-6xl font-bold text-neutral-900 mb-6">
                Transform Your
                <span className="text-transparent bg-clip-text bg-gradient-to-r from-primary-500 to-secondary-500">
                  {' '}Learning Journey
                </span>
              </h1>
              <p className="text-xl text-neutral-600 mb-8 leading-relaxed">
                Discover personalized learning paths, interactive quizzes, and AI-powered recommendations 
                that adapt to your unique learning style and goals.
              </p>
              <div className="flex flex-col sm:flex-row gap-4">
                {isAuthenticated ? (
                  <Link to="/dashboard">
                    <Button size="xl" className="w-full sm:w-auto">
                      Go to Dashboard
                      <ArrowRight className="ml-2 h-5 w-5" />
                    </Button>
                  </Link>
                ) : (
                  <>
                    <Link to="/register">
                      <Button size="xl" className="w-full sm:w-auto">
                        Start Learning Free
                        <ArrowRight className="ml-2 h-5 w-5" />
                      </Button>
                    </Link>
                    <Link to="/login">
                      <Button variant="outline" size="xl" className="w-full sm:w-auto">
                        Sign In
                      </Button>
                    </Link>
                  </>
                )}
              </div>
            </motion.div>

            <motion.div
              initial={{ opacity: 0, x: 50 }}
              animate={{ opacity: 1, x: 0 }}
              transition={{ duration: 0.8, delay: 0.2 }}
              className="relative"
            >
              <div className="relative z-10">
                <Card className="p-8 bg-white/80 backdrop-blur-sm border-0 shadow-large">
                  <div className="flex items-center space-x-4 mb-6">
                    <div className="w-12 h-12 bg-gradient-to-br from-primary-500 to-secondary-500 rounded-full flex items-center justify-center">
                      <Play className="h-6 w-6 text-white" />
                    </div>
                    <div>
                      <h3 className="font-semibold text-neutral-900">JavaScript Mastery</h3>
                      <p className="text-sm text-neutral-600">85% Complete</p>
                    </div>
                  </div>
                  <div className="w-full bg-neutral-200 rounded-full h-3 mb-4">
                    <div className="bg-gradient-to-r from-primary-500 to-secondary-500 h-3 rounded-full w-4/5" />
                  </div>
                  <div className="flex items-center justify-between text-sm">
                    <span className="text-neutral-600">12 of 15 lessons</span>
                    <span className="text-accent-600 font-medium">2h left</span>
                  </div>
                </Card>
              </div>
              
              {/* Floating elements */}
              <motion.div
                animate={{ y: [-10, 10, -10] }}
                transition={{ duration: 4, repeat: Infinity }}
                className="absolute -top-4 -right-4 w-20 h-20 bg-gradient-to-br from-accent-400 to-accent-600 rounded-full opacity-20"
              />
              <motion.div
                animate={{ y: [10, -10, 10] }}
                transition={{ duration: 3, repeat: Infinity }}
                className="absolute -bottom-8 -left-8 w-32 h-32 bg-gradient-to-br from-warning-400 to-warning-600 rounded-full opacity-10"
              />
            </motion.div>
          </div>
        </div>
      </section>

      {/* Stats Section */}
      <section className="py-16 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
            className="grid grid-cols-2 lg:grid-cols-4 gap-8"
          >
            {stats.map((stat, index) => (
              <div key={stat.label} className="text-center">
                <motion.div
                  initial={{ scale: 0 }}
                  whileInView={{ scale: 1 }}
                  transition={{ duration: 0.5, delay: index * 0.1 }}
                  viewport={{ once: true }}
                  className="text-3xl lg:text-4xl font-bold text-primary-600 mb-2"
                >
                  {stat.value}
                </motion.div>
                <p className="text-neutral-600 font-medium">{stat.label}</p>
              </div>
            ))}
          </motion.div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-20 bg-surface">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
            className="text-center mb-16"
          >
            <h2 className="text-3xl lg:text-4xl font-bold text-neutral-900 mb-4">
              Why Choose LEARNnow?
            </h2>
            <p className="text-xl text-neutral-600 max-w-3xl mx-auto">
              Experience the future of learning with our cutting-edge features designed 
              to accelerate your growth and maximize retention.
            </p>
          </motion.div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {features.map((feature, index) => {
              const Icon = feature.icon;
              return (
                <motion.div
                  key={feature.title}
                  initial={{ opacity: 0, y: 20 }}
                  whileInView={{ opacity: 1, y: 0 }}
                  transition={{ duration: 0.6, delay: index * 0.1 }}
                  viewport={{ once: true }}
                >
                  <Card hover className="text-center h-full">
                    <div className={`w-16 h-16 bg-${feature.color}-100 rounded-xl flex items-center justify-center mx-auto mb-6`}>
                      <Icon className={`h-8 w-8 text-${feature.color}-600`} />
                    </div>
                    <h3 className="text-xl font-semibold text-neutral-900 mb-4">
                      {feature.title}
                    </h3>
                    <p className="text-neutral-600">
                      {feature.description}
                    </p>
                  </Card>
                </motion.div>
              );
            })}
          </div>
        </div>
      </section>

      {/* Testimonials Section */}
      <section className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
            className="text-center mb-16"
          >
            <h2 className="text-3xl lg:text-4xl font-bold text-neutral-900 mb-4">
              What Our Learners Say
            </h2>
            <p className="text-xl text-neutral-600">
              Join thousands of successful learners who transformed their careers
            </p>
          </motion.div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {testimonials.map((testimonial, index) => (
              <motion.div
                key={testimonial.name}
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.6, delay: index * 0.1 }}
                viewport={{ once: true }}
              >
                <Card className="h-full">
                  <div className="flex items-center space-x-1 mb-4">
                    {[...Array(testimonial.rating)].map((_, i) => (
                      <Star key={i} className="h-5 w-5 text-warning-400 fill-current" />
                    ))}
                  </div>
                  <p className="text-neutral-700 mb-6 italic">
                    "{testimonial.content}"
                  </p>
                  <div className="flex items-center space-x-3">
                    <div className="w-12 h-12 bg-gradient-to-br from-primary-500 to-secondary-500 rounded-full flex items-center justify-center">
                      <span className="text-white font-semibold">
                        {testimonial.avatar}
                      </span>
                    </div>
                    <div>
                      <p className="font-semibold text-neutral-900">
                        {testimonial.name}
                      </p>
                      <p className="text-sm text-neutral-600">
                        {testimonial.role}
                      </p>
                    </div>
                  </div>
                </Card>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 bg-gradient-to-r from-primary-500 to-secondary-500">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
          >
            <h2 className="text-3xl lg:text-4xl font-bold text-white mb-6">
              Ready to Start Your Learning Journey?
            </h2>
            <p className="text-xl text-white/90 mb-8">
              Join thousands of learners who are already transforming their careers with LEARNnow
            </p>
            {!isAuthenticated && (
              <div className="flex flex-col sm:flex-row gap-4 justify-center">
                <Link to="/register">
                  <Button 
                    size="xl" 
                    className="bg-white text-primary-600 hover:bg-neutral-50 w-full sm:w-auto"
                  >
                    Get Started Free
                    <ArrowRight className="ml-2 h-5 w-5" />
                  </Button>
                </Link>
                <Link to="/courses">
                  <Button 
                    variant="outline" 
                    size="xl" 
                    className="border-white text-white hover:bg-white hover:text-primary-600 w-full sm:w-auto"
                  >
                    Explore Courses
                  </Button>
                </Link>
              </div>
            )}
          </motion.div>
        </div>
      </section>
    </div>
  );
};

export default Home;