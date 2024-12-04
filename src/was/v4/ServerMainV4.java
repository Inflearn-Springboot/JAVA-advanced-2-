package was.v4;

import was.v3.HttpServerV3;

import java.io.IOException;

public class ServerMainV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV4 httpServerV4 = new HttpServerV4(PORT);
        httpServerV4.start();
    }
}