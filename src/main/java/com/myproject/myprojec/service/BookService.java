package com.myproject.myprojec.service;

import com.myproject.myprojec.csvUpload.csvHelper.AuthorHelper;
import com.myproject.myprojec.csvUpload.csvHelper.BookHelper;
import com.myproject.myprojec.dto.BookDto;
import com.myproject.myprojec.persistence.entity.BookEntity;
import com.myproject.myprojec.persistence.rpository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto createBook(BookDto dto) {
        BookEntity bookEntity = new BookEntity();
//        bookEntity.setTitle(dto.getTitle());
//        bookEntity.setIsbn(dto.getIsbn());
//        bookEntity.setPublisher(dto.getPublisher());
//        bookEntity.setYearOfPublication(dto.getYearOfPublication());
        BookDto.mapDtoToEntity(dto);

        bookEntity = bookRepository.save(bookEntity);
        return BookDto.mapEntityToDto(bookEntity);
    }


    public BookDto getBook(Long id) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book not found"));
        return BookDto.mapEntityToDto(bookEntity);
    }

//    public QueryResponseWrapper<BookDto> getBooks(SearchCriteria searchCriteria) {
//         Page<BookEntity> content = bookRepository.findALLWithPagination(searchCriteria.composePageRequest());
//        List<BookEntity> content1 = content.getContent();
//        //content1 map to dto list
//        return new QueryResponseWrapper<>(content.getTotalElements(), content1);
//    }


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

    //for CSV upload
    public void save(MultipartFile file) {
        try {
            List<BookEntity> entities = BookHelper.csvToBookEntity(file.getInputStream());
            bookRepository.saveAll(entities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<BookEntity> entities = bookRepository.findAll();
        ByteArrayInputStream in = BookHelper.bookEntityToCSV(entities);
        return in;
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

}
