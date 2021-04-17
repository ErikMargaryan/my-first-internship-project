package com.myproject.myprojec.service;

//import com.myproject.myprojec.csvUpload.csvHelper.AuthorHelper;
//import com.myproject.myprojec.csvUpload.csvHelper.BookHelper;

import com.myproject.myprojec.persistence.entity.BookEntity;
import com.myproject.myprojec.persistence.rpository.BookRepository;
import com.myproject.myprojec.service.control.CsvControl;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.BookDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.service.model.csv.Book;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CsvControl<Book> csvControl;

    @Autowired
    public BookService(BookRepository bookRepository, CsvControl<Book> csvControl) {
        this.bookRepository = bookRepository;
        this.csvControl = csvControl;
    }

    public BookDto createBook(BookDto dto) {
        BookEntity bookEntity = new BookEntity();
        BookDto.mapDtoToEntity(dto);

        bookEntity = bookRepository.save(bookEntity);
        return BookDto.mapEntityToDto(bookEntity);
    }


    public BookDto getBook(Long id) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book not found"));
        return BookDto.mapEntityToDto(bookEntity);
    }

    public QueryResponseWrapper<BookDto> getBooks(SearchCriteria searchCriteria) {
        Page<BookEntity> page = bookRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<BookEntity> content = page.getContent();
        List<BookDto> dtos = content.stream().map(BookDto::mapEntityToDto).collect(Collectors.toList());
        return new QueryResponseWrapper<>(page.getTotalElements(), dtos);
    }


    public BookDto updateBookData(Long id, BookDto dto) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book not found"));

        if (dto.getTitle() != null) {
            bookEntity.setTitle(dto.getTitle());
        }
        if (dto.getIsbn() != null) {
            bookEntity.setIsbn(dto.getIsbn());
        }
        if (dto.getPublisher() != null) {
            bookEntity.setPublisher(dto.getPublisher());
        }
        if (dto.getYearOfPublication() != null) {
            bookEntity.setYearOfPublication(dto.getYearOfPublication());
        }

        bookEntity = bookRepository.save(bookEntity);
        return BookDto.mapEntityToDto(bookEntity);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

//    //for CSV upload
//    public void save(MultipartFile file) {
//        try {
//            List<BookEntity> entities = BookHelper.csvToBookEntity(file.getInputStream());
//            bookRepository.saveAll(entities);
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store csv data: " + e.getMessage());
//        }
//    }
//
//    public ByteArrayInputStream load() {
//        List<BookEntity> entities = bookRepository.findAll();
//        ByteArrayInputStream in = BookHelper.bookEntityToCSV(entities);
//        return in;
//    }
//
//    public List<BookEntity> getAllBooks() {
//        return bookRepository.findAll();
//    }

    public void parseCsv(MultipartFile file) throws NotFoundException {
        List<List<Book>> entitiesFromCsv = csvControl.getEntitiesFromCsv(file, Book.class);
        List<List<BookEntity>> collect = entitiesFromCsv.stream().map(books -> books.stream().map(Book::mapCsvToEntity).collect(Collectors.toList())).collect(Collectors.toList());

        collect.forEach(bookRepository::saveAll);
        System.out.println(121);
    }


}
