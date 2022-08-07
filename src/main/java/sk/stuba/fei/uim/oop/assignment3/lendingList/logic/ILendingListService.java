package sk.stuba.fei.uim.oop.assignment3.lendingList.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendingList.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.lendingList.web.bodies.AddBookRequest;

import java.util.List;

public interface ILendingListService {

    List<LendingList> getAll();

    LendingList create();

    LendingList getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    LendingList addBook(long id, AddBookRequest request) throws NotFoundException, IllegalOperationException;

    void deleteBook(long id, AddBookRequest request) throws NotFoundException;

    void lend(long id) throws NotFoundException,IllegalOperationException;
}
