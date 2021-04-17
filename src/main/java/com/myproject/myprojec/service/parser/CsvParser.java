package com.myproject.myprojec.service.parser;

import org.springframework.stereotype.Component;

@Component
public class CsvParser<T> {

//    private final Logger logger = LoggerFactory.getLogger(CsvParser.class);
//
//    public List<T> parse(File csvFile, Class<T> classT) {
//        try (Reader reader = new FileReader(csvFile)) {
//            return new CsvToBeanBuilder<T>(reader)
//                    .withType(classT)
//                    .withIgnoreEmptyLine(true)
//                    .withThrowExceptions(false)
//                    .build()
//                    .parse();
//        } catch (IOException e) {
//            logger.warn(e.getMessage(), classT.getName());
//        } catch (IllegalStateException ex) {
//            logger.warn(ex.getMessage());
//        }catch (Exception exx) {
//            System.out.println(exx.getMessage());
//        }
//        return List.of();
//    }

}