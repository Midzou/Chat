package Client;


import Client.packet.PackerAuthorize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;

public class Authorization extends JFrame{

    static JTextField smallField;


    public Authorization()
    {
        smallField = new JTextField(12);

        final JPasswordField password = new JPasswordField(12);
        password.setEchoChar('*');
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contents.add(smallField);
        contents.add(password  );
        setContentPane(contents);
        setSize(400,100);
        setLocation(800,200);
        setTitle("authorization");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jpBotton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(jpBotton,BorderLayout.SOUTH);
        JButton jbStart = new JButton("Login");
        JButton jbExite = new JButton("Register");
        jpBotton.add(jbStart);
        jpBotton.add(jbExite);
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientLoader.sendPacket(new PackerAuthorize(smallField.getText(),password.getText()));
                try {
                    DataInputStream dis = null;
                    try {
                        dis = new DataInputStream(ClientLoader.getSocket().getInputStream());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if (dis.available() <= 0) {
                        if(dis.readUTF().equals("error"))
                        {
                            JOptionPane.showMessageDialog(null, "Error!");
                            System.out.println("error");
                        }else
                        {
                            new Chat();
                            setVisible(false);
                            System.out.println("successful");
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                        }
                    }
                } catch (IOException ex) {
                }
            }
        });
        jbExite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Register_beta t = new Register_beta();
                setVisible(false);
            }
        });
        setVisible(true);
    }
    public static String getNickname() {
        return smallField.getText();
    }
}
