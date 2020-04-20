package com.ren.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by RL on 2020/04/19 20:40
 * Description: 处理网络请求的handler
 */
public interface RequestHandler {
    void onReRequest(InputStream recive, OutputStream repo);

}
