package network.tcp.chat;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable{
    private DataInputStream input = null;

    public ReadHandler(DataInputStream iuput) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            while(true){
                // 서버로부터 문자 받기 <- 이부분을 어떻게 해야하는 지 해결

                String received = input.readUTF();
                log("client <- server : " + received);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
