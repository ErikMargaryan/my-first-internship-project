package com.myproject.myprojec.service;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.entity.BookAuthorEntity;
import com.myproject.myprojec.persistence.entity.BookEntity;
import com.myproject.myprojec.persistence.repository.AuthorRepository;
import com.myproject.myprojec.persistence.repository.BookAuthorRepository;
import com.myproject.myprojec.persistence.repository.BookRepository;
import com.myproject.myprojec.csvUpload.control.CsvControl;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.BookDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import com.myproject.myprojec.csvUpload.csvModel.Book;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;
    private final CsvControl<Book> csvControl;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository, CsvControl<Book> csvControl) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
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

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public void parseCsv(MultipartFile file) throws NotFoundException {
        List<List<Book>> books = csvControl.getEntitiesFromCsv(file, Book.class);
//        AtomicInteger existed = new AtomicInteger();
//        List<List<BookEntity>> booksList = books.stream().map(book -> book.stream()
//                .map(Book::mapCsvToEntity)
//                .collect(Collectors.toList()))
//                .collect(Collectors.toList());
//        booksList.forEach(bookRepository::saveAll);
//        System.out.println(121);
        Set<AuthorEntity> authors = new HashSet<>(authorRepository.findAll());
        books.forEach(bookList -> bookList.forEach(book -> {
            authors.addAll(book.getAuthorsName().stream().map(AuthorEntity::new).collect(Collectors.toList()));
        }));
        AtomicInteger i = new AtomicInteger();
        List<AuthorEntity> authorEntities = authorRepository.saveAll(authors);

        books.forEach(bookList -> {
            List<BookEntity> collect = bookList.stream()
                    .map(book -> {
                        BookEntity bookEntity = Book.mapCsvToEntity(book);
                        List<BookAuthorEntity> bookAuthorEntityList = authorEntities.stream().filter(entity -> book.getAuthorsName().contains(entity.getName()))
                                .map(authorEntity -> new BookAuthorEntity(bookEntity, authorEntity)).collect(Collectors.toList());
                        bookEntity.setBookAuthorEntityList(bookAuthorEntityList);
                        System.out.println(i.incrementAndGet());
                        return bookEntity;
                    })
                    .collect(Collectors.toList());

            List<BookEntity> bookEntityList = bookRepository.saveAll(collect);
            saveRelations(bookEntityList);

        });
    }

    public void saveRelations(List<BookEntity> bookEntities) {
        List<BookAuthorEntity> bookAuthorEntityList = new ArrayList<>();
        bookEntities.forEach(entity -> {
            bookAuthorEntityList.addAll(entity.getBookAuthorEntityList().stream().peek(bookAuthorEntity -> bookAuthorEntity.getBook().setId(entity.getId())).collect(Collectors.toList()));
        });
        bookAuthorRepository.saveAll(bookAuthorEntityList);
    }

}
