package com.hhd.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.Mail;
import com.hhd.util.EmailService;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/email")
public class EmailController {

	private static Logger log = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/")
	public ModelAndView index(Model model, HttpSession session) {
		ModelAndView mv = new ModelAndView("sendmail");
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/sendAttachmentEmail", consumes = "multipart/form-data", method = RequestMethod.POST)
	public ModelAndView sendEmailWithAttachment(@ModelAttribute("attachmentEmail") Mail mail,Model model,
			@RequestParam("to") String to,
			@RequestParam("comment") String commnent,
			BindingResult br, final @RequestParam("attachment") MultipartFile attachFile) throws MessagingException {
		try {
			ModelAndView mav = new ModelAndView("sendmail");
			log.info("Spring Boot - Sending Attachment Email...");
			if (br.hasErrors()) {
				log.error("Something gone wrong...");
				return new ModelAndView("attachment-email");
			} else {
				// reads form input
				final String email_ = to;
				final String name = "Usuario de homehealth";
				final String subject = "Mensaje de Home Health";
				final String comment = commnent;

				log.info(name + " " + email_ + " " + subject + " " + comment);

				if ((attachFile != null) && (attachFile.getSize() > 0) && (!attachFile.equals(""))) {
					log.info("FileName=====" + attachFile.getOriginalFilename());
				} else {
					log.info("FileName=====" + attachFile.getOriginalFilename() + " " + attachFile);
				}
				mail.setUsername(email_);
				mail.setTo(email_);
				mail.setSubject(subject);
				mail.setText(comment);
				//mav.addObject("name", mail.getUsername());
				log.info("Sening Attachment Email...");
				emailService.sendAttachmentEmail(mail, attachFile);
				model.addAttribute("success","El mensaje fue enviado exitosamente!");
				log.info("Done...");
				return mav;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return null; //new ModelAndView("attachment-email");
		}
	}
	
}
