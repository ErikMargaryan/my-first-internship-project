package com.myproject.myprojec.service;

import com.myproject.myprojec.csvUpload.csvHelper.AuthorHelper;
import com.myproject.myprojec.dto.AuthorDto;
import com.myproject.myprojec.persistence.entity.AuthorEntity;
import com.myproject.myprojec.persistence.rpository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

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

    //for CSV upload
        public void save(MultipartFile file) {
            try {
            List<AuthorEntity> entities = AuthorHelper.csvToAuthorEntity(file.getInputStream());
            authorRepository.saveAll(entities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<AuthorEntity> entities = authorRepository.findAll();
        ByteArrayInputStream in = AuthorHelper.authorEntityToCSV(entities);
        return in;
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

}
