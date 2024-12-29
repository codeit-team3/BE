package com.codeit.sprint.team3.backend.chat.application.service;

import com.codeit.sprint.team3.backend.chat.application.port.in.SaveChatMessageUseCase;
import com.codeit.sprint.team3.backend.chat.application.port.out.SaveChatMessagePort;
import com.codeit.sprint.team3.backend.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class BufferedMessageService implements SaveChatMessageUseCase {
    private final SaveChatMessagePort saveChatMessagePort;

    private final int TRIGGER_SIZE = 10;
    private final AtomicBoolean running = new AtomicBoolean(false);

    private final BlockingQueue<ChatMessage> messageQueue = new LinkedBlockingQueue<>();

    @Override
    public void save(ChatMessage message) {
        messageQueue.add(message);
        if (messageQueue.size() >= TRIGGER_SIZE && running.compareAndSet(false, true)) {
            saveToDataBase();
        }
    }

    @Async
    protected void saveToDataBase() {
        try{
            List<ChatMessage> list = new ArrayList<>();
            messageQueue.drainTo(list);

            saveChatMessagePort.save(list);
        } finally {
            running.set(false);
        }
    }


}
