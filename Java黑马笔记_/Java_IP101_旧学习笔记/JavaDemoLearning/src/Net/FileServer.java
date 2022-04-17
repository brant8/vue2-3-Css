package Net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        File file = new File("upload");
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file+"\\abc.txt");

        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = is.read(bytes)) != -1){
            fos.write(bytes,0,len);
        }

        socket.getOutputStream().write("已收到".getBytes());
        fos.close();
        socket.close();
        server.close();
    }
}
