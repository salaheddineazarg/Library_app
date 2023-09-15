package Interfaces;

import DTO.Borrower;

public interface Iborrower {
    Borrower create(Borrower borrower);
    int delete (int id);
    boolean update(Borrower borrower);
    Borrower getOne(int id);
}
