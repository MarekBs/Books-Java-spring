package sk.stuba.fei.uim.oop.assignment3.lendingList.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendingList.logic.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.AddBookRequest;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.ListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class LendingListController {

    @Autowired
    private ILendingListService service;

    @GetMapping()
    public List<ListResponse> getAllLandingLists(){
        return this.service.getAll().stream().map(ListResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<ListResponse> createList(){
        return new ResponseEntity<>(new ListResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse getList(@PathVariable("id") Long listId) throws NotFoundException {
        return new ListResponse(this.service.getById(listId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteList(@PathVariable("id") Long listId) throws NotFoundException {
        this.service.delete(listId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse addBook(@PathVariable("id") Long listId, @RequestBody AddBookRequest body) throws NotFoundException, IllegalOperationException {
        return new ListResponse(this.service.addBook(listId,body));
    }
    @DeleteMapping(value = "/{id}/remove")
    public void deleteBookFromList(@PathVariable("id") Long listId, @RequestBody AddBookRequest body) throws NotFoundException {
        this.service.deleteBook(listId,body);
    }
    @GetMapping("/{id}/lend")
    public void lendList(@PathVariable("id") Long listId) throws NotFoundException,IllegalOperationException{
        this.service.lend(listId);
    }
}
