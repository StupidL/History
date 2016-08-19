package me.stupideme.history;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by StupidL on 2016/8/19.
 */

class JuheDemo {

    private static final String DEF_CHATSET = "UTF-8";
    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";


    static void getRequest1(String month, String day, List<Event> list) {
        String result = null;
        String url = "http://api.juheapi.com/japi/toh";//请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();//请求参数
        params.put("key", API.REQUEST_URL.API_KEY);//应用APPKEY(应用详细页查询)
        params.put("v", "1.0");//版本，当前：1.0
        params.put("month", month);//月份，如：10
        params.put("day", day);//日，如：1

        try {
            result = net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if (object.getInt("error_code") == 0) {
                JSONArray array = object.getJSONArray("result");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    Event event = new Event();
                    event.setId(o.getString("_id"));
                    event.setTitle(o.getString("title"));
                    event.setYear(o.getString("year"));
                    event.setMonth(o.getString("month"));
                    event.setDay(o.getString("day"));
                    event.setLunar(o.getString("lunar"));
                    event.setDes(o.getString("des"));
                    event.setPicUrl(o.getString("pic"));
                    list.add(event);
                }
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRequest2(String id) {
        String result = null;
        String content = null;
        String url = "http://api.juheapi.com/japi/tohdet";//请求接口地址
        Map<String, Object> params = new HashMap<>();//请求参数
        params.put("key", API.REQUEST_URL.API_KEY);//应用APPKEY(应用详细页查询)
        params.put("v", "1.0");//版本，当前：1.0
        params.put("id", id);//事件ID

        try {
            result = net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if (object.getInt("error_code") == 0) {
                JSONObject o = object.getJSONObject("result");
                content = o.getString("content");
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    private static String net(String strUrl, Map<String, Object> params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlEncode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlEncode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    private static String urlEncode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
