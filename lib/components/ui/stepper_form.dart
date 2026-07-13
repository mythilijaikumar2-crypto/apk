import 'package:flutter/material.dart';
import '../../core/theme.dart';
import 'custom_button.dart';

class StepperStep {
  final String title;
  final Widget content;

  StepperStep({required this.title, required this.content});
}

class StepperForm extends StatefulWidget {
  final List<StepperStep> steps;
  final int currentStep;
  final ValueChanged<int> onStepChanged;
  final VoidCallback onComplete;
  final String completeButtonText;

  const StepperForm({
    super.key,
    required this.steps,
    required this.currentStep,
    required this.onStepChanged,
    required this.onComplete,
    this.completeButtonText = "Submit",
  });

  @override
  State<StepperForm> createState() => _StepperFormState();
}

class _StepperFormState extends State<StepperForm> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // Stepper Progress Header
        Padding(
          padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 8),
          child: Row(
            children: List.generate(
              widget.steps.length * 2 - 1,
              (index) {
                if (index.isOdd) {
                  // Connection Line
                  final int stepIndexBefore = index ~/ 2;
                  final bool isPassed = widget.currentStep > stepIndexBefore;
                  return Expanded(
                    child: Container(
                      height: 2,
                      color: isPassed ? AppColors.primary : AppColors.border,
                    ),
                  );
                } else {
                  // Step Circle
                  final int stepIndex = index ~/ 2;
                  final bool isActive = widget.currentStep == stepIndex;
                  final bool isCompleted = widget.currentStep > stepIndex;
                  return Column(
                    children: [
                      Container(
                        width: 28,
                        height: 28,
                        decoration: BoxDecoration(
                          color: isCompleted
                              ? AppColors.primary
                              : (isActive ? AppColors.surface : AppColors.background),
                          shape: BoxShape.circle,
                          border: Border.all(
                            color: (isActive || isCompleted)
                                ? AppColors.primary
                                : AppColors.border,
                            width: 2,
                          ),
                          boxShadow: isActive ? [AppShadows.soft] : null,
                        ),
                        child: Center(
                          child: isCompleted
                              ? const Icon(Icons.check, size: 16, color: Colors.white)
                              : Text(
                                  "${stepIndex + 1}",
                                  style: TextStyle(
                                    fontSize: 12,
                                    fontWeight: FontWeight.bold,
                                    color: isActive
                                        ? AppColors.primary
                                        : AppColors.textMuted,
                                  ),
                                ),
                        ),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        widget.steps[stepIndex].title,
                        style: TextStyle(
                          fontSize: 10,
                          fontWeight: isActive ? FontWeight.w600 : FontWeight.normal,
                          color: isActive ? AppColors.primary : AppColors.textMuted,
                        ),
                      ),
                    ],
                  );
                }
              },
            ),
          ),
        ),
        const Divider(color: AppColors.border, height: 1),
        // Content Area
        Expanded(
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(16),
            child: widget.steps[widget.currentStep].content,
          ),
        ),
        const Divider(color: AppColors.border, height: 1),
        // Navigation Buttons
        Padding(
          padding: const EdgeInsets.all(16),
          child: Row(
            children: [
              if (widget.currentStep > 0)
                Expanded(
                  child: CustomButton(
                    text: "Back",
                    variant: ButtonVariant.outline,
                    onPressed: () => widget.onStepChanged(widget.currentStep - 1),
                  ),
                )
              else
                const Spacer(),
              const SizedBox(width: 16),
              Expanded(
                child: CustomButton(
                  text: widget.currentStep == widget.steps.length - 1
                      ? widget.completeButtonText
                      : "Continue",
                  onPressed: () {
                    if (widget.currentStep == widget.steps.length - 1) {
                      widget.onComplete();
                    } else {
                      widget.onStepChanged(widget.currentStep + 1);
                    }
                  },
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
