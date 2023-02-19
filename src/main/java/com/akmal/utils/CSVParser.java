package com.akmal.utils;

import com.akmal.column.Column;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CSVParser {

    private final char separator = ',';
    private final char quote = '"';

    public List<Column> readFileColumn(String filePath, int columnNumber) throws IOException {
        Scanner scanner = new Scanner(Path.of(filePath), StandardCharsets.UTF_8);
        List<Column> columns = new ArrayList<>();

        if (!scanner.hasNextLine()) {
            scanner.close();
            return Collections.emptyList();
        }

        String firstLine = scanner.nextLine();
        List<String> rowColumns = parseLine(firstLine);

        if (rowColumns.size() < columnNumber) {
            throw new IllegalArgumentException(String.format("Ошибка! Номер столбца (%d) превышает число столбцов таблицы (%d)",
                    columnNumber, rowColumns.size()));
        }

        String firstRowColumn = parseLine(firstLine).get(columnNumber - 1);
        long currentOffset = firstLine.getBytes(StandardCharsets.UTF_8).length + 1;
        columns.add(new Column(0, firstLine.getBytes(StandardCharsets.UTF_8).length, firstRowColumn));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int lineLength = line.getBytes(StandardCharsets.UTF_8).length;
            String column = parseLine(line).get(columnNumber - 1);
            columns.add(new Column(currentOffset, lineLength, column));

            currentOffset += lineLength + 1;
        }

        scanner.close();

        return columns;
    }

    public List<String> parseLine(String csvLine) {
        List<String> result = new ArrayList<>();
        char[] chars = csvLine.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int tempIdx;

            if (chars[i] == '"') {
                tempIdx = parseQuotedColumn(chars, i + 1);
                result.add(csvLine.substring(i + 1, tempIdx));
            } else {
                tempIdx = parseNumericColumn(chars, i);
                result.add(csvLine.substring(i, tempIdx + 1));
            }
            i = tempIdx + 1;
        }
        return result;
    }

    private int parseQuotedColumn(char[] chars, int idx) {
        char prevChar = ' ';

        while (chars[idx] != quote || prevChar == '\\') {
            prevChar = chars[idx];
            idx++;
        }
        return idx;
    }

    private int parseNumericColumn(char[] chars, int idx) {
        while (idx < chars.length && chars[idx] != separator) idx++;
        return --idx;
    }
}
