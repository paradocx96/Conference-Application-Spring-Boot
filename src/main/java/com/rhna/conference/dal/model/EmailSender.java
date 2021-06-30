//Name : Malwatta H.G.
//ID : IT19240848
//Group : REG_WE_03

package com.rhna.conference.dal.model;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	
	@Autowired
	JavaMailSender mailSender;
	
	private String toAddress;
	private final String fromAddress = "rhnaconference@gmail.com";
	private final String senderName = "RHNA Conference";
	private final String subject = "Thank your for registration!";
	private String content = "<h4>Dear [[name]], </h4><br>"
			+ "You're account has been successfully registered! <br>"
			+ "Your account is now ready to use."
			+ "<h3><a href=\"[[URL]]\" target=\"_self\">click here to visit!</a></h3><br>"
			+ "Thank you,<br>"
			+ "RHNA Conference.<br>"
			+ "CEO : Hirush Gimhan</h3><br>";
	
	private MimeMessage message;
	private MimeMessageHelper helper;
	private String username;
	private String email;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void sendEmail() throws UnsupportedEncodingException, MessagingException {
		
		 message = mailSender.createMimeMessage();
		 helper = new MimeMessageHelper(message);
		 
		 toAddress = getEmail();
		 
		 helper.setFrom(fromAddress, senderName);
		 helper.setTo(toAddress);
		 helper.setSubject(subject);
			
		 content = content.replace("[[name]]", getUsername());
		 String verifyURL = "https://deploymentv2.dyz7y76o29wov.amplifyapp.com/sign-in";
		 content = content.replace("[[URL]]", verifyURL);
		 helper.setText(content, true);
			
		 mailSender.send(message);
			
		 System.out.println("Email has been sent");
			
		
	}
	

}
