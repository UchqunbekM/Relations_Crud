package com.example.relations.Controller;

import com.example.relations.Dto.Faceltydto;
import com.example.relations.Entity.Faculty;
import com.example.relations.Entity.University;
import com.example.relations.Repos.FacultyRepo;
import com.example.relations.Repos.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FacultyController {
    @Autowired
    private FacultyRepo facultyRepo;
    @Autowired
    private UniversityRepo universityRepo;

    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public List<Faculty> getFaculty() {
        return facultyRepo.findAll();
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.GET)
    public Optional<Faculty> getFacultyById(@PathVariable int id) {
        return facultyRepo.findById(id);
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.DELETE)
    public String deleteFacultyById(@PathVariable int id) {
        try {
            facultyRepo.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "Faculty not found";
        }
    }

    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public String saveFaculty(@RequestBody Faceltydto faceltydto) {
        if (facultyRepo.existsByNameAndUniversityId(faceltydto.getName(), faceltydto.getUniversityId())) {
            return "This university such faculty exist";
        }
        Faculty faculty = new Faculty();
        faculty.setName(faceltydto.getName());
        Optional<University> byId = universityRepo.findById(faceltydto.getUniversityId());
        if (byId.isPresent()) {
            University university = byId.get();
            faculty.setUniversity(university);
        }
        facultyRepo.save(faculty);
        return "success";
    }


    @RequestMapping(value = "/faculty/byuniverId/{id}", method = RequestMethod.GET)
    public List<Faculty> getFalByUniverId(@PathVariable int id) {
        List<Faculty> allByUniversity_id = facultyRepo.findAllByUniversity_Id(id);
        return allByUniversity_id;
    }


    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.PUT)
    public String editFaculty(@PathVariable int id, @RequestBody Faceltydto faculty) {
        Optional<Faculty> byId = facultyRepo.findById(id);
        Optional<University> byId1 = universityRepo.findById(faculty.getUniversityId());
        if (!byId.isPresent()) {return "Faculty not found"; } {
            if (!byId1.isPresent()) {
                return "University not found";
            }
            Faculty faculty1 = byId.get();
            faculty1.setUniversity(byId1.get());
            faculty1.setName(faculty.getName());
            facultyRepo.save(faculty1);
        }
        return "Faculty Saved";
    }
}
