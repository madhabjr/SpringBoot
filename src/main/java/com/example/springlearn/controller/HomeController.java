package com.example.springlearn.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.springlearn.models.Games;
import com.example.springlearn.models.User;
import com.example.springlearn.repo.UserRepository;
import com.example.springlearn.services.GamesServices;
import com.example.springlearn.services.UserServices;

@Controller
public class HomeController {
 


@Autowired
	UserServices services;
@Autowired
UserRepository userRepo;

	//User
	@RequestMapping("/")
	public String homePage() {
		
		return "index";
	}
	@RequestMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User()); 
		return "register";
	}
	
	@RequestMapping("/save")
	public String savedata(User user, @RequestParam("profileImage") MultipartFile file) 
			throws IOException {
		user.setRole("USER_ROLE");
		user.setEnabled(true);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		if (file.isEmpty()) {
            // if the file is empty then try our message
            System.out.println("File is empty");
            user.setImageUrl("game.png");

       } else {
            // file the file to folder and update the name to contact
            user.setImageUrl(file.getOriginalFilename());
            File saveFile = new ClassPathResource("static/img").getFile();
           Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
           Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
           System.out.println("Image is uploaded");
}
	    services.save(user);
		return "redirect:/";
	}
	
	@RequestMapping("/display")
	public String showUsers(Model model) {
		List<User> listUser = services.listUser();
		model.addAttribute("listUser",listUser);
		return "showusers";
	}
	
	
    
	//User
	@RequestMapping("/edit/{id}")
	public ModelAndView editData(@PathVariable(name="id") Integer id) {
		ModelAndView mav = new ModelAndView("update");
		User user =services.get(id);
		mav.addObject("user",user);
		return mav;
	}
	
	
	@RequestMapping("/delete/{id}")
	public String deleteData(@PathVariable(name="id") Integer id) {
		services.delete(id);
		return "redirect:/display";
	}
	
	@RequestMapping("/login")
	public String loginpage() {
		return"login";
	}
	
	
	
}