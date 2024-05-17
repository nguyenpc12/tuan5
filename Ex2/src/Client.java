import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    private static JLabel clockLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        clockLabel = new JLabel();
        frame.add(clockLabel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private static void updateTime() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("time"); // Gửi yêu cầu "time" tới server
            long time = Long.parseLong(in.readLine()); // Đọc thời gian trả về từ server
            socket.close();

            // Cập nhật đồng hồ với thời gian nhận được
            clockLabel.setText("Time: " + new java.util.Date(time));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}