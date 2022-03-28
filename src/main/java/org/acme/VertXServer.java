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
                                byte[] input = buffer.getBytes();
                                String dataInBits = toBits(input);
                                System.out.println("Data in bits is: " + dataInBits);
                                //Servers received data:
                                System.err.println("Server received data: " + input);
                                //Send data to the client
                                socket.write("The received DATA is: " + input + "\r\n");
                            });
                });
        server.listen();
    }
    public String toBits(final byte[] valArray) {
        byte val;
        StringBuilder FinalString = new StringBuilder();
        for (int j = 0; j < valArray.length;j++) {
            val = valArray[j];
            final StringBuilder result = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                result.append((int) (val >> (8 - (i + 1)) & 0x0001));
            }
            FinalString = FinalString.append(result);
        }
        return FinalString.toString();
    }
}
