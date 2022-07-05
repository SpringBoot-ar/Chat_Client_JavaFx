package springboot_ar.chat.client.ws.repo;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import springboot_ar.chat.client.ws.service.ClientWsService;

import java.lang.reflect.Type;

public class ClientWsRepo {

    private StompSession session = null;
    private ClientWsService clientWsService = null;
    private final String TOPIC_CLIENT_STATUS = "";


    private StompSession.Subscription subClientStatus = null;


    public ClientWsRepo(StompSession session, ClientWsService clientWsService) {
        this.session = session;
        this.clientWsService = clientWsService;
    }

    private void subClientStatus(){
        subClientStatus = session.subscribe(TOPIC_CLIENT_STATUS, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                clientWsService.onClientStatus();
                return null;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {

            }
        });
    }

    public boolean subscribe(){
        if(this.session.isConnected()){
            subClientStatus();
            return true;
        }
        return false;
    }
    public void unsubscribe(){

    }
}
