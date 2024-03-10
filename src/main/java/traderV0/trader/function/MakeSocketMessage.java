package traderV0.trader.function;

import org.springframework.stereotype.Component;

@Component
public class MakeSocketMessage {
    public String basicJsonMessage(String domain, String subDomain, String data){
        return domain + ":" + subDomain + ":" + data;
    }
}
