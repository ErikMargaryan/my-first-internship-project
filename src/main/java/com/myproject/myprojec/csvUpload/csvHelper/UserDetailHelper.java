//package com.myproject.myprojec.csvUpload.csvHelper;
//
//import com.myproject.myprojec.persistence.entity.UserDetailEntity;
//import org.apache.commons.csv.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class UserDetailHelper {
//
//    public static String TYPE = "text/csv";
//
//    public static boolean hasCSVFormat(MultipartFile file) {
//        System.out.println(file.getContentType());
//        if (TYPE.equals(file.getContentType())
//                || file.getContentType().equals("application/vnd.ms-excel")) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public static List<UserDetailEntity> csvToUserDetailEntity(InputStream is) {
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//            List<UserDetailEntity> userDetailEntityList = new ArrayList<>();
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//            for (CSVRecord csvRecord : csvRecords) {
//                UserDetailEntity userDetailEntity = new UserDetailEntity(
//                        Long.parseLong(csvRecord.get("id")),
//                        Integer.parseInt(csvRecord.get("age")),
//                        csvRecord.get("address")
//                );
//                userDetailEntityList.add(userDetailEntity);
//            }
//            return userDetailEntityList;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//    }
//
//    public static ByteArrayInputStream genreEntityToCSV(List<UserDetailEntity> userDetailEntityList) {
//        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//            for (UserDetailEntity userDetailEntity : userDetailEntityList) {
//                List<String> data = Arrays.asList(
//                        String.valueOf(userDetailEntity.getId()),
//                        String.valueOf(userDetailEntity.getAge()),
//                        userDetailEntity.getAddress()
//                );
//                csvPrinter.printRecord(data);
//            }
//            csvPrinter.flush();
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
//        }
//    }
//}
