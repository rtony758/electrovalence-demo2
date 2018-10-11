package com.tony.electrovalencedemo2.util;

import org.springframework.context.annotation.Configuration;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Configuration
public class TokenUtil {
    BufferedReader in = null;
    StringBuffer resultOfToken = null;

    public String GetWeatherData(String userName, String password, String url) throws Exception {

        try {
            String authStr = userName + ":" + password;
            String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));
            URL realUrl = new URL(url);

            /**  打开和URL之间的连接*/
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

            /** 设置通用的请求属性*/
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Authorization", "Basic " + encoding);

            /** 建立实际的连接*/
            connection.connect();

            /** 设置接口超时时间为3分钟*/
            connection.setConnectTimeout(180000000);

            /** 读取页面信息时间为3分钟*/
            connection.setReadTimeout(180000000);

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                resultOfToken = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    resultOfToken.append(line);
                }
                return resultOfToken.toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Token的doGet方法执行失败" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


}
