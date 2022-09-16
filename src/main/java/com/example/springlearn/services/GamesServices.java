package com.example.springlearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springlearn.models.Games;
import com.example.springlearn.repo.GameRepository;

@Service
public class GamesServices{

	@Autowired
	GameRepository repo;
	
	public List <Games> listGames(){
		return repo.findAll();
	}
	public void save(Games games) {
		repo.save(games);
	}
	public Games get(Integer gid) {
		return repo.findById(gid).get();
	}
	public void delete(Integer gid) {
		repo.deleteById(gid);
	}
}