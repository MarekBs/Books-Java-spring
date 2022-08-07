package sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.LendingList;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class ListResponse {

    private Long id;


    private List<BookResponse> lendingList;

    private boolean lended;

    public ListResponse(LendingList a){
        this.id= a.getId();
        this.lendingList= a.getLendList().stream().map(BookResponse::new).collect(Collectors.toList());
        this.lended = a.isLended();


    }
}
