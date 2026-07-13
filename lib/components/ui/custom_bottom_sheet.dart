import 'package:flutter/material.dart';
import '../../core/theme.dart';

class CustomBottomSheet extends StatelessWidget {
  final String title;
  final Widget content;
  final bool showHeader;

  const CustomBottomSheet({
    super.key,
    required this.title,
    required this.content,
    this.showHeader = true,
  });

  static Future<T?> show<T>({
    required BuildContext context,
    required String title,
    required Widget content,
    bool showHeader = true,
  }) {
    return showModalBottomSheet<T>(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (context) {
        return CustomBottomSheet(
          title: title,
          content: content,
          showHeader: showHeader,
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(24),
          topRight: Radius.circular(24),
        ),
      ),
      padding: EdgeInsets.only(
        bottom: MediaQuery.of(context).viewInsets.bottom,
      ),
      child: SafeArea(
        top: false,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const SizedBox(height: 8),
            // Drag Handle Indicator
            Container(
              width: 36,
              height: 4,
              decoration: BoxDecoration(
                color: Colors.grey.shade300,
                borderRadius: BorderRadius.circular(2),
              ),
            ),
            const SizedBox(height: 12),
            if (showHeader) ...[
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      title,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                        color: AppColors.textDark,
                      ),
                    ),
                    IconButton(
                      icon: const Icon(Icons.close_rounded, color: AppColors.textMuted),
                      onPressed: () => Navigator.of(context).pop(),
                      padding: EdgeInsets.zero,
                      constraints: const BoxConstraints(),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 8),
              const Divider(color: AppColors.border, height: 1),
            ],
            Flexible(
              child: SingleChildScrollView(
                padding: const EdgeInsets.all(20),
                child: Material(
                  color: Colors.transparent,
                  child: content,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
