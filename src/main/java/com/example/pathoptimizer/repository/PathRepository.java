package com.example.pathoptimizer.repository;

import com.example.pathoptimizer.model.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathRepository extends JpaRepository<Path, Long> {

}
