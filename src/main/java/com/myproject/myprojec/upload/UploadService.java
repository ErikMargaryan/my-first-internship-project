//package com.myproject.myprojec.upload;
//
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.toMap;
//
//@Service
//public class UploadService {
//
//    private final UploadUtil uploadUtil;
//
//    public UploadService(UploadUtil uploadUtil) {
//        this.uploadUtil = uploadUtil;
//    }
//
//    public List<Map<String, String>> upload(MultipartFile file) throws Exception {
//
//        Path tempDir = Files.createTempDirectory("");
//
//        File tempFile = tempDir.resolve(Objects.requireNonNull(file.getOriginalFilename())).toFile();
//
//        file.transferTo(tempFile);
//
//        Workbook workbook = WorkbookFactory.create(tempFile);
//
//        Sheet sheet = workbook.getSheetAt(0);
//
//        Supplier<Stream<Row>> rowStreamSupplier = uploadUtil.getRowStreamSupplier(sheet);
//
//        Row headerRow = rowStreamSupplier.get().findFirst().get();
//
//        List<String> headerCells = uploadUtil.getStream(headerRow)
//                .map(Cell::getNumericCellValue)
//                .map(String::valueOf)
//                .collect(Collectors.toList());
//
//        int colCount = headerCells.size();
//
//        return rowStreamSupplier.get()
//                .skip(1)
//                .map(row -> {
//
//                    List<String> cellList;
//                    cellList = uploadUtil.getStream(row)
//                            .map(Cell::getStringCellValue)
//                            .collect(Collectors.toList());
//
//                    return uploadUtil.cellIteratorSupplier(colCount)
//                            .get()
//                            .collect(toMap(headerCells::get, cellList::get));
//                })
//                .collect(Collectors.toList());
//    }
//}
