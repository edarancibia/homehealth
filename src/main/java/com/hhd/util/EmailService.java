package com.hhd.util;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.hhd.entities.Mail;

public interface EmailService {

	public void sendAttachmentEmail(Mail email, MultipartFile file) throws MessagingException;
}
