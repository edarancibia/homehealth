package com.hhd.util;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamSource;
import java.io.InputStream;

import com.hhd.entities.Mail;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private Environment env; // to get valuse from property file.
	
	@Override
	public void sendAttachmentEmail(Mail email, MultipartFile attachfile) throws MessagingException {     
		emailSender.send(new MimeMessagePreparator() {
            @SuppressWarnings("unlikely-arg-type")
			public void prepare(MimeMessage mimeMessage) throws Exception {
            	// Enable the multipart flag!
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
                String content = "Hola, este mensaje ha sido enviado desde la ficha electrónica de Home Health. Para responder o realizar consultas puede escribir a: saludhogar.osorno@gmail.com.<br>";
				helper.setSubject(email.getSubject());
				helper.setText(content+" <b>Comentario:</b> "+ email.getText(), true);
				System.out.println("In Service=========="+email.getUsername());
				helper.setTo(email.getUsername());
				System.out.println("In Service=========="+env.getProperty("spring.mail.username"));
				helper.setFrom(env.getProperty("spring.mail.username"));
                
                // Determine If There Is An File Upload. If Yes, Attach It To The Client Email              
                if ((attachfile != null) && (attachfile.getSize() > 0) && (!attachfile.equals(""))) {
                    System.out.println("\nAttachment Name?= " + attachfile.getOriginalFilename() + "\n");
                    helper.addAttachment(attachfile.getOriginalFilename(), new InputStreamSource() {                   
                        public InputStream getInputStream() throws IOException {
                            return attachfile.getInputStream();
                        }
                    });
                } else {
                    System.out.println("No Attachment Is Selected By The User. Sending Text Email.");
                    //logic for sending text email.
                }
            }
        });
	}

}
