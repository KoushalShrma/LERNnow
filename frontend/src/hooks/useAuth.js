import { useState, useEffect, createContext, useContext } from 'react';
import { authAPI, tokenManager } from '@/lib/api';
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
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    initializeAuth();
  }, []);

  const initializeAuth = async () => {
    try {
      const token = tokenManager.getToken();
      const savedUser = tokenManager.getUser();

      if (token && savedUser) {
        // Verify token is still valid
        const response = await authAPI.getCurrentUser();
        setUser(response.data);
        setIsAuthenticated(true);
      }
    } catch (error) {
      // Token is invalid, clear storage
      tokenManager.clear();
      setUser(null);
      setIsAuthenticated(false);
    } finally {
      setLoading(false);
    }
  };

  const login = async (credentials) => {
    try {
      setLoading(true);
      const response = await authAPI.login(credentials);
      const { token, userId, email, name } = response.data;

      const userData = { id: userId, email, name };
      
      tokenManager.setToken(token);
      tokenManager.setUser(userData);
      setUser(userData);
      setIsAuthenticated(true);

      toast.success(`Welcome back, ${name}!`);
      return { success: true, user: userData };
    } catch (error) {
      const message = error.response?.data?.message || 'Login failed';
      toast.error(message);
      return { success: false, error: message };
    } finally {
      setLoading(false);
    }
  };

  const register = async (userData) => {
    try {
      setLoading(true);
      await authAPI.register(userData);
      
      // Auto-login after successful registration
      const loginResult = await login({
        email: userData.email,
        password: userData.password
      });

      if (loginResult.success) {
        toast.success('Account created successfully!');
      }
      
      return loginResult;
    } catch (error) {
      const message = error.response?.data || 'Registration failed';
      toast.error(message);
      return { success: false, error: message };
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    tokenManager.clear();
    setUser(null);
    setIsAuthenticated(false);
    toast.success('Logged out successfully');
  };

  const updateUser = (updatedUser) => {
    setUser(updatedUser);
    tokenManager.setUser(updatedUser);
  };

  const value = {
    user,
    loading,
    isAuthenticated,
    login,
    register,
    logout,
    updateUser,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};