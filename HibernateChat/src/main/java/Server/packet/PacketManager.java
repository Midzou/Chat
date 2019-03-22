package Server.packet;


import java.util.HashMap;
import java.util.Map;

public class PacketManager {
    private final static Map<String, Class<? extends OPacket>> packets = new HashMap<>();

    static {
        packets.put((String) "1",PackerAuthorize.class);
        packets.put((String) "2",PacketRegistration.class);
        packets.put((String) "5",PacketSendMessage.class);


    }

    public static OPacket getPacket(String id) {
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
