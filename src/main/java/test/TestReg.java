package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

public class TestReg {
	public static void main(String[] args) {
		Map<String,Object> outValues = new HashMap<String,Object>();
		outValues.put("asd", "zw");
		System.out.println("".matches(".*\\$\\{\\w+\\}.*"));
		System.out.println(outValues.get("asd"));
		String ss = "qweasd...123asd>>asd{wer}123wwasd{123456}123";
		String reg = "asd@(\\{\\w+\\})@123";
		int begin = reg.indexOf("@(");
		int end = reg.length()-(reg.lastIndexOf(")@")+2);
		reg = reg.replaceAll("@", "");
		System.out.println("reg:"+reg);
		Pattern p=Pattern.compile(reg); 
        Matcher m=p.matcher(ss); 
        String str = "";
        if(m.find()){ 
          str = m.group(); 
        }
        System.out.println(">>>"+str.substring(begin,str.length()-end));
        String variableStr = "qwe${asd}123";
        String variableKey = variableStr.substring(variableStr.indexOf("${")+2,variableStr.indexOf("}"));
        System.out.println("variableKey:"+variableKey);
        System.out.println(variableStr.startsWith("${")&&variableStr.endsWith("}"));
        
        
        String variableValue = variableStr.replaceAll("\\$\\{\\w+\\}",(String)outValues.get(variableKey));
        System.out.println(variableValue);
        
        System.out.println("--------------------------------");
        variableStr = "as${asd}+${123}qwe${zxc}mm$";
        Pattern pattern=Pattern.compile("\\$\\{\\w+\\}"); 
        Matcher matcher=pattern.matcher(variableStr);
        String parameterValue = variableStr;
        while(matcher.find()){
        	String s = matcher.group();
        	Object v = outValues.get(s.substring(2,s.length()-1));
        	parameterValue = parameterValue.replaceFirst("\\$\\{\\w+\\}",v==null?"":(String)v);
        }
        System.out.println(parameterValue);
        
//        JSONObject json = new JSONObject();
//        try {
//			System.out.println(json.getString("asd"));
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
        
        String valueKey = "aa->ad->1->2->a->aa";
        System.out.println(valueKey.indexOf("->")>0&&!valueKey.endsWith("->")&&valueKey.indexOf("->->")==-1);
	
        System.out.println("===========");
        try {
			g();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public static boolean jsonHasKey(JSONObject data,String valueKey) throws JSONException{
		if(valueKey.indexOf("->")>0&&!valueKey.endsWith("->")&&valueKey.indexOf("->->")==-1){
			String[] keys = valueKey.split("->");
			if(data.has(keys[0])){
				JSONObject json = data.getJSONObject(keys[0]);
				for(int i=1;i<keys.length-1;i++){
					if(json.has(keys[i])){
						json = json.getJSONObject(keys[i]);
					}else{
						return false;
					}
				}
				if(json.has(keys[keys.length-1])){
					return true;
				}
			}
		}else{
			if(data.has(valueKey)){
				return true;
			}
		}
		return false;
	}
	public static String jsonGetValueByKey(JSONObject data,String valueKey) throws JSONException{
		String value = null;
		if(valueKey.indexOf("->")>0&&!valueKey.endsWith("->")&&valueKey.indexOf("->->")==-1){
			String[] keys = valueKey.split("->");
			if(data.has(keys[0])){
				JSONObject json = data.getJSONObject(keys[0]);
				for(int i=1;i<keys.length-1;i++){
					if(json.has(keys[i])){
						json = json.getJSONObject(keys[i]);
					}
				}
				if(json.has(keys[keys.length-1])){
					value = json.getString(keys[keys.length-1]);
				}
			}
		}else{
			if(data.has(valueKey)){
				value = data.getString(valueKey);
			}
		}
		return value;
	}
	public static void g() throws JSONException{
		String valueKey = "asd->qwe->zxc->poi";
		JSONObject data = new JSONObject();
		data.put("asd",new JSONObject().put("qwe",new JSONObject().put("zxc",new JSONObject().put("poi", "zw"))));
		System.out.println(">>>>>>>>>"+jsonGetValueByKey(data, valueKey));
		System.out.println(data.toString());
		String[] keys = valueKey.split("->");
		System.out.println(Arrays.toString(keys));
		System.out.println(data.has(keys[0]));
		if(data.has(keys[0])){
			JSONObject json = data.getJSONObject(keys[0]);
			System.out.println("BB"+json.toString());
			for(int i=1;i<keys.length-1;i++){
				if(json.has(keys[i])){
					System.out.println("AA");
					json = json.getJSONObject(keys[i]);
				}
			}
			if(json.has(keys[keys.length-1])){
				System.out.println(json.getString(keys[keys.length-1]));
			}
		}
	}
	public static void f(int a,String... ss){
		for(String s:ss){
			System.out.println(s);
		}
	}
}
