package sk.stuba.fei.uim.oop.assignment3.lendingList.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ILendingListRepository extends JpaRepository<LendingList,Long> {
    List<LendingList> findAll();
    LendingList findListById(Long id);
}
