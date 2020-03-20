package com.zetyun.driver.http;

import com.zetyun.driver.log.LogWriter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class HttpClient {
    public static String get(String url, Map<String, String> headers) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpResponse = null;
        HttpGet httpGet;
        String response = null;

        try {
            httpclient = HttpClients.createDefault();
            LogWriter.debug(HttpClient.class, "Create default http client success");

            httpGet = new HttpGet(url);
            LogWriter.debug(HttpClient.class, "Create HttpGet success");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
                LogWriter.debug(HttpClient.class, "Add http header[key = " + entry.getKey() + "][value = " + entry.getValue() + "] success");
            }

            httpResponse = httpclient.execute(httpGet);
            LogWriter.debug(HttpClient.class, "Send http Get method success");

            //获取返回消息
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity, "UTF-8");
            LogWriter.debug(HttpClient.class, "Set http response character to UTF-8 success");
        } catch (Exception ex) {
            LogWriter.error(HttpClient.class, "Send http get method failed, exception: " + ex.getMessage());
            LogWriter.error(HttpClient.class, "     URL=" + url);
            response = null;
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception ex) {
            }
        }

        return response;
    }

    public static String post(String url, Map<String, String> headers, String body) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpResponse = null;
        HttpPost httpPost;
        String response = null;

        try {
            httpclient = HttpClients.createDefault();
            LogWriter.debug(HttpClient.class, "Create default http client success");

            httpPost = new HttpPost(url);
            LogWriter.debug(HttpClient.class, "Create HttpPost success");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
                LogWriter.debug(HttpClient.class, "Add http header[key = " + entry.getKey() + "][value = " + entry.getValue() + "] success");
            }

            // Update StringEntity to use charset.
            StringEntity se = new StringEntity(body, "utf-8");
            httpPost.setEntity(se);
            LogWriter.debug(HttpClient.class, "Set http post body success[body = " + body + "]");

            httpResponse = httpclient.execute(httpPost);
            LogWriter.debug(HttpClient.class, "Send http post method success");

            //获取返回消息
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity, "UTF-8");
            LogWriter.debug(HttpClient.class, "Set http response character to UTF-8 success");
        } catch (Exception ex) {
            LogWriter.error(HttpClient.class, "Send http post method failed, exception: " + ex.getMessage());
            LogWriter.error(HttpClient.class, "     URL=" + url);
            response = null;
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception ex) {
            }
        }

        return response;
    }

    public static String delete(String url, Map<String, String> headers) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpResponse = null;
        HttpDelete httpDelete;
        String response = null;

        try {
            httpclient = HttpClients.createDefault();
            LogWriter.debug(HttpClient.class, "Create default http client success");

            httpDelete = new HttpDelete(url);
            LogWriter.debug(HttpClient.class, "Create HttpDelete success");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDelete.addHeader(entry.getKey(), entry.getValue());
                LogWriter.debug(HttpClient.class, "Add http header[key = " + entry.getKey() + "][value = " + entry.getValue() + "] success");
            }

            httpResponse = httpclient.execute(httpDelete);
            LogWriter.debug(HttpClient.class, "Send http delete method success");

            //获取返回消息
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity, "UTF-8");
            LogWriter.debug(HttpClient.class, "Set http response character to UTF-8 success");
        } catch (Exception ex) {
            LogWriter.error(HttpClient.class, "Send http delete method failed, exception: " + ex.getMessage());
            LogWriter.error(HttpClient.class, "     URL=" + url);
            response = null;
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception ex) {
            }
        }

        return response;
    }

    public static String put(String url, Map<String, String> headers, String body) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpResponse = null;
        HttpPut httpPut;
        String response = null;

        try {
            httpclient = HttpClients.createDefault();
            LogWriter.debug(HttpClient.class, "Create default http client success");

            httpPut = new HttpPut(url);
            LogWriter.debug(HttpClient.class, "Create HttpPut success");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.addHeader(entry.getKey(), entry.getValue());
                LogWriter.debug(HttpClient.class, "Add http header[key = " + entry.getKey() + "][value = " + entry.getValue() + "] success");
            }

            // Update StringEntity to use charset.
            StringEntity se = new StringEntity(body, "utf-8");
            httpPut.setEntity(se);
            LogWriter.debug(HttpClient.class, "Set http put body success[body = " + body + "]");

            httpResponse = httpclient.execute(httpPut);
            LogWriter.debug(HttpClient.class, "Send http put method success");

            //获取返回消息
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity, "UTF-8");
            LogWriter.debug(HttpClient.class, "Set http response character to UTF-8 success");
        } catch (Exception ex) {
            LogWriter.error(HttpClient.class, "Send http put method failed, exception: " + ex.getMessage());
            LogWriter.error(HttpClient.class, "     URL=" + url);
            response = null;
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception ex) {
            }
        }

        return response;
    }

    public static String uploadFile(String requestUrl, Map<String, String> headers, String stepData) throws Exception {
        String fileName = System.getProperty("user.dir") + stepData;
        return uploadFile(requestUrl, headers, fileName, "file");
    }

    public static String uploadFile(String requestUrl, Map<String, String> headers, String fileName, String fieldName) throws Exception {
        String responseStr = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(requestUrl);

            // Add headers.
            String boundary = UUID.randomUUID().toString().replaceAll("-", "");
            headers.clear();
            headers.put("Content-Type", "multipart/form-data; boundary=" + boundary);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
                LogWriter.debug(HttpClient.class, "Add http header[key = " + entry.getKey() + "][value = " + entry.getValue() + "] success");
            }

            // The file body.
            FileBody fileBody = new FileBody(new File(fileName));

            // The multipart entity builder.
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setBoundary(boundary);

            // Add file params.
            multipartEntityBuilder.addPart(fieldName, fileBody);

            HttpEntity requestEntity = multipartEntityBuilder.build();
            httpPost.setEntity(requestEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity responseEntity = response.getEntity();
                responseStr = EntityUtils.toString(responseEntity, "UTF-8");
                EntityUtils.consume(responseEntity);
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return responseStr;
    }
}
