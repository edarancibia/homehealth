package com.hhd.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.AmazonClientException;
import com.hhd.entities.File;
import com.hhd.impl.AmazonClient;
import com.hhd.impl.FileServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/storage")
public class BucketController {

	private static final Log LOG = LogFactory.getLog(BucketController.class);
	
	@Autowired
	public FileServiceImpl filesService;
	
	private AmazonClient amazonClient;
	
	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("files");
		model.addAttribute("idficha", idficha);
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
	}

	@Autowired
	BucketController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	/*@PostMapping("/uploadFile")
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
		LOG.info("la url es: "+ this.amazonClient.uploadFile(file));
		String url = this.amazonClient.uploadFile(file);
		return url;
	}*/
	
	@PostMapping("/uploadFile")
	public ModelAndView uploadFile(@RequestPart(value = "file") MultipartFile file,
			@RequestParam(value = "fileName2") String filename2, @RequestParam(value = "idficha") int idficha,
			@RequestParam(value = "rutpac") int rutpac, HttpSession session,
			File file2,Model model) throws IOException{
		
		String url = amazonClient.uploadFile(file);
		url = url.substring(54);
		LOG.info("los datos: " + url);
		//String url2 = file.getOriginalFilename();
		String url1 = "https://home-health-osorno.s3.us-east-2.amazonaws.com/";
		
		String urlFinal = url1 + url;
		File fileNew = new File();
		fileNew.setIdFicha(idficha);
		fileNew.setFecha(new Date());
		fileNew.setDescripcion(filename2);
		fileNew.setUrl(urlFinal);
		fileNew.setRutPac(rutpac);
		
		String rut = session.getAttribute("rutusu").toString().substring(0,8);
		fileNew.setRutUsu(Integer.parseInt(rut.trim()));
		filesService.addFile(fileNew);
		model.addAttribute("idficha", idficha);
		return this.index(idficha, model, session);
	}

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}
	
	@GetMapping("/files-list/{rutpac}")
	public @ResponseBody List<Map<String, Object>> getFilesByFicha(@PathVariable int rutpac){
		List<Map<String, Object>> files = filesService.fingFilesByRutPac(rutpac);
		return files;
	}
}
