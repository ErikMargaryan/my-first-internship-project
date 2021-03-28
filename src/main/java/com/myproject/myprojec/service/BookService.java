package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.BookDto;
import com.myproject.myprojec.mapper.BookMapper;
import com.myproject.myprojec.model.entity.BookEntity;
import com.myproject.myprojec.rpository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto createBook(BookDto dto) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(dto.getTitle());
        bookEntity.setPrice(dto.getPrice());

        bookEntity = bookRepository.save(bookEntity);
        return BookMapper.mapEntityToDto(bookEntity);
    }

    public BookDto getBook(Long id) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book not found"));
        return BookMapper.mapEntityToDto(bookEntity);
    }

    public BookDto updateBookData(Long id, BookDto dto) throws Exception {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book not found"));

        if (dto.getTitle() != null) {
            bookEntity.setTitle(dto.getTitle());
        }
        //Price type is double
        bookEntity.setPrice(dto.getPrice());

        bookEntity = bookRepository.save(bookEntity);
        return BookMapper.mapEntityToDto(bookEntity);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
