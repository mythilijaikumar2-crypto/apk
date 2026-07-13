import 'package:flutter/material.dart';
import '../../core/theme.dart';

enum BadgeStatus { success, pending, error, info }

class StatusBadge extends StatelessWidget {
  final String label;
  final BadgeStatus status;

  const StatusBadge({
    Key? key,
    required this.label,
    required this.status,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Color getBgColor() {
      switch (status) {
        case BadgeStatus.success:
          return AppColors.success.withValues(alpha: 0.1);
        case BadgeStatus.pending:
          return AppColors.warning.withValues(alpha: 0.1);
        case BadgeStatus.error:
          return AppColors.error.withValues(alpha: 0.1);
        case BadgeStatus.info:
          return AppColors.info.withValues(alpha: 0.1);
      }
    }

    Color getTextColor() {
      switch (status) {
        case BadgeStatus.success:
          return AppColors.success;
        case BadgeStatus.pending:
          return AppColors.warning;
        case BadgeStatus.error:
          return AppColors.error;
        case BadgeStatus.info:
          return AppColors.info;
      }
    }

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
      decoration: BoxDecoration(
        color: getBgColor(),
        borderRadius: BorderRadius.circular(20),
      ),
      child: Text(
        label,
        style: TextStyle(
          color: getTextColor(),
          fontSize: 12,
          fontWeight: FontWeight.w600,
        ),
      ),
    );
  }
}
