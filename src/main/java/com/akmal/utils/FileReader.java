package com.akmal.utils;

import com.akmal.column.Column;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileReader {
    private final File filePath;

    public FileReader(File filePath) {
        this.filePath = filePath;
    }

    public List<String> readRowsByOffsetAndLength(Collection<Column> columnCollection) {
        List<String> result = new ArrayList<>();

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r")) {

            for (Column column : columnCollection) {
                randomAccessFile.seek(column.getRowOffset());
                byte[] bytes = new byte[column.getLineLength()];
                randomAccessFile.read(bytes, 0, bytes.length);
                result.add(new String(bytes, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
