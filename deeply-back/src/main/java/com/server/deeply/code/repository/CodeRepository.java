package com.server.deeply.code.repository;

import com.server.deeply.code.jpa.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long>{
}
