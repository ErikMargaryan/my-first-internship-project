package com.myproject.myprojec.service;

//import com.myproject.myprojec.csvUpload.csvHelper.UserRatedBookHelper;
import com.myproject.myprojec.dto.UserRatedBookDto;
import com.myproject.myprojec.model.QueryResponseWrapper;
import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.persistence.rpository.UserRatedBookRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRatedBookService {

    private final UserRatedBookRepository userRatedBookRepository;

    @Autowired
    public UserRatedBookService(UserRatedBookRepository userRatedBookRepository) {
        this.userRatedBookRepository = userRatedBookRepository;
    }

    public UserRatedBookDto createUsersRatedBooks(UserRatedBookDto dto) {
        UserRatedBookEntity userRatedBookEntity = new UserRatedBookEntity();
//        userRatedBookEntity.setBookRating(dto.getBookRating());
        UserRatedBookDto.mapDtoToEntity(dto);
        userRatedBookEntity = userRatedBookRepository.save(userRatedBookEntity);
        return UserRatedBookDto.mapEntityToDto(userRatedBookEntity);
    }

    public UserRatedBookDto getUsersRatedBooks(Long id) throws Exception {
        UserRatedBookEntity userRatedBookEntity = userRatedBookRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
        return UserRatedBookDto.mapEntityToDto(userRatedBookEntity);
    }

    public QueryResponseWrapper<UserRatedBookDto> getUserRates(SearchCriteria searchCriteria) {
        Page<UserRatedBookEntity> page = userRatedBookRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<UserRatedBookEntity> content = page.getContent();
        List<UserRatedBookDto> dtos = content.stream().map(UserRatedBookDto::mapEntityToDto).collect(Collectors.toList());
        return new QueryResponseWrapper<>(page.getTotalElements(), dtos);
    }

    public UserRatedBookDto updateUsersRate(Long id, UserRatedBookDto dto) throws Exception {
        UserRatedBookEntity userRatedBookEntity = userRatedBookRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
        userRatedBookEntity.setBookRating(dto.getBookRating());
        userRatedBookEntity = userRatedBookRepository.save(userRatedBookEntity);
        return UserRatedBookDto.mapEntityToDto(userRatedBookEntity);
    }

    public void deleteUsersRate(Long id) {
        userRatedBookRepository.deleteById(id);
    }

//    //for CSV upload
//    public void save(MultipartFile file) {
//        try {
//            List<UserRatedBookEntity> entities = UserRatedBookHelper.csvToUserRatedBookEntity(file.getInputStream());
//            userRatedBookRepository.saveAll(entities);
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store csv data: " + e.getMessage());
//        }
//    }
//
//    public ByteArrayInputStream load() {
//        List<UserRatedBookEntity> entities = userRatedBookRepository.findAll();
//        ByteArrayInputStream in = UserRatedBookHelper.userRatedBookEntityToCSV(entities);
//        return in;
//    }
//
//    public List<UserRatedBookEntity> getAllUserRates() {
//        return userRatedBookRepository.findAll();
//    }
}
