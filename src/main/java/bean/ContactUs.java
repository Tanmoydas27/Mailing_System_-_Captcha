package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactUs {
	
	
	private int id;
	private String name;
	private String email;
	private String phone;
	private String message;
		
		

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public int SendMessages()  throws SQLException{
		
		Connection conn = DBconnect.connct();
		
		String sql = "insert into inquires(name, email, phone, message)values(?,?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, phone);
		ps.setString(4, message);
		
		int response =  ps.executeUpdate();
		
		if(response == 1)
		{
			sendNotification();
		}
		
		conn.close();
		
		return response;
		
	}
	
	
	public void sendNotification() {
        // Mention the Recipient's email address
        String to = email;
        // Mention the Sender's email address
        String from = "tanmoydas271002@gmail.com";
        // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
        String host = "smtp.gmail.com";
        
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tanmoydas271002@gmail.com", "upcqorydtbcdrxsj");
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message1 = new MimeMessage(session);
            // Set From: header field of the header.
            message1.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message1.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message1.setSubject("Contact Notifiction");
            // Now set the actual message
            message1.setText("Thank you "+ name + ". For connecting with us. My Team will connect u ASAP.");
            System.out.println("sending...");
            // Send message
            Transport.send(message1);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		ContactUs con = new ContactUs();
		
		con.sendNotification();
		
	}
}
