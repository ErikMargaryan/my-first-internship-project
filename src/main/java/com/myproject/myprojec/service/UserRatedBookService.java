package com.myproject.myprojec.service;

import com.myproject.myprojec.persistence.entity.UserRatedBookEntity;
import com.myproject.myprojec.persistence.repository.UserRatedBookRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRatedBookService {

    private final UserRatedBookRepository userRatedBookRepository;

    @Autowired
    public UserRatedBookService(UserRatedBookRepository userRatedBookRepository) {
        this.userRatedBookRepository = userRatedBookRepository;
    }

    public UserRatedBookEntity createUsersRatedBooks(UserRatedBookEntity userRatedBookEntity) {
        userRatedBookEntity = userRatedBookRepository.save(userRatedBookEntity);
        return userRatedBookEntity;
    }

    public UserRatedBookEntity getById(Long id) throws Exception {
        return userRatedBookRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
    }

    public QueryResponseWrapper<UserRatedBookEntity> getUserRates(SearchCriteria searchCriteria) {
        Page<UserRatedBookEntity> page = userRatedBookRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<UserRatedBookEntity> content = page.getContent();
        return new QueryResponseWrapper<>(page.getTotalElements(), content);
    }

    public UserRatedBookEntity updateUsersRate(Long id, UserRatedBookEntity entity) throws Exception {
        UserRatedBookEntity userRatedBookEntity = userRatedBookRepository.findById(id)
                .orElseThrow(() -> new Exception("User rate not found"));
        userRatedBookEntity.setBookRating(entity.getBookRating());
        userRatedBookEntity = userRatedBookRepository.save(userRatedBookEntity);
        return userRatedBookEntity;
    }

    public void deleteUsersRate(Long id) {
        userRatedBookRepository.deleteById(id);
    }

    public List<UserRatedBookEntity> getAllUserRates() {
        return userRatedBookRepository.findAll();
    }

}
