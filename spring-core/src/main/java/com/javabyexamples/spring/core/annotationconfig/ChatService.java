package com.javabyexamples.spring.core.annotationconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatService {

    private final HistoryService historyService;

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatService(HistoryService historyService, ChatRoomService chatRoomService) {
        this.historyService = historyService;
        this.chatRoomService = chatRoomService;
    }

    public void chat() {
        chatRoomService.start();
        historyService.store();
    }
}
