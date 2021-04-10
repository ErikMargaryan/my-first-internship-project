//package com.myproject.myprojec.csvUpload.csvHelper;
//
//import com.myproject.myprojec.persistence.entity.AuthorEntity;
//import org.apache.commons.csv.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class AuthorHelper {
//
//    public static String TYPE = "text/csv";
//
//    public static boolean hasCSVFormat(MultipartFile file) {
//        System.out.println(file.getContentType());
//        return (TYPE.equals(file.getContentType())
//                || file.getContentType().equals("application/vnd.ms-excel"));
//    }
//
//    public static List<AuthorEntity> csvToAuthorEntity(InputStream is) {
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
//            List<AuthorEntity> authorEntityList = new ArrayList<>();
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//            for (CSVRecord csvRecord : csvRecords) {
//                AuthorEntity authorEntity = new AuthorEntity(
//                        csvRecord.get("Book-Author")
//                );
//                authorEntityList.add(authorEntity);
//            }
//            return authorEntityList;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//    }
//
//    public static ByteArrayInputStream authorEntityToCSV(List<AuthorEntity> authorEntityList) {
//        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//
//            for (AuthorEntity authorEntity : authorEntityList) {
//                List<String> data = Arrays.asList(
//                        String.valueOf(authorEntity.getId()),
//                        authorEntity.getName()
//                );
//                csvPrinter.printRecord(data);
//            }
//            csvPrinter.flush();
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
//        }
//    }
//
//}
