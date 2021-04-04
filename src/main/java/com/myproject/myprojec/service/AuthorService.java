package com.myproject.myprojec.service;

import com.myproject.myprojec.dto.AuthorDto;
//import com.myproject.myprojec.mapper.AuthorMapper;
import com.myproject.myprojec.model.entity.AuthorEntity;
import com.myproject.myprojec.rpository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto createAuthor(AuthorDto dto) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName(dto.getFirstName());
        authorEntity.setLastName(dto.getLastName());

        authorEntity = authorRepository.save(authorEntity);
        return AuthorDto.mapEntityToDto(authorEntity);
    }

    public AuthorDto getAuthor(Long id) throws Exception {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Author not found"));
        return AuthorDto.mapEntityToDto(authorEntity);
    }

    public AuthorDto updateAuthorData(Long id, AuthorDto dto) throws Exception {
        AuthorEntity authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new Exception("Author not found"));
        if (dto.getFirstName() != null) {
            authorEntity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            authorEntity.setLastName(dto.getLastName());
        }
        authorEntity = authorRepository.save(authorEntity);
        return AuthorDto.mapEntityToDto(authorEntity);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

}
