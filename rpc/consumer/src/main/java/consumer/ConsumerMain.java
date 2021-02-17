package consumer;

import pojo.Invocation;
import service.HelloService;

import java.io.IOException;

public class ConsumerMain {
    public static void main(String[] args) throws IOException {
        Invocation invocation =  new Invocation(HelloService.class.getName(), "sayHello", new Object[]{"myRPC的客户端"}, new Class[]{String.class});

        HttpClient httpClient = new HttpClient();
        String result = httpClient.post("localhost", 8080, invocation);
        System.out.println(result);


    }

}
