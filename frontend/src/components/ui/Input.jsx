import { forwardRef } from 'react';
import { clsx } from 'clsx';

const Input = forwardRef(({ 
  label,
  error,
  helperText,
  className,
  type = 'text',
  ...props 
}, ref) => {
  const inputClasses = clsx(
    'w-full px-3 py-2 border rounded-lg transition-colors duration-200',
    'focus:outline-none focus:ring-2 focus:ring-primary-500 focus:border-transparent',
    'placeholder-neutral-400',
    error 
      ? 'border-red-300 bg-red-50 text-red-900 focus:ring-red-500' 
      : 'border-neutral-300 bg-white text-neutral-900 hover:border-neutral-400',
    className
  );

  return (
    <div className="space-y-1">
      {label && (
        <label className="block text-sm font-medium text-neutral-700">
          {label}
        </label>
      )}
      <input
        ref={ref}
        type={type}
        className={inputClasses}
        {...props}
      />
      {error && (
        <p className="text-sm text-red-600">{error}</p>
      )}
      {helperText && !error && (
        <p className="text-sm text-neutral-500">{helperText}</p>
      )}
    </div>
  );
});

Input.displayName = 'Input';

export default Input;