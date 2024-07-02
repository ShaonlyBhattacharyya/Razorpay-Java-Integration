<%@ page import="java.util.Scanner" %>

<%
// Get the OTP from the session
int storedOtp = (int) session.getAttribute("otp");

// Check if the otp parameter exists in the request
String otpParameter = request.getParameter("otp");
if (otpParameter == null || otpParameter.isEmpty()) {
    // Handle case where OTP is not provided
    out.println("<script>alert('Please enter the OTP.'); window.location='register.html';</script>");
} else {
    // Parse the OTP entered by the user
    int enteredOtp = Integer.parseInt(otpParameter.trim());

    // Check if entered OTP matches the stored OTP
    if (enteredOtp == storedOtp) {
        // Redirect to Dashboard or Success page
        response.sendRedirect("dashboard.html");
    } else {
        // Show error message
        out.println("<script>alert('Invalid OTP. Please try again.'); window.location='register.html';</script>");
    }
}
%>

