package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.TaskPerformanceResultService;
import service.TaskPerformanceService;
import util.AlertJson;
import util.AnalystResultPerformance;
import util.ExecuteInterface;
import entity.TaskPerformanceResult;
import entity.User;

public class ExecuteTaskPerformanceServlet extends HttpServlet {

	private static final long serialVersionUID = 6351443226792080946L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession();
			final User login = (User) session.getAttribute("login_user");
			if(login==null){
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return ;
			}
			final int taskId = Integer.parseInt(request.getParameter("task_id"));
			int threadNumber= Integer.parseInt(request.getParameter("thread_number"));
			final List<Integer> taskPerformanceResultIds = new ArrayList<Integer>();
			for(int i=0;i<threadNumber;i++){
				new Thread(){
					public void run() {
						try {
							int taskPerformanceResultId = ExecuteInterface.executeTaskPerformance(taskId, login);
							taskPerformanceResultIds.add(taskPerformanceResultId);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
			while(true){
				if(taskPerformanceResultIds.size()==threadNumber){
					break;
				}
				Thread.sleep(100);
			}
			String taskPerformanceResultIdsStr = "";
			for(Integer taskPerformanceResultId:taskPerformanceResultIds){
				taskPerformanceResultIdsStr+=","+taskPerformanceResultId;
			}
			taskPerformanceResultIdsStr = taskPerformanceResultIdsStr.substring(1);
			
			TaskPerformanceResultService taskPerformanceResultService = new TaskPerformanceResultService();
			List<TaskPerformanceResult> taskPerformanceResults = taskPerformanceResultService.findTaskPerformanceResultByTaskPerformanceResultIds(taskPerformanceResultIds);
			
			Map<String,Object> map = AnalystResultPerformance.analystTaskResult(taskPerformanceResults);
			long totalTime = (Long) map.get("totalTime");
			long avgTime = (Long) map.get("avgTime");
			long minTime = (Long) map.get("minTime");
			long maxTime = (Long) map.get("maxTime");
			long time5 = (Long) map.get("time5");
			long time9 = (Long) map.get("time9");
			double tps = (Double)map.get("tps");
			double throughput = 0;
			int isDelete = 1;
			Timestamp addTime = new Timestamp(System.currentTimeMillis());
			TaskPerformanceService taskPerformanceService = new TaskPerformanceService();
			taskPerformanceService.addTaskPerformance(taskId, taskPerformanceResultIdsStr, threadNumber, totalTime, avgTime, time5, time9, minTime, maxTime, tps, throughput, login.getId(), addTime, isDelete);
			net.sf.json.JSONObject json = AlertJson.successJson("任务性能测试完成", "show_task_"+taskId,"show_task_"+taskId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
