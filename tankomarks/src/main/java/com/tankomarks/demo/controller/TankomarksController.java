package com.tankomarks.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tankomarks.demo.model.Manga;
import com.tankomarks.demo.repository.MangaRepository;

@Controller
public class TankomarksController {
	
	@Autowired
	private MangaRepository mangaRepo;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model, String busqueda) {
		
		if (busqueda != null) {
			
			List<Manga> manga = mangaRepo.buscarMangas(busqueda); 
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
			return "busqueda";
			
		} else {
			
			model.addAttribute("mangas", mangaRepo.mostrarMangas()); //provisional, solo quiero mostrar leyendos y leídos
			return "index";
			
		}
		
	}
	
	@GetMapping("/favoritos")
	public String favoritos() {
		return "favoritos";
	}
	
	@GetMapping("/perfil")
	public String perfil() {
		return "perfil";
	}

}
