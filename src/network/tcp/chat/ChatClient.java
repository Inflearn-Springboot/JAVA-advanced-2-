package network.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ChatClient {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        try(Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())){
            log("소캣 연결 : " + socket);
            while(true){
                Scanner scanner = new Scanner(System.in);
                System.out.print("전송 문자 : " );
                String sendMsg = scanner.nextLine();

                if (sendMsg.equals("exit")){
                    return;
                }

                ReadHandler readHandler = new ReadHandler(input);
                WriterHandler writerHandler = new WriterHandler(sendMsg, output);

                Thread thread1 = new Thread(readHandler);
                Thread thread2 = new Thread(writerHandler);
                thread1.start();
                thread2.start();

                Thread.sleep(500);
            }
        } catch (IOException e){
            log(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
