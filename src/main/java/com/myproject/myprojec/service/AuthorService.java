package com.myproject.myprojec.service;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.repository.AuthorRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        authorEntity = authorRepository.save(authorEntity);
        return authorEntity;
    }

    public AuthorEntity getById(Long id) throws Exception {
        return authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Author not found"));
    }

    public QueryResponseWrapper<AuthorEntity> getAuthors(SearchCriteria searchCriteria) {
        Page<AuthorEntity> page = authorRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<AuthorEntity> content = page.getContent();
        return new QueryResponseWrapper<>(page.getTotalElements(), content);
    }

    public AuthorEntity updateAuthorData(Long id, AuthorEntity entity) throws Exception {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Author not found"));
        if (entity.getName() != null) {
            authorEntity.setName(entity.getName());
        }
        authorEntity = authorRepository.save(authorEntity);
        return authorEntity;
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

}
