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
@RequestMapping("/")
public class BankController {
	
	@Autowired
    BankService bankService;

	 @GetMapping("/banks")
	    public Page<Bank> getBanks(
	            @RequestParam int page,
	            @RequestParam int size,
	            @RequestParam String sortBy,
	            @RequestParam(defaultValue = "asc") String sortDir) {
	        
	        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
	        return  bankService.getAllBanks(pageable);
	    }

	    @GetMapping("/banks/{id}")
	    public ResponseEntity<Bank> getBank(@PathVariable Long id) {
	        Bank bank = bankService.getBankById(id);
	        return ResponseEntity.ok(bank);
	    }

	    @PostMapping("/banks")
	    public Bank createBank(@RequestBody Bank bank) {
	        return bankService.saveBank(bank);
	    }

	    @PutMapping("/banks/{id}")
	    public ResponseEntity<Bank> updateBank(@PathVariable Long id, @RequestBody Bank bankDetails) {
	        Bank bank = bankService.getBankById(id);
	        if (bank == null) {
	            return ResponseEntity.notFound().build();
	        }
	        bank.setBankname(bankDetails.getBankname());
	        return ResponseEntity.ok(bankService.saveBank(bank));
	    }

	    @DeleteMapping("/banks/{id}")
	    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
	        bankService.deleteBank(id);
	        return ResponseEntity.noContent().build();
	    }

	 
}
