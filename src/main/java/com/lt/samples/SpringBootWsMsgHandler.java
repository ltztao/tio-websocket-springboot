package com.lt.samples;

import com.lt.samples.annotation.TioWebSocketMsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Objects;

/**
 * @author fyp
 * @crate 2019/6/13 21:23
 * @project tio-starters
 *
 * 和 Tio WebSocket 用法一致，需要实现 IWsMsgHandler 接口，可以添加 @Service 注解，不加的话会自动扫描该类(需要配置 tio.websocket.server.use-scanner: true)
 */
@TioWebSocketMsgHandler
public class SpringBootWsMsgHandler implements IWsMsgHandler {
     private final static Logger log = LoggerFactory.getLogger(SpringBootWsMsgHandler.class);
    /**
     * 握手
     * */
    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String clientip = httpRequest.getClientIp();
        String myname = httpRequest.getParam("name");
        Tio.bindUser(channelContext, myname);
        log.info("收到来自{}的ws握手包\r\n{}", clientip, httpRequest.toString());
        return httpResponse;
    }

    /**
     * 握手完毕
     * */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {

    }

    /**
     * binaryType = arraybuffer
     * */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }


    /**
     * 关闭
     * */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    /**
     * binaryType=text
     * */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
//        Tio.sendToAll(channelContext.getGroupContext(), WsResponse.fromText("服务端收到了消息："+s,"utf-8"));

        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequest();//获取websocket握手包
        if (log.isDebugEnabled()) {
            log.debug("握手包:{}", httpRequest);
        }
        //		log.info("收到ws消息:{}", text);
        if (Objects.equals("心跳内容", text)) {
            return null;
        }
        String msg = "{name:'" + channelContext.userid + "',message:'" + text + "'}";
        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
        WsResponse wsResponse = WsResponse.fromText(msg, "utf-8");
        return null;
    }
}
