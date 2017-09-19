package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);
    public static boolean debug = false;
    public static JSONObject get(String link, Map<String, Object> dataMap,Map<String, String> headMap) throws Exception {
        if(dataMap!=null&&dataMap.size()>0){
        	link += "?" + MapToHttpString(dataMap);
        	//logger.info("请求的data:" + mapToJson(dataMap));	
        }
    	logger.info("请求的url:" + link);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(link);
        addHeaders(httpget, headMap);
        debugHeader(httpget.getAllHeaders());
        long beginTime = System.currentTimeMillis();
        CloseableHttpResponse response = httpclient.execute(httpget);
        long endTime = System.currentTimeMillis();
        Map<String,String> headers = getHeaderMap(response);
        Map<String,String> cookies = getCookieMap(headers.get("Set-Cookie"));
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString = EntityUtils.toString(entity, "utf-8");
        logger.info("请求的返回code:" + code);
        logger.info("请求的返回data:" + resultString);
        return getReturnJSON(resultString, code,headers,cookies,beginTime,endTime);
    }
    public static JSONObject postJson(String link, Map<String, Object> dataMap, Map<String, String> headMap) throws Exception {
        logger.info("请求的url:" + link);
        logger.info("请求的data:" + mapToJson(dataMap));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(link.toString());
        addHeaders(httpost, headMap);
        httpost.setHeader("Content-Type","application/json;charset=utf-8");
        httpost.setEntity(new StringEntity(mapToJson(dataMap), HTTP.UTF_8));
        debugHeader(httpost);
        long beginTime = System.currentTimeMillis();
        HttpResponse response = httpclient.execute(httpost);
        long endTime = System.currentTimeMillis();
        Map<String,String> headers = getHeaderMap(response);
        Map<String,String> cookies = getCookieMap(headers.get("Set-Cookie"));
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString = EntityUtils.toString(entity);
        logger.info("请求的返回code:" + code);
        logger.info("请求的返回data:" + resultString);
        return getReturnJSON(resultString, code,headers,cookies,beginTime,endTime);
    }
    public static String uploadFile(String filename, String url) throws IOException {
        logger.info("上传视频路径:" + filename);
        logger.info("上传视频url:" + url);
        File file = new File(filename);
        String response = "";
        if (!file.exists()) {
            return "file not exists";
        }
        PostMethod postMethod = new PostMethod(url);
        try {
            //FilePart：用来上传文件的类
            FilePart fp = new FilePart("filedata", file);
            Part[] parts = {fp};
            //对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(mre);
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
            } else {
                response = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        logger.info("返回结果:" + response);
        return response;
    }
    
    public static Map<String,String> getCookieMap(String cookieStr){
    	Map<String,String> map = new HashMap<String,String>();
    	if(cookieStr==null){
    		return map;
    	}
    	String[] cookieArray = cookieStr.split(";");
    	for (String s : cookieArray) {
    		if(s.indexOf("=")>0){
    			String[] ss = s.split("=");
    			map.put(ss[0],ss[1]);
    		}
		}
    	return map;
    }
    public static Map<String,String> getHeaderMap(HttpResponse response){
    	Map<String,String> map = new HashMap<String,String>();
    	Header[] headers = response.getAllHeaders();
    	for (Header header : headers) {
    		String key = header.getName();
    		if(map.containsKey(key)){
    			map.put(key, map.get(key)+";"+header.getValue());
    		}else{
    			map.put(key, header.getValue());
    		}
		}
    	return map;
    }
    

    public static void debugHeader(Header[] headers) {
        if (debug) {
            for (Header header : headers) {
                logger.info("header:" + header.getName() + ":" + header.getValue());
            }
        }
    }

    public static void debugHeader(HttpRequestBase httpRequest) {
        if(debug) {
            for (Header header : httpRequest.getAllHeaders()) {
                logger.info("header:" + header.getName() + ":" + header.getValue());
            }
        }
    }
    
    public static void addHeaders(HttpRequestBase httpRequest, Map<String, String> headMap) {
        httpRequest.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        httpRequest.setHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        httpRequest.setHeader("Accept", "application/json,text/javascript,*/*");
        for (String key : headMap.keySet()) {
            httpRequest.setHeader(key, headMap.get(key));
        }
    }
    
    public static String mapToJson(Map<String, Object> dataMap) {
        JSONObject js = new JSONObject(dataMap);
        return js.toString();
    }

    public static JSONObject post(String link, Map<String, Object> parameterMap,Map<String, String> headMap) throws Exception {
        logger.info("请求的url:" + link);
        logger.info("请求的data:" + mapToJson(parameterMap));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(link.toString());
        httpost.setEntity(new StringEntity(MapToHttpString(parameterMap)));
        addHeaders(httpost, headMap);
        debugHeader(httpost);
        //发送post请求
        long beginTime = System.currentTimeMillis();
        HttpResponse response = httpclient.execute(httpost);
        long endTime = System.currentTimeMillis();
        Map<String,String> headers = getHeaderMap(response);
        Map<String,String> cookies = getCookieMap(headers.get("Set-Cookie"));
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString = EntityUtils.toString(entity,"utf-8");
        logger.info("请求的返回code:" + code);
        logger.info("请求的返回data:" + resultString);
        return getReturnJSON(resultString, code,headers,cookies,beginTime,endTime);
    }
    
    public static JSONObject put(String link, Map<String, Object> dataMap, Map<String, String> headMap) throws JSONException, IOException {
        logger.info("请求的url:" + link);
        logger.info("请求的data:" + mapToJson(dataMap));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpput = new HttpPut(link.toString());
        
        httpput.setEntity(new StringEntity(mapToJson(dataMap), HTTP.UTF_8));
        addHeaders(httpput, headMap);
        debugHeader(httpput);
        long beiginTime = System.currentTimeMillis();
        HttpResponse response = httpclient.execute(httpput);
        long endTime = System.currentTimeMillis();
        Map<String,String> headers = getHeaderMap(response);
        Map<String,String> cookies = getCookieMap(headers.get("Set-Cookie"));
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString = EntityUtils.toString(entity);
        logger.info("请求的返回code:" + code);
        logger.info("请求的返回data:" + resultString);
        return getReturnJSON(resultString, code,headers,cookies,beiginTime,endTime);
    }

    public static JSONObject delete(String link, Map<String, Object> dataMap, Map<String, String> headMap) throws JSONException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        if (dataMap != null && dataMap.size() > 0) {
          //  logger.info("请求的data:" + mapToJson(dataMap));
            link += "?" + MapToHttpString(dataMap);
        }
        logger.info("请求的url:" + link);
        HttpDelete httpDelete = new HttpDelete(link);
        addHeaders(httpDelete, headMap);
        debugHeader(httpDelete);
        long beiginTime = System.currentTimeMillis();
        //发送delete
        HttpResponse response = httpclient.execute(httpDelete);
        long endTime = System.currentTimeMillis();
        Map<String,String> headers = getHeaderMap(response);
        Map<String,String> cookies = getCookieMap(headers.get("Set-Cookie"));
        int code = response.getStatusLine().getStatusCode();// 返回状态码
        HttpEntity entity = response.getEntity();
        // 把内容转成字符串
        String resultString = EntityUtils.toString(entity);
        logger.info("请求的返回code:" + code);
        logger.info("请求的返回data:" + resultString);
        return getReturnJSON(resultString, code, headers,cookies,beiginTime,endTime);
    }


    public static String MapToHttpString(Map<String, Object> params){
    	String dataString = "";
        if (params==null||params.isEmpty()) {
            return dataString;
        }
        try {
            for (String key : params.keySet()) {
				dataString += key + "=" + URLEncoder.encode(String.valueOf(params.get(key)),"utf-8") + "&";
            }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return dataString.substring(0, dataString.length() - 1);
    }

    public static JSONObject getReturnJSON(String result, int Httpcode,Map<String,String> headers,Map<String,String> cookies,long beginTime,long endTime) throws JSONException {
        JSONObject JS = new JSONObject();
        try {
            JS.put("httpcode", Httpcode);
            JS.put("header",new JSONObject(headers));
            JS.put("cookie",new JSONObject(cookies));
            JS.put("beginTime",beginTime);
            JS.put("endTime",endTime);
            if(result.startsWith("{")){
                JS.put("data", new JSONObject(result));
            }else {
                JS.put("data",result);  //new JSONArray(result)
            }
        } catch (JSONException e) {
            JS.put("data", result);
        }
        return JS;
    }
    public static void main(String[] args) throws Exception {
    	Map<String,Object> p = new HashMap<String,Object>();
    	p.put("username","zhaowei5@le.com");
    	p.put("password","zhaowei123456");
    	p.put("captchaCode","8888");
    	p.put("backUrl","");
		JSONObject json = HttpRequest.post("http://uc.lecloud.com/login.do",p,new HashMap<String,String>());
		System.out.println(json.toString());
//    	Map<String,String> map = new HashMap<String,String>();
//    	map.put("123","asd");
//    	map.put("345", "wer");
//    	System.out.println(new JSONObject(map).toString());
//    	JSONObject json = HttpRequest.get("http://www.jd.com",new HashMap<String,Object>(), new HashMap<String,String>());
//    	System.out.println("AA"+json.toString());
	}
}
