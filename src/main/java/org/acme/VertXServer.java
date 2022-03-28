package org.acme;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServerOptions;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class VertXServer {

    static final String HOST = System.getProperty("host", "localhost");
    static final int PORT = Integer.parseInt(System.getProperty("port", "5000"));

    @PostConstruct
    public void createServer() {
        Vertx vertx = Vertx.vertx();
        var options = new NetServerOptions().setPort(PORT).setHost(HOST);
        var server = vertx.createNetServer(options);
        server.connectHandler(
                socket -> {
                    socket.handler(
                            buffer -> {
                                String input = buffer.getString(0, buffer.length());
                                //Servers received data:
                                System.err.println("Server received data: " + input);
                                //Send data to the client
                                socket.write("The received DATA is: " + input + "\r\n");
                            });
                });
        server.listen();
    }
}
