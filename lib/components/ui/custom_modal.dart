import 'package:flutter/material.dart';
import '../../core/theme.dart';

class CustomModal extends StatelessWidget {
  final String title;
  final Widget content;
  final List<Widget>? actions;
  final bool showCloseButton;

  const CustomModal({
    super.key,
    required this.title,
    required this.content,
    this.actions,
    this.showCloseButton = true,
  });

  static Future<T?> show<T>({
    required BuildContext context,
    required String title,
    required Widget content,
    List<Widget>? actions,
    bool showCloseButton = true,
    bool barrierDismissible = true,
  }) {
    return showGeneralDialog<T>(
      context: context,
      barrierLabel: "CustomModalBarrier",
      barrierDismissible: barrierDismissible,
      barrierColor: Colors.black54,
      transitionDuration: const Duration(milliseconds: 300),
      pageBuilder: (context, animation1, animation2) {
        return Align(
          alignment: Alignment.center,
          child: CustomModal(
            title: title,
            content: content,
            actions: actions,
            showCloseButton: showCloseButton,
          ),
        );
      },
      transitionBuilder: (context, anim, anim2, child) {
        final double scale = 0.8 + (anim.value * 0.2);
        final double opacity = anim.value;
        return Opacity(
          opacity: opacity,
          child: Transform.scale(
            scale: scale,
            child: child,
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Dialog(
      backgroundColor: Colors.transparent,
      elevation: 0,
      insetPadding: const EdgeInsets.symmetric(horizontal: 24, vertical: 40),
      child: Container(
        decoration: BoxDecoration(
          color: AppColors.surface,
          borderRadius: BorderRadius.circular(20),
          boxShadow: [AppShadows.medium],
          border: Border.all(color: AppColors.border, width: 1),
        ),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Modal Header
            Padding(
              padding: const EdgeInsets.only(left: 20, right: 12, top: 16, bottom: 8),
              child: Row(
                children: [
                  Expanded(
                    child: Text(
                      title,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                        color: AppColors.textDark,
                        decoration: TextDecoration.none,
                      ),
                    ),
                  ),
                  if (showCloseButton)
                    IconButton(
                      icon: const Icon(Icons.close_rounded, color: AppColors.textMuted),
                      onPressed: () => Navigator.of(context).pop(),
                    ),
                ],
              ),
            ),
            const Divider(color: AppColors.border, height: 1),
            // Modal Body
            Flexible(
              child: SingleChildScrollView(
                padding: const EdgeInsets.all(20),
                child: Material(
                  color: Colors.transparent,
                  child: content,
                ),
              ),
            ),
            if (actions != null && actions!.isNotEmpty) ...[
              const Divider(color: AppColors.border, height: 1),
              // Modal Actions
              Padding(
                padding: const EdgeInsets.all(16),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: actions!
                      .map((action) => Padding(
                            padding: const EdgeInsets.only(left: 8),
                            child: action,
                          ))
                      .toList(),
                ),
              ),
            ],
          ],
        ),
      ),
    );
  }
}
