package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;


    private Long author;

    private int pages;

    private int amount;

    private int lendCount;


}
