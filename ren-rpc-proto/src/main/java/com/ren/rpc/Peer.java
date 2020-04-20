package com.ren.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by RL on 2020/04/19 19:19
 * Description: 表示一个网络端点
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
