import 'package:flutter/material.dart';
import '../components/layout/app_shell.dart';
import '../components/ui/custom_button.dart';
import '../components/ui/custom_input.dart';
import '../components/ui/custom_dropdown.dart';
import '../components/ui/custom_modal.dart';
import '../components/ui/custom_toast.dart';
import '../components/ui/custom_card.dart';
import '../components/ui/custom_tabs.dart';
import '../components/ui/custom_table.dart';
import '../components/ui/custom_skeleton.dart';
import '../components/ui/custom_bottom_sheet.dart';
import '../components/ui/status_badge.dart';
import '../components/ui/stepper_form.dart';
import '../core/theme.dart';

class DemoScreen extends StatefulWidget {
  const DemoScreen({Key? key}) : super(key: key);

  @override
  State<DemoScreen> createState() => _DemoScreenState();
}

class _DemoScreenState extends State<DemoScreen> {
  int _activeTabIndex = 0;
  int _stepperIndex = 0;
  bool _isSkeletonLoading = false;
  String? _selectedCity = 'Chennai';

  final TextEditingController _nameController = TextEditingController(text: "Antigravity Dev");
  final TextEditingController _passController = TextEditingController(text: "secure_pass_123");

  @override
  Widget build(BuildContext context) {
    return AppShell(
      title: "Design System Showcase",
      currentBottomIndex: 0,
      onBottomTabChanged: (index) {
        CustomToast.show(context, message: "Tab $index clicked", type: ToastType.info);
      },
      body: SingleChildScrollView(
        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Hero Welcome Section
            CustomCard(
              gradient: AppColors.primaryGradient,
              child: const Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "Fintech Super-App UI Kit",
                    style: TextStyle(color: Colors.white, fontSize: 22, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 6),
                  Text(
                    "Phase 1: Foundation. Standardized premium light-mode elements built with native Dart widgets.",
                    style: TextStyle(color: Colors.white70, fontSize: 13),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 24),

            // Typography Section
            _buildSectionHeader("Typography"),
            Text("Display Large Heading", style: Theme.of(context).textTheme.displayLarge?.copyWith(fontSize: 28)),
            const SizedBox(height: 4),
            Text("Title Large Subheading", style: Theme.of(context).textTheme.titleLarge),
            const SizedBox(height: 4),
            Text("Body Large Standard Paragraph", style: Theme.of(context).textTheme.bodyLarge),
            const SizedBox(height: 4),
            Text("Body Medium Slate Muted Text", style: Theme.of(context).textTheme.bodyMedium),
            const SizedBox(height: 24),

            // Custom Buttons Section
            _buildSectionHeader("Custom Buttons"),
            Wrap(
              spacing: 12,
              runSpacing: 12,
              children: [
                CustomButton(
                  text: "Primary Action",
                  width: 170,
                  onPressed: () {
                    CustomToast.show(context, message: "Primary button pressed!", type: ToastType.success);
                  },
                ),
                CustomButton(
                  text: "Secondary Action",
                  variant: ButtonVariant.secondary,
                  width: 170,
                  onPressed: () {
                    CustomToast.show(context, message: "Secondary button pressed!", type: ToastType.info);
                  },
                ),
                CustomButton(
                  text: "Outline Button",
                  variant: ButtonVariant.outline,
                  width: 170,
                  onPressed: () {},
                ),
                CustomButton(
                  text: "Ghost Button",
                  variant: ButtonVariant.ghost,
                  width: 170,
                  onPressed: () {},
                ),
                const CustomButton(
                  text: "Loading Button",
                  width: 170,
                  isLoading: true,
                ),
              ],
            ),
            const SizedBox(height: 24),

            // Form Inputs Section
            _buildSectionHeader("Form Inputs & Validation"),
            CustomInput(
              label: "Merchant Full Name",
              hint: "Enter your full name",
              controller: _nameController,
              prefixIcon: Icons.person_outline,
            ),
            const SizedBox(height: 16),
            CustomInput(
              label: "Security PIN / Password",
              hint: "Enter password",
              controller: _passController,
              isPassword: true,
              prefixIcon: Icons.lock_outline,
            ),
            const SizedBox(height: 16),
            CustomDropdown<String>(
              label: "Operating Region (State/City)",
              value: _selectedCity,
              prefixIcon: Icons.map_outlined,
              items: const [
                DropdownMenuItem(value: 'Chennai', child: Text('Chennai (Tamil Nadu)')),
                DropdownMenuItem(value: 'Mumbai', child: Text('Mumbai (Maharashtra)')),
                DropdownMenuItem(value: 'Delhi', child: Text('New Delhi (NCR)')),
              ],
              onChanged: (val) {
                setState(() {
                  _selectedCity = val;
                });
              },
            ),
            const SizedBox(height: 24),

            // Status Badges & Toasts Section
            _buildSectionHeader("Status Badges & Overlays"),
            const Wrap(
              spacing: 8,
              runSpacing: 8,
              children: [
                StatusBadge(label: "Success", status: BadgeStatus.success),
                StatusBadge(label: "Pending Verification", status: BadgeStatus.pending),
                StatusBadge(label: "Transaction Failed", status: BadgeStatus.error),
                StatusBadge(label: "Processing", status: BadgeStatus.info),
              ],
            ),
            const SizedBox(height: 16),
            Row(
              children: [
                Expanded(
                  child: CustomButton(
                    text: "Trigger Toast",
                    variant: ButtonVariant.outline,
                    onPressed: () {
                      CustomToast.show(
                        context,
                        message: "Secure PIN setup successfully completed!",
                        type: ToastType.success,
                      );
                    },
                  ),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: CustomButton(
                    text: "Open Modal",
                    variant: ButtonVariant.outline,
                    onPressed: () {
                      CustomModal.show(
                        context: context,
                        title: "Terms of Registration",
                        content: const Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              "By registering as a banking correspondent partner, you agree to comply with RBI directives, maintain digital records securely, and handle agent terminal logins with biometric secondary authorization.",
                              style: TextStyle(fontSize: 14, color: AppColors.textDark),
                            ),
                          ],
                        ),
                        actions: [
                          CustomButton(
                            text: "I Accept",
                            width: 100,
                            height: 36,
                            onPressed: () => Navigator.pop(context),
                          ),
                        ],
                      );
                    },
                  ),
                ),
              ],
            ),
            const SizedBox(height: 12),
            CustomButton(
              text: "Show Bottom Sheet Drawer",
              variant: ButtonVariant.outline,
              onPressed: () {
                CustomBottomSheet.show(
                  context: context,
                  title: "Quick Wallet Cash-In",
                  content: Column(
                    children: [
                      const Text(
                        "You can load money into your agent wallet via Aadhaar Enabled Payment System (AEPS) or instant bank account transfer.",
                        style: TextStyle(fontSize: 14, color: AppColors.textMuted),
                      ),
                      const SizedBox(height: 20),
                      CustomButton(
                        text: "Add ₹5,000",
                        onPressed: () => Navigator.pop(context),
                      ),
                    ],
                  ),
                );
              },
            ),
            const SizedBox(height: 24),

            // Tab Navigation Section
            _buildSectionHeader("Sliding Tabs Indicator"),
            CustomTabs(
              tabs: const ["AEPS Transfer", "Micro ATM", "DMT Wallet"],
              selectedIndex: _activeTabIndex,
              onChanged: (idx) {
                setState(() {
                  _activeTabIndex = idx;
                });
              },
            ),
            const SizedBox(height: 12),
            Text("Selected Sub-Module index: $_activeTabIndex", style: Theme.of(context).textTheme.bodyMedium),
            const SizedBox(height: 24),

            // Dynamic Skeleton Toggle
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                _buildSectionHeader("Skeletons & Latency"),
                Switch(
                  value: _isSkeletonLoading,
                  onChanged: (val) {
                    setState(() {
                      _isSkeletonLoading = val;
                    });
                  },
                ),
              ],
            ),
            _isSkeletonLoading
                ? const SkeletonList(itemCount: 2)
                : const Column(
                    children: [
                      Card(
                        child: ListTile(
                          leading: CircleAvatar(backgroundColor: Colors.blueAccent, child: Icon(Icons.compare_arrows_rounded, color: Colors.white)),
                          title: Text("Wallet Load Successful"),
                          subtitle: Text("Ref: AEPS-829038203 | Status: Settled"),
                          trailing: Text("+₹10,500", style: TextStyle(color: Colors.green, fontWeight: FontWeight.bold)),
                        ),
                      ),
                    ],
                  ),
            const SizedBox(height: 24),

            // Tabular Data Section
            _buildSectionHeader("Data Table"),
            CustomTable(
              columns: const [
                TableColumnDef(label: "Service", width: 1.5),
                TableColumnDef(label: "Agent ID", width: 1.2),
                TableColumnDef(label: "Status", width: 1.0, alignment: Alignment.center),
                TableColumnDef(label: "Amount", width: 1.3, alignment: Alignment.centerRight),
              ],
              rows: [
                [
                  const Text("AEPS Cashout", style: TextStyle(fontWeight: FontWeight.bold)),
                  const Text("AG-7281"),
                  const StatusBadge(label: "Success", status: BadgeStatus.success),
                  const Text("₹2,500.00", style: TextStyle(fontWeight: FontWeight.bold)),
                ],
                [
                  const Text("Electricity Bill", style: TextStyle(fontWeight: FontWeight.bold)),
                  const Text("AG-3920"),
                  const StatusBadge(label: "Failed", status: BadgeStatus.error),
                  const Text("₹1,430.00", style: TextStyle(fontWeight: FontWeight.bold)),
                ],
              ],
            ),
            const SizedBox(height: 24),

            // Multi-step Wizard
            _buildSectionHeader("Form Stepper (Timeline Wizard)"),
            Container(
              height: 220,
              decoration: BoxDecoration(
                border: Border.all(color: AppColors.border),
                borderRadius: BorderRadius.circular(16),
              ),
              clipBehavior: Clip.antiAlias,
              child: StepperForm(
                steps: [
                  StepperStep(
                    title: "Business info",
                    content: const Text("Enter agent trade name, retail shop registration code and commercial address details."),
                  ),
                  StepperStep(
                    title: "Upload KYC",
                    content: const Text("Drag-and-drop or select PDF of PAN Card, Aadhar Card, and Shop GSTIN document."),
                  ),
                  StepperStep(
                    title: "Authorize",
                    content: const Text("Confirm agreement to partner compliance terms. Tap submit to initialize verification queue."),
                  ),
                ],
                currentStep: _stepperIndex,
                onStepChanged: (idx) {
                  setState(() {
                    _stepperIndex = idx;
                  });
                },
                onComplete: () {
                  CustomToast.show(context, message: "Application submitted!", type: ToastType.success);
                  setState(() {
                    _stepperIndex = 0;
                  });
                },
              ),
            ),
            const SizedBox(height: 40),
          ],
        ),
      ),
    );
  }

  Widget _buildSectionHeader(String title) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 12, top: 4),
      child: Row(
        children: [
          Container(
            width: 4,
            height: 18,
            decoration: BoxDecoration(
              color: AppColors.primary,
              borderRadius: BorderRadius.circular(2),
            ),
          ),
          const SizedBox(width: 8),
          Text(
            title,
            style: const TextStyle(
              fontSize: 16,
              fontWeight: FontWeight.bold,
              color: AppColors.textDark,
            ),
          ),
        ],
      ),
    );
  }
}
