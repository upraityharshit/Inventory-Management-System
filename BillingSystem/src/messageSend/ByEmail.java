package messageSend;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

public class ByEmail {
	
	public String SendEmail(String rec,String Subject,String Text){
		String subject=Subject;//"Java Message";
		String text=Text;//"This is Java";
		String to=rec;//"upraityharshit@gmail.com";
		String from="shanuupraity@gmail.com";
		String password="9760493277";
		
		Properties pro= System.getProperties();
		pro.setProperty("mail.smtp.auth", "true");
		pro.setProperty("mail.smtp.starttls.enable", "true");
		pro.setProperty("mail.smtp.host", "smtp.gmail.com");
		pro.setProperty("mail.smtp.port", "587");
		
		Session session=Session.getDefaultInstance(pro,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(from,password);
			}			
		});
		try{
			MimeMessage mess=new MimeMessage(session);
			mess.setFrom(new InternetAddress(from));
			mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mess.setSubject(subject);
			mess.setText(text);
			Transport.send(mess);
			//System.out.println("Send Done");
			return "ok";
		}
		catch(MessagingException e){
			return "not ok";
		}		
	}

	public static void main(String[] args) {
	}

}
