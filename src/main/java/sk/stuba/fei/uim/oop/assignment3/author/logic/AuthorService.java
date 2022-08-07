package sk.stuba.fei.uim.oop.assignment3.author.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.web.bodies.AuthorUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private IAuthorRepository repository;

   public AuthorService(IAuthorRepository repository) {
        this.repository = repository;
        Author a1 = new Author();
        a1.setName("Marek");
        a1.setSurname("Belis");
        this.repository.save(a1);
        Author a2 = new Author();
        a2.setName("Oto");
        a2.setSurname("Frank");
        this.repository.save(a2);

    }

    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author create(AuthorRequest request) {
        Author a = new Author();
        a.setName(request.getName());
        a.setSurname(request.getSurname());
        return this.repository.save(a);
    }

    @Override
    public Author getById(long id) throws NotFoundException {
        Author a = this.repository.findAuthorById(id);
        if (a == null) {
            throw new NotFoundException();
        }
        return a;
    }

    @Override
    public Author update(long id, AuthorUpdateRequest request) throws NotFoundException {
        Author p = this.getById(id);
        if (request.getName() != null) {
            p.setName(request.getName());
        }
        if (request.getSurname() != null) {
            p.setSurname(request.getSurname());
        }
        return this.repository.save(p);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    public Author getAuthor(BookRequest request){
       return this.repository.findAuthorById(request.getAuthor());
    }
    public IAuthorRepository repA(){
       return this.repository;
    }

}



