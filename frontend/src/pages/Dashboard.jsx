import { motion } from 'framer-motion';
import { 
  BookOpen, 
  Trophy, 
  Clock, 
  TrendingUp, 
  Target,
  Play,
  Award,
  Calendar,
  Users,
  Star
} from 'lucide-react';
import { useAuth } from '@/hooks/useAuth';
import { useUserStats, useUserActivity, useRecommendations } from '@/hooks/useApi';
import Card from '@/components/ui/Card';
import Button from '@/components/ui/Button';
import LoadingSpinner from '@/components/ui/LoadingSpinner';

const Dashboard = () => {
  const { user } = useAuth();
  const { data: stats, isLoading: statsLoading } = useUserStats(user?.id);
  const { data: activities, isLoading: activitiesLoading } = useUserActivity(user?.id, 5);
  const { data: recommendations, isLoading: recommendationsLoading } = useRecommendations(user?.id, 3);

  const statCards = [
    {
      title: 'Courses Completed',
      value: stats?.completedTopics || 0,
      total: stats?.totalTopics || 0,
      icon: BookOpen,
      color: 'primary',
      change: '+12%',
    },
    {
      title: 'Average Score',
      value: `${Math.round(stats?.averageScore || 0)}%`,
      icon: Trophy,
      color: 'secondary',
      change: '+5%',
    },
    {
      title: 'Study Time',
      value: `${Math.floor((stats?.totalStudyTimeMinutes || 0) / 60)}h ${(stats?.totalStudyTimeMinutes || 0) % 60}m`,
      icon: Clock,
      color: 'accent',
      change: '+18%',
    },
    {
      title: 'Current Streak',
      value: `${stats?.streakDays || 0} days`,
      icon: TrendingUp,
      color: 'warning',
      change: stats?.streakDays > 0 ? 'Active' : 'Start today!',
    },
  ];

  const getActivityIcon = (type) => {
    switch (type) {
      case 'quiz':
        return Trophy;
      case 'video':
        return Play;
      case 'achievement':
        return Award;
      default:
        return BookOpen;
    }
  };

  const getRecommendationIcon = (type) => {
    switch (type) {
      case 'continue':
        return Play;
      case 'new':
        return Target;
      case 'challenge':
        return Award;
      default:
        return BookOpen;
    }
  };

  if (statsLoading) {
    return (
      <div className="min-h-screen bg-surface flex items-center justify-center">
        <LoadingSpinner size="lg" />
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
          <h1 className="text-3xl font-bold text-neutral-900 mb-2">
            Welcome back, {user?.name}! 👋
          </h1>
          <p className="text-neutral-600">
            Here's your learning progress and recommendations for today.
          </p>
        </motion.div>

        {/* Stats Grid */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
          className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8"
        >
          {statCards.map((stat, index) => {
            const Icon = stat.icon;
            return (
              <Card key={stat.title} hover className="relative overflow-hidden">
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-sm font-medium text-neutral-600 mb-1">
                      {stat.title}
                    </p>
                    <p className="text-2xl font-bold text-neutral-900">
                      {stat.value}
                      {stat.total && (
                        <span className="text-sm font-normal text-neutral-500">
                          /{stat.total}
                        </span>
                      )}
                    </p>
                    <p className={`text-xs font-medium mt-1 ${
                      stat.change.includes('+') ? 'text-accent-600' : 'text-neutral-500'
                    }`}>
                      {stat.change}
                    </p>
                  </div>
                  <div className={`p-3 rounded-lg bg-${stat.color}-100`}>
                    <Icon className={`h-6 w-6 text-${stat.color}-600`} />
                  </div>
                </div>
                {stat.total && (
                  <div className="mt-4">
                    <div className="w-full bg-neutral-200 rounded-full h-2">
                      <div
                        className={`bg-${stat.color}-500 h-2 rounded-full transition-all duration-300`}
                        style={{ width: `${(stat.value / stat.total) * 100}%` }}
                      />
                    </div>
                  </div>
                )}
              </Card>
            );
          })}
        </motion.div>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Recent Activity */}
          <motion.div
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ delay: 0.2 }}
            className="lg:col-span-2"
          >
            <Card>
              <div className="flex items-center justify-between mb-6">
                <h2 className="text-xl font-semibold text-neutral-900">
                  Recent Activity
                </h2>
                <Button variant="ghost" size="sm">
                  View all
                </Button>
              </div>

              {activitiesLoading ? (
                <LoadingSpinner />
              ) : activities && activities.length > 0 ? (
                <div className="space-y-4">
                  {activities.map((activity, index) => {
                    const Icon = getActivityIcon(activity.type);
                    return (
                      <motion.div
                        key={activity.id}
                        initial={{ opacity: 0, y: 10 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ delay: index * 0.1 }}
                        className="flex items-center space-x-4 p-4 bg-neutral-50 rounded-lg hover:bg-neutral-100 transition-colors duration-200"
                      >
                        <div className="p-2 bg-white rounded-lg shadow-soft">
                          <Icon className="h-5 w-5 text-primary-600" />
                        </div>
                        <div className="flex-1">
                          <p className="font-medium text-neutral-900">
                            {activity.title}
                          </p>
                          <p className="text-sm text-neutral-600">
                            {activity.date}
                          </p>
                        </div>
                        {activity.score && (
                          <div className="text-right">
                            <p className="font-semibold text-accent-600">
                              {activity.score}%
                            </p>
                          </div>
                        )}
                        {activity.progress && (
                          <div className="text-right">
                            <p className="font-semibold text-primary-600">
                              {activity.progress}%
                            </p>
                          </div>
                        )}
                      </motion.div>
                    );
                  })}
                </div>
              ) : (
                <div className="text-center py-8">
                  <Calendar className="h-12 w-12 text-neutral-400 mx-auto mb-4" />
                  <p className="text-neutral-600">No recent activity</p>
                  <p className="text-sm text-neutral-500 mt-1">
                    Start learning to see your activity here
                  </p>
                </div>
              )}
            </Card>
          </motion.div>

          {/* Recommendations */}
          <motion.div
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ delay: 0.3 }}
          >
            <Card>
              <h2 className="text-xl font-semibold text-neutral-900 mb-6">
                Recommended for You
              </h2>

              {recommendationsLoading ? (
                <LoadingSpinner />
              ) : recommendations && recommendations.length > 0 ? (
                <div className="space-y-4">
                  {recommendations.map((rec, index) => {
                    const Icon = getRecommendationIcon(rec.type);
                    return (
                      <motion.div
                        key={rec.id}
                        initial={{ opacity: 0, y: 10 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ delay: index * 0.1 }}
                        className="p-4 border border-neutral-200 rounded-lg hover:border-primary-200 hover:bg-primary-50 transition-all duration-200 cursor-pointer"
                      >
                        <div className="flex items-start space-x-3">
                          <div className="p-2 bg-white rounded-lg shadow-soft">
                            <Icon className="h-4 w-4 text-primary-600" />
                          </div>
                          <div className="flex-1">
                            <h3 className="font-medium text-neutral-900 mb-1">
                              {rec.title}
                            </h3>
                            <p className="text-sm text-neutral-600 mb-2">
                              {rec.description}
                            </p>
                            {rec.timeLeft && (
                              <p className="text-xs text-warning-600 font-medium">
                                {rec.timeLeft}
                              </p>
                            )}
                            {rec.duration && (
                              <p className="text-xs text-secondary-600 font-medium">
                                {rec.duration}
                              </p>
                            )}
                            {rec.reward && (
                              <p className="text-xs text-accent-600 font-medium">
                                {rec.reward}
                              </p>
                            )}
                          </div>
                        </div>
                      </motion.div>
                    );
                  })}
                </div>
              ) : (
                <div className="text-center py-8">
                  <Star className="h-12 w-12 text-neutral-400 mx-auto mb-4" />
                  <p className="text-neutral-600">No recommendations yet</p>
                  <p className="text-sm text-neutral-500 mt-1">
                    Complete more courses to get personalized recommendations
                  </p>
                </div>
              )}
            </Card>
          </motion.div>
        </div>

        {/* Quick Actions */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.4 }}
          className="mt-8"
        >
          <Card>
            <h2 className="text-xl font-semibold text-neutral-900 mb-6">
              Quick Actions
            </h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <Button
                variant="outline"
                size="lg"
                className="flex items-center justify-center space-x-2 h-16"
              >
                <BookOpen className="h-5 w-5" />
                <span>Browse Courses</span>
              </Button>
              <Button
                variant="outline"
                size="lg"
                className="flex items-center justify-center space-x-2 h-16"
              >
                <Trophy className="h-5 w-5" />
                <span>Take a Quiz</span>
              </Button>
              <Button
                variant="outline"
                size="lg"
                className="flex items-center justify-center space-x-2 h-16"
              >
                <Users className="h-5 w-5" />
                <span>Join Community</span>
              </Button>
            </div>
          </Card>
        </motion.div>
      </div>
    </div>
  );
};

export default Dashboard;