package com.jsp.Crudoperationonetoonemappingrealation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    public Page<People> getAllPeople(Pageable pageable) {
        return peopleRepository.findAll(pageable);
    }

    public People getPeopleById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public People savePeople(People people) {
        return peopleRepository.save(people);
    }

    public void deletePeople(Long id) {
        peopleRepository.deleteById(id);
    }
}
