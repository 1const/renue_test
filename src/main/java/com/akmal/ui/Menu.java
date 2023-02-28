package com.akmal.ui;

import com.akmal.column.Column;
import com.akmal.searcher.CSVSearcher;
import com.akmal.utils.FileReader;
import com.akmal.utils.Number;
import com.akmal.utils.RowPrinter;

import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Menu {
    public final static String QUIT_PHRASE = "!quit";
    private final CSVSearcher csvSearcher;
    private final FileReader fileReader;

    public Menu(CSVSearcher rowSearcher, FileReader fileReader) {
        this.csvSearcher = rowSearcher;
        this.fileReader = fileReader;
    }

    public void start() {
        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите строку: ");
            input = scanner.nextLine();
            if (input.equals(QUIT_PHRASE)) break;

            long start = System.currentTimeMillis();
            SortedSet<Column> columnRows = csvSearcher.findRows(input);

            if (columnRows.size() != 0 && Number.isNumeric(columnRows.first().getValue())) {
                SortedSet<Column> columnNumericSortedRows = new TreeSet<>((o1, o2) -> {
                    Double o1Double = Double.parseDouble(o1.getValue());
                    Double o2Double = Double.parseDouble(o2.getValue());
                    int cmp = o1Double.compareTo(o2Double);
                    return cmp == 0 ? Long.compare(o1.getRowOffset(), o2.getRowOffset()) : cmp;
                });
                columnNumericSortedRows.addAll(columnRows);
                columnRows = columnNumericSortedRows;
            }

            long elapsed = System.currentTimeMillis() - start;

            List<String> rows = fileReader.readRowsByOffsetAndLength(columnRows);
            RowPrinter.printRows(rows, columnRows);

            String msg = "Количество найденных строк: " + columnRows.size()
                    + ". Время, затраченное на поиск: " + elapsed + "мс";
            System.out.println(msg);

        }
    }
}
