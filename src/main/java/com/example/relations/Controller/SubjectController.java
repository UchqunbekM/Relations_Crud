package com.example.relations.Controller;

import com.example.relations.Entity.Subject;
import com.example.relations.Repos.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    private SubjectRepo subjectRepo;

    @RequestMapping (value = "/subject", method = RequestMethod.GET)
    public String getSubject(){
        return subjectRepo.findAll().toString();
    }


    @RequestMapping (value = "/subject/{id}", method = RequestMethod.GET)
    public String getSubject(@PathVariable int id){
        return subjectRepo.findById(id).toString();
    }


    @RequestMapping (value = "/subject", method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        subjectRepo.save(subject);
        return "Success";
    }


    @RequestMapping (value = "/subject/{id}", method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable int id){
        subjectRepo.deleteById(id);
        return "success";
    }


    @RequestMapping (value = "/subject/{id}", method = RequestMethod.PUT)
    public String editSubject(@PathVariable int id, @RequestBody Subject subject){
        Optional<Subject> byId = subjectRepo.findById(id);
        if (byId.isPresent()) {
            Subject subject1 = byId.get();
            subject1.setName(subject.getName());
            subjectRepo.save(subject1);
            return "succeess";
        }
    return "not found";
    }


}
