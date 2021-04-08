package com.myproject.myprojec.csvUpload.csvHelper;

import com.myproject.myprojec.persistence.entity.BookEntity;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        System.out.println(file.getContentType());
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public static List<BookEntity> csvToBookEntity(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<BookEntity> bookEntityList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                BookEntity bookEntity = new BookEntity(
                        csvRecord.get("ISBN"),
                        csvRecord.get("title"),
                        csvRecord.get("Publisher"),
                        Integer.parseInt(csvRecord.get("Year-Of-Publication"))
                );
                bookEntityList.add(bookEntity);
            }
            return bookEntityList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

        public static ByteArrayInputStream bookEntityToCSV(List<BookEntity> bookEntityList) {
            final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

            try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                 CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
                for (BookEntity bookEntity : bookEntityList) {
                    List<String> data = Arrays.asList(
                            String.valueOf(bookEntity.getId()),
                            bookEntity.getIsbn(),
                            bookEntity.getTitle(),
                            bookEntity.getPublisher(),
                            String.valueOf(bookEntity.getYearOfPublication())
                    );
                    csvPrinter.printRecord(data);
                }
                csvPrinter.flush();
                return new ByteArrayInputStream(out.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
            }
        }
}
