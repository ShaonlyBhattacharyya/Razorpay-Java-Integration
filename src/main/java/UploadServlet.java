/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 *
 * @author Devil
 */
@WebServlet(urlPatterns = {"/UploadServlet"})
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");

        InputStream pdfInputStream = null;
        InputStream is1 = null;
        InputStream is2 = null;
        InputStream is3 = null;
        InputStream is4 = null;
        InputStream is5 = null;

        Part filePart = request.getPart("file1");
        Part filePart1 = request.getPart("img1");
        Part filePart2 = request.getPart("img2");
        Part filePart3 = request.getPart("img3");
        Part filePart4 = request.getPart("img4");
        Part filePart5 = request.getPart("img5");

        if (filePart != null) {
            pdfInputStream = filePart.getInputStream();
        }
        if (filePart1 != null) {
            is1 = filePart1.getInputStream();
        }
        if (filePart2 != null) {
            is2 = filePart2.getInputStream();
        }
        if (filePart3 != null) {
            is3 = filePart3.getInputStream();
        }
        if (filePart4 != null) {
            is4 = filePart4.getInputStream();
        }
        if (filePart5 != null) {
            is5 = filePart5.getInputStream();
        }

        String jdbcUrl = "jdbc:mysql://localhost:3306/hello";
        String dbUser = "root";
        String dbPassword = "ShaonlyTatini@123";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
    String sql = "INSERT INTO filedownload (fname, lname, email, cv, img1, img2, img3, img4, img5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, fname);
        preparedStatement.setString(2, lname);
        preparedStatement.setString(3, email);

        if (pdfInputStream != null) {
            preparedStatement.setBlob(4, pdfInputStream);
        }
        if (is1 != null) {
            preparedStatement.setBlob(5, is1);
        }
        if (is2 != null) {
            preparedStatement.setBlob(6, is2);
        }
        if (is3 != null) {
            preparedStatement.setBlob(7, is3);
        }
        if (is4 != null) {
            preparedStatement.setBlob(8, is4);
        }
        if (is5 != null) {
            preparedStatement.setBlob(9, is5);
        }

        preparedStatement.executeUpdate();
        response.sendRedirect("success.html"); // Redirect to success page
    }
} catch (SQLException e) {
    e.printStackTrace();
    response.sendRedirect("error.html");
} finally {
    // Close input streams
    if (pdfInputStream != null) {
        pdfInputStream.close();
    }
    if (is1 != null) {
        is1.close();
    }
    if (is2 != null) {
        is2.close();
    }
    if (is3 != null) {
        is3.close();
    }
    if (is4 != null) {
        is4.close();
    }
    if (is5 != null) {
        is5.close();
    }
        }
    }
}