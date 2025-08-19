package com.springboot.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;

@Service
public class QueueService {


    private final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
    private final Set<String> seen = ConcurrentHashMap.newKeySet();
    private final TaskService taskService;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public QueueService(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostConstruct
    public void startConsumer() {
        System.out.println("post construct linkedBlockingQueue = " + linkedBlockingQueue);
        executorService.submit(this::triggred0); // run once
    }
    public boolean add(String s) {
        if(seen.add(s)){
            linkedBlockingQueue.offer(s);
            return true;
        }
        return false;
    }

    private void triggred0() {
        while (true) {
            try{
                Object data = linkedBlockingQueue.take();
                if (Objects.nonNull(data)) {
                    seen.remove(data.toString());
                    String[] entry = data.toString().split("\\|");
                    taskService.backFill(entry[0],entry[1]);
                }
            }catch (Exception e){
                System.out.println("e = " + e);
            }
        }
    }

    public LinkedBlockingQueue getQ() {
        return linkedBlockingQueue;
    }
}
