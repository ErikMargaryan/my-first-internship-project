package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.UserRatedBookDto;
import com.myproject.myprojec.mapper.UsersRatedBooksMapper;
import com.myproject.myprojec.model.entity.UserRatedBookEntity;
import com.myproject.myprojec.rpository.UserRatedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRatedBookService {

    private final UserRatedBookRepository userRatedBookRepository;

    @Autowired
    public UserRatedBookService(UserRatedBookRepository userRatedBookRepository) {
        this.userRatedBookRepository = userRatedBookRepository;
    }

    public UserRatedBookDto createUsersRatedBooks(UserRatedBookDto dto) {
        UserRatedBookEntity userRatedBookEntity = new UserRatedBookEntity();
        userRatedBookEntity.setBookRating(dto.getBookRating());
        userRatedBookEntity = userRatedBookRepository.save(userRatedBookEntity);
        return UsersRatedBooksMapper.mapEntityToDto(userRatedBookEntity);
    }

    public UserRatedBookDto getUsersRatedBooks(Long id) throws Exception {
        UserRatedBookEntity userRatedBookEntity = userRatedBookRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
        return UsersRatedBooksMapper.mapEntityToDto(userRatedBookEntity);
    }

    public UserRatedBookDto updateUsersRate(Long id, UserRatedBookDto dto) throws Exception {
        UserRatedBookEntity userRatedBookEntity = userRatedBookRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
        userRatedBookEntity.setBookRating(dto.getBookRating());
        userRatedBookEntity = userRatedBookRepository.save(userRatedBookEntity);
        return UsersRatedBooksMapper.mapEntityToDto(userRatedBookEntity);
    }

    public void deleteUsersRate(Long id) {
        userRatedBookRepository.deleteById(id);
    }
}
