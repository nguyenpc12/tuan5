import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Mở cổng 12345 để lắng nghe kết nối
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Chấp nhận kết nối từ client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String request = in.readLine(); // Đọc yêu cầu từ client
                if (request != null && request.equals("time")) {
                    // Trả về thời gian hiện tại cho client
                    out.println(System.currentTimeMillis());
                }

                // Đóng luồng và kết nối
                out.close();
                in.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}