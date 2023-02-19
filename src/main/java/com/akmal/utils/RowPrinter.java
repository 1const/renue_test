package com.akmal.utils;

import com.akmal.column.Column;

import java.util.Collection;
import java.util.List;

public class RowPrinter {

    public static void printRows(List<String> rows, Collection<Column> columnRows) {
        int i = 0;

        for (Column column : columnRows) {
            System.out.println("\"" + column.getValue() + "\"" + "[" + rows.get(i) + "]");
            i++;
        }
    }
}
