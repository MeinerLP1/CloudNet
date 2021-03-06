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

package eu.cloudnetservice.cloudnet.v2.bridge.event.bukkit;

import eu.cloudnetservice.cloudnet.v2.lib.utility.document.Document;
import org.bukkit.event.HandlerList;

/**
 * This event is called whenever a custom channel message has been received.
 * This event can be used to receive communication from servers across the entire network.
 */
public class BukkitCustomChannelMessageReceiveEvent extends BukkitCloudEvent {

    private static final HandlerList handlerList = new HandlerList();

    private final String channel;
    private final String message;
    private final Document document;

    public BukkitCustomChannelMessageReceiveEvent(String channel, String message, Document document) {
        super();
        this.channel = channel;
        this.message = message;
        this.document = document;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    /**
     * @return the message carried by this channel message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the document attached to this channel message.
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @return the channel this channel message was sent on.
     */
    public String getChannel() {
        return channel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
