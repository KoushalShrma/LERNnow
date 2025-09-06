import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';
import { SignedIn, SignedOut } from '@clerk/clerk-react';
import { AuthProvider } from '@/hooks/useAuth.jsx';
import Layout from '@/components/layout/Layout';

// Pages
import AuthPage from '@/pages/auth/AuthPage';
import Dashboard from '@/pages/Dashboard';
import CoursePage from '@/pages/CoursePage';

// Create a client
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 2,
      staleTime: 5 * 60 * 1000, // 5 minutes
      cacheTime: 10 * 60 * 1000, // 10 minutes
    },
  },
});

// Protected Route Component
const ProtectedRoute = ({ children }) => {
  return (
    <SignedIn>
      {children}
    </SignedIn>
  );
};

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <AuthProvider>
        <Router>
          <Routes>
            {/* Auth route */}
            <Route path="/auth" element={<AuthPage />} />
            
            {/* Protected routes with layout */}
            <Route path="/" element={<Layout />}>
              <Route 
                index 
                element={
                  <>
                    <SignedIn>
                      <Navigate to="/dashboard" replace />
                    </SignedIn>
                    <SignedOut>
                      <Navigate to="/auth" replace />
                    </SignedOut>
                  </>
                } 
              />
              
              <Route 
                path="dashboard" 
                element={
                  <ProtectedRoute>
                    <Dashboard />
                  </ProtectedRoute>
                } 
              />
              
              <Route 
                path="course/:courseId" 
                element={
                  <ProtectedRoute>
                    <CoursePage />
                  </ProtectedRoute>
                } 
              />
              
              {/* Catch all route */}
              <Route path="*" element={<Navigate to="/" replace />} />
            </Route>
          </Routes>
        </Router>
      </AuthProvider>
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  );
}

export default App;