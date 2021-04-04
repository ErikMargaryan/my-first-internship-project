package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.BookDto;
import com.myproject.myprojec.model.QueryResponseWrapper;
import com.myproject.myprojec.persistence.entity.BookEntity;
import com.myproject.myprojec.persistence.rpository.BookRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public QueryResponseWrapper<BookDto> getBooks(SearchCriteria searchCriteria) {
         Page<BookEntity> content = bookRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<BookEntity> content1 = content.getContent();
        //content1 map to dto list
        return new QueryResponseWrapper<>(content.getTotalElements(), content1);
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
        bookEntity.setYearOfPublication(dto.getYearOfPublication());

        bookEntity = bookRepository.save(bookEntity);
        return BookDto.mapEntityToDto(bookEntity);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

//    public List<List<BookDto>> getEntitiesFromCsv(MultipartFile csvFile, BookDto.class) {
//
//    }
}
