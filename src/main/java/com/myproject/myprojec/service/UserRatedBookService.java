package com.myproject.myprojec.service;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.persistence.repository.UserRatedBookRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.UserRatedBookDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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

    public List<UserRatedBookEntity> getAllUserRates() {
        return userRatedBookRepository.findAll();
    }

}
