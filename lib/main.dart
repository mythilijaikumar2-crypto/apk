import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'core/theme.dart';
import 'screens/demo_screen.dart';

void main() {
  runApp(
    MultiProvider(
      providers: [
        // Register providers here as we implement state management
        ChangeNotifierProvider(create: (_) => AppState()),
      ],
      child: const FintechSuperApp(),
    ),
  );
}

class AppState extends ChangeNotifier {
  // Global app state holder (auth state, preferences, theme details)
  bool _isAuthenticated = false;
  String _locale = 'en';

  bool get isAuthenticated => _isAuthenticated;
  String get locale => _locale;

  void setAuthenticated(bool value) {
    _isAuthenticated = value;
    notifyListeners();
  }

  void setLocale(String languageCode) {
    _locale = languageCode;
    notifyListeners();
  }
}

class FintechSuperApp extends StatelessWidget {
  const FintechSuperApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Fintech Super App',
      theme: AppTheme.lightTheme,
      debugShowCheckedModeBanner: false,
      initialRoute: '/',
      routes: {
        '/': (context) => const DemoScreen(),
        // Additional routes will be registered here as screens are added
      },
    );
  }
}
