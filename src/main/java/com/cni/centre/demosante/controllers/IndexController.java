package com.cni.centre.demosante.controllers;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.cni.centre.demosante.beans.Medicament;

@Controller
public class IndexController {

	
	@GetMapping("/index")
	public String getMessage(Model model) {
		
		List<Medicament> listmed=Arrays.asList(
				new Medicament("panadol", "calmant"),
				new Medicament("doliprane", "calmant"));
		model.addAttribute("listmed",listmed);
		return "index";
		 
		
	}	
}
