package Server;

import Server.packet.OPacket;
import Server.packet.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;


public class ClietnHandler extends Thread{

    private static Socket client;
    private String nickname ="NULL";
    private String password ="NULL";


    public ClietnHandler(Socket client) {
        this.client=client;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setData(String nickname,String password) {

        this.nickname = nickname;
        this.password = password;
    }

    public static Socket getSocket()
    {
        return client;
    }

    @Override
    public void run()
    {
        while(true) {
          if(!readData())
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

    public boolean readData()
    {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            try {
                ObjectInputStream dis1 = new ObjectInputStream(client.getInputStream());
                Map<Integer, String> states;
                states = (Map) dis1.readObject();
                String id= (String) states.get(1);
                if(id.equals( "1" ))
                {
                    setData( states.get(2),states.get( 3 ) );

                }
                OPacket packet = PacketManager.getPacket(id);
                packet.read(states);
                packet.handel();
            }catch (Exception ex){}
        }catch (IOException ex){ex.printStackTrace();}
        return true;
    }
    public void invalidate()
    {
        ServerLoader.invalidate(client);
    }
}
