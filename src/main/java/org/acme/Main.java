package org.acme;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

@QuarkusMain
public class Main {

    public static void main(String ... args) {
        Quarkus.run(MyApp.class, args);
    }
    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Running main method from custom app");
            VertXServer vs = new VertXServer();
            vs.createServer();
            Quarkus.waitForExit();
            return 0;
        }
    }
}
