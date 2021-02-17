package tomcat;

import org.apache.commons.io.IOUtils;
import pojo.Invocation;
import pojo.URL;
import registry.NativeRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServletHandler {
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //服务器请求处理逻辑
            //1 通过请求获取请求服务调用的参数
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Invocation invocation = (Invocation) objectInputStream.readObject();
            //2 从注册中心获取服务的列表
            Class impClass = NativeRegistry.get(invocation.getInterfaceName(), new URL("localhost", 8080));

            //3 调用服务 反射
            Method method = impClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(impClass.newInstance(), invocation.getParams());
            //结果返回
            IOUtils.write(result, resp.getOutputStream());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
