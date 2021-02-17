package registry;

import pojo.URL;

import java.util.HashMap;
import java.util.Map;

public class NativeRegistry {
    //注册中心map
    private  static Map<String, Map<URL, Class>> registCenter = new HashMap<String, Map<URL, Class>>();

    //注册服务
    public static void regist(String interfaceName, URL url, Class implClass) {
        Map<URL, Class> map = new HashMap<URL, Class>();
        map.put(url, implClass);
        registCenter.put(interfaceName, map);
    }
    //获取服务信息
    public static Class get(String interfaceName, URL url) {
        Map<URL, Class> urlClassMap = registCenter.get(interfaceName);
        Class aClass = urlClassMap.get(url);
        return aClass;
    }
}
