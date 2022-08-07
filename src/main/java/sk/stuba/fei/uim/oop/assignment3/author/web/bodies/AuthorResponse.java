package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthorResponse {

    private Long id;

    private String name;

    private String surname;


    private List<Long> books;

    public AuthorResponse(Author a){
        this.id = a.getId();
        this.name = a.getName();
        this.surname = a.getSurname();
        this.books = a.getBooks().stream().map(Book::getId).collect(Collectors.toList());

    }

}
