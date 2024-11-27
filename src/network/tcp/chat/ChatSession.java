package network.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ChatSession implements Runnable{
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final ChatSessionManager sessionManager;
    private boolean closed = false;
    private String username = null;


    public ChatSession(Socket socket, ChatSessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run(){
        try{
            while(true){
                String firstString = input.readUTF();
                String[] words = firstString.split("\\|");
                String action = words[0].substring(1);
                System.out.println("action = " + action);

                if(action.equals("join")|| action.equals("change")){
                    String name = words[1];
                    log("(username 저장) client -> server: " + name);

                    if(name.isEmpty()){
                        System.out.println("이름을 다시 적어주세요");
                        continue;
                    }
                    // Session 이름 저장
                    putName(name);
                } else if(action.equals("message")){
                    String messages = words[1];
                    output.writeUTF(messages);
                    log("(messages 송신) client <- server : " + messages);
                } else if(action.equals("users")){
                    // 전체 사용자 목록 SessionMannager로 부터 가져오기


                }else if(action.equals("exit")){
                    log("채팅 서버를 종료합니다");
                    break;
                }
            }
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            close();
        }
    }

    public synchronized void close(){
        if(closed) {
            return;
        }
        closeAll(socket, input, output);
        closed = true;
        log("연결 종료  : " + socket);
    }

    public synchronized void putName(String username){
        this.username = username;
    }
}
