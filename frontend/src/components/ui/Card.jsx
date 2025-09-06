import { forwardRef } from 'react';
import { motion } from 'framer-motion';
import { clsx } from 'clsx';

const Card = forwardRef(({ 
  children, 
  className,
  hover = false,
  padding = 'md',
  ...props 
}, ref) => {
  const paddingClasses = {
    none: '',
    sm: 'p-4',
    md: 'p-6',
    lg: 'p-8',
  };

  const classes = clsx(
    'bg-white rounded-xl border border-neutral-200 shadow-soft',
    paddingClasses[padding],
    hover && 'hover:shadow-medium transition-shadow duration-200',
    className
  );

  const MotionDiv = hover ? motion.div : 'div';
  const motionProps = hover ? {
    whileHover: { y: -2 },
    transition: { duration: 0.2 }
  } : {};

  return (
    <MotionDiv
      ref={ref}
      className={classes}
      {...motionProps}
      {...props}
    >
      {children}
    </MotionDiv>
  );
});

Card.displayName = 'Card';

export default Card;