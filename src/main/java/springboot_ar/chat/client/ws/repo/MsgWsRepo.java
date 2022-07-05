package springboot_ar.chat.client.ws.repo;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import springboot_ar.chat.client.ws.service.ClientWsService;
import springboot_ar.chat.client.ws.service.MsgWsService;

import java.lang.reflect.Type;

public class MsgWsRepo {

    private StompSession session = null;
    private MsgWsService msgWsService = null;
    private final String TOPIC_NEW_MSG = "";

    private StompSession.Subscription subNewMsg = null;

    public MsgWsRepo(StompSession session, MsgWsService msgWsService) {
        this.session = session;
        this.msgWsService = msgWsService;
    }

    private void subNewMsg(){
        subNewMsg = session.subscribe(TOPIC_NEW_MSG, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                msgWsService.onNewMsg();
                return null;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {

            }
        });
    }

    public boolean subscribe(){
        if(this.session.isConnected()){
            subNewMsg();
            return true;
        }
        return false;
    }
    public void unsubscribe(){
        subNewMsg.unsubscribe();
    }
}
