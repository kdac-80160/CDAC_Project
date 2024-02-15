package com.app.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfiguration {
	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.port}")
	private String mailPort;
	
	@Value("${spring.mail.username}")
	private String userName;
	
	@Value("${spring.mail.password}")
	private String userPassword;
	
	@Bean
	public JavaMailSender getMailSender()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailHost);
		mailSender.setPort(Integer.parseInt(mailPort));
		mailSender.setUsername(userName);
		mailSender.setPassword(userPassword);
		Properties prop = mailSender.getJavaMailProperties();
		/**
		 * props.put("mail.smtp.starttls.enable", "true"): This sets the "mail.smtp.starttls" property to "true," 
		 * enabling the use of Transport Layer Security (TLS) 
		 * for secure communication with the SMTP server
		 * */
		prop.put("mail.smtp.starttls.enable", "true");
		return mailSender;
	}
}
