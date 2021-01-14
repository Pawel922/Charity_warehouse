package pl.coderslab.charity.service;

public interface EmailSender {
	void sendEmail(String to, String subject, String content);
}
