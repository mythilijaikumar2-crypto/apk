import 'package:flutter/material.dart';
import '../../core/theme.dart';

class AppShell extends StatelessWidget {
  final Widget body;
  final String title;
  final int currentBottomIndex;
  final ValueChanged<int>? onBottomTabChanged;
  final Widget? floatingActionButton;

  const AppShell({
    Key? key,
    required this.body,
    required this.title,
    this.currentBottomIndex = 0,
    this.onBottomTabChanged,
    this.floatingActionButton,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        // Desktop Frame Simulator: Wrap screen in center container if screen is wide
        if (constraints.maxWidth > 500) {
          return Scaffold(
            backgroundColor: Colors.blueGrey.shade900,
            body: Center(
              child: Container(
                width: 420,
                height: 840,
                margin: const EdgeInsets.symmetric(vertical: 24),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(40),
                  border: Border.all(color: Colors.black87, width: 12),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black.withValues(alpha: 0.5),
                      blurRadius: 30,
                      offset: const Offset(0, 15),
                    )
                  ],
                ),
                clipBehavior: Clip.antiAlias,
                child: _buildInnerScaffold(context),
              ),
            ),
          );
        }

        // Native mobile layout
        return _buildInnerScaffold(context);
      },
    );
  }

  Widget _buildInnerScaffold(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.background,
      appBar: AppBar(
        backgroundColor: AppColors.surface,
        surfaceTintColor: Colors.transparent,
        elevation: 0,
        centerTitle: true,
        title: Text(
          title,
          style: const TextStyle(
            color: AppColors.textDark,
            fontWeight: FontWeight.bold,
            fontSize: 18,
          ),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.notifications_outlined,
                color: AppColors.textDark),
            onPressed: () {
              ScaffoldMessenger.of(context).showSnackBar(
                const SnackBar(
                    content: Text("Notifications panel coming soon!")),
              );
            },
          ),
          const Padding(
            padding: EdgeInsets.only(right: 16, left: 4),
            child: CircleAvatar(
              radius: 16,
              backgroundColor: AppColors.primary,
              child: Text(
                "A",
                style: TextStyle(
                    color: Colors.white,
                    fontSize: 13,
                    fontWeight: FontWeight.bold),
              ),
            ),
          ),
        ],
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(1.0),
          child: Container(color: AppColors.border, height: 1.0),
        ),
      ),
      drawer: Drawer(
        backgroundColor: AppColors.surface,
        child: Column(
          children: [
            UserAccountsDrawerHeader(
              decoration: const BoxDecoration(
                gradient: AppColors.primaryGradient,
              ),
              currentAccountPicture: const CircleAvatar(
                backgroundColor: Colors.white,
                child: Text(
                  "A",
                  style: TextStyle(
                      fontSize: 24.0,
                      color: AppColors.primary,
                      fontWeight: FontWeight.bold),
                ),
              ),
              accountName: const Text(
                "Agent Rajesh Kumar",
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
              ),
              accountEmail: const Text("rajesh.kumar@fintechagent.com"),
            ),
            Expanded(
              child: ListView(
                padding: EdgeInsets.zero,
                children: [
                  _buildDrawerItem(Icons.home_outlined, "Home / Dashboard"),
                  _buildDrawerItem(
                      Icons.account_balance_outlined, "Banking Services"),
                  _buildDrawerItem(
                      Icons.payment_outlined, "Payments & Transfer"),
                  _buildDrawerItem(
                      Icons.receipt_long_outlined, "Bill Payments"),
                  _buildDrawerItem(
                      Icons.directions_bus_filled_outlined, "Travel Booking"),
                  _buildDrawerItem(
                      Icons.security_outlined, "Insurance Products"),
                  _buildDrawerItem(
                      Icons.gavel_outlined, "E-Governance Services"),
                  const Divider(color: AppColors.border),
                  _buildDrawerItem(Icons.settings_outlined, "Settings"),
                  _buildDrawerItem(
                      Icons.help_outline_outlined, "Help & Support"),
                  _buildDrawerItem(Icons.logout_rounded, "Logout",
                      textColor: AppColors.error, iconColor: AppColors.error),
                ],
              ),
            ),
          ],
        ),
      ),
      body: body,
      floatingActionButton: floatingActionButton,
      bottomNavigationBar: onBottomTabChanged != null
          ? BottomNavigationBar(
              currentIndex: currentBottomIndex,
              onTap: onBottomTabChanged,
              type: BottomNavigationBarType.fixed,
              backgroundColor: AppColors.surface,
              selectedItemColor: AppColors.primary,
              unselectedItemColor: AppColors.textMuted,
              selectedLabelStyle:
                  const TextStyle(fontWeight: FontWeight.w600, fontSize: 12),
              unselectedLabelStyle: const TextStyle(fontSize: 12),
              items: const [
                BottomNavigationBarItem(
                  icon: Icon(Icons.dashboard_outlined),
                  activeIcon: Icon(Icons.dashboard),
                  label: "Dashboard",
                ),
                BottomNavigationBarItem(
                  icon: Icon(Icons.history_outlined),
                  activeIcon: Icon(Icons.history),
                  label: "History",
                ),
                BottomNavigationBarItem(
                  icon: Icon(Icons.analytics_outlined),
                  activeIcon: Icon(Icons.analytics),
                  label: "Analytics",
                ),
                BottomNavigationBarItem(
                  icon: Icon(Icons.person_outline_rounded),
                  activeIcon: Icon(Icons.person),
                  label: "Profile",
                ),
              ],
            )
          : null,
    );
  }

  Widget _buildDrawerItem(
    IconData icon,
    String title, {
    Color textColor = AppColors.textDark,
    Color iconColor = AppColors.primary,
  }) {
    return ListTile(
      leading: Icon(icon, color: iconColor),
      title: Text(
        title,
        style: TextStyle(color: textColor, fontWeight: FontWeight.w500),
      ),
      onTap: () {},
    );
  }
}
