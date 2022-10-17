package com.example.relations.Controller;

import com.example.relations.Dto.UniverDto;
import com.example.relations.Entity.Address;
import com.example.relations.Entity.University;
import com.example.relations.Repos.AddressRepo;
import com.example.relations.Repos.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    private UniversityRepo universityRepo;
    @Autowired
    private AddressRepo addressRepo;

    @RequestMapping(value = "/univer", method = RequestMethod.GET)
    public List<University>getUniversity() {
        return (universityRepo.findAll());
    }

    @RequestMapping(value = "/univer/{id}", method = RequestMethod.GET)
    public String getUniversitybyId(@PathVariable int id) {
        return String.valueOf(universityRepo.findById(id));
    }

    @RequestMapping(value = "/univer", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniverDto univerDto) {
        Address address=new Address();
        address.setCity(univerDto.getCity());
        address.setDistrict(univerDto.getDistrict());
        address.setStreet(univerDto.getStreet());
        Address savedaddrees = addressRepo.save(address);
        University university=new University();
        university.setName(univerDto.getName());
        university.setAddress(savedaddrees);
        universityRepo.save(university);
        return "Success";
    }

    @RequestMapping(value = "/univer/{id}", method = RequestMethod.DELETE)
    public String deleteByidUniversity(@PathVariable int id) {
        universityRepo.deleteById(id);
        return "Succeess";
    }

    @RequestMapping(value = "/univer/{id}", method = RequestMethod.PUT)
    public String getUniversity(@PathVariable int id, @RequestBody  UniverDto univerDto) {
        Optional<University> byId = universityRepo.findById(id);
        if (byId.isPresent()) {
            University university = byId.get();
            university.setName(univerDto.getName());
            Address address = university.getAddress();
           address.setCity(univerDto.getCity());
           address.setStreet(univerDto.getStreet());
           address.setDistrict(univerDto.getDistrict());
           addressRepo.save(address);
           university.setAddress(address);
            universityRepo.save(university);
            return "Edited";
        } else return  "University not found";
    }

}
