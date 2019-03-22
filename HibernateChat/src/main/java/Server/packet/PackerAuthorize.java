package Server.packet;

import Server.ClietnHandler;
import Server.Logging;
import Server.ServerLoader;
import Server.models.User;
import Server.services.UserService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class PackerAuthorize extends OPacket {
    private String nickname;
    private String password;
    public PackerAuthorize() {}
    public PackerAuthorize(String nickname)
    {
        nickname =this.nickname;
    }
    public String getNickname(){return nickname;}
    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {}

    @Override
    public void read(Map<Integer, String> text) throws IOException {
        try {
            nickname = (String) text.get(2); //considered a nickname
            password = (String) text.get(3); //considered a password
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void handel() {
boolean check=false;
        UserService userService = new UserService();
        for(User person :userService.findAllUsers())
        {
            if(person.getName().equals( nickname ) && person.getPassword().equals( password ) )
            {
                check=true;
            }
        }
        if(check)
        {
            ServerLoader.sendPacket( ClietnHandler.getSocket(),"successful");
            try {
                Logging.read("Connect successful: "+password +"/"+nickname);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            {
            ServerLoader.sendPacket( ClietnHandler.getSocket(),"error");
            }
    }
}
