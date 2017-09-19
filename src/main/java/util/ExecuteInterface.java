package util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import service.CheckService;
import service.HeadService;
import service.InterfaceService;
import service.OutValueService;
import service.ParameterService;
import service.ResultPerformanceService;
import service.ResultService;
import service.TaskPerformanceResultService;
import service.TaskResultService;
import entity.Head;
import entity.Interface;
import entity.InterfaceCheck;
import entity.OutValue;
import entity.Parameter;
import entity.User;

public class ExecuteInterface {
	public static int executeInterfacePerformance(int interfaceId,User login) throws Exception{
		InterfaceService interfaceService = new InterfaceService();
		Interface inter = interfaceService.findInterfaceById(interfaceId);
		ParameterService parameterService = new ParameterService();
		List<Parameter> parameters = parameterService.findParameterByInterface(interfaceId);
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		for (Parameter p : parameters) {
			parameterMap.put(p.getParameterKey(), p.getParameterValue());
		}
		HeadService headService = new HeadService();
		List<Head> heads = headService.findHeadByInterface(interfaceId);
		Map<String,String> headMap = new HashMap<String,String>();
		for (Head h : heads) {
			headMap.put(h.getHeadKey(),h.getHeadValue());
		}
		int requestMode = inter.getRequestMode();
		JSONObject result = null;
		
		if(requestMode==1){
			result = HttpRequest.get(inter.getInterfaceAddress(),  parameterMap, headMap);
		}else if(requestMode==2){
			result = HttpRequest.post(inter.getInterfaceAddress(), parameterMap, headMap);
		}else if(requestMode==3){
			result = HttpRequest.postJson(inter.getInterfaceAddress(), parameterMap, headMap);
		}else if(requestMode==4){
			result = HttpRequest.put(inter.getInterfaceAddress(), parameterMap, headMap);
		}else if(requestMode==5){
			result = HttpRequest.delete(inter.getInterfaceAddress(), parameterMap, headMap);
		}
		long beginDate = result.getLong("beginTime");
		long endDate = result.getLong("endTime");
		long useDate = endDate-beginDate;
		int httpCode = inter.getHttpCode();
		int returnStatus = result.getInt("httpcode");
		String returnContent = result.getString("data");
		int isSuccess = 0;
		if(returnStatus==httpCode){
			isSuccess=1;
		}
		int executeUser = login.getId();
		Timestamp executeDate = new Timestamp(System.currentTimeMillis());
		int isDelete = 1;
		CheckService cs = new CheckService();
		List<InterfaceCheck> checks = cs.findCheckByInterface(interfaceId);
		Map<String,String> map = CheckResult.checkResult(checks, result);
		int isPass = Integer.parseInt(map.get("isPass"));
		String executeResult = map.get("executeResult");
		ResultPerformanceService rs = new ResultPerformanceService();
		int resultPerformaceId = rs.addResultPerformance(interfaceId, returnStatus, returnContent, isSuccess, isPass, executeResult, executeUser, executeDate, isDelete,beginDate,endDate,useDate);
		return resultPerformaceId;
	}
	public static void executeInterface(int interfaceId,User login) throws Exception{
		InterfaceService interfaceService = new InterfaceService();
		Interface inter = interfaceService.findInterfaceById(interfaceId);
		ParameterService parameterService = new ParameterService();
		List<Parameter> parameters = parameterService.findParameterByInterface(interfaceId);
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		for (Parameter p : parameters) {
			parameterMap.put(p.getParameterKey(), p.getParameterValue());
		}
		HeadService headService = new HeadService();
		List<Head> heads = headService.findHeadByInterface(interfaceId);
		Map<String,String> headMap = new HashMap<String,String>();
		for (Head h : heads) {
			headMap.put(h.getHeadKey(),h.getHeadValue());
		}
		int requestMode = inter.getRequestMode();
		JSONObject result = null;
		if(requestMode==1){
			result = HttpRequest.get(inter.getInterfaceAddress(),  parameterMap, headMap);
		}else if(requestMode==2){
			result = HttpRequest.post(inter.getInterfaceAddress(), parameterMap, headMap);
		}
		int httpCode = inter.getHttpCode();
		int returnStatus = result.getInt("httpcode");
		String returnContent = result.getString("data");
		int isSuccess = 0;
		if(returnStatus==httpCode){
			isSuccess=1;
		}
		int executeUser = login.getId();
		Timestamp executeDate = new Timestamp(System.currentTimeMillis());
		int isDelete = 1;
		CheckService cs = new CheckService();
		List<InterfaceCheck> checks = cs.findCheckByInterface(interfaceId);
		Map<String,String> map = CheckResult.checkResult(checks, result);
		int isPass = Integer.parseInt(map.get("isPass"));
		String executeResult = map.get("executeResult");
		ResultService rs = new ResultService();
		rs.addResult(interfaceId, returnStatus, returnContent, isSuccess, isPass, executeResult, executeUser, executeDate, isDelete);
		
	}
	public static void executeTask(int taskId,User login) throws Exception{
		InterfaceService interfaceService = new InterfaceService();
		List<Interface> interfaces = interfaceService.findInterfaceByTask(taskId);
		Map<String,Object> outValues = new HashMap<String,Object>();
		String resultIds = "";
		int globalPass = 1;
		for(Interface inter:interfaces){
			Map<String,Integer> map = executeInOutInterface(inter, login,outValues);
			int resultId = map.get("resultId");
			int isSuccess = map.get("isSuccess");
			int isPass = map.get("isPass");
			if(isSuccess==0){
				globalPass = 0;
			}
			if(isPass==0){
				globalPass = 0;
			}
			resultIds+=","+resultId;
		}
		resultIds = resultIds.substring(1);
		TaskResultService taskResultService = new TaskResultService();
		int isDelete = 1;
		Timestamp executeTime = new Timestamp(System.currentTimeMillis());
		taskResultService.addTaskResult(taskId, resultIds,login.getId(), executeTime, isDelete,globalPass);
		
	}
	public static int executeTaskPerformance(int taskId,User login) throws Exception{
		InterfaceService interfaceService = new InterfaceService();
		List<Interface> interfaces = interfaceService.findInterfaceByTask(taskId);
		Map<String,Object> outValues = new HashMap<String,Object>();
		String resultIds = "";
		int globalPass = 1;
		long beginDate = System.currentTimeMillis();
		for(Interface inter:interfaces){
			Map<String,Integer> map = executeInOutInterface(inter, login,outValues);
			int resultId = map.get("resultId");
			int isSuccess = map.get("isSuccess");
			int isPass = map.get("isPass");
			if(isSuccess==0){
				globalPass = 0;
			}
			if(isPass==0){
				globalPass = 0;
			}
			resultIds+=","+resultId;
		}
		long endDate = System.currentTimeMillis();
		long useDate = endDate-beginDate;
		resultIds = resultIds.substring(1);
		TaskPerformanceResultService taskPerformanceResultService = new TaskPerformanceResultService();
		int isDelete = 1;
		Timestamp executeTime = new Timestamp(System.currentTimeMillis());
		int taskPerformanceResultId = taskPerformanceResultService.addTaskPerformanceResult(taskId, resultIds, login.getId(), executeTime, isDelete, globalPass, beginDate, endDate, useDate);
		return taskPerformanceResultId;
	}
	public static Map<String,Integer> executeInOutInterface(Interface inter,User login,Map<String,Object> outValues) throws Exception{
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		int interfaceId = inter.getId();
		JSONObject result = getResult(inter,outValues); 
		outValue(interfaceId,result,outValues);
		int httpCode = inter.getHttpCode();
		int returnStatus = result.getInt("httpcode");
		String returnContent = result.getString("data");
		long beginDate = result.getLong("beginTime");
		long endDate = result.getLong("endTime");
		long useDate = endDate-beginDate;
		int isSuccess = 0;
		if(returnStatus==httpCode){
			isSuccess=1;
		}
		int executeUser = login.getId();
		Timestamp executeDate = new Timestamp(System.currentTimeMillis());
		int isDelete = 1;
		CheckService cs = new CheckService();
		List<InterfaceCheck> checks = cs.findCheckByInterface(interfaceId);
		Map<String,String> map = CheckResult.checkResult(checks, result);
		int isPass = Integer.parseInt(map.get("isPass"));
		String executeResult = map.get("executeResult");
		ResultPerformanceService rps = new ResultPerformanceService();
		int resultId = rps.addResultPerformance(interfaceId, returnStatus, returnContent, isSuccess, isPass, executeResult, executeUser, executeDate, isDelete, beginDate, endDate, useDate);
		resultMap.put("resultId", resultId);
		resultMap.put("isSuccess", isSuccess);
		resultMap.put("isPass", isPass);
		return resultMap;
	}
	public static void outValue(int interfaceId,JSONObject result,Map<String,Object> outValues) throws Exception{
		OutValueService outValueService = new OutValueService();
		List<OutValue> outs = outValueService.findOutValueByInterface(interfaceId);
		for (OutValue o:outs) {
			int space = o.getValueSpace();
			if(space==1){
				JSONObject cookies = result.getJSONObject("cookie");
				if(cookies.has(o.getOutValueKey())){
					String variableValue = cookies.getString(o.getOutValueKey());
					outValues.put(o.getOutValueName(), variableValue);
				}
			}else if(space==2){
				try{
					JSONObject data = result.getJSONObject("data");
					String valueKey = o.getOutValueKey();
					String value = jsonGetValueByKey(data, valueKey);
					if(value!=null){
						outValues.put(o.getOutValueName(),value );
					}
				}catch(JSONException e){
					e.printStackTrace();
					return;
				}
			}else if(space==3){
				String data = result.getString("data");
				String reg = o.getOutValueKey();
				if(reg.indexOf("@(")>=0&&reg.indexOf(")@")>0){
					int begin = reg.indexOf("@(");
					int end = reg.length()-(reg.lastIndexOf(")@")+2);
					reg = reg.replaceAll("@", "");
					Pattern p=Pattern.compile(reg); 
					Matcher m=p.matcher(data); 
					if(m.find()){ 
						String str = m.group(); 
						outValues.put(o.getOutValueName(),str.substring(begin,str.length()-end));
						System.out.println("+++"+outValues.toString());
					}
				}
			}
		}
	}
	/**
	 * 判断json中是否包含key的JSONObject
	 * @param json json串
	 * @param key 键，格式：zw[index]:判断是否包含key为zw的JSONArray，且长度大于index
	 * @return 包含返回true，否则返回false
	 * @throws JSONException
	 */
	public static boolean hasKey(JSONObject json,String key) throws JSONException{
		if(key.matches(".+\\[\\d+\\]")){
			String k = key.substring(0,key.indexOf("["));
			if(json.has(k)){
				int index = Integer.parseInt(key.substring(key.indexOf("[")+1,key.indexOf("]")));
				JSONArray jsons = json.getJSONArray(k);
				return jsons.length()>index;
			}else{
				return false;
			}
		}else{
			return json.has(key);
		}
	}
	/**
	 * 取出json中为key的JSON
	 * @param json json串
	 * @param key 键，格式：zw[1]:取出key为zw的JSONArray中下标为1的JSONObject
	 * @return JSONObject对象
	 * @throws JSONException
	 */
	public static JSONObject getJSONObjectByKey(JSONObject json,String key) throws JSONException{
		JSONObject j = null;
		if(key.matches(".+\\[\\d+\\]")){
			String k = key.substring(0,key.indexOf("["));
			if(json.has(k)){
				int index = Integer.parseInt(key.substring(key.indexOf("[")+1,key.indexOf("]")));
				JSONArray jsons = json.getJSONArray(k);
				if(jsons.length()>index){
					j = jsons.getJSONObject(index);
				}
			}
		}else{
			if(json.has(key)){
				j = json.getJSONObject(key);
			}
		}
		return j;
	}
	/**
	 * 在json串中取出key为valueKey的值
	 * @param data json串
	 * @param valueKey key，此值格式为k1-k2[1]-k3:data中为k1的json中key为k2的JSONArray下表为1的JSONObject
	 * 中key为k3的值。
	 * @return 字符串：value
	 * @throws JSONException
	 */
	public static String jsonGetValueByKey(JSONObject data,String valueKey) throws JSONException{
		String value = null;
		if(valueKey.indexOf("-")>0&&!valueKey.endsWith("-")&&valueKey.indexOf("--")==-1){
			String[] keys = valueKey.split("-");
			if(hasKey(data, keys[0])){
				JSONObject json = getJSONObjectByKey(data, keys[0]);
				for(int i=1;i<keys.length-1;i++){
					if(hasKey(json, keys[i])){
						json = getJSONObjectByKey(json, keys[i]);
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
	/**
	 * 判断json串中是否包含某一key
	 * @param data json串
	 * @param valueKey key，此值格式为k1-k2[1]-k3:data中为k1的json中key为k2的JSONArray下表为1的JSONObject
	 * 中key为k3的值。
	 * @return 包含返回true，否则返回false。
	 * @throws JSONException
	 */
	public static boolean jsonHasKey(JSONObject data,String valueKey) throws JSONException{
		if(valueKey.indexOf("-")>0&&!valueKey.endsWith("-")&&valueKey.indexOf("--")==-1){
			String[] keys = valueKey.split("-");
			if(hasKey(data, keys[0])){
				JSONObject json = getJSONObjectByKey(data, keys[0]);
				for(int i=1;i<keys.length-1;i++){
					if(hasKey(json, keys[i])){
						json = getJSONObjectByKey(json,keys[i]);
					}else{
						return false;
					}
				}
				if(json.has(keys[keys.length-1])){
					return true;
				}
			}
		}else{
			if(hasKey(data, valueKey)){
				return true;
			}
		}
		return false;
	}
	public static JSONObject getResult(Interface inter,Map<String,Object> outValues) throws Exception{
		ParameterService parameterService = new ParameterService();
		List<Parameter> parameters = parameterService.findParameterByInterface(inter.getId());
		Map<String,Object> parameterMap = getParameterMap(parameters,outValues);
		HeadService headService = new HeadService();
		List<Head> heads = headService.findHeadByInterface(inter.getId());
		Map<String,String> headMap = getHeadMap(heads,outValues);
		int requestMode = inter.getRequestMode();
		JSONObject result = null;
		if(requestMode==1){
			result = HttpRequest.get(inter.getInterfaceAddress(),  parameterMap, headMap);
		}else if(requestMode==2){
			result = HttpRequest.post(inter.getInterfaceAddress(), parameterMap, headMap);
		}else if(requestMode==3){
			result = HttpRequest.postJson(inter.getInterfaceAddress(), parameterMap, headMap);
		}else if(requestMode==4){
			result = HttpRequest.put(inter.getInterfaceAddress(), parameterMap, headMap);
		}else if(requestMode==5){
			result = HttpRequest.delete(inter.getInterfaceAddress(), parameterMap, headMap);
		}
		return result;
	}
	public static Map<String,String> getHeadMap(List<Head> heads,Map<String,Object> outValues){
		Map<String,String> headMap = new HashMap<String,String>();
		for (Head h : heads) {
			String variableStr = h.getHeadVariable();
			if(variableStr!=null&&variableStr.matches(".*\\$\\{\\w+\\}.*")){
				Pattern pattern=Pattern.compile("\\$\\{\\w+\\}"); 
		        Matcher matcher=pattern.matcher(variableStr);
		        String headValue = variableStr;
		        while(matcher.find()){
		        	String s = matcher.group();
		        	Object v = outValues.get(s.substring(2,s.length()-1));
		        	headValue = headValue.replaceFirst("\\$\\{\\w+\\}",v==null?"":(String)v);
		        }
		        headMap.put(h.getHeadKey(),headValue);
			}else{
				headMap.put(h.getHeadKey(),h.getHeadValue());
			}
		}
		return headMap;
	}
	public static Map<String,Object> getParameterMap(List<Parameter> parameters,Map<String,Object> outValues){
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		for (Parameter p : parameters) {
			String variableStr = p.getParameterVariable();
			if(variableStr!=null&&variableStr.startsWith("${")&&variableStr.endsWith("}")){
				String variableKey = variableStr.substring(variableStr.indexOf("${")+2,variableStr.indexOf("}"));
				if(outValues.containsKey(variableKey)){
					Object variableValue = outValues.get(variableKey);
					parameterMap.put(p.getParameterKey(),variableValue);
				}else{
					parameterMap.put(p.getParameterKey(), p.getParameterValue());
				}
			}else if(variableStr!=null&&variableStr.matches(".*\\$\\{\\w+\\}.*")){
				Pattern pattern=Pattern.compile("\\$\\{\\w+\\}"); 
		        Matcher matcher=pattern.matcher(variableStr);
		        String parameterValue = variableStr;
		        while(matcher.find()){
		        	String s = matcher.group();
		        	Object v = outValues.get(s.substring(2,s.length()-1));
		        	parameterValue = parameterValue.replaceFirst("\\$\\{\\w+\\}",v==null?"":(String)v);
		        }
		        parameterMap.put(p.getParameterKey(),parameterValue);
			}else{
				parameterMap.put(p.getParameterKey(), p.getParameterValue());
			}
		}
		return parameterMap;
	}
}
