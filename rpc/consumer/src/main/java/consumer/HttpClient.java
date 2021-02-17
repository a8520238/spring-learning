package consumer;

import org.apache.commons.io.IOUtils;
import pojo.Invocation;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public String post(String hostName, int port, Invocation invocation) throws IOException {
        // 1 进行连接
        URL url = new URL("http", hostName, port, "/client");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        //2 发送调用信息
        OutputStream os = urlConnection.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(invocation);
        oos.flush();
        oos.close();
        // 3 将输入流转换成字符串，获取远程调用结果
        InputStream inputStream = urlConnection.getInputStream();
        return IOUtils.toString(inputStream);
    }
}
