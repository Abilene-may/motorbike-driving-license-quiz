package com.example.motorbikedrivinglicensequiz.repositories;

import com.example.motorbikedrivinglicensequiz.domains.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>,
    JpaSpecificationExecutor<Test> {

}
