package traderV0.trader.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import traderV0.trader.constant.UserState;
import traderV0.trader.entity.UserInfo;
import traderV0.trader.function.MakeSocketMessage;
import traderV0.trader.function.Security;
import traderV0.trader.repository.UserRepository;

import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    private final UserRepository userRepository;
    private final Security security;
    private final MakeSocketMessage makeSocketMessage;
    private Map<String, WebSocketSession> connectedSession = new HashMap<>();

    @Override
    @Transactional
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(session.getUri().getQuery().split("=")[1]);
        if (findUserOpt.isEmpty()){
            log.info("Invalid Session");
            String jsonMessage = makeSocketMessage.basicJsonMessage("user", "error","Invalid Session");
            session.sendMessage(new TextMessage(jsonMessage));
            session.close();
        }else{
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                log.info("Session Has Expired");
                String jsonMessage = makeSocketMessage.basicJsonMessage("user", "error","Session Has Expired");
                session.sendMessage(new TextMessage(jsonMessage));
                session.close();
            } else{
                findUser.setWebSession(session.getId());
                findUser.updateSessionDate();
                findUser.changeStateToWaiting();
                String jsonMessage = makeSocketMessage.basicJsonMessage("user", "connection","Socket Open");
                session.sendMessage(new TextMessage(jsonMessage));
                connectedSession.put(session.getId(), session);
            }

        }
    }

    @Override
    @Transactional
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Optional<UserInfo> findUserOpt = userRepository.findByWebSession(session.getId());
        if (!findUserOpt.isEmpty()){
            UserInfo findUser = findUserOpt.get();
            findUser.setWebSession(null);
            findUser.changeStateToOffLine();
            try {
                connectedSession.remove(session.getId());
                session.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void sendAllPlayingUser(String message){
        List<UserInfo> findUser = userRepository.findByState(UserState.PLAYING);
        for (UserInfo user : findUser) {
            try {
                connectedSession.get(user.getWebSession()).sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendAllWaitingUser(String message){
        List<UserInfo> findUser = userRepository.findByState(UserState.WAITING);
        log.warn("size : {}",findUser.size());
        for (UserInfo user : findUser) {
            try {
                connectedSession.get(user.getWebSession()).sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
