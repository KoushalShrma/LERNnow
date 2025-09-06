# LEARNnow Frontend

A modern, responsive frontend for the LEARNnow learning platform built with React, Vite, and TailwindCSS.

## Features

- **Modern UI/UX**: Jupiter Federal Bank-inspired design with premium aesthetics
- **Responsive Design**: Optimized for all device sizes
- **Real-time API Integration**: Seamlessly connected to Spring Boot backend
- **Authentication**: JWT-based secure authentication
- **Interactive Components**: Smooth animations and micro-interactions
- **Performance Optimized**: Code splitting, lazy loading, and caching

## Tech Stack

- **React 18** - Modern React with hooks and concurrent features
- **Vite** - Fast build tool and development server
- **TailwindCSS** - Utility-first CSS framework
- **React Router** - Client-side routing
- **React Query** - Server state management and caching
- **Framer Motion** - Smooth animations and transitions
- **Axios** - HTTP client for API calls
- **React Hook Form** - Form handling and validation

## Getting Started

### Prerequisites

- Node.js 18+ 
- npm 9+

### Installation

1. Install dependencies:
```bash
npm install
```

2. Copy environment variables:
```bash
cp .env.example .env.local
```

3. Update environment variables in `.env.local`:
```env
VITE_API_BASE_URL=http://localhost:8080
```

### Development

Start the development server:
```bash
npm run dev
```

The application will be available at `http://localhost:3000`

### Building for Production

Build the application:
```bash
npm run build
```

Preview the production build:
```bash
npm run preview
```

## Project Structure

```
src/
├── components/          # Reusable UI components
│   ├── ui/             # Basic UI components (Button, Input, etc.)
│   └── layout/         # Layout components (Header, Footer)
├── pages/              # Page components
│   ├── auth/           # Authentication pages
│   └── ...             # Other pages
├── hooks/              # Custom React hooks
├── lib/                # Utility libraries and API client
├── styles/             # Global styles and Tailwind config
└── main.jsx           # Application entry point
```

## API Integration

The frontend communicates with the Spring Boot backend through a centralized API client (`src/lib/api.js`) that handles:

- Authentication tokens
- Request/response interceptors
- Error handling
- API endpoint organization

## Design System

### Color Palette (Jupiter-inspired)

- **Primary**: Spacefire Orange (#FC644F)
- **Secondary**: Teal Accent (#008080)
- **Accent**: Light Green (#A8D5BA)
- **Warning**: Yellow Ochre (#D8B451)
- **Neutral**: Warm Beige (#FAF6F0) to Dark Grey (#4A4A4A)
- **Background**: Pure White (#FFFFFF)

### Typography

- **Font Family**: Inter (Google Fonts)
- **Font Weights**: 300-900
- **Line Heights**: 150% for body, 120% for headings

### Components

All components follow consistent design principles:
- 8px spacing system
- Consistent border radius (8px, 12px, 16px)
- Subtle shadows and hover effects
- Smooth transitions (200ms duration)

## Performance Optimizations

- **Code Splitting**: Automatic route-based code splitting
- **Lazy Loading**: Components loaded on demand
- **Image Optimization**: Responsive images with proper sizing
- **Caching**: React Query for server state caching
- **Bundle Analysis**: Optimized chunk sizes

## Security

- **JWT Token Management**: Secure token storage and automatic refresh
- **Input Validation**: Client-side validation with server-side verification
- **XSS Protection**: Sanitized user inputs
- **HTTPS**: Enforced in production

## Browser Support

- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

## Contributing

1. Follow the existing code style and conventions
2. Use TypeScript for new components when possible
3. Write meaningful commit messages
4. Test your changes thoroughly
5. Update documentation as needed

## Deployment

The frontend can be deployed to any static hosting service:

- **Vercel** (recommended)
- **Netlify**
- **AWS S3 + CloudFront**
- **GitHub Pages**

Make sure to update the `VITE_API_BASE_URL` environment variable to point to your production backend.

## License

This project is licensed under the MIT License.