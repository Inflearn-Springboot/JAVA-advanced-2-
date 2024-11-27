package network.tcp.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatSessionManager {

    private List<ChatSession> sessions = new ArrayList<>();

    public synchronized void add(ChatSession session){
        sessions.add(session);
    }

    public synchronized void remove(ChatSession session){
        sessions.remove(session);
    }

//    public synchronized void printAllUser(){
//        sessions.
//    }

    public synchronized void closeAll(){
        for (ChatSession session : sessions) {
            session.close();
        }
        sessions.clear();
    }
}
