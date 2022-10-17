package com.example.relations.Controller;

import com.example.relations.Entity.Address;
import com.example.relations.Repos.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressContoller {

    @Autowired
    private AddressRepo addressRepo;

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public List<Address> getAddress(){
        List<Address> addressList = addressRepo.findAll();
        return addressList;
    }

    @RequestMapping (value = "/address/{id}", method = RequestMethod.GET)
    public String getAddressbyId(@PathVariable int id){
        Optional<Address> byId = addressRepo.findById(id);
        if (byId.isPresent()) {
            return String.valueOf(byId);
        }
return "Address not found";
    }

    @RequestMapping (value = "/address", method = RequestMethod.POST)
    public String saveAddress(@RequestBody Address address){
        addressRepo.save(address);
        return "Successfully added";
    }

    @RequestMapping (value = "/address/{id}", method = RequestMethod.DELETE)
    public String deleteAddressById(@PathVariable int id){
        addressRepo.deleteById(id);
        return "Successfully deleted";
    }

    @RequestMapping (value = "/address/{id}", method = RequestMethod.PUT)
    public String editAddressbyId (@PathVariable int id, @RequestBody Address address){
        Optional<Address> byId = addressRepo.findById(id);
        if (byId.isPresent()) {
            Address address1 = byId.get();
            address1.setDistrict(address.getDistrict());
            address1.setCity(address.getCity());
            address1.setStreet(address.getStreet());
            addressRepo.save(address1);
            return "Succeess";
        }
        return "not found";
    }
}
