package com.example.relations.Repos;

import com.example.relations.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo  extends JpaRepository<Subject, Integer> {
}
