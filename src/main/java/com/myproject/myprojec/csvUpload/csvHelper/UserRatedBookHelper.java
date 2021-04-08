package com.myproject.myprojec.csvUpload.csvHelper;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRatedBookHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        System.out.println(file.getContentType());
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public static List<UserRatedBookEntity> csvToUserRatedBookEntity(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<UserRatedBookEntity> userRatedBookEntityList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                UserRatedBookEntity userRatedBookEntity = new UserRatedBookEntity(
                        Long.parseLong(csvRecord.get("id")),
                        Integer.parseInt(csvRecord.get("bookRating"))
                );
                userRatedBookEntityList.add(userRatedBookEntity);
            }
            return userRatedBookEntityList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream userRatedBookEntityToCSV(List<UserRatedBookEntity> userRatedBookEntityList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (UserRatedBookEntity userRatedBookEntity : userRatedBookEntityList) {
                List<String> data = Arrays.asList(
                        String.valueOf(userRatedBookEntity.getId()),
                        String.valueOf(userRatedBookEntity.getBookRating())
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
