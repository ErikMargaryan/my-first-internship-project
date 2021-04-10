//package com.myproject.myprojec.csvUpload.csvHelper;
//
//import com.myproject.myprojec.persistence.entity.GenreEntity;
//import org.apache.commons.csv.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class GenreHelper {
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
//    public  static List<GenreEntity> csvToGenreEntity(InputStream is) {
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//            List<GenreEntity> genreEntityList = new ArrayList<>();
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//            for (CSVRecord csvRecord : csvRecords) {
//                GenreEntity genreEntity = new GenreEntity(
//                        csvRecord.get("Genre")
//                );
//                genreEntityList.add(genreEntity);
//            }
//            return genreEntityList;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//    }
//    public static ByteArrayInputStream genreEntityToCSV(List<GenreEntity> genreEntityList) {
//        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//            for (GenreEntity genreEntity : genreEntityList) {
//                List<String> data = Arrays.asList(
//                        genreEntity.getGenres()
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
