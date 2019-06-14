package com.tomato.framework.rest.client;

import com.tomato.framework.core.exception.SysException;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class HttpClientUtil {
    
    private static volatile HttpClientUtil instance;
    
    private OkHttpClient client;
    
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    public HttpClientUtil(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }
    
    public static HttpClientUtil getInstance() {
        if (instance == null) {
            synchronized (HttpClientUtil.class) {
                if (instance == null) {
                    instance = new HttpClientUtil(new OkHttpClient());
                }
            }
        }
        return instance;
    }
    
    public String get(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new SysException(e);
        }
    }
    
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    
    public String delete(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).delete(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    
    public String put(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).put(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    
}
