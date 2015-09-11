package com.ctrip.thackchina.server;

import java.io.UnsupportedEncodingException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

public class HttpHandler extends SimpleChannelUpstreamHandler {
    private HttpRequest request;
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws UnsupportedEncodingException {
        this.request = (HttpRequest) e.getMessage();
        writeResponse(e, "");
    }
   
    private void writeResponse(MessageEvent e, String uri) throws UnsupportedEncodingException {
        Channel ch = e.getChannel();
        String url = request.getUri();
        
        /**
         * 根据url参数不同返回不同的内容到客户端
         */
        String content = "Hello thackChina!!!!" + "\n" + url;
        
        
        
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setStatus(HttpResponseStatus.OK);
        ChannelBuffer buffer=new DynamicChannelBuffer(2048);
        buffer.writeBytes(content.getBytes("utf-8"));
        response.setContent(buffer );
        ch.write(response);
        ch.disconnect();
        ch.close();
    }
   
}