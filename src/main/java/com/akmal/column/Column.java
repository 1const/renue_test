package com.akmal.column;

public class Column implements Comparable<Column> {
    private long rowOffset;
    private int lineLength;
    private String value;

    public Column(long rowOffset, int lineLength, String value) {
        this.rowOffset = rowOffset;
        this.lineLength = lineLength;
        this.value = value;
    }

    @Override
    public int compareTo(Column o) {
        int cmp = this.value.toUpperCase().compareTo(o.value.toUpperCase());
        return cmp == 0 ? Long.compare(this.rowOffset, o.rowOffset) : cmp;
    }

    public long getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(long rowOffset) {
        this.rowOffset = rowOffset;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}