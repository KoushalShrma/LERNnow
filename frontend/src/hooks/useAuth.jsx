import { useState, useEffect, createContext, useContext } from 'react';
import { useUser, useAuth as useClerkAuth } from '@clerk/clerk-react';
import { tokenManager } from '@/lib/api';
import toast from 'react-hot-toast';

const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const { user: clerkUser, isLoaded, isSignedIn } = useUser();
  const { signOut } = useClerkAuth();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(!isLoaded);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    if (isLoaded) {
      setLoading(false);
      if (isSignedIn && clerkUser) {
        const userData = {
          id: clerkUser.id,
          email: clerkUser.primaryEmailAddress?.emailAddress,
          name: clerkUser.fullName || clerkUser.firstName || 'User',
          firstName: clerkUser.firstName,
          lastName: clerkUser.lastName,
          imageUrl: clerkUser.imageUrl
        };
        setUser(userData);
        setIsAuthenticated(true);
        tokenManager.setUser(userData);
      } else {
        setUser(null);
        setIsAuthenticated(false);
        tokenManager.removeUser();
      }
    }
  }, [isLoaded, isSignedIn, clerkUser]);

  const logout = async () => {
    try {
      await signOut();
      tokenManager.clear();
      setUser(null);
      setIsAuthenticated(false);
      toast.success('Logged out successfully');
    } catch (error) {
      toast.error('Error signing out');
    }
  };

  const updateUser = (updatedUser) => {
    setUser(updatedUser);
    tokenManager.setUser(updatedUser);
  };

  const value = {
    user,
    loading,
    isAuthenticated,
    logout,
    updateUser,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};