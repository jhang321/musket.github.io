package com.ctrip.thackchina.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class HttpServer {
	// java -Djava.ext.dirs=./ -classpatch hotword.jar com.ctrip.com.app
	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        bootstrap.setPipelineFactory(new HttpServerPipelineFactory());
        bootstrap.bind(new InetSocketAddress(8080));
        System.out.println("服务器已经启动，请访问http://127.0.0.1:8080/index.html进行测试！\n\n");
	}
}