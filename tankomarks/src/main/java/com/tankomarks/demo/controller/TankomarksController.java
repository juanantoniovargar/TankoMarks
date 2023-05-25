package com.tankomarks.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tankomarks.demo.model.Manga;
import com.tankomarks.demo.model.Usuario;
import com.tankomarks.demo.model.Valoracion;
import com.tankomarks.demo.repository.MangaRepository;
import com.tankomarks.demo.repository.UsuarioRepository;
import com.tankomarks.demo.repository.ValoracionRepository;

@Controller
public class TankomarksController {
	
	@Autowired
	private MangaRepository mangaRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private ValoracionRepository valoracionRepo;
	
	
	@GetMapping("/login")
	public String login() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	            return "redirect:/administracion";
			} else {
				return "redirect:/";
			}
		} else {
			return "login";
		}
		
	}
	
	@GetMapping("/administracion")
	public String administracion(Model model, String busqueda, String demografia) {
		
		if (busqueda != null && demografia.equals("0")) {
			
			List<Manga> manga = mangaRepo.buscarMangas(busqueda);
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
		} else if (busqueda != null && !(demografia.equals("0"))) {
			
			List<Manga> manga = mangaRepo.buscarMangasFiltrados(busqueda, demografia);
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
		} else {
			model.addAttribute("mangas", mangaRepo.mostrarMangas());
		}
		
		return "administracion";
		
	}
	
	@GetMapping("/")
	public String home(Model model, Principal principal, String busqueda, String demografia) {
		
		if (busqueda != null && demografia.equals("0")) {
			
			List<Manga> manga = mangaRepo.buscarMangas(busqueda);
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
			return "busqueda";
			
		} else if (busqueda != null && !(demografia.equals("0"))) {
			
			List<Manga> manga = mangaRepo.buscarMangasFiltrados(busqueda, demografia);
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
			return "busqueda";
			
		} else {
			
			String email = principal.getName();
			int id_usuario = usuarioRepo.getId_usuario(email);
			model.addAttribute("mangasLeyendo", mangaRepo.mostrarMangasLeyendo(id_usuario));
			model.addAttribute("mangasLeido", mangaRepo.mostrarMangasLeido(id_usuario));
			return "index";
			
		}
		
	}
	
	@GetMapping("/favoritos")
	public String favoritos(Model model, Principal principal, String busqueda) {
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		if (busqueda != null) {
			
			List<Valoracion> valoracion = valoracionRepo.buscarValoraciones(busqueda, id_usuario);
			model.addAttribute("valoraciones", valoracion);
			
			int size = valoracion.size();
			model.addAttribute("size", size);
			
		} else {
			model.addAttribute("valoraciones", valoracionRepo.mostrarValoraciones(id_usuario));
		}
		
		return "favoritos";
		
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model, Principal principal) {
		
		String email = principal.getName();
		Usuario usuario = usuarioRepo.findByEmail(email);
		
		model.addAttribute("usuario", usuario);
		
		return "perfil";
		
	}
	
	@PostMapping("/guardarPerfil")
	public String guardarPerfil(String nombre, HttpServletRequest request, Principal principal) {
		
		String email = principal.getName();
		usuarioRepo.actualizarPerfil(nombre, email);
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		
	}
	
	@GetMapping("/detalles/{id_manga}")
	public String detalles(@PathVariable("id_manga") int id_manga, Model model) {
		
		model.addAttribute("manga", mangaRepo.mostrarDetallesManga(id_manga));
		
		return "detalles";
	}
	
	@GetMapping("/404")
    public String handle404Error() {
        return "error";
    }

}
