package com.example.springlearn.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springlearn.models.Games;
import com.example.springlearn.models.User;

@Repository
public interface GameRepository extends JpaRepository<Games,Integer> {
	
	@Query("from Games as g where g.user.id =:userId")
//    public List<Games> findGamesByUser(@Param("userId") int userId);
	public Page<Games> findGamesByUser(@Param("userId") int userId, Pageable pePageable);
	
//	public List<Games> findByNameContainingAndUser(String gamename, User user);
}
