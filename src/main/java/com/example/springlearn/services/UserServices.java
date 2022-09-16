package com.example.springlearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springlearn.models.User;
import com.example.springlearn.repo.UserRepository;

@Service
public class UserServices {

	@Autowired
	UserRepository repo;	
	
	public List <User> listUser(){
		return repo.findAll();
	}
	public void save(User user) {
		repo.save(user);
	}
	public User get(Integer id) {
		return repo.findById(id).get();
	}
	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
