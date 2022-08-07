package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.AddBookRequest;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository repository;

    private final AuthorService service;

    public BookService(AuthorService service) {
        this.service = service;
    }

    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) {
        Book b = new Book();
        b.setName(request.getName());
        b.setDescription(request.getDescription());
        b.setAuthor(request.getAuthor());
        b.setPages(request.getPages());
        b.setAmount(request.getAmount());
        b.setLendCount(request.getLendCount());
        Author a = service.getAuthor(request);
        a.getBooks().add(b);
        return this.repository.save(b);

    }

    @Override
    public Book getById(long id) throws NotFoundException {
        Book a = this.repository.findBookById(id);
        if (a == null) {
            throw new NotFoundException();
        }
        return a;
    }

    @Override
    public Book update(long id, BookUpdateRequest request) throws NotFoundException {
        Book p = this.getById(id);
        if (request.getName() != null) {
        p.setName(request.getName());
    }
        if (request.getDescription() != null) {
        p.setDescription(request.getDescription());
    }
        if(request.getAuthor() != null && request.getAuthor() !=0) {
            Author a2 =service.repA().findAuthorById(repository.findBookById(id).getAuthor());
            a2.getBooks().remove(repository.findBookById(id));
            Author a = service.repA().findAuthorById(request.getAuthor());
            a.getBooks().add(p);
            p.setAuthor(request.getAuthor());

    }
        if(request.getPages() != 0){
            p.setPages(request.getPages());
        }
        return this.repository.save(p);

}

    @Override
    public void delete(long id) throws NotFoundException {
        if(this.getById(id) != null) {
            Author a = service.repA().findAuthorById(repository.findBookById(id).getAuthor());
            a.getBooks().remove(repository.findBookById(id));
            this.repository.delete(this.getById(id));
        }
    }

    @Override
    public long getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public long addAmount(long id, long increment) throws NotFoundException {
        Book p = this.getById(id);
        p.setAmount((int) (p.getAmount() + increment));
        this.repository.save(p);
        return p.getAmount();
    }

    @Override
    public long getLendCount(long id) throws NotFoundException {
        return this.getById(id).getLendCount();
    }

    public Book getB(AddBookRequest request) {
        return this.repository.findBookById(request.getId());
    }

}
