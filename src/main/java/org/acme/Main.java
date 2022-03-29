package org.acme;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusMain
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String ... args) {
        logger.info("Entering in main");
        Quarkus.run(MyApp.class, args);
    }
    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Running main method from custom app");
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(new VertXServer(), new DeploymentOptions().setInstances(1));
            Quarkus.waitForExit();
            return 0;
        }
    }
}
