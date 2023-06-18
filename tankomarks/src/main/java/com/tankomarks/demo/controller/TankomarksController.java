package com.tankomarks.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
import java.util.UUID;

//import javax.persistence.EntityManager;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tankomarks.demo.model.Capitulo;
//import com.tankomarks.demo.model.Demografia;
import com.tankomarks.demo.model.Manga;
import com.tankomarks.demo.model.Tomo;
import com.tankomarks.demo.model.Usuario;
import com.tankomarks.demo.model.Valoracion;
import com.tankomarks.demo.repository.CapituloRepository;
//import com.tankomarks.demo.repository.DemografiaRepository;
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
	// private DemografiaRepository demografiaRepo;
	
	// @Autowired
	// EntityManager entityManager;
	
	private String idManga1;
    private String idManga2;
    private String idManga3;
	
	
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
			
			List<Manga> manga = mangaRepo.buscarMangasAdmin(busqueda);
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
		} else if (busqueda != null && !(demografia.equals("0"))) {
			
			List<Manga> manga = mangaRepo.buscarMangasFiltradosAdmin(busqueda, demografia);
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
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		if (busqueda != null && demografia.equals("0")) {
			
			List<Manga> manga = mangaRepo.buscarMangas(busqueda, id_usuario);
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
			return "busqueda";
			
		} else if (busqueda != null && !(demografia.equals("0"))) {
			
			List<Manga> manga = mangaRepo.buscarMangasFiltrados(busqueda, demografia, id_usuario);
			model.addAttribute("mangas", manga);
			
			int size = manga.size();
			model.addAttribute("size", size);
			
			return "busqueda";
			
		} else {
			
			if (idManga1 == null) {
				
				model.addAttribute("mangasLeyendo", mangaRepo.mostrarMangasLeyendo(id_usuario));
				model.addAttribute("mangasLeido", mangaRepo.mostrarMangasLeido(id_usuario));
				return "index";
				
			} else {
				
				model.addAttribute("mangasLeyendo", mangaRepo.mostrarMangasOrdenados(idManga1, idManga2, idManga3));
				model.addAttribute("mangasLeido", mangaRepo.mostrarMangasLeido(id_usuario));
				return "index";
				
			}
			
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
    public String mangasPropios(Principal principal, Model model) {
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		model.addAttribute("mangas", mangaRepo.mostrarMangasPropios(id_usuario));
		
        return "administracion";
        
    }
	
	@GetMapping("/adminNuevo")
    public String adminNuevo(Model model) {
		
		model.addAttribute("manga", new Manga());
		
		boolean check;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			
			check = true;
			
		} else {
			
			check = false;
			
		}
		
		model.addAttribute("check", check);
		
        return "formularioManga";
        
    }
	
	@PostMapping("/adminGuardar")
	public String adminGuardar(int id_manga, String nombre, String descripcion, int demografia, @RequestParam("foto") MultipartFile foto, Principal principal) {
		
		try {
			
			String enlacefoto;
			
			if (foto.isEmpty()) {
				
				Manga aux = mangaRepo.mostrarDetallesManga(id_manga);
				enlacefoto = aux.getEnlacefoto();
				
			} else {
				
				if (id_manga != 0) {
					
					Manga manga = mangaRepo.mostrarDetallesManga(id_manga);
					String rutaImagen = "src/main/resources/static/imagesDB/" + manga.getEnlacefoto();
					String rutaLimpia = rutaImagen.replace("../../../imagesDB/", "");
				    Path path = Paths.get(rutaLimpia);
				    Files.delete(path);
				    
				}
				
				byte[] bytes = foto.getBytes();
			      
			    String nombreFotoUnico = UUID.randomUUID().toString();
			    String nombreFotoOriginal = foto.getOriginalFilename();
			    String extensionFoto = nombreFotoOriginal.substring(nombreFotoOriginal.lastIndexOf("."));
			    String nombreFotoFinal = nombreFotoUnico + extensionFoto;
				    
			    Path rutaArchivo = Paths.get("src/main/resources/static/imagesDB/" + nombreFotoFinal); // "../imagesDB/" + nombreFotoFinal
				Files.write(rutaArchivo, bytes);
				    
				String ruta = "../../../imagesDB/";
				enlacefoto = ruta + nombreFotoFinal;
				
			}
			
			// !!!!!!! USAR IF PARA MANGA.SETUSUARIO (COMPROBAR MIRANDO QUE NO SEA ROL ADMINISTRADOR) !!!!!!!
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
				
				if (id_manga == 0) {
					
					mangaRepo.guardarManga(nombre, descripcion, enlacefoto, demografia);
					return "redirect:/administracion?success";
					
				} else {
					
					mangaRepo.actualizarManga(nombre, descripcion, enlacefoto, demografia, id_manga);
					return "redirect:/administracion?success2";
					
				}
				
			} else {
				
				String email = principal.getName();
				int id_usuario = usuarioRepo.getId_usuario(email);
				
				if (id_manga == 0) {
					
					mangaRepo.guardarMangaPropio(nombre, descripcion, enlacefoto, demografia, id_usuario);
					return "redirect:/mangasPropios?success";
					
				} else {
					
					mangaRepo.actualizarMangaPropio(nombre, descripcion, enlacefoto, demografia, id_manga, id_usuario);
					return "redirect:/mangasPropios?success2";
					
				}
				
			}
			
			
		      
		} catch (IOException e) {
			
		    e.printStackTrace();
		    
			return "redirect:/adminNuevo?error";
			
	    }
		
	}
	
	@GetMapping("/adminEditar/{id_manga}")
    public String adminEditar(@PathVariable("id_manga") int id_manga, Model model) {
		
		model.addAttribute("manga", mangaRepo.mostrarDetallesManga(id_manga));
		
		boolean check;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			
			check = true;
			
		} else {
			
			check = false;
			
		}
		
		model.addAttribute("check", check);
		
        return "formularioManga";
        
    }
	
	@GetMapping("/adminEliminar/{id_manga}")
    public String adminEliminar(@PathVariable("id_manga") int id_manga, Model model) throws IOException {
		
		Manga manga = mangaRepo.mostrarDetallesManga(id_manga);
		String rutaImagen = "src/main/resources/static/imagesDB/" + manga.getEnlacefoto();
		String rutaLimpia = rutaImagen.replace("../../../imagesDB/", "");
	    Path path = Paths.get(rutaLimpia);
	    Files.delete(path);
		
		mangaRepo.eliminarManga(id_manga);
		//tomoRepo.eliminarTomoPorManga(id_manga);
		//capituloRepo.eliminarCapitulosPorManga(id_manga);
		
        return "redirect:/administracion";
        
    }
	
	@GetMapping("/adminTomos/{manga_id_manga}")
    public String adminTomos(@PathVariable("manga_id_manga") int id_manga, Model model) {
		
		model.addAttribute("tomos", tomoRepo.mostrarTomos(id_manga));
		model.addAttribute("tomoManga", id_manga);
		
		boolean check;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			
			check = true;
			
		} else {
			
			check = false;
			
		}
		
		model.addAttribute("check", check);
		
        return "adminTomos";
        
    }
	
	@GetMapping("/adminNuevoTomo/{manga_id_manga}")
    public String adminNuevoTomo(Model model, @PathVariable("manga_id_manga") int id_manga) {
		
		model.addAttribute("tomo", new Tomo());
		model.addAttribute("tomoManga", id_manga);
		
        return "formularioTomo";
    
	}
	
	@PostMapping("/adminGuardarTomo")
	public String adminGuardarTomo(int id_tomo, int numero, Integer manga, @RequestParam("foto") MultipartFile foto) {
		
		try {
			
			String enlacefoto;
			
			if (foto.isEmpty()) {
				
				Tomo aux = tomoRepo.VerTomo(id_tomo);
				enlacefoto = aux.getEnlacefoto();
				
			} else {
				
				if (id_tomo != 0) {
					
					Tomo tomo = tomoRepo.VerTomo(id_tomo);
					String rutaImagen = "src/main/resources/static/imagesDB/" + tomo.getEnlacefoto();
					String rutaLimpia = rutaImagen.replace("../../../imagesDB/", "");
				    Path path = Paths.get(rutaLimpia);
				    Files.delete(path);
				    
				}
				
				byte[] bytes = foto.getBytes();
			      
			    String nombreFotoUnico = UUID.randomUUID().toString();
			    String nombreFotoOriginal = foto.getOriginalFilename();
			    String extensionFoto = nombreFotoOriginal.substring(nombreFotoOriginal.lastIndexOf("."));
			    String nombreFotoFinal = nombreFotoUnico + extensionFoto;
				    
			    Path rutaArchivo = Paths.get("src/main/resources/static/imagesDB/" + nombreFotoFinal); // "../imagesDB/" + nombreFotoFinal
				Files.write(rutaArchivo, bytes);
				    
				String ruta = "../../../imagesDB/";
				enlacefoto = ruta + nombreFotoFinal;
				
			}
			
			if (id_tomo == 0) {
				
				tomoRepo.guardarTomo(numero, enlacefoto, manga);
				return "redirect:/adminTomos/" + manga + "?success"; // /" + manga + "?success
				
			} else {
				
				tomoRepo.actualizarTomo(numero, enlacefoto, id_tomo);
				int id_manga = tomoRepo.getMangaPorId_tomo(id_tomo);
				return "redirect:/adminTomos/" + id_manga + "?success2"; // /" + manga + "?success2
				
			}
		      
		} catch (IOException e) {
			
		    e.printStackTrace();
		    
			return "redirect:/adminNuevoTomo?error";
			
	    }
		
	}
	
	@GetMapping("/adminEditarTomo/{id_tomo}")
    public String adminEditarTomo(@PathVariable("id_tomo") int id_tomo, Model model) {
		
		model.addAttribute("tomo", tomoRepo.VerTomo(id_tomo));
		
        return "formularioTomo";
    
	}
	
	@GetMapping("/adminEliminarTomo/{id_tomo}")
    public String adminEliminarTomo(@PathVariable("id_tomo") int id_tomo, Model model, HttpServletRequest request) throws IOException {
		
		Tomo tomo = tomoRepo.VerTomo(id_tomo);
		String rutaImagen = "src/main/resources/static/imagesDB/" + tomo.getEnlacefoto();
		String rutaLimpia = rutaImagen.replace("../../../imagesDB/", "");
	    Path path = Paths.get(rutaLimpia);
	    Files.delete(path);
		
		tomoRepo.eliminarTomo(id_tomo);
		//capituloRepo.eliminarCapitulosPorTomo(id_tomo);
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		
    }
	
	@GetMapping("/adminCapitulos/{tomo_id_tomo}")
    public String adminCapitulos(@PathVariable("tomo_id_tomo") int id_tomo, Model model) {
		
		model.addAttribute("capitulos", capituloRepo.mostrarCapitulos(id_tomo));
		model.addAttribute("capituloTomo", id_tomo);
		model.addAttribute("volver", tomoRepo.VerTomo(id_tomo));
		
		boolean check;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			
			check = true;
			
		} else {
			
			check = false;
			
		}
		
		model.addAttribute("check", check);
		
        return "adminCapitulos";
        
    }
	
	@GetMapping("/adminNuevoCapitulo/{tomo_id_tomo}")
    public String adminNuevoCapitulo(@PathVariable("tomo_id_tomo") int id_tomo, Model model) {
		
		model.addAttribute("capitulo", new Capitulo());
		model.addAttribute("capituloTomo", id_tomo);
		
        return "formularioCapitulo";
        
    }
	
	@PostMapping("/adminGuardarCapitulo")
	public String adminGuardarCapitulo(int id_capitulo, String nombre, Integer tomo) {
			
			if (id_capitulo == 0) {
				
				capituloRepo.guardarCapitulo(nombre, tomo);
				return "redirect:/adminCapitulos/" + tomo + "?success"; 
				
			} else {
				
				capituloRepo.actualizarCapitulo(nombre, id_capitulo);
				int id_tomo = capituloRepo.getTomoPorId_capitulo(id_capitulo);
				return "redirect:/adminCapitulos/" + id_tomo + "?success2";
				
			}
			
	}
	
	@GetMapping("/adminEditarCapitulo/{id_capitulo}")
    public String adminEditarCapitulo(@PathVariable("id_capitulo") int id_capitulo, Model model) {
		
		model.addAttribute("capitulo", capituloRepo.mostrarDetallesCapitulo(id_capitulo));
		
        return "formularioCapitulo";
        
    }
	
	@GetMapping("/adminEliminarCapitulo/{id_capitulo}")
    public String adminEliminarCapitulo(@PathVariable("id_capitulo") int id_capitulo, Model model, HttpServletRequest request) {

		capituloRepo.eliminarCapitulo(id_capitulo);
		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
		
    }
	
	@GetMapping("/leyendo")
    public String leyendo(Model model, Principal principal) {
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		model.addAttribute("mangas", mangaRepo.mostrarTodosMangasLeyendo(id_usuario));
		
        return "leyendo";
    
	}
	
	@PostMapping("/ordenLeyendo")
	public String ordenLeyendo(@RequestParam("value1") String value1, @RequestParam("value2") String value2, @RequestParam("value3") String value3) {
	    
		idManga1 = value1;
		idManga2 = value2;
		idManga3 = value3;

	    return "redirect:/";
			
	}
	
	@GetMapping("/leidos")
    public String leidos(Model model, Principal principal) {
		
		String email = principal.getName();
		int id_usuario = usuarioRepo.getId_usuario(email);
		
		model.addAttribute("mangas", mangaRepo.mostrarTodosMangasLeido(id_usuario));
		
        return "leidos";
        
    }
	
	@GetMapping("/404")
    public String handle404Error() {
        return "error";
    }

}
