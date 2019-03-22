package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ServerLoader {

    private static ServerSocket server;
    public static Map<Socket, ClietnHandler> handlers = new HashMap<>();

    public static void main(String[] args) throws IOException {
      start();
      handle();
    }

    private static void handle() {
        while (true) {
            try {
                Socket client = server.accept();
                ClietnHandler handler = new ClietnHandler(client);
                handler.start();
                handlers.put(client, handler);
            }catch (SocketException ex) {
                return;
            }catch (IOException ex) {ex.printStackTrace();}
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

    public static void sendPacket(Socket receiver ,String text)
    {
        try {
            DataOutputStream dos= new DataOutputStream(receiver.getOutputStream());
            dos.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void start()
    {
        try {
            server = new ServerSocket(8888);
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static boolean isStarted() {
        return server != null && !server.isClosed();
    }
    public static void end()
    {
        try {
            if(isStarted()) {
                server.close();
                server.getChannel().close();
                server = null;
            }
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static ClietnHandler getHandler(Socket socket)
    {
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket)
    {
        handlers.remove(socket);
    }
}
