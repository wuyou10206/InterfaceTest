package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.ResultPerformance;
import entity.TaskPerformanceResult;


public class AnalystResultPerformance {
	public static Map<String,Object> analystResult(List<ResultPerformance> resultPerformances){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Long> begins = new ArrayList<Long>();
		List<Long> ends = new ArrayList<Long>();
		List<Long> uses = new ArrayList<Long>();
		long sum = 0;
		for (ResultPerformance resultPerformance : resultPerformances) {
			begins.add(resultPerformance.getBeginDate());
			ends.add(resultPerformance.getEndDate());
			uses.add(resultPerformance.getUseDate());
			sum+=resultPerformance.getUseDate();
		}
		Collections.sort(begins);
		Collections.sort(ends);
		Collections.sort(uses);
		long minTime = uses.get(0);
		long maxTime = uses.get(uses.size()-1);
		long begin = begins.get(0);
		long end = ends.get(ends.size()-1);
		long totalTime = end-begin;
		long avgTime = sum/uses.size();
		int index5 =(uses.size()*0.5)%1==0?(int) (uses.size()*0.5)-1:(int) (uses.size()*0.5);
		int index9 = (uses.size()*0.9)%1==0?(int) (uses.size()*0.9)-1:(int) (uses.size()*0.9);
//		long end5 = ends.get(index5);
//		long end9 = ends.get(index9);
//		long time5 = end5-begin;
//		long time9 = end9-begin;
		long time5 = uses.get(index5);
		long time9 = uses.get(index9);
		double tps = uses.size()/(totalTime/1000D);
		map.put("minTime", minTime);
		map.put("maxTime", maxTime);
		map.put("totalTime",totalTime);
		map.put("avgTime", avgTime);
		map.put("time5", time5);
		map.put("time9", time9);
		map.put("tps", tps);
		return map;
	}
	public static Map<String,Object> analystTaskResult(List<TaskPerformanceResult> taskPerformanceResults){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Long> begins = new ArrayList<Long>();
		List<Long> ends = new ArrayList<Long>();
		List<Long> uses = new ArrayList<Long>();
		long sum = 0;
		for (TaskPerformanceResult resultPerformance : taskPerformanceResults) {
			begins.add(resultPerformance.getBeginDate());
			ends.add(resultPerformance.getEndDate());
			uses.add(resultPerformance.getUseDate());
			sum+=resultPerformance.getUseDate();
		}
		Collections.sort(begins);
		Collections.sort(ends);
		Collections.sort(uses);
		long minTime = uses.get(0);
		long maxTime = uses.get(uses.size()-1);
		long begin = begins.get(0);
		long end = ends.get(ends.size()-1);
		long totalTime = end-begin;
		long avgTime = sum/uses.size();
		int index5 =(uses.size()*0.5)%1==0?(int) (uses.size()*0.5)-1:(int) (uses.size()*0.5);
		int index9 = (uses.size()*0.9)%1==0?(int) (uses.size()*0.9)-1:(int) (uses.size()*0.9);
//		long end5 = ends.get(index5);
//		long end9 = ends.get(index9);
//		long time5 = end5-begin;
//		long time9 = end9-begin;
		long time5 = uses.get(index5);
		long time9 = uses.get(index9);
		double tps = uses.size()/(totalTime/1000D);
		map.put("minTime", minTime);
		map.put("maxTime", maxTime);
		map.put("totalTime",totalTime);
		map.put("avgTime", avgTime);
		map.put("time5", time5);
		map.put("time9", time9);
		map.put("tps", tps);
		return map;
	}
}
