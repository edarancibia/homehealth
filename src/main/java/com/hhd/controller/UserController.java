package com.hhd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.Usuario;
import com.hhd.impl.UsuarioServiceImpl;
import com.hhd.util.SendMailService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/")
public class UserController {
	
	@Autowired
	public UsuarioServiceImpl usuarioService;
	
	@Autowired
	public SendMailService mailService;

	private static final Log LOG = LogFactory.getLog(UserController.class);
	
	@GetMapping("")
	public ModelAndView indexLogin(Usuario usuario) {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView index(Model model,@ModelAttribute(name="usuario") Usuario usuario,@RequestParam(name="errormail",required = false)String errormail,
			@RequestParam(name="success",required = false)String success) {
		ModelAndView mv = new ModelAndView("user-register");
		model.addAttribute("success",success);
		model.addAttribute("errormail",errormail);
		return mv;
	}
	
	@PostMapping("/home")
	public ModelAndView login(@ModelAttribute(name="usuario") Usuario usuario,
			@RequestParam("email") String email,
			@RequestParam("clave") String clave,
			HttpSession session, Model model) {
		
		String vista;
		//ModelAndView mv = new ModelAndView(vista);
		String vigente = "S";
		Usuario usuDb = usuarioService.findByEmailAndClaveAndVigente(email, clave, vigente);
		
		if(null !=  usuDb) {
			Usuario usu = usuarioService.findByEmail(email);
			session.setAttribute("idusuario", usu.getId());
			session.setAttribute("role", usu.getRole());
			session.setAttribute("username", usu.getNombre()+' '+usu.getaPat()+ ' '+ usu.getaMat());
			session.setAttribute("rutusu",usuDb.getRutNum());
			model.addAttribute("rutusu", session.getAttribute("rutusu"));
			model.addAttribute("username", session.getAttribute("username"));
			LOG.info("usuario rut: " +session.getAttribute("rutusu"));
			vista = "inicio";
			return new ModelAndView(vista);
			
		}else {
			vista = "login";
			model.addAttribute("errormail","Usuario o contraseña incorrectos");
			return new ModelAndView(vista);
		}
		
	}
	
	@PostMapping("/save")
	public ModelAndView addUser(@ModelAttribute(name = "usuario") Usuario usuario, Model model,
			@RequestParam("email") String email){
		ModelAndView mv = new ModelAndView("user-register");
		Usuario userDb = usuarioService.findByEmail(email);
		
		if(userDb != null) {
			model.addAttribute("errormail","el correo ya existe");
			//return "redirect:/user-register?errormail=1";
			return mv;
		}else {
			usuario.setVigente("S");
			usuarioService.addUsuario(usuario);
			model.addAttribute("result", 1);
			model.addAttribute("success","El usuario fue registrado exitosamente!");
			//return "redirect:/user-register?success=1";
			return mv;
		}
		
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    return new ModelAndView("login") ;  //Where you go after logout here.
	}
	
	@GetMapping("/forgot-pass")
	public ModelAndView recuperarPass(Model model,@ModelAttribute(name="usuario") Usuario usuario,@RequestParam(name="errormail",required = false)String errormail,
			@RequestParam(name="success",required = false)String success) {
		ModelAndView mv = new ModelAndView("recuperar-pass");
		model.addAttribute("success",success);
		model.addAttribute("errormail",errormail);
		return mv;
	}
	
	@PostMapping("/send-pass")
	public ModelAndView sendPass(@ModelAttribute(name = "usuario") Usuario usuario,
			Model model,@RequestParam("email") String email) {
		ModelAndView mv = new ModelAndView("recuperar-pass");
		Usuario userBd = usuarioService.findByEmail(email);
		
		if(userBd == null) {
			model.addAttribute("errormail","el correo no es valido");
			return mv;
		}else {
			//envia correo al invitado
			String message = "Hola!, tu contraseña es: "
			+ userBd.getClave() +
					". Para iniciar sesión click aqui: \n" + "https://hhosorno.herokuapp.com";
			String subject = "Recuperar contraseña Home Health";
	        mailService.sendMail("ficha.hho@gmail.com",email,subject,message);
	        model.addAttribute("result", 1);
	        model.addAttribute("success","Contraseña enviada exitosamente");
	        return mv;
	        
		}
		
	}
	
}
