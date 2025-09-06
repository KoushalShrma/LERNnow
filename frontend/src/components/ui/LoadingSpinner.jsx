import { motion } from 'framer-motion';
import { clsx } from 'clsx';

const LoadingSpinner = ({ size = 'md', className }) => {
  const sizes = {
    sm: 'h-4 w-4',
    md: 'h-8 w-8',
    lg: 'h-12 w-12',
    xl: 'h-16 w-16',
  };

  return (
    <div className={clsx('flex items-center justify-center', className)}>
      <motion.div
        className={clsx('border-2 border-primary-200 border-t-primary-500 rounded-full', sizes[size])}
        animate={{ rotate: 360 }}
        transition={{ duration: 1, repeat: Infinity, ease: 'linear' }}
      />
    </div>
  );
};

export default LoadingSpinner;