package com.example.relations.Repos;

import com.example.relations.Entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepo extends JpaRepository<University, Integer> {
}
