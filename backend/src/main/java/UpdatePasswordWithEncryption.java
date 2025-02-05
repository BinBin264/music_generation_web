//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.security.NoSuchAlgorithmException;
//import java.sql.*;
//import java.util.Base64;
//
//public class UpdatePasswordWithEncryption {
//
//    // Cấu hình kết nối cơ sở dữ liệu
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/pbl6"; // Địa chỉ database
//    private static final String USER = "root"; // Tên người dùng
//    private static final String PASSWORD = "123456"; // Mật khẩu
//
//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
//        keyGenerator.init(512); // 512-bit key for HMAC-SHA-512
//        SecretKey secretKey = keyGenerator.generateKey();
//        String base64Secret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//        System.out.println("Base64 Secret: " + base64Secret);
//
////        // Thông tin người dùng muốn cập nhật
////        String email = "john.doe@example.com"; // Email của người dùng cần cập nhật mật khẩu
////        String newPassword = "password123"; // Mật khẩu mới
////
////        // Mã hóa mật khẩu mới
////        String encodedPassword = encodePassword(newPassword);
////
////        // Cập nhật mật khẩu vào cơ sở dữ liệu
////        updatePasswordInDatabase(email, encodedPassword);
////    }
////
////    // Mã hóa mật khẩu bằng BCryptPasswordEncoder
////    private static String encodePassword(String password) {
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        return passwordEncoder.encode(password);
////    }
////
////    // Cập nhật mật khẩu vào cơ sở dữ liệu
////    private static void updatePasswordInDatabase(String email, String encodedPassword) {
////        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
////            // SQL query để cập nhật mật khẩu
////            String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
////
////            // Tạo PreparedStatement để thực thi truy vấn
////            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
////                // Thiết lập giá trị cho các tham số trong truy vấn
////                preparedStatement.setString(1, encodedPassword); // Mật khẩu đã mã hóa
////                preparedStatement.setString(2, email); // Email người dùng cần cập nhật mật khẩu
////
////                // Thực thi truy vấn
////                int rowsAffected = preparedStatement.executeUpdate();
////
////                // Kiểm tra xem có thay đổi nào không
////                if (rowsAffected > 0) {
////                    System.out.println("Password updated successfully for email: " + email);
////                } else {
////                    System.out.println("No user found with email: " + email);
////                }
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }
//}
