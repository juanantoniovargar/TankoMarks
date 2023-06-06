package com.tankomarks.demo.controller;

import java.security.Principal;
import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.Query;
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
//import org.springframework.web.bind.annotation.RequestParam;

import com.tankomarks.demo.model.Capitulo;
import com.tankomarks.demo.model.Manga;
import com.tankomarks.demo.model.Usuario;
import com.tankomarks.demo.model.Valoracion;
import com.tankomarks.demo.repository.CapituloRepository;
import com.tankomarks.demo.repository.MangaRepository;
import com.tankomarks.demo.repository.TomoRepository;
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

	@Autowired
	private TomoRepository tomoRepo;
	
	@Autowired
	private CapituloRepository capituloRepo;
	
	// @Autowired
	// EntityManager entityManager;
	
	
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
	public String detalles(@PathVariable("id_manga") int id_manga, Model model, Principal principal) {
		
		String email = principal.getName();
		model.addAttribute("usuario", usuarioRepo.findByEmail(email));
		
		model.addAttribute("manga", mangaRepo.mostrarDetallesManga(id_manga));
		
		return "detalles";
		
	}
	
	@PostMapping("/actualizarManga")
	public String actualizarManga(boolean activadoLeer, boolean activadoLeido, int id_manga, HttpServletRequest request, Principal principal) {
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		if (activadoLeido && activadoLeer && mangaRepo.verificaLeido(id_usuario, id_manga) == 1) {

        	mangaRepo.eliminarLeido(id_usuario, id_manga);
			mangaRepo.activarLeyendo(id_usuario, id_manga);
			
		} else if (activadoLeido && activadoLeer && mangaRepo.verificaLeyendo(id_usuario, id_manga) == 1) {

        	mangaRepo.eliminarLeyendo(id_usuario, id_manga);
			mangaRepo.activarLeido(id_usuario, id_manga);
			
		}
		
		if (activadoLeer && !activadoLeido) {
			
			mangaRepo.activarLeyendo(id_usuario, id_manga);
			
        } else if (!activadoLeer && mangaRepo.verificaLeyendo(id_usuario, id_manga) == 1) {
        	
        	mangaRepo.eliminarLeyendo(id_usuario, id_manga);
        	
        }
		
		if (activadoLeido && !activadoLeer) {
			
			mangaRepo.activarLeido(id_usuario, id_manga);
			
        } else if (!activadoLeido && mangaRepo.verificaLeido(id_usuario, id_manga) == 1) {
        	
        	mangaRepo.eliminarLeido(id_usuario, id_manga);
        
        }
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		
	}
	
	@GetMapping("/tomos/{manga_id_manga}")
	public String tomos(@PathVariable("manga_id_manga") int manga, Model model, Principal principal) {
		
		String email = principal.getName();
		
		model.addAttribute("tomos", tomoRepo.mostrarTomos(manga));
		model.addAttribute("nombreTomos", tomoRepo.mostrarNombreMangaTomos(manga));
	    model.addAttribute("usuario", usuarioRepo.findByEmail(email));
		
		return "tomos";
		
	}
	
	@GetMapping("/capitulos/{tomo_id_tomo}")
	public String capitulos(@PathVariable("tomo_id_tomo") int tomo, Model model, Principal principal) {
		
		String email = principal.getName();
	    model.addAttribute("usuario", usuarioRepo.findByEmail(email));
		
		model.addAttribute("capitulos", capituloRepo.mostrarCapitulos(tomo));
		model.addAttribute("imagenCapitulos", capituloRepo.mostrarImagenTomoCapitulos(tomo));
		
		return "capitulos";
		
	}
	
	@GetMapping("/detallesCapitulo/{capitulo_id_capitulo}")
	public String detallesCapitulo(@PathVariable("capitulo_id_capitulo") int id_capitulo, Model model, Principal principal, HttpServletRequest request) {
		
		String referer = request.getHeader("Referer");
	    model.addAttribute("referer", referer);
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		Capitulo capitulo = capituloRepo.verificarEstado(id_usuario, id_capitulo);
		boolean estaActivado = (capitulo != null) ? true : false;

        model.addAttribute("estaActivado", estaActivado);
        
        model.addAttribute("capitulo", capituloRepo.mostrarDetallesCapitulo(id_capitulo));
        
        if (valoracionRepo.mostrarDetalleValoracion(id_usuario, id_capitulo) == null) {
        	model.addAttribute("valoracion", new Valoracion());
        } else {
        	model.addAttribute("valoracion", valoracionRepo.mostrarDetalleValoracion(id_usuario, id_capitulo));
        }
		
		return "detallesCapitulo";
		
	}
	
	@PostMapping("/guardarValoracion")
	public String guardarValoracion(int id_capitulo, boolean favorito, String comentario, HttpServletRequest request, Principal principal) {
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		if (valoracionRepo.mostrarDetalleValoracion(id_usuario, id_capitulo) == null) {
			valoracionRepo.crearValoracion(favorito, comentario, id_capitulo, id_usuario);
        } else {
        	valoracionRepo.modificarValoracion(favorito, comentario, id_capitulo, id_usuario);
        }
		
		valoracionRepo.eliminarValoracion();
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		
	}
	
	@PostMapping("/actualizarCapitulo")
	public String actualizarCapitulo(boolean activado, int id_capitulo, int id_tomo, HttpServletRequest request, Principal principal) {
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		if (activado) {
            capituloRepo.activarLeido(id_usuario, id_capitulo);
        } else {
        	capituloRepo.eliminarLeido(id_usuario, id_capitulo);
        }
		
		if ((capituloRepo.verificaLeyendoTomo(id_usuario, id_tomo) == 0)) {
			
			if (activado) {
				capituloRepo.activarLeyendoTomo(id_usuario, id_tomo);
	        } else {
	        	capituloRepo.eliminarLeyendoTomo(id_usuario, id_tomo);
	        }
			
			if ((capituloRepo.numCapitulosLeidos(id_usuario, id_tomo)) == (capituloRepo.totalCapitulosPorTomo(id_tomo) - 1)) {
				capituloRepo.eliminarLeidoTomo(id_usuario, id_tomo);
				if (!activado) {
					capituloRepo.activarLeyendoTomo(id_usuario, id_tomo);
				}
				
			}
			
		}
		
		if ((capituloRepo.numCapitulosLeidos(id_usuario, id_tomo)) == (capituloRepo.totalCapitulosPorTomo(id_tomo))) {
			capituloRepo.eliminarLeyendoTomo(id_usuario, id_tomo);
			capituloRepo.activarLeidoTomo(id_usuario, id_tomo);
		}
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		
	}
	
	@GetMapping("/mangasPropios")
    public String mangasPropios() {
        return "";
    }
	
	@GetMapping("/leyendo")
    public String leyendo() {
        return "";
    }
	
	@GetMapping("/leidos")
    public String leidos() {
        return "";
    }
	
	@GetMapping("/adminNuevo")
    public String adminNuevo() {
        return "";
    }
	
	@GetMapping("/adminEditar")
    public String adminEditar() {
        return "";
    }
	
	@GetMapping("/adminEliminar/{id_manga}")
    public String adminEliminar(@PathVariable("id_manga") int id_manga, Model model) {
		
		mangaRepo.eliminarManga(id_manga);
		
        return "redirect:/administracion";
        
    }
	
	@GetMapping("/adminTomos")
    public String adminTomos() {
		
		
		
        return "adminTomos";
        
    }
	
	@GetMapping("/adminNuevoTomo")
    public String adminNuevoTomo() {
        return "";
    }
	
	@GetMapping("/adminEditarTomo")
    public String adminEditarTomo() {
        return "";
    }
	
	@GetMapping("/adminEliminarTomo")
    public String adminEliminarTomo() {
        return "";
    }
	
	@GetMapping("/adminCapitulos")
    public String adminCapitulos() {
        return "";
    }
	
	@GetMapping("/adminNuevoCapitulo")
    public String adminNuevoCapitulo() {
        return "";
    }
	
	@GetMapping("/adminEditarCapitulo")
    public String adminEditarCapitulo() {
        return "";
    }
	
	@GetMapping("/adminEliminarCapitulo")
    public String adminEliminarCapitulo() {
        return "";
    }
	
	@GetMapping("/404")
    public String handle404Error() {
        return "error";
    }

}
