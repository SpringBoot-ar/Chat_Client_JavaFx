package springboot_ar.chat.client.ws.service;

public class WsService {
    public ClientWsService client = null;
    public MsgWsService msg = null;

    public WsService() {
        client = new ClientWsService();
        msg = new MsgWsService();
    }
}
