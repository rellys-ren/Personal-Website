package com.ren.rpc.client;

import com.ren.rpc.Peer;
import com.ren.rpc.common.utils.ReflectionUtils;
import com.ren.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by RL on 2020/04/19 22:22
 * Description: no description
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {
    /**
     * 已经连接的好的client
     */
    private List<TransportClient> clients;
    public RandomTransportSelector(){
        //为了线程安全，也可以在每个实现方法中加synchronized
        clients = new CopyOnWriteArrayList<>();
    }
    @Override
    public void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for(Peer peer : peers){
            for(int i = 0; i < count; i++){
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server: {}",peer);
        }
    }

    @Override
    public TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public void close() {
        for(TransportClient client : clients){
            client.close();
        }
        clients.clear();
    }
}
