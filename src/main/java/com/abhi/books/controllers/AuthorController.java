package com.abhi.books.controllers;

import com.abhi.books.domain.dto.AuthorDto;
import com.abhi.books.domain.entities.AuthorEntity;
import com.abhi.books.mappers.Mapper;
import com.abhi.books.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService,
                            Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    /*
     * For POST <IP>/authors
     * This looks for JSON object Author inside the Request Body
     * and extracts that into the parameter,
     * and converts it from JSON to the Java object Author
     */
    @PostMapping(path = "/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorEntity authorEntityToCreate = authorMapper.mapFrom(authorDto);
        AuthorEntity authorEntitySaved = authorService.createAuthor(authorEntityToCreate);
        return authorMapper.mapTo(authorEntitySaved);
    }
}
