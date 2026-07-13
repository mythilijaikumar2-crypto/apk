import 'package:flutter/material.dart';
import '../../core/theme.dart';

enum ButtonVariant { primary, secondary, outline, ghost }

class CustomButton extends StatelessWidget {
  final String text;
  final VoidCallback? onPressed;
  final ButtonVariant variant;
  final bool isLoading;
  final IconData? icon;
  final IconData? trailingIcon;
  final double? width;
  final double height;

  const CustomButton({
    super.key,
    required this.text,
    this.onPressed,
    this.variant = ButtonVariant.primary,
    this.isLoading = false,
    this.icon,
    this.trailingIcon,
    this.width,
    this.height = 48,
  });

  @override
  Widget build(BuildContext context) {
    final bool isDisabled = onPressed == null || isLoading;
    
    Color getBgColor() {
      if (isDisabled) return Colors.grey.shade200;
      switch (variant) {
        case ButtonVariant.primary:
          return AppColors.primary;
        case ButtonVariant.secondary:
          return AppColors.accent;
        case ButtonVariant.outline:
        case ButtonVariant.ghost:
          return Colors.transparent;
      }
    }

    Color getTextColor() {
      if (isDisabled) return Colors.grey.shade500;
      switch (variant) {
        case ButtonVariant.primary:
        case ButtonVariant.secondary:
          return Colors.white;
        case ButtonVariant.outline:
          return AppColors.primary;
        case ButtonVariant.ghost:
          return AppColors.textDark;
      }
    }

    Border? getBorder() {
      if (variant == ButtonVariant.outline) {
        return Border.all(
          color: isDisabled ? Colors.grey.shade300 : AppColors.primary,
          width: 1.5,
        );
      }
      return null;
    }

    return SizedBox(
      width: width ?? double.infinity,
      height: height,
      child: Container(
        decoration: BoxDecoration(
          color: getBgColor(),
          border: getBorder(),
          borderRadius: BorderRadius.circular(12),
          boxShadow: (variant == ButtonVariant.primary || variant == ButtonVariant.secondary) && !isDisabled
              ? [AppShadows.soft]
              : null,
        ),
        child: Material(
          color: Colors.transparent,
          child: InkWell(
            onTap: isDisabled ? null : onPressed,
            borderRadius: BorderRadius.circular(12),
            child: Center(
              child: isLoading
                  ? SizedBox(
                      width: 20,
                      height: 20,
                      child: CircularProgressIndicator(
                        strokeWidth: 2,
                        valueColor: AlwaysStoppedAnimation<Color>(getTextColor()),
                      ),
                    )
                  : Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        if (icon != null) ...[
                          Icon(icon, size: 18, color: getTextColor()),
                          const SizedBox(width: 8),
                        ],
                        Text(
                          text,
                          style: TextStyle(
                            color: getTextColor(),
                            fontSize: 16,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                        if (trailingIcon != null) ...[
                          const SizedBox(width: 8),
                          Icon(trailingIcon, size: 18, color: getTextColor()),
                        ],
                      ],
                    ),
            ),
          ),
        ),
      ),
    );
  }
}
