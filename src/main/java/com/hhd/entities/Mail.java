package com.hhd.entities;

import java.util.Date;

public class Mail {

	private String username;
    private String to;
    private String subject;
    private String text;
    private File file;
    private Date sendDate;
    
    
    public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public Date getSendDate() {
		return sendDate;
	}


	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Mail(String username, String to, String subject, String text, File file, Date sendDate) {
		this.username = username;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.file = file;
		this.sendDate = sendDate;
	}


	public Mail(){}
}
