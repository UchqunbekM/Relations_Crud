package com.example.relations.Repos;

import com.example.relations.Entity.Groups;
import com.example.relations.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Groups, Integer> {


    List<Groups> findAllByFaculty_University_Id(Integer faculty_university_id);

//    @Query("select gr from groups gr where gr.faculty.university.id=?1")
//    List<Groups> getGroupsByFaculty_UniversityId();

    @Query(value = "select * from groups g join faculty f on\n" +
            "        f.id=g.faculty_id join university u on u.id=f.university_id\n" +
            "where u.id=:universityId",nativeQuery = true)
    List<Groups> getGroupsByFaculty_Univerid();

}
