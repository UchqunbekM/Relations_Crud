package com.example.relations.Controller;

import com.example.relations.Dto.GroupDto;
import com.example.relations.Entity.Faculty;
import com.example.relations.Entity.Groups;
import com.example.relations.Repos.FacultyRepo;
import com.example.relations.Repos.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupsController {
    @Autowired
    private GroupRepo repo;
    @Autowired
    private FacultyRepo facultyRepo;

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public List<Groups> getGroup() {
        return repo.findAll();
    }



    @RequestMapping(value = "/group/univer", method = RequestMethod.GET)
    public List<Groups> getbyidGroup(@PathVariable int id) {
        return repo.findAllByFaculty_University_Id(id);
    }



    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String addGroup( @RequestBody GroupDto groupDto) {
        Groups groups=new Groups();
        groups.setName(groupDto.getName());
        Optional<Faculty> byId = facultyRepo.findById(groupDto.getFacultyId());
        if (!byId.isPresent()) {
            return "Not found";
        }
        groups.setFaculty(byId.get());
        repo.save(groups);
        return "Saved";
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public String deleteGroup(@PathVariable int id) {
        repo.deleteById(id);
        return "success";
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public String editGroup() {
        return repo.findAll().toString();
    }
}
