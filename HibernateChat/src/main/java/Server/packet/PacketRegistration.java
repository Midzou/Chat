package Server.packet;

import Server.ClietnHandler;
import Server.Logging;
import Server.ServerLoader;
import Server.models.User;
import Server.services.UserService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PacketRegistration extends OPacket {
    private String nickname;
    private String password;
    private String repitPassword;

    public PacketRegistration() {}

    public PacketRegistration(String nickname,String password,String repitPassword)
    {
        nickname =this.nickname;
        password = this.password;
        repitPassword = this.repitPassword;
    }
    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
    }

    @Override
    public void read(Map<Integer, String> text) throws IOException {
        try {
            nickname = (String) text.get(2);   //considered a nickname
            password = (String) text.get(3);   //considered a password
            repitPassword = (String) text.get(4);  //considered a repit password
            System.out.println( nickname+" "+password +" "+repitPassword );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handel(){
        boolean check = true;
        if(repitPassword.equals( password ) )
        {
            UserService userService = new UserService();
            for(User person :userService.findAllUsers()){
                if(person.getName().equals( nickname ) )
                {
                    check = false;
                    break;
                }
            }
        }else{check=false;}

        if(check)
        {
            UserService userService = new UserService();
            User user = new User(nickname,password);
            userService.saveUser(user);//добавили пользователя
            ServerLoader.sendPacket( ClietnHandler.getSocket(),"successful");
            try {
                Logging.read("Registration successful " + nickname + "/"+password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{ ServerLoader.sendPacket( ClietnHandler.getSocket(),"error");}
    }
}
