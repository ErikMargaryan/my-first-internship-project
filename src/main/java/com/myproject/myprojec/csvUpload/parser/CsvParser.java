package com.myproject.myprojec.csvUpload.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;


@Slf4j
@Component
public class CsvParser<T> {


    public List<T> parse(File csvFile, Class<T> classT) {
        try (Reader reader = new FileReader(csvFile)) {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(classT)
                    .withIgnoreEmptyLine(true)
                    .withThrowExceptions(false)
                    .build()
                    .parse();
        } catch (IOException e) {
            log.warn(e.getMessage(), classT.getName());
        } catch (IllegalStateException ex) {
            log.warn(ex.getMessage());
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
        }
        return List.of();
    }

}