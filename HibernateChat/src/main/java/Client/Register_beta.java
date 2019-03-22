package Client;

import Client.packet.OPacket;
import Client.packet.PackerAuthorize;
import Client.packet.PackerRegistration;
import Client.packet.PackerSendMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register_beta extends JFrame  {
    private JScrollPane scrollPane1,scrollPane2,scrollPane3;
    private SimpleDateFormat dt1;
    private Date time;
    private String dtime;

    public Register_beta()
    {
        super("Registration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        final JTextArea area1 = new JTextArea( 8, 10);

        area1.setFont(new Font("Dialog", Font.PLAIN, 14));
        area1.setTabSize(10);
        final JTextArea area2 = new JTextArea(15, 10);
        final JTextArea area3 = new JTextArea(5,5  );
        final JButton SendMessage = new JButton("Send");

        area2.setLineWrap(true);
        area2.setWrapStyleWord(true);
        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);

        final JPanel contents = new JPanel();
        contents.setLayout(null);

        area1.setBounds(10,10,170,40);
        area2.setBounds(10,60,170,40);
        area3.setBounds(10,110,170,40);
        SendMessage.setBounds(10,160,170,40  );

        contents.add(area1);
        contents.add(area2);
        contents.add(area3);
        contents.add(SendMessage);



        SendMessage.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==SendMessage){
                    Map<Integer, String> states = new HashMap<Integer, String>();
                    states.put(1, area1.getText());
                    states.put(2, area2.getText());
                    states.put(3, area3.getText());

                    ClientLoader.sendPacket(new PackerRegistration(states.get(1),states.get(2),states.get(3)));
                    try {
                        DataInputStream dis = new DataInputStream( ClientLoader.getSocket().getInputStream());
                        if (dis.available() <= 0) {
                            if(dis.readUTF().equals("error"))
                            {
                                JOptionPane.showMessageDialog(null, "Error!");

                                System.out.println("error");
                            }else
                            {
                                setVisible( false );
                                Authorization p=new Authorization();
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
            }
        });
        setContentPane(contents);
        setLocation(800,200);
        setSize(200, 300);
        setVisible(true);
    }
}
