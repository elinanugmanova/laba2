import java.util.ArrayList;

public class TablePrint {
    public static void printRow(StringBuilder table, ArrayList<String> values) {
        table.append("|");
        for (String value : values) {
            table.append(String.format(" %-16s |", value));
        }
        table.append("\n");
    }

    public static void printHorizontalLine(StringBuilder table, int columns) {
        table.append("+");
        for (int i = 0; i < columns; i++) {
            table.append("-".repeat(18) + "+");
        }
        table.append("\n");
    }
}
