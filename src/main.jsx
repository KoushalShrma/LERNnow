@@ .. @@
 import ReactDOM from 'react-dom/client';
 import { BrowserRouter } from 'react-router-dom';
 import { ClerkProvider } from '@clerk/clerk-react';
-import { AuthProvider } from './hooks/useAuth';
+import { AuthProvider } from './hooks/useAuth.jsx';
 import App from './App.jsx';
 import './index.css';