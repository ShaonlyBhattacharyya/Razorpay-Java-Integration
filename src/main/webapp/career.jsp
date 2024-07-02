<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>

<%
    // Database connection details
    String jdbcUrl = "jdbc:mysql://localhost:3306/hello";
    String dbUser = "root";
    String dbPassword = "ShaonlyTatini@123";

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the database connection
        connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

        // Retrieve form data
        String selectedJob = request.getParameter("selectedJob");

        // Insert data into the "career" table
        String sql = "INSERT INTO career (selected_job) VALUES (?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, selectedJob);

        // Execute the SQL statement
        preparedStatement.executeUpdate();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application Submitted</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
        }

        p {
            color: #333;
        }
    </style>
</head>
<body>

    <h1>Application Submitted</h1>
    <p>Thank you for applying for the position. Your application has been submitted successfully.</p>

</body>
</html>
