import 'package:flutter/material.dart';
import '../../core/theme.dart';

class CustomCard extends StatelessWidget {
  final Widget child;
  final VoidCallback? onTap;
  final EdgeInsetsGeometry padding;
  final double? width;
  final double? height;
  final Border? border;
  final Color? color;
  final List<BoxShadow>? shadows;
  final LinearGradient? gradient;

  const CustomCard({
    super.key,
    required this.child,
    this.onTap,
    this.padding = const EdgeInsets.all(16),
    this.width,
    this.height,
    this.border,
    this.color,
    this.shadows,
    this.gradient,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      width: width,
      height: height,
      decoration: BoxDecoration(
        color: gradient == null ? (color ?? AppColors.surface) : null,
        gradient: gradient,
        borderRadius: BorderRadius.circular(16),
        border: border ?? Border.all(color: AppColors.border, width: 1),
        boxShadow: shadows ?? [AppShadows.soft],
      ),
      child: Material(
        color: Colors.transparent,
        child: InkWell(
          onTap: onTap,
          borderRadius: BorderRadius.circular(16),
          child: Padding(
            padding: padding,
            child: child,
          ),
        ),
      ),
    );
  }
}
