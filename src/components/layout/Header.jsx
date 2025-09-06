@@ .. @@
 import React from 'react';
 import { Link } from 'react-router-dom';
-import { useAuth } from '../../hooks/useAuth';
+import { useAuth } from '../../hooks/useAuth.jsx';
 import { SignedIn, SignedOut, SignInButton, SignUpButton, UserButton } from '@clerk/clerk-react';