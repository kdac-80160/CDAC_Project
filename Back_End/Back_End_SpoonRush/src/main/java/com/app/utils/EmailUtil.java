package com.app.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendOtpToEmail(String email, String otp) throws MessagingException
	{
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setTo(email);
		helper.setSubject("OTP verification for SpoonRush");
		
		String htmlContent = "<div style='border: 2px solid #007BFF; padding: 20px;'>"
				+ "<h2 style='color: #007BFF;'>SpoonRush - OTP Verification</h2>"
				+ "<p>Your One Time Password (OTP): <strong style='color: #28a745; font-size: 18px;'>" + otp
				+ "</strong></p>"
				+ "<p style='color: #6c757d; font-size: 14px;'>This OTP is valid for a limited time. Please use it to verify your account.</p>"
				+ "</div>";
		
		helper.setText(htmlContent,true);
		javaMailSender.send(mimeMessage);
	}
}
