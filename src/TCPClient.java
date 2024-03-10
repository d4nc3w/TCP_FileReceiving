import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "yourIpAddress"; //replace
        int serverPort = 6789; //replace

        try(Socket socket = new Socket(serverAddress, serverPort)) {
            InputStream in = socket.getInputStream();
            FileOutputStream out = new FileOutputStream("received.txt");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("File received successfully!");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
