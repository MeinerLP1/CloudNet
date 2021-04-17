/*
 * Copyright 2017 Tarek Hosni El Alaoui
 * Copyright 2020 CloudNetService
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.cloudnetservice.cloudnet.v2.master.network.packet.api.sync;

import eu.cloudnetservice.cloudnet.v2.lib.network.protocol.packet.Packet;
import eu.cloudnetservice.cloudnet.v2.lib.network.protocol.packet.PacketRC;
import eu.cloudnetservice.cloudnet.v2.lib.network.protocol.packet.PacketSender;
import eu.cloudnetservice.cloudnet.v2.lib.utility.document.Document;
import eu.cloudnetservice.cloudnet.v2.master.CloudNet;

import java.util.UUID;

public class PacketAPIInGetPlayer implements PacketAPIIO {

    public void handleInput(Packet packet, PacketSender packetSender) {
        UUID uniqueId = packet.getData().getObject("uniqueId", UUID.class);
        if (uniqueId != null && CloudNet.getInstance().getNetworkManager().getOnlinePlayers().containsKey(uniqueId)) {
            packetSender.sendPacket(getResult(
                packet, new Document("player",
                                     CloudNet.getInstance().getNetworkManager().getOnlinePlayers().get(uniqueId))));
        } else {
            packetSender.sendPacket(getResult(packet, new Document()));
        }
    }

    public Packet getResult(Packet packet, Document value) {
        return new Packet(packet.getUniqueId(), PacketRC.PLAYER_HANDLE, value);
    }
}
