package provider;

import pojo.URL;
import registry.NativeRegistry;
import service.HelloService;
import service.impl.HelloServiceImpl;
import tomcat.HttpServer;

public class ServiceProvider {
    public static void main(String[] args) {
        URL url = new URL("localhost", 8080);
        //注册服务
        NativeRegistry.regist(HelloService.class.getName(), new URL("localhost", 8080), HelloServiceImpl.class);
        //启动tomcat，暴露服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
