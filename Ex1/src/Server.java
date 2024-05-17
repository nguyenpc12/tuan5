import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            while (true) {
                if ((clientMessage = reader.readLine()) != null) {
                    System.out.println("Client: " + clientMessage);
                }
                System.out.print("Server: ");
                serverMessage = serverInput.readLine();
                writer.println(serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
