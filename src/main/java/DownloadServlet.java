import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@WebServlet(urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hello";
        String dbUser = "root";
        String dbPassword = "ShaonlyTatini@123";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String sql = "SELECT cv, img1, img2, img3, img4, img5 FROM filedownload";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ZipOutputStream zos = new ZipOutputStream(baos);

                int fileCounter = 1;
                while (resultSet.next()) {
                    for (int i = 1; i <= 6; i++) {
                        InputStream inputStream = resultSet.getBinaryStream(i);
                        if (inputStream != null) {
                            String fileName = "file" + fileCounter + ".pdf"; // Unique file name
                            zos.putNextEntry(new ZipEntry(fileName));
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = inputStream.read(buffer)) > 0) {
                                zos.write(buffer, 0, len);
                            }
                            zos.closeEntry();
                            fileCounter++;
                        }
                    }
                }

                zos.close();
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"files.zip\"");

                try (OutputStream os = response.getOutputStream()) {
                    baos.writeTo(os);
                    os.flush();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
