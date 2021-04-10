//package com.myproject.myprojec.model;
//
//import com.myproject.myprojec.model.entity.*;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class BookWrapper {
//
//    private Long id;
//    private String title;
//    private List<BookAuthorEntity> bookAuthorEntityList;
//    private Double price;
//    private List<UsersRatedBooksEntity> usersRatedBooksEntityList;
//    private List<BookGenreEntity> bookGenreEntityList;
//
//    public BookWrapper(Long id, String title, List<BookAuthorEntity> bookAuthorEntityList, Double price, List<UsersRatedBooksEntity> usersRatedBooksEntityList, List<BookGenreEntity> bookGenreEntityList) {
//        this.id = id;
//        this.title = title;
//        this.bookAuthorEntityList = bookAuthorEntityList;
//        this.price = price;
//        this.usersRatedBooksEntityList = usersRatedBooksEntityList;
//        this.bookGenreEntityList = bookGenreEntityList;
//    }
//
//    public BookWrapper(BookEntity bookEntity) {
//        this.id = bookEntity.getId();
//        this.title = bookEntity.getTitle();
//        List<BookAuthorEntity> bookAuthorEntityList = bookEntity.getBookAuthorEntityList();
//        if (!CollectionUtils.isEmpty(bookAuthorEntityList)) {
//            this.bookAuthorEntityList = getBookAuthorEntityList();
//        }
//        this.price = bookEntity.getPrice();
//        List<UsersRatedBooksEntity> usersRatedBooksEntityList = bookEntity.getUsersRatedBooksList();
//        if (!CollectionUtils.isEmpty(usersRatedBooksEntityList)) {
//            this.usersRatedBooksEntityList = bookEntity.getUsersRatedBooksList();
//        }
//        List<BookGenreEntity> bookGenreEntityList = bookEntity.getBookGenreEntityList();
//        if (!CollectionUtils.isEmpty(bookGenreEntityList)) {
//            this.usersRatedBooksEntityList = bookEntity.getUsersRatedBooksList();
//        }
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public List<BookAuthorEntity> getBookAuthorEntityList() {
//        return bookAuthorEntityList;
//    }
//
//    public void setBookAuthorEntityList(List<BookAuthorEntity> bookAuthorEntityList) {
//        this.bookAuthorEntityList = bookAuthorEntityList;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public List<UsersRatedBooksEntity> getUsersRatedBooksEntityList() {
//        return usersRatedBooksEntityList;
//    }
//
//    public void setUsersRatedBooksEntityList(List<UsersRatedBooksEntity> usersRatedBooksEntityList) {
//        this.usersRatedBooksEntityList = usersRatedBooksEntityList;
//    }
//
//    public List<BookGenreEntity> getBookGenreEntityList() {
//        return bookGenreEntityList;
//    }
//
//    public void setBookGenreEntityList(List<BookGenreEntity> bookGenreEntityList) {
//        this.bookGenreEntityList = bookGenreEntityList;
//    }
//}
