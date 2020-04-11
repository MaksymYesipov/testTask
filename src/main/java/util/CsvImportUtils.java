package util;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import consts.TaskConstants;

public class CsvImportUtils {

    public static CSVReader getCsvReader(String fileName) {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        CSVParser parser = new CSVParserBuilder().withSeparator(TaskConstants.CSV_SEPARATOR).build();

        return new CSVReaderBuilder(new InputStreamReader(stream)).withCSVParser(parser).build();
    }

    private CsvImportUtils() {
        throw new UnsupportedOperationException();
    }
}
