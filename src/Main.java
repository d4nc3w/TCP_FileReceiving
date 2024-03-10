import java.io.*;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

final class MySocket {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private InputStream inByte;
    private FileOutputStream fileOutByte;

    public MySocket(String host, int port) {
        try {
            socket = new Socket(host, port);
            getFileAndRead();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public MySocket(String host, int port,int numberOfBytes){
        try {
            socket = new Socket(host, port);
            getFileAndRead();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void getFileAndRead(){
        try{
            InputStream l = socket.getInputStream();
            l.read();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),UTF_8));
            out = new BufferedWriter(new FileWriter("received.txt"));
            String line;
            String text = new String();
            while((line = in.readLine()) != null){
                line += '\n';
                out.write(line);
                text+=line;
            }
            System.out.println(text);
            in.close();
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void getFileAndReadByByte(int numberOfBytes) {
        try{
            inByte = socket.getInputStream();
            fileOutByte = new FileOutputStream("receivedByByte.txt");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inByte.read(buffer)) != -1) {
                fileOutByte.write(buffer, 0, bytesRead);
            }
            System.out.println("File received successfully!");
            inByte.close();
            fileOutByte.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
