package sk.stuba.fei.uim.oop.assignment3.author.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.logic.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping()
    public List<AuthorResponse> getAllBooks(){
        return this.service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }
    @PostMapping()
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest request){
    return new ResponseEntity<>(new AuthorResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse getAuthor(@PathVariable("id") Long authorId) throws NotFoundException{
        return new AuthorResponse(this.service.getById(authorId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorResponse updateAuthor(@PathVariable("id") Long authorId,@RequestBody AuthorUpdateRequest body) throws NotFoundException{
        return new AuthorResponse(this.service.update(authorId,body));
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorId) throws NotFoundException{
        this.service.delete(authorId);
    }
}

