package Client;

import Client.packet.PackerSendMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Chat extends JFrame  {

    Map<Integer, String> users = new HashMap<Integer, String>();
    private JScrollPane scrollPane1,scrollPane2,scrollPane3;
    private SimpleDateFormat dt1;
    private Date time;
    private String dtime;

    public Chat()
    {
        super("Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTextArea area1 = new JTextArea( 8, 10);
        new ReadMsg(area1).start();
        area1.setFont(new Font("Dialog", Font.PLAIN, 14));
        area1.setTabSize(10);
        final JTextArea area3 = new JTextArea("Ввод сообщения",5,5  );
        JButton SendMessage = new JButton("Send");
        SendMessage.setBounds(270,320,90,40  );

        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);

        final JPanel contents = new JPanel();
        contents.setLayout(null);
        scrollPane1=new JScrollPane(area1);
        scrollPane3=new JScrollPane(area3);
        area1.setEditable(false);

        scrollPane1.setBounds(10,10,350,300);
        scrollPane3.setBounds(10,320,250,40);
        contents.add(scrollPane1);
        contents.add(scrollPane3);
        contents.add(SendMessage);

        SendMessage.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg1)
            {
                String autor=Authorization.getNickname();
                time = new Date();
                dt1 = new SimpleDateFormat("HH:mm:ss");
                dtime = dt1.format(time);
                ClientLoader.sendPacket(new PackerSendMessage("["+dtime+"] "+autor +": " + area3.getText()));
            }
        });
        setContentPane(contents);
        setSize(385, 400);
        setVisible(true);
        
    }


    private class ReadMsg extends Thread {
        JTextArea area1;

        public ReadMsg(JTextArea area1){this.area1=area1;}
        @Override
        public void run() {
            String str;
            try {
                DataInputStream dis = new DataInputStream( ClientLoader.getSocket().getInputStream());
                while (true) {
                    str = dis.readUTF();
                    area1.append( str+ "\n" );
                }
            } catch (IOException e) {
            }
        }
    }
}
