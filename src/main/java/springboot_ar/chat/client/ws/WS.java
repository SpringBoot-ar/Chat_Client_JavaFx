package springboot_ar.chat.client.ws;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import springboot_ar.chat.client.ws.repo.WsRepo;
import springboot_ar.chat.client.ws.service.WsService;

import java.util.concurrent.TimeUnit;

public class WS{
    private WebSocketClient client;
    private WebSocketStompClient stompClient;
    private StompSessionHandler sessionHandler;
    public WsService wsService = null;
    public WsRepo wsRepo = null;

    WS(){
        client = new StandardWebSocketClient();
        stompClient = new WebSocketStompClient(client);
        stompClient.setDefaultHeartbeat(new long[]{TimeUnit.SECONDS.toMillis(10L), TimeUnit.SECONDS.toMillis(10L)});
        stompClient.setReceiptTimeLimit(TimeUnit.SECONDS.toMillis(10L));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        sessionHandler = new MyStompSessionHandler();


        wsService = new WsService();
    }

    private boolean isCallConnect = false;
    private boolean isConnectConnected = false;
    private String hostLink = "localhost:8080/ws/";

    public void connect(String hostLink){
        if(!isCallConnect){
            this.hostLink = hostLink;
            isCallConnect = true;

            stompClient.connect(this.hostLink, sessionHandler).addCallback(new ListenableFutureCallback<StompSession>() {
                @Override
                public void onFailure(Throwable ex) {

                }
                @Override
                public void onSuccess(StompSession result) {
                    isConnectConnected = true;
                    wsRepo = new WsRepo(result,WS.this);
                    wsRepo.subscribe();
                }
            });

        }
    }
    public void disconnect(){
        if(isCallConnect){
            isCallConnect = false;

            wsRepo.unsubscribe();
            stompClient.stop(() -> {
                isConnectConnected = false;
            });
        }
    }
}
