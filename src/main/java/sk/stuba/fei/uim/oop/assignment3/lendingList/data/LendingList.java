package sk.stuba.fei.uim.oop.assignment3.lendingList.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class LendingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Book> lendList;


    private boolean lended;

    public LendingList(){
        this.lendList = new ArrayList<>();
    }


}
