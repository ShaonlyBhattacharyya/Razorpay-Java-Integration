<%@ page import="java.util.Properties" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="java.util.Random" %>

<%
// Get form data
String name = request.getParameter("name");
String email = request.getParameter("email");
String mobile = request.getParameter("mobile");

// Generate OTP
Random rand = new Random();
int otp = rand.nextInt(900000) + 100000; // Generate 6-digit OTP

// Send OTP to Email
String fromEmail = "bhattacharyyashaonly@gmail.com"; // Sender's email address
String host = "smtp.gmail.com"; // SMTP server host
String port = "587"; // SMTP server port
String usernameSMTP = "bhattacharyyashaonly@gmail.com"; // SMTP server username
String passwordSMTP = "hkgugtuunewnwfca"; // SMTP server password

Properties props = new Properties();
props.put("mail.smtp.host", host);
props.put("mail.smtp.port", port);
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");

// Create session with authentication
Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usernameSMTP, passwordSMTP);
    }
});

try {
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(mailSession);
    message.setFrom(new InternetAddress(fromEmail));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
    message.setSubject("OTP for Registration");

    // Email content
    String content = "Hello " + name + ",\n\n"
                   + "Your OTP for registration is: " + otp + "\n\n"
                   + "Enter this OTP to complete your registration.\n\n"
                   + "Thank you,\n"
                   + "Your Website Team";

    // Set email content
    message.setText(content);

    // Send message
    Transport.send(message);

    // Store OTP in session attribute
    session.setAttribute("otp", otp);
    
    // Forward to OTP verification form
    response.sendRedirect("verify-otp.html");

} catch (MessagingException mex) {
    // Show error message with more details
    out.println("<script>alert('Error sending OTP. Please try again later.');</script>");
    mex.printStackTrace();
}
%>
