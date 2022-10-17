package com.example.relations.Controller;

import com.example.relations.Dto.StudentDto;
import com.example.relations.Entity.Address;
import com.example.relations.Entity.Groups;
import com.example.relations.Entity.Student;
import com.example.relations.Repos.AddressRepo;
import com.example.relations.Repos.GroupRepo;
import com.example.relations.Repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentContoller {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private AddressRepo addressRepo;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudent() {
        return studentRepo.findAll();
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String getBYidStudent(@PathVariable int id) {
        return studentRepo.findById(id).toString();
    }

    @RequestMapping(value = "/forMinistry", method = RequestMethod.GET)
    public Page<Student> getStudentListforMinistry(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Student> studentPage=studentRepo.findAll(pageable);
        return studentPage;
    }

    @RequestMapping(value = "/forUniversity/{id}", method = RequestMethod.GET)
    public Page<Student> getStudentListforUniversity(@PathVariable Integer id,@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Student> allByGroup_faculty_universityId = studentRepo.findAllByGroup_Faculty_UniversityId(id, pageable);
        return allByGroup_faculty_universityId;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        Optional<Groups> byId = groupRepo.findById(studentDto.getGroupId());
        if (!byId.isPresent()) {
            return "Group not found";
        }
        student.setGroup(byId.get());
        Address address = new Address();
        address.setStreet(studentDto.getStreet());
        address.setDistrict(studentDto.getDistrict());
        address.setCity(studentDto.getCity());
        addressRepo.save(address);
        student.setAddress(address);
        studentRepo.save(student);
        return "success";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable int id) {
        studentRepo.deleteById(id);
        return "Success";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public String editStudent(@PathVariable int id, @RequestBody Student student) {
        studentRepo.deleteById(id);
        return "Success";
    }
}
