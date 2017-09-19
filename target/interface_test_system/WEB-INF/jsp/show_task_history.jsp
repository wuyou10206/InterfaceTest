<%@page import="entity.TimerTask"%>
<%@ page language="java" import="java.util.*,entity.*,service.*"
	pageEncoding="utf-8"%>

<div class="pageContent sortDrag" selector="h1" layoutH="42">
	<%
		List<TaskResult> taskResults = (List<TaskResult>)request.getAttribute("taskResults");
		int taskId = (Integer) request.getAttribute("task_id");
		TimerTask timerTask = (TimerTask)request.getAttribute("timerTask");
		for(int i=0;i<taskResults.size();i++){
			TaskResult taskResult = taskResults.get(i);
		
	%>
	
	<div class="panel collapse">
	<%
		
		if(taskResult!=null){
			ResultService resultService = new ResultService();
			List<Result> results = resultService.findResultByResultIds(taskResult.getResultIds());
			String executeUser = new UserService().findUserById(taskResult.getUserId()).getAlias();
			int taskPass = taskResult.getTaskPass();
	%>
		<h1 style="color:#FF00FF;"><%=i+1%>、执行时间为:<%=taskResult.getExecuteTime()%>的执行结果:<%=taskPass==1?"<span style='color:green;'>任务执行通过</span>":"<span style='color:red;'>任务执行失败</span>" %></h1>
		<div>
			<table class="list" width="98%">
				<thead>
					<tr>
						<th>接口ID</th>
						<th>结果ID</th>
						<th>结论</th>
						<th>执行结果</th>
						<th>执行人</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(Result r:results){
					Interface inter = new InterfaceService().findInterfaceById(r.getInterfaceId());
				%>
					<tr>
						<th><%=r.getInterfaceId() %></th>
						<th><%=r.getId() %></th>
						<th>
						<span style="color:blue;">执行时间为：<%=r.getExecuteDate()%></span><br/>
						<%
						if(r.getIsSuccess()==1){
						%>
					<span style="color:green;">请求访问通过，HttpCode为<%=r.getReturnStatus() %>,期望的HttpCode为<%=inter.getHttpCode() %></span><br/>
					<%
						}else{
						%>
					<span style="color:red;">请求访问未通过，HttpCode为<%=r.getReturnStatus() %>,期望的HttpCode为<%=inter.getHttpCode() %></span><br/>
					<%
						}					
					
						if(r.getIsPass()==1){
						%>
					<span style="color:green;">所有检查点均通过验证</span><br/>
					<%
						}else{
						%>
					<span style="color:red;">检查点未通过验证，详情请看执行结果</span><br/>
					<%
						}					
					%>
						</th>
						<th><%=r.getExecuteResult() %></th>
						<th><%=executeUser %></th>
					</tr>
				<%} %>
				</tbody>
			</table>
		</div>
		<%}else{%>
		<span style="font-size:30px">该任务还未执行过</span>
		<%} %>
	</div>
	<%} %>
</div>

