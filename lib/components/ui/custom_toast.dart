import 'package:flutter/material.dart';
import '../../core/theme.dart';

enum ToastType { success, error, warning, info }

class CustomToast {
  static void show(
    BuildContext context, {
    required String message,
    ToastType type = ToastType.info,
    Duration duration = const Duration(seconds: 3),
  }) {
    IconData getIcon() {
      switch (type) {
        case ToastType.success:
          return Icons.check_circle_rounded;
        case ToastType.error:
          return Icons.error_rounded;
        case ToastType.warning:
          return Icons.warning_rounded;
        case ToastType.info:
          return Icons.info_rounded;
      }
    }

    Color getColor() {
      switch (type) {
        case ToastType.success:
          return AppColors.success;
        case ToastType.error:
          return AppColors.error;
        case ToastType.warning:
          return AppColors.warning;
        case ToastType.info:
          return AppColors.info;
      }
    }

    final snackBar = SnackBar(
      backgroundColor: Colors.transparent,
      elevation: 0,
      behavior: SnackBarBehavior.floating,
      duration: duration,
      padding: EdgeInsets.zero,
      margin: const EdgeInsets.all(16),
      content: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
        decoration: BoxDecoration(
          color: AppColors.surface,
          borderRadius: BorderRadius.circular(12),
          border: Border.all(color: getColor().withValues(alpha: 0.3), width: 1.5),
          boxShadow: [AppShadows.medium],
        ),
        child: Row(
          children: [
            Icon(getIcon(), color: getColor(), size: 22),
            const SizedBox(width: 12),
            Expanded(
              child: Text(
                message,
                style: const TextStyle(
                  color: AppColors.textDark,
                  fontSize: 14,
                  fontWeight: FontWeight.w500,
                ),
              ),
            ),
            IconButton(
              icon: const Icon(Icons.close_rounded, size: 18, color: AppColors.textMuted),
              onPressed: () {
                ScaffoldMessenger.of(context).hideCurrentSnackBar();
              },
              padding: EdgeInsets.zero,
              constraints: const BoxConstraints(),
            ),
          ],
        ),
      ),
    );

    ScaffoldMessenger.of(context).hideCurrentSnackBar();
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }
}
