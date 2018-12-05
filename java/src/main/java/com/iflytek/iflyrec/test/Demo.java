package com.iflytek.iflyrec.test;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Demo {

    private static final String CHARSET_UTF8 = "UTF-8";

    private static final String SERVICE_URL = "https://api.iflyrec.com";
    private static String accessKeyId = "xxx";
    private static String accessKeySecret = "xxx";

    public void step01_upload() {
        File file = new File("/Users/plucky/own/AndroidDaddy/java/tmp/1分钟音频.mp3");
        boolean ex = file.exists();
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] content = null;
        try {
            content = IOUtils.toByteArray(fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ☆☆☆使用TreeMap对内容根据Key进行自然排序
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("dateTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()));
        map.put("accessKeyId", accessKeyId);
        map.put("signatureRandom", UUID.randomUUID().toString());
        map.put("fileName", file.getName());
        map.put("fileSize", content.length);
        map.put("duration", 56431L);//真实的音频时长
        map.put("language", "cn");//目前服务支持 cn-中文, en-英文两个语种
        String formUrlString = null;
        try {
            formUrlString = NRTSignature.formUrlEncodedValueParameters(map);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String result = requestPost(SERVICE_URL + "/v2/upload" + "?" + formUrlString, map, content);
        System.out.println("Step One ResultInfo = " + result);
        XFOrder order = new Gson().fromJson(result, XFOrder.class);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        step02_getResult(order.content.orderId);
    }

    /**
     * 在获取某个订单的识别结果之前最好设置一定的等待时间，我们服务针对不同时长的订单
     * ，处理的时间也会不同。当然我们服务也提供了订单识别完成的回调功能（详见用户文档）
     * ，当订单处理完成后会通知客户，用户可通过订单ID获取相应的转写结果。
     */
    public String step02_getResult(String orderId) {
        // ☆☆☆使用TreeMap对内容根据Key进行自然排序
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("dateTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()));
        map.put("signatureRandom", UUID.randomUUID().toString());
        map.put("accessKeyId", accessKeyId);
        map.put("orderId", orderId);//订单ID
        String formUrlString = null;
        try {
            formUrlString = NRTSignature.formUrlEncodedValueParameters(map);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String result = requestGet(SERVICE_URL + "/v2/getResult" + "?" + formUrlString, map);
        System.out.println("Step Two ResultInfo = " + result);
        return result;
    }

    private String requestGet(String url, Map<String, Object> map) {
        String signature = null;
        try {
            signature = NRTSignature.gernerateSignature(map, accessKeySecret);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("signature", signature);
        CloseableHttpResponse response = null;
        String responseString = null;
        try {
            response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                String message = "call servie failed: " + response.getStatusLine();
                System.out.println(message);
            }
            HttpEntity entity = response.getEntity();
            byte[] responseContent = IOUtils.toByteArray(entity.getContent());
            responseString = IOUtils.toString(responseContent, CHARSET_UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(response);
        }
        return responseString;

    }

    private String requestPost(String url, Map<String, Object> map, byte[] uploadContent) {
        String signature = null;
        try {
            // 生成signature
            signature = NRTSignature.gernerateSignature(map, accessKeySecret);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("signature", signature);
        HttpEntity reqEntity = EntityBuilder.create().setBinary(uploadContent)
                .setContentType(ContentType.create("application/json", CHARSET_UTF8)).build();
        httppost.setEntity(reqEntity);
        CloseableHttpResponse response = null;
        String responseString = null;
        try {
            response = client.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                String message = "call servie failed: " + response.getStatusLine();
                System.out.println(message);
            }
            HttpEntity entity = response.getEntity();
            byte[] responseContent = IOUtils.toByteArray(entity.getContent());
            responseString = IOUtils.toString(responseContent, CHARSET_UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(response);
        }
        return responseString;
    }

}
