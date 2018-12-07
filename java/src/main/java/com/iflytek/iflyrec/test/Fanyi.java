package com.iflytek.iflyrec.test;

import com.google.gson.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Fanyi {
    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
//抓包代码 HttpClientBuilder.create().setProxy(new HttpHost("127.0.0.1", 8888)).build();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Accept", "application/json");

        httpPost.setHeader("Content-Type", "application/json");

        String charSet = "UTF-8";

        StringEntity entity = new StringEntity(params, charSet);

        httpPost.setEntity(entity);

        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);

            StatusLine status = response.getStatusLine();

            int state = status.getStatusCode();

            if (state == HttpStatus.SC_OK) {

                HttpEntity responseEntity = response.getEntity();

                String jsonString = EntityUtils.toString(responseEntity);
                System.out.println(jsonString);
                return jsonString;

            } else {
                System.out.print("请求返回:" + state + "(" + url + ")");
            }

        } finally {

            if (response != null) {

                try {

                    response.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

            try {

                httpclient.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        return null;

    }


    public static void main(String[] args) throws Exception {

        JsonObject json = new JsonObject();

        String s = "天青色等烟雨而我在等你";
        json.addProperty("content", s);

        json.addProperty("sourceLanguage", 1);

        json.addProperty("targetLanguage", 2);

        json.addProperty("appId", "q3s298");

        doPost("http://api.iflyrec.com/PublicTranslation/quickTranslate", json.toString());

    }
}
