package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;


@Getter
@Setter
public class BookResponse {
    private Long id;

    private String name;

    private String description;


    private Long author;

    private int pages;

    private int amount;

    private int lendCount;

    public BookResponse(Book a){
        this.id = a.getId();
        this.name = a.getName();
        this.description = a.getDescription();
        this.author = a.getAuthor();
        this.pages = a.getPages();
        this.amount = a.getAmount();
        this.lendCount = a.getLendCount();

    }

}
