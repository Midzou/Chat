package Client;

import Client.packet.OPacket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientLoader {
    private static Socket socket;

    public ClientLoader()
    {
        try {
            socket=new Socket("localhost",8888);
        }catch(IOException ex) {ex.printStackTrace();}
    }

    public static void sendPacket(OPacket packet)
    {
        try {

            try {
                socket=new Socket("localhost",8888);
            }catch(IOException ex) {ex.printStackTrace();}
            ObjectOutputStream dos=new ObjectOutputStream(socket.getOutputStream());
            dos.writeObject(packet.getState());
            dos.flush();
        }catch (IOException ex) {ex.printStackTrace();}
    }

    public static Socket getSocket()
    {
        return socket;
    }
}
