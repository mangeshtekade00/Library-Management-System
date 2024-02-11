package com.example.librarymanagementsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.librarymanagementsystem.Entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,String> {


}
