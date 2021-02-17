package service.impl;

import service.HelloService;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return name + "成功调用了rpc中的服务";
    }
}
