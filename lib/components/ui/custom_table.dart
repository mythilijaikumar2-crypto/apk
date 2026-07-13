import 'package:flutter/material.dart';
import '../../core/theme.dart';

class TableColumnDef {
  final String label;
  final double? width;
  final AlignmentGeometry alignment;

  const TableColumnDef({
    required this.label,
    this.width,
    this.alignment = Alignment.centerLeft,
  });
}

class CustomTable extends StatelessWidget {
  final List<TableColumnDef> columns;
  final List<List<Widget>> rows;
  final Widget? emptyState;

  const CustomTable({
    super.key,
    required this.columns,
    required this.rows,
    this.emptyState,
  });

  @override
  Widget build(BuildContext context) {
    if (rows.isEmpty) {
      return emptyState ??
          const Center(
            child: Padding(
              padding: EdgeInsets.all(32),
              child: Text("No records found."),
            ),
          );
    }

    return Container(
      decoration: BoxDecoration(
        color: AppColors.surface,
        borderRadius: BorderRadius.circular(12),
        border: Border.all(color: AppColors.border, width: 1),
      ),
      clipBehavior: Clip.antiAlias,
      child: Column(
        children: [
          // Header
          Container(
            color: Colors.grey.shade50,
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
            child: Row(
              children: columns.map((col) {
                final cellWidget = Text(
                  col.label,
                  style: const TextStyle(
                    color: AppColors.textMuted,
                    fontSize: 13,
                    fontWeight: FontWeight.w600,
                  ),
                );
                return Expanded(
                  flex: col.width != null ? (col.width! * 10).toInt() : 10,
                  child: Align(
                    alignment: col.alignment,
                    child: cellWidget,
                  ),
                );
              }).toList(),
            ),
          ),
          const Divider(color: AppColors.border, height: 1),
          // Rows
          ListView.separated(
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
            itemCount: rows.length,
            separatorBuilder: (context, index) => const Divider(color: AppColors.border, height: 1),
            itemBuilder: (context, rowIndex) {
              final rowCells = rows[rowIndex];
              return Container(
                padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 14),
                child: Row(
                  children: List.generate(rowCells.length, (colIndex) {
                    final colDef = columns[colIndex];
                    return Expanded(
                      flex: colDef.width != null ? (colDef.width! * 10).toInt() : 10,
                      child: Align(
                        alignment: colDef.alignment,
                        child: rowCells[colIndex],
                      ),
                    );
                  }),
                ),
              );
            },
          ),
        ],
      ),
    );
  }
}
