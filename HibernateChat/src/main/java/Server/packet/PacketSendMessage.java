package Server.packet;

import Server.ClietnHandler;
import Server.Logging;
import Server.ServerLoader;
import Server.models.User;
import Server.services.UserService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Server.ServerLoader.handlers;

public class PacketSendMessage extends OPacket {
    private String message;

    public PacketSendMessage() {}

    public PacketSendMessage(String message)
    {
        message =this.message;
    }
    @Override
    public short getId() {
        return 5;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
    }

    @Override
    public void read(Map<Integer, String> text) throws IOException {
        try {
            message = (String) text.get(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handel(){
        try {
            Logging.read(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Map.Entry<Socket, ClietnHandler> item : handlers.entrySet()){
            ServerLoader.sendPacket( item.getKey(),(message));
        }
    }
}
