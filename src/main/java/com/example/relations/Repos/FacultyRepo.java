package com.example.relations.Repos;

import com.example.relations.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Integer> {
    boolean existsByNameAndUniversityId(String name, Integer university_id);

    List<Faculty> findAllByUniversity_Id(Integer univer_id);
}
