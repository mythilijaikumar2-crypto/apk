import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:fintech_super_app/components/ui/custom_button.dart';
import 'package:fintech_super_app/components/ui/status_badge.dart';

void main() {
  group('CustomButton Tests', () {
    testWidgets('Renders button text correctly', (WidgetTester tester) async {
      await tester.pumpWidget(
        MaterialApp(
          home: Scaffold(
            body: CustomButton(
              text: 'Register',
              onPressed: () {},
            ),
          ),
        ),
      );

      expect(find.text('Register'), findsOneWidget);
    });

    testWidgets('Triggers callback on tap', (WidgetTester tester) async {
      bool wasTapped = false;
      await tester.pumpWidget(
        MaterialApp(
          home: Scaffold(
            body: CustomButton(
              text: 'Tap Me',
              onPressed: () {
                wasTapped = true;
              },
            ),
          ),
        ),
      );

      await tester.tap(find.text('Tap Me'));
      await tester.pump();

      expect(wasTapped, isTrue);
    });

    testWidgets('Shows loader when isLoading is true', (WidgetTester tester) async {
      await tester.pumpWidget(
        const MaterialApp(
          home: Scaffold(
            body: CustomButton(
              text: 'Submit',
              isLoading: true,
            ),
          ),
        ),
      );

      expect(find.byType(CircularProgressIndicator), findsOneWidget);
      expect(find.text('Submit'), findsNothing);
    });
  });

  group('StatusBadge Tests', () {
    testWidgets('Renders success status badge', (WidgetTester tester) async {
      await tester.pumpWidget(
        const MaterialApp(
          home: Scaffold(
            body: StatusBadge(
              label: 'Approved',
              status: BadgeStatus.success,
            ),
          ),
        ),
      );

      expect(find.text('Approved'), findsOneWidget);
    });
  });
}
