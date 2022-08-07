package sk.stuba.fei.uim.oop.assignment3.lendingList.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.BookService;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.ILendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.AddBookRequest;

import java.util.List;
import java.util.Objects;

@Service
public class LendingListService implements ILendingListService {

    @Autowired
    private ILendingListRepository repository;

    private final BookService service;

    public LendingListService(BookService service) {
        this.service = service;
    }

    @Override
    public List<LendingList> getAll() {
        return this.repository.findAll();
    }

    @Override
    public LendingList create() {
        LendingList l = new LendingList();
        return this.repository.save(l);
    }

    @Override
    public LendingList getById(long id) throws NotFoundException {
        LendingList l = this.repository.findListById(id);
        if (l == null) {
            throw new NotFoundException();
        }
        return l;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        LendingList l = this.repository.findListById(id);
        if(l==null){throw new NotFoundException();}
        if(l.isLended()){
            for (int i=0;i<l.getLendList().size();i++){
                l.getLendList().get(i).setLendCount(l.getLendList().get(i).getLendCount()-1);
            }
        }
        this.repository.delete(l);
    }


    @Override
    public LendingList addBook(long id, AddBookRequest request) throws NotFoundException, IllegalOperationException {
        LendingList l = this.repository.findListById(id);
        if(l==null || service.getB(request)==null){
            throw new NotFoundException();
        }
        if(l.isLended()){
            throw new IllegalOperationException();
        }
        for (int i=0;i<l.getLendList().size();i++){
            if (Objects.equals(l.getLendList().get(i).getId(), request.getId())){
                throw new IllegalOperationException();
            }
        }


        Book b = service.getB(request);
        l.getLendList().add(b);
        this.repository.save(l);

        return l;
    }

    @Override
    public void deleteBook(long id, AddBookRequest request) throws NotFoundException {
        LendingList l = this.repository.findListById(id);
        if(l==null){
            throw new NotFoundException();
        }

        int k=0;
        for (int i=0;i<l.getLendList().size();i++){
            if (Objects.equals(l.getLendList().get(i).getId(), request.getId())){
                l.getLendList().remove(l.getLendList().get(i));
                this.repository.save(l);
                k++;
            }
        }
        if(k==0){throw new NotFoundException();}
    }

    @Override
    public void lend(long id) throws NotFoundException, IllegalOperationException {
        LendingList l = this.repository.findListById(id);
        if(l==null){
            throw  new NotFoundException();
        }
        if(l.isLended()){
            throw new IllegalOperationException();
        }

        l.setLended(true);

        for (int i=0;i<l.getLendList().size();i++){
            l.getLendList().get(i).setLendCount(l.getLendList().get(i).getLendCount()+1);
        }
        this.repository.save(l);

    }
}
