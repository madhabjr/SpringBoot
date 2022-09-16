package com.example.springlearn.controller;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.springlearn.models.Games;
import com.example.springlearn.models.User;
import com.example.springlearn.repo.GameRepository;
import com.example.springlearn.repo.UserRepository;
import com.example.springlearn.services.GamesServices;

@Controller
@RequestMapping("/user")
public class GamesController {
	
	@Autowired	
	private BCryptPasswordEncoder bcryptPasswordEncoder; 
	@Autowired
	GamesServices services;
	@Autowired
	UserRepository userRepo;
	@Autowired
	GameRepository gamesRepo;
	
	@RequestMapping("/addg")
	public String addgPage(Model model) {
		model.addAttribute("games", new Games()); 
		return "Games/addg";
	}
	
	@RequestMapping("/saveg")
    public String savedata(Games games, Principal p, @RequestParam("profileImage") MultipartFile file) throws IOException {
        String name = p.getName();
        User user = this.userRepo.getUserByUserName(name);
        user.getGames().add(games);
        games.setUser(user);    
        if (file.isEmpty()) {
            // if the file is empty then try our message
            System.out.println("File is empty");
            games.setImage("game.png");

       } else {
            // file the file to folder and update the name to contact
            games.setImage(file.getOriginalFilename());
            File saveFile = new ClassPathResource("static/img").getFile();
           Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
           Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
           System.out.println("Image is uploaded");
}
        services.save(games);
        return "redirect:/";
    }
	
	@RequestMapping("/showgames/{page}")
	
	public String showGames(@PathVariable("page") Integer page,Model model, Principal p) {
        String name = p.getName();
        User user = this.userRepo.getUserByUserName(name);
        Pageable pageable = PageRequest.of(page, 2);
        Page<Games> games = this.gamesRepo.findGamesByUser(user.getId(), pageable);
        model.addAttribute("games",games);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", games.getTotalPages());
        return "Games/showgames";
//	public String showGames(Model model) {
//		List<Games> listGames = services.listGames();
//		model.addAttribute("listGames",listGames);
//		return "Games/showgames";
	}
	
	@RequestMapping("/profile")
    public String openSettings() {
        return "Games/profile";
    }
    
    @ModelAttribute
    public void addCommonData(Principal p,Model model) {
        String userName = p.getName();
        User user = userRepo.getUserByUserName(userName);
        model.addAttribute("user",user);
    }
	
	
	@RequestMapping("/editg/{gId}")
	public ModelAndView editData(@PathVariable(name="gId") Integer gId) {
		ModelAndView mov = new ModelAndView("Games/updateg");
		Games games =services.get(gId);
		mov.addObject("games",games);
		return mov;
	}
	
	
	@RequestMapping("/deleteg/{gId}")
	public String deleteData(@PathVariable(name="gId") Integer gId) {
		services.delete(gId);
		return "redirect:/";
	}
	
	@RequestMapping("/settings")
    public String openSetting() {
        return "Games/settings";
    }

	@RequestMapping("/changepass")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword, Principal p)
	{
		System.out.println("OLD PASS"+oldPassword );
		System.out.println("NEW PASS"+newPassword );
		
		String userName = p.getName();
		User currentUser= this.userRepo.getUserByUserName(userName);
		
		if(this.bcryptPasswordEncoder.matches(oldPassword, currentUser.getPassword()))
		{
			currentUser.setPassword(this.bcryptPasswordEncoder.encode(newPassword));
			this.userRepo.save(currentUser);
			
		}else
		{
			return "Games/settings";
		}
		return "Games/addg";
	
	}

//	@RequestMapping("/search/{query}")
//	public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal)
//	{
//		System.out.println("query");
//		
//		User user= this.userRepo.getUserByUserName(principal.getName());
//		
//		List<Games> games = this.gamesRepo.findByNameContainingAndUser(query, user);
//		return ResponseEntity.ok(games);
//	}
	
	
}
