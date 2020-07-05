package eu.cloudnetservice.cloudnet.v2.api.network.packet.api;

import eu.cloudnetservice.cloudnet.v2.lib.network.protocol.packet.Packet;
import eu.cloudnetservice.cloudnet.v2.lib.network.protocol.packet.PacketRC;
import eu.cloudnetservice.cloudnet.v2.lib.process.ProxyProcessData;
import eu.cloudnetservice.cloudnet.v2.lib.utility.document.Document;

/**
 * Created by Tareko on 21.08.2017.
 */
public class PacketOutStartProxy extends Packet {

    public PacketOutStartProxy(final ProxyProcessData proxyProcessData) {
        super(PacketRC.SERVER_HANDLE + 6,
              new Document("proxyProcess", proxyProcessData));
    }
}