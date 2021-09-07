package com.myproject.myprojec.service;

import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.rpository.AuthorRepository;
import com.myproject.myprojec.service.criteria.SearchCriteria;
import com.myproject.myprojec.service.dto.AuthorDto;
import com.myproject.myprojec.service.model.QueryResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto createAuthor(AuthorDto dto) {
        AuthorEntity authorEntity = new AuthorEntity();
        AuthorDto.mapDtoToEntity(dto);

        authorEntity = authorRepository.save(authorEntity);
        return AuthorDto.mapEntityToDto(authorEntity);
    }

    public AuthorDto getAuthor(Long id) throws Exception {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Author not found"));
        return AuthorDto.mapEntityToDto(authorEntity);
    }

    public QueryResponseWrapper<AuthorDto> getAuthors(SearchCriteria searchCriteria) {
        Page<AuthorEntity> page = authorRepository.findALLWithPagination(searchCriteria.composePageRequest());
        List<AuthorEntity> content = page.getContent();
        List<AuthorDto> dtos = content.stream().map(AuthorDto::mapEntityToDto).collect(Collectors.toList());
        return new QueryResponseWrapper<>(page.getTotalElements(), dtos);
    }

    public AuthorDto updateAuthorData(Long id, AuthorDto dto) throws Exception {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Author not found"));
        if (dto.getName() != null) {
            authorEntity.setName(dto.getName());
        }
        authorEntity = authorRepository.save(authorEntity);
        return AuthorDto.mapEntityToDto(authorEntity);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

}
