package com.myproject.myprojec.service.control;

import com.myproject.myprojec.service.parser.CsvParser;
import com.myproject.myprojec.service.util.CsvHelper;
import javassist.NotFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CsvControl<T> {

    @Value("${csv.upload.dir}")
    private String uploadDir;
    private final int SIZE_OF_FILE_IN_MB = 5;


    public List<List<T>> getEntitiesFromCsv(MultipartFile csvFile, Class<T> type) throws NotFoundException {
        String pathname = String.join(File.separator, uploadDir, UUID.randomUUID().toString(), csvFile.getOriginalFilename());
        File parentCsv = new File(pathname);
        List<List<T>> list = List.of();
        CsvHelper fileHelper = new CsvHelper();
        CsvParser<T> csvParser = new CsvParser<>();
        if (parentCsv.mkdirs()) {
            try {
                csvFile.transferTo(parentCsv);
                list = fileHelper.splitFile(parentCsv, SIZE_OF_FILE_IN_MB)
                        .stream()
                        .map(file -> csvParser.parse(file, type))
                        .filter(ts -> !ts.isEmpty())
                        .collect(Collectors.toList());
                FileUtils.deleteDirectory(parentCsv.getParentFile());
            } catch (Exception e) {
                throw new NotFoundException(String.format("Could not create the file %s", csvFile.getOriginalFilename()));
            }
        }
        return list;
    }

}
