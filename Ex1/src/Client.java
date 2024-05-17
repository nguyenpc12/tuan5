import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage, clientMessage;
            while (true) {
                System.out.print("Client: ");
                clientMessage = userInput.readLine();
                writer.println(clientMessage);

                if ((serverMessage = reader.readLine()) != null) {
                    System.out.println("Server: " + serverMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
