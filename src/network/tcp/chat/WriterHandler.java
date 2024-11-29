package network.tcp.chat;

import java.io.DataOutputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class WriterHandler implements Runnable{

    private final String sendMsg;
    private final DataOutputStream output;

    public WriterHandler(String sendMsg, DataOutputStream output) {
        this.sendMsg = sendMsg;
        this.output = output;
    }

    @Override
    public void run() {
            // 서버에게 문자보내기
        try {
            output.writeUTF(sendMsg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log("client -> server : " + sendMsg);

    }
}
