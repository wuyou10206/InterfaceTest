package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import entity.InterfaceCheck;

public class CheckResult {
	public static String checkTrue(String checkStr){
		return "<span style='color:green;'>"+checkStr+"</span><br/>";
	}
	public static String checkFalse(String checkStr){
		return "<span style='color:red;'>"+checkStr+"</span><br/>";
	}
	public static Map<String,String> checkResult(List<InterfaceCheck> checks,JSONObject result) throws JSONException{
		Map<String,String> map = new HashMap<String,String>();
		if(checks.size()==0){
			map.put("isPass","1");
			map.put("executeResult","该接口没有检查点");
			return map;
		}
		String executeStr = "";
		int isPass = 1;
		for (InterfaceCheck check:checks) {
			int checkMode = check.getCheckMode();
			if(checkMode==1){
				try{
					JSONObject data = result.getJSONObject("data");
					if(ExecuteInterface.jsonHasKey(data,check.getCheckPoint())){
						executeStr+=checkTrue("执行结果中包含"+check.getCheckPoint()+"这个key");
					}else{
						executeStr+=checkFalse("执行结果中不包含"+check.getCheckPoint()+"这个key=>"+check.getCheckDesc());
						isPass = 0;
					}
				}catch(JSONException e){
					e.printStackTrace();
					executeStr+=checkFalse("执行失败：返回错误的JSON=>"+check.getCheckDesc());
					isPass = 0;
				}
			}else if(checkMode==2){
				try{
				JSONObject data = result.getJSONObject("data");
					String checkPoint = check.getCheckPoint();
					if(checkPoint.indexOf("=")>0&&checkPoint.indexOf("=")<checkPoint.length()-1){
						String[] ss = checkPoint.split("=");
						if(ExecuteInterface.jsonHasKey(data,ss[0])){
							String valueStr = ExecuteInterface.jsonGetValueByKey(data,ss[0]);
							try{
								int value = Integer.parseInt(valueStr);
								int number = Integer.parseInt(ss[1]);
								if(value==number){
									executeStr+=checkTrue(ss[0]+":这个key的值与<"+ss[1]+">的值相等");
								}else{
									executeStr+=checkFalse(ss[0]+":这个key的值与<"+ss[1]+">的值不相等=>"+check.getCheckDesc());
									isPass = 0;
								}
							}catch(NumberFormatException e){
								executeStr+=checkFalse(ss[0]+":这个key的值不是int类型，或者"+ss[1]+":这个值不是int类型，请修改后再次执行=>"+check.getCheckDesc());
								isPass = 0;
							}		
						}else{
							executeStr+=checkFalse("执行结果中不包含"+ss[0]+"这个key=>"+check.getCheckDesc());
							isPass = 0;
						}
					}else if(checkPoint.indexOf(">")>0&&checkPoint.indexOf(">")<checkPoint.length()-1){
						String[] ss = checkPoint.split(">");
						if(ExecuteInterface.jsonHasKey(data,ss[0])){
							String valueStr = ExecuteInterface.jsonGetValueByKey(data,ss[0]);
							try{
								int value = Integer.parseInt(valueStr);
								int number = Integer.parseInt(ss[1]);
								if(value>number){
									executeStr+=checkTrue(ss[0]+":这个key的值大于<"+ss[1]+">的值");
								}else{
									executeStr+=checkFalse(ss[0]+":这个key的值不大于<"+ss[1]+">的值=>"+check.getCheckDesc());
									isPass = 0;
								}
							}catch(NumberFormatException e){
								executeStr+=checkFalse(ss[0]+":这个key的值不是int类型，或者"+ss[1]+":这个值不是int类型，请修改后再次执行=>"+check.getCheckDesc());
								isPass = 0;
							}		
						}else{
							executeStr+=checkFalse("执行结果中不包含"+ss[0]+"这个key=>"+check.getCheckDesc());
							isPass = 0;
						}
					}else if(checkPoint.indexOf("<")>0&&checkPoint.indexOf("<")<checkPoint.length()-1){
						String[] ss = checkPoint.split("<");
						if(ExecuteInterface.jsonHasKey(data,ss[0])){
							String valueStr = ExecuteInterface.jsonGetValueByKey(data,ss[0]);
							try{
								int value = Integer.parseInt(valueStr);
								int number = Integer.parseInt(ss[1]);
								if(value<number){
									executeStr+=checkTrue(ss[0]+":这个key的值小于<"+ss[1]+">的值");
								}else{
									executeStr+=checkFalse(ss[0]+":这个key的值不小于<"+ss[1]+">的值=>"+check.getCheckDesc());
									isPass = 0;
								}
							}catch(NumberFormatException e){
								executeStr+=checkFalse(ss[0]+":这个key的值不是int类型，或者"+ss[1]+":这个值不是int类型，请修改后再次执行=>"+check.getCheckDesc());
								isPass = 0;
							}		
						}else{
							executeStr+=checkFalse("执行结果中不包含"+ss[0]+"这个key=>"+check.getCheckDesc());
							isPass = 0;
						}
					}else if(checkPoint.indexOf("!=")>0&&checkPoint.indexOf("!=")<checkPoint.length()-1){
						String[] ss = checkPoint.split("!=");
						if(ExecuteInterface.jsonHasKey(data,ss[0])){
							String valueStr = ExecuteInterface.jsonGetValueByKey(data,ss[0]);
							try{
								int value = Integer.parseInt(valueStr);
								int number = Integer.parseInt(ss[1]);
								if(value>number){
									executeStr+=checkTrue(ss[0]+":这个key的值不等于<"+ss[1]+">的值");
								}else{
									executeStr+=checkFalse(ss[0]+":这个key的值等于<"+ss[1]+">的值=>"+check.getCheckDesc());
									isPass = 0;
								}
							}catch(NumberFormatException e){
								executeStr+=checkFalse(ss[0]+":这个key的值不是int类型，或者"+ss[1]+":这个值不是int类型，请修改后再次执行=>"+check.getCheckDesc());
								isPass = 0;
							}		
						}else{
							executeStr+=checkFalse("执行结果中不包含"+ss[0]+"这个key=>"+check.getCheckDesc());
							isPass = 0;
						}
					}else if(checkPoint.indexOf(">=")>0&&checkPoint.indexOf(">=")<checkPoint.length()-1){
						String[] ss = checkPoint.split(">=");
						if(ExecuteInterface.jsonHasKey(data,ss[0])){
							String valueStr = ExecuteInterface.jsonGetValueByKey(data,ss[0]);
							try{
								int value = Integer.parseInt(valueStr);
								int number = Integer.parseInt(ss[1]);
								if(value>number){
									executeStr+=checkTrue(ss[0]+":这个key的值大于等于<"+ss[1]+">的值");
								}else{
									executeStr+=checkFalse(ss[0]+":这个key的值小于<"+ss[1]+">的值=>"+check.getCheckDesc());
									isPass = 0;
								}
							}catch(NumberFormatException e){
								executeStr+=checkFalse(ss[0]+":这个key的值不是int类型，或者"+ss[1]+":这个值不是int类型，请修改后再次执行=>"+check.getCheckDesc());
								isPass = 0;
							}		
						}else{
							executeStr+=checkFalse("执行结果中不包含"+ss[0]+"这个key=>"+check.getCheckDesc());
							isPass = 0;
						}
					}else if(checkPoint.indexOf("<=")>0&&checkPoint.indexOf("<=")<checkPoint.length()-1){
						String[] ss = checkPoint.split("<=");
						if(ExecuteInterface.jsonHasKey(data,ss[0])){
							String valueStr =ExecuteInterface.jsonGetValueByKey(data,ss[0]);
							try{
								int value = Integer.parseInt(valueStr);
								int number = Integer.parseInt(ss[1]);
								if(value>number){
									executeStr+=checkTrue(ss[0]+":这个key的值小于等于<"+ss[1]+">的值");
								}else{
									executeStr+=checkFalse(ss[0]+":这个key的值大于<"+ss[1]+">的值=>"+check.getCheckDesc());
									isPass = 0;
								}
							}catch(NumberFormatException e){
								executeStr+=checkFalse(ss[0]+":这个key的值不是int类型，或者"+ss[1]+":这个值不是int类型，请修改后再次执行=>"+check.getCheckDesc());
								isPass = 0;
							}		
						}else{
							executeStr+=checkFalse("执行结果中不包含"+ss[0]+"这个key=>"+check.getCheckDesc());
							isPass = 0;
						}
					}else{
						executeStr+=checkFalse(check.getCheckPoint()+":这个检查点格式错误，请更改后再次执行=>"+check.getCheckDesc());
						isPass = 0;
					}
				}catch(JSONException e){
					executeStr+=checkFalse("执行失败：返回错误的JSON=>"+check.getCheckDesc());
					isPass = 0;
				}
			}else if(checkMode==3){
				String dataStr = result.getString("data");
				String checkPointStr = check.getCheckPoint();
				if(dataStr.contains(checkPointStr)){
					executeStr+=checkTrue("执行结果中包含"+checkPointStr);
				}else{
					executeStr+=checkFalse("执行结果中不包含"+checkPointStr+"=>"+check.getCheckDesc());
				}
			}else if(checkMode==4){
				try{
					JSONObject data = result.getJSONObject("data");
					if(ExecuteInterface.jsonHasKey(data,check.getCheckPoint())){
						String value = ExecuteInterface.jsonGetValueByKey(data,check.getCheckPoint());
						if(value.equals("")){
							executeStr+=checkFalse(check.getCheckPoint()+":这个key的值为空字符串=>"+check.getCheckDesc());
							isPass = 0;
						}else{
							executeStr+=checkTrue(check.getCheckPoint()+":这个key的值不是空字符串");
						}
					}else{
						executeStr+=checkFalse("执行结果中不包含"+check.getCheckPoint()+"这个key=>"+check.getCheckDesc());
						isPass = 0;
					}
				}catch(JSONException e){
					executeStr+=checkFalse("执行失败：返回错误的JSON=>"+check.getCheckDesc());
					isPass = 0;
				}
			}else if(checkMode==5){
				try{
					JSONObject data = result.getJSONObject("data");
					String checkPoint = check.getCheckPoint();
					if(checkPoint.indexOf("=")>0&&checkPoint.indexOf("=")<checkPoint.length()-1){
						String[] ss = checkPoint.split("=");
						if(ExecuteInterface.jsonHasKey(data,ss[0])){
							String valueStr = ExecuteInterface.jsonGetValueByKey(data,ss[0]);
							if(valueStr.equals(ss[1])){
								executeStr+=checkTrue(ss[0]+":这个key的值与<"+ss[1]+">的值相等");
							}else{
								executeStr+=checkFalse(ss[0]+":这个key的值与<"+ss[1]+">的值不相等=>"+check.getCheckDesc());
								isPass = 0;
							}		
						}else{
							executeStr+=checkFalse("执行结果中不包含"+ss[0]+"这个key=>"+check.getCheckDesc());
							isPass = 0;
						}
					}else{
						executeStr+=checkFalse(check.getCheckPoint()+":这个检查点格式错误，请更改后再次执行=>"+check.getCheckDesc());
						isPass = 0;
					}
				}catch(JSONException e){
					executeStr+=checkFalse("执行失败：返回错误的JSON=>"+check.getCheckDesc());
					isPass = 0;
				}
			}
		}
		map.put("isPass",isPass+"");
		map.put("executeResult",executeStr);
		return map;
	}
}
