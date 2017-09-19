package util;

import net.sf.json.JSONObject;

public class AlertJson {
	
	public static JSONObject successJson(String message,String navTabId,String rel){
		JSONObject object=new JSONObject();
		object.put("statusCode", "200");
		object.put("message", message);
		object.put("navTabId", navTabId);
		object.put("rel", rel);
		object.put("callbackType","");  //closeCurrent
		object.put("forwardUrl", "");
		return object;
	}
	public static JSONObject successJsonByForward(String message,String forwardUrl){
		JSONObject object=new JSONObject();
		object.put("statusCode", "200");
		object.put("message", message);
		object.put("navTabId", "");
		object.put("rel", "");
		object.put("callbackType", "forward");  //closeCurrent
		object.put("forwardUrl",forwardUrl);
		return object;
	}
	public static JSONObject failedJson(String message,String navTabId,String rel){
		JSONObject object=new JSONObject();
		object.put("statusCode", "300");
		object.put("message", message);
		object.put("navTabId", navTabId);
		object.put("rel", rel);
		object.put("callbackType", "");  //closeCurrent
		object.put("forwardUrl", "");
		return object;
	}
}
