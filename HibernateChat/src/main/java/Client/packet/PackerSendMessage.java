package Client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackerSendMessage extends OPacket {
    private String text;

    private  Map<Integer, String> states;

    public PackerSendMessage(String text)
    {
        this.text=text;

    }
    @Override
    public String getId() {
        return  "5";
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Object getState() {
        states = new HashMap<Integer, String>();
        states.put(1, getId());
        states.put(2, getText());
        return states;
    }

    @Override
    public Object getStr() {
        return null;
    }

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getRepitPassword() {
        return null;
    }


    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    @Override
    public void handle() {

    }
}

