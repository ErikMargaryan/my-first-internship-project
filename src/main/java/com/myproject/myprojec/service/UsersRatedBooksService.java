package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.UsersRatedBooksDto;
import com.myproject.myprojec.mapper.UsersRatedBooksMapper;
import com.myproject.myprojec.model.entity.UsersRatedBooksEntity;
import com.myproject.myprojec.rpository.UsersRatedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRatedBooksService {

    private final UsersRatedBooksRepository usersRatedBooksRepository;

    @Autowired
    public UsersRatedBooksService(UsersRatedBooksRepository usersRatedBooksRepository) {
        this.usersRatedBooksRepository = usersRatedBooksRepository;
    }

    public UsersRatedBooksDto createUsersRatedBooks(UsersRatedBooksDto dto) {
        UsersRatedBooksEntity usersRatedBooksEntity = new UsersRatedBooksEntity();
        usersRatedBooksEntity.setISBN(dto.getISBN());
        usersRatedBooksEntity.setBookRating(dto.getBookRating());
        usersRatedBooksEntity = usersRatedBooksRepository.save(usersRatedBooksEntity);
        return UsersRatedBooksMapper.mapEntityToDto(usersRatedBooksEntity);
    }

    public UsersRatedBooksDto getUsersRatedBooks(Long id, UsersRatedBooksDto dto) throws Exception {
        UsersRatedBooksEntity usersRatedBooksEntity = usersRatedBooksRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
        return UsersRatedBooksMapper.mapEntityToDto(usersRatedBooksEntity);
    }

    public UsersRatedBooksDto updateUsersRate(Long id, UsersRatedBooksDto dto) throws Exception {
        UsersRatedBooksEntity usersRatedBooksEntity = usersRatedBooksRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
        if (dto.getISBN() != null) {
            usersRatedBooksEntity.setISBN(dto.getISBN());
        }
        usersRatedBooksEntity.setBookRating(dto.getBookRating());
        usersRatedBooksEntity = usersRatedBooksRepository.save(usersRatedBooksEntity);
        return UsersRatedBooksMapper.mapEntityToDto(usersRatedBooksEntity);
    }

    public void deleteUsersRate(Long id) {
        usersRatedBooksRepository.deleteById(id);
    }
}
