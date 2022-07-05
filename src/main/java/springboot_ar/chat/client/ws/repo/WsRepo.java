package springboot_ar.chat.client.ws.repo;

import org.springframework.messaging.simp.stomp.StompSession;
import springboot_ar.chat.client.ws.WS;

public class WsRepo {
    private WS ws = null;
    public ClientWsRepo client = null;
    public MsgWsRepo msg = null;

    public WsRepo(StompSession session,WS ws) {
        this.ws = ws;
        client = new ClientWsRepo(session,this.ws.wsService.client);
        msg = new MsgWsRepo(session,this.ws.wsService.msg);
    }

    public void subscribe() {
        client.subscribe();
        msg.subscribe();
    }

    public void unsubscribe() {
        client.unsubscribe();
        msg.unsubscribe();
    }
}
