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

import service.InterfacePerformanceService;
import service.ResultPerformanceService;
import util.AlertJson;
import util.AnalystResultPerformance;
import util.ExecuteInterface;
import entity.ResultPerformance;
import entity.User;

public class ExecuteInterfacePerformanceServlet extends HttpServlet {

	private static final long serialVersionUID = 8256857103813854679L;

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
			final int interfaceId = Integer.parseInt(request.getParameter("interface_id"));
			int threadNumber= Integer.parseInt(request.getParameter("thread_number"));
			final List<Integer> resultPerformanceIds = new ArrayList<Integer>();
			for(int i=0;i<threadNumber;i++){
				new Thread(){
					public void run() {
						try {
							int resultPerformanceId = ExecuteInterface.executeInterfacePerformance(interfaceId, login);
							resultPerformanceIds.add(resultPerformanceId);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
			while(true){
				if(resultPerformanceIds.size()==threadNumber){
					break;
				}
				Thread.sleep(100);
			}
			String resultPerformanceIdsStr = "";
			for(Integer resultPerformanceId:resultPerformanceIds){
				resultPerformanceIdsStr+=","+resultPerformanceId;
			}
			resultPerformanceIdsStr = resultPerformanceIdsStr.substring(1);
			ResultPerformanceService resultPerformanceService = new ResultPerformanceService();
			List<ResultPerformance> resultPerformances = resultPerformanceService.findResultByResultPerformanceIds(resultPerformanceIds);
			Map<String,Object> map = AnalystResultPerformance.analystResult(resultPerformances);
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
			InterfacePerformanceService interfacePerformanceService = new InterfacePerformanceService();
			interfacePerformanceService.addInterfacePerformance(interfaceId, resultPerformanceIdsStr, threadNumber, totalTime, avgTime, time5, time9, minTime, maxTime, tps, throughput,login.getId(), addTime, isDelete);
			net.sf.json.JSONObject json = AlertJson.successJson("性能测试执行完成","show_interface_"+interfaceId,"show_interface_"+interfaceId);
			PrintWriter out=response.getWriter();
			out.print(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
