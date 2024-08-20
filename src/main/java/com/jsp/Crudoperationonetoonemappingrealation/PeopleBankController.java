package com.jsp.Crudoperationonetoonemappingrealation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people-banks")
public class PeopleBankController {

    @Autowired
    PeopleService peopleService;



    @GetMapping("/people")
    public Page<People> getPeople(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return peopleService.getAllPeople(pageable);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<People> getPeople(@PathVariable Long id) {
        People people = peopleService.getPeopleById(id);
        return ResponseEntity.ok(people);
    }

    @PostMapping("/people")
    public People createPeople(@RequestBody People people) {
        return peopleService.savePeople(people);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<People> updatePeople(@PathVariable Long id, @RequestBody People peopleDetails) {
        People people = peopleService.getPeopleById(id);
        if (people == null) {
            return ResponseEntity.notFound().build();
        }
        people.setName(peopleDetails.getName());
        return ResponseEntity.ok(peopleService.savePeople(people));
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable Long id) {
        peopleService.deletePeople(id);
        return ResponseEntity.noContent().build();
    }


   
}
