package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Bank;

@Repository
public interface BankRepo extends JpaRepository<Bank, Long>{

}
