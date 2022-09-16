package com.example.springlearn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.springlearn.models.User;
import com.example.springlearn.repo.UserRepository;
import com.example.springlearn.services.UserServices;

@RestController
@RequestMapping("/api")
public class ApiClient {

	@Autowired
	UserServices services;
	
	@Autowired
	UserRepository repo;
	
	@GetMapping("/display")
	public List<User> showUsers(Model model) {
		 return services.listUser();
		
	}
	
	@PostMapping("/save")
	public ResponseEntity <Object> savedata(User user) {
	    services.save(user);
	    return new ResponseEntity<>("USER IS ADDED",HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
    public ResponseEntity<Object> editData(@PathVariable(name="id") Integer id,User user) {
        Optional<User> useris = repo.findById(id);

       if (useris.isEmpty())
           return ResponseEntity.notFound().build();
       user.setId(id); 
        repo.save(user);
    
        return ResponseEntity.noContent().build();
        
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity <Object> deleteData(@PathVariable(name="id") Integer id) {
		services.delete(id);
		return new ResponseEntity<>("DETAILS HAVE BEEN DELETED",HttpStatus.CREATED);
	}
}
