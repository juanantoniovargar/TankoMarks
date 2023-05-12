package com.tankomarks.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tankomarks.demo.service.UsuarioService;
import com.tankomarks.demo.dto.RegistroDto;

@Controller
@RequestMapping("/registro")
public class RegistroController {
	
	private UsuarioService usuarioService;

	public RegistroController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public RegistroDto RegistroDto() {
		return new RegistroDto();
	}
	
	@GetMapping
	public String mostrarRegistroForm() {
		return "registro";
	}
	
	@PostMapping
	public String registrarUsuario(@ModelAttribute("usuario") RegistroDto registroDto) {
		try{
			usuarioService.guardar(registroDto);
		}catch(Exception e) {
			return "redirect:/registro?fail";		
		}
		return "redirect:/registro?success";
	}

}
