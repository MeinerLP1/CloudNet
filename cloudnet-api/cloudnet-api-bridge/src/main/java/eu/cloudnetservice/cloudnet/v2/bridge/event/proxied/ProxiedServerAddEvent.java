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

package eu.cloudnetservice.cloudnet.v2.bridge.event.proxied;

import eu.cloudnetservice.cloudnet.v2.lib.server.info.ServerInfo;

/**
 * This event is called whenever a server has been added to the CloudNet network.
 * When receiving this event, the server might not be done with starting up.
 */
public class ProxiedServerAddEvent extends ProxiedCloudEvent {

    private final ServerInfo serverInfo;

    public ProxiedServerAddEvent(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    /**
     * The server information object about the server that has been added to the network.
     *
     * @return the server information of the added server.
     */
    public ServerInfo getServerInfo() {
        return serverInfo;
    }
}
