package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Pensioner;

@Repository
public interface PensionserRepo extends JpaRepository<Pensioner, Long>{

}
