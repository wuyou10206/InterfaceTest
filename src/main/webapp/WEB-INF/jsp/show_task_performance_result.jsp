<%@ page language="java" import="java.util.*,entity.*,service.*"
	pageEncoding="utf-8"%>
<div class="pageContent sortDrag" selector="h1" layoutH="0">
	<%
		int taskId = (Integer) request.getAttribute("task_id");
		TaskPerformance tp = (TaskPerformance) request
				.getAttribute("tp");
		String userName = new UserService().findUserById(tp.getUserId())
				.getAlias();
		String taskName = new TaskService().findTaskByID(tp.getTaskId()).getTaskName();
	%>
	<div class="panelBar">
		<ul class="toolBar">

			<li class="line">line</li>
			<li><a class="icon" rel="show_task_performance_history"
				href="show_task_performance_history?task_id=<%=taskId%>"
				target="navTab" title="查看历史记录"><span>查看历史记录</span> </a></li>

		</ul>
	</div>
	<div class="panel">
		<h1>任务性能测试报告</h1>
		<div class="row">
			<div class="col-md-12 col-sm-12">

				<div class="panel collapse">
					<h1>简要报告</h1>
					<div>
						<table class="list" width="100%">
							<thead>
								<tr>
									<th>ID</th>
									<th>Task</th>
									<th>ThreadNumber</th>
									<th>TotalTime</th>
									<th>AvgTime</th>
									<th>MinTime</th>
									<th>MaxTime</th>
									<th>50%Time</th>
									<th>90%Time</th>
									<th>TPS</th>
									<th>Throughput</th>
									<th>User</th>
									<th>AddTime</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><%=tp.getId()%></th>
									<th><%=taskName%>-<%=tp.getTaskId()%></th>
									<th><%=tp.getThreadNumber()%></th>
									<th><%=tp.getTotalTime()%>毫秒</th>
									<th><%=tp.getAvgTime()%>毫秒</th>
									<th><%=tp.getMinTime()%>毫秒</th>
									<th><%=tp.getMaxTime()%>毫秒</th>
									<th><%=tp.getTime5()%>毫秒</th>
									<th><%=tp.getTime9()%>毫秒</th>
									<th><%=String.format("%.2f", tp.getTps())%></th>
									<th><%=tp.getThroughput()%></th>
									<th><%=userName%></th>
									<th><%=tp.getAddTime()%></th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="col-md-12 col-sm-12">

				<div class="panel collapse">
					<h1>详细报告</h1>
					<div>
						<table class="list" width="100%">
							<thead>
								<tr>
									<th>序号</th>
									<th>ID</th>
									<th>是否通过</th>
									<th>执行时间</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>使用时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<TaskPerformanceResult> tprs = (List<TaskPerformanceResult>) request
											.getAttribute("tprs");
									for (int i = 0; i < tprs.size(); i++) {
										TaskPerformanceResult tpr = tprs.get(i);
								%>
								<tr>
									<th><%=i + 1%></th>
									<th><%=tpr.getId()%></th>
									<th><%=tpr.getTaskPass() == 1 ? "<span style='color:green;'>通过</span>"
						: "<span style='color:red;'>未通过</span>"%></th>
									<th><%=tpr.getExecuteTime()%></th>
									<th><%=tpr.getBeginDate()%></th>
									<th><%=tpr.getEndDate()%></th>
									<th><%=tpr.getUseDate()%>毫秒</th>
									<th><a
										href="show_task_performance_details?task_performance_result_id=<%=tpr.getId()%>"
										target="navTab" rel="show_task_performance_details_<%=tpr.getId()%>"><span>详情-<%=tpr.getId() %></span>
									</a></th>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</div>

</div>
