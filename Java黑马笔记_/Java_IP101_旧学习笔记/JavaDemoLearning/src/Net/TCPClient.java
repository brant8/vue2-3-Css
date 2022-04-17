package Net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream os = socket.getOutputStream();
        os.write("hello server..".getBytes());

        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        System.out.println("!!"+bytes+"!!");
        int len = is.read(bytes);
        System.out.println("!!"+bytes+"!!");
        System.out.println(new String(bytes));
        System.out.println(new String(bytes,0,len));

        socket.close();
    }
}
