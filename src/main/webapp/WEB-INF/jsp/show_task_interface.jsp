<%@page import="entity.TimerTask"%>
<%@ page language="java" import="java.util.*,entity.*,service.*"
	pageEncoding="utf-8"%>

<div class="pageContent sortDrag" selector="h1" layoutH="42">
	<%
		TaskResult taskResult = (TaskResult) request
				.getAttribute("taskResult");
		int taskId = (Integer) request.getAttribute("task_id");
		TimerTask timerTask = (TimerTask) request.getAttribute("timerTask");
		TaskPerformance tp = (TaskPerformance)request.getAttribute("tp");
		String loopTimeStr = "";
		if (timerTask != null) {
			long loopTime = timerTask.getLoopTime();
			if (loopTime < 1000) {
				loopTimeStr = loopTime + "毫秒";
			} else if (loopTime < 1000 * 60) {
				loopTimeStr = (loopTime / 1000) + "秒" + (loopTime % 1000)
						+ "毫秒";
			} else if (loopTime < 1000 * 60 * 60) {
				loopTimeStr = (loopTime / 60 / 1000) + "分"
						+ (loopTime % 60000 / 1000) + "秒"
						+ (loopTime % 1000) + "毫秒";
			} else {
				loopTimeStr = (loopTime / 60 / 60 / 1000) + "小时"
						+ (loopTime % 3600000 / 60000) + "分"
						+ (loopTime % 60000 / 1000) + "秒"
						+ (loopTime % 1000) + "毫秒";
			}
		}
	%>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" target="dialog"
				rel="modify_task_interfaceIds"
				href="modify_task_interfaceIds?task_id=<%=taskId%>"><span>修改任务</span>
			</a>
			</li>

			<li><a class="icon" rel="execute_task"
				href="execute_interface_in_out?task_id=<%=taskId%>"
				target="ajaxTodo" title="确定要执行该任务么?"><span>执行该任务</span> </a>
			</li>
			<%
				if (timerTask == null) {
			%>
			<li><a class="add" rel="add_timer_task"
				href="add_timer_task?task_id=<%=taskId%>" target="dialog"
				title="增加定时任务"><span>增加定时任务</span> </a>
			</li>
			<%
				} else {
			%>
			<li><a class="delete" rel="delete_timer_task"
				href="delete_timer_task?task_id=<%=taskId%>" target="ajaxTodo"
				title="取消定时任务"><span>取消定时任务</span> </a>
			</li>

			<li><span style="color:red;">该任务的定时任务为：<%=timerTask.getExecuteTime()%>开始执行，每隔<%=loopTimeStr%>执行一次</span>
			</li>
			<%
				}
			%>
			<%
				if (taskResult != null) {
			%>
			<li><a class="edit" rel="show_task_history"
				href="show_task_history?task_id=<%=taskId%>" target="navTab"
				title="查看历史记录"><span>查看历史记录</span> </a>
			</li>
			<%
				}
			%>
			<li><a class="add" rel="test_task_performance"
				href="test_task_performance?task_id=<%=taskId%>" target="dialog"
				title="测试任务性能"><span>测试任务性能</span> </a>
			</li>
			<%if(tp!=null){ %>
			<li><a class="icon"
				href="show_task_performance_result?task_id=<%=taskId%>"
				target="navTab" title="查看测试性能结果"><span>查看测试性能结果</span> </a>
			</li>
			<%} %>
		</ul>
	</div>
	<div class="panel">
		<h1>接口列表</h1>
		<div>
			<table class="list" width="98%">
				<thead>
					<tr>
						<th width="50">ID</th>
						<th>接口名字</th>
						<th width="580">接口地址</th>
						<th>请求方式</th>
						<th>创建者</th>
						<th>创建时间</th>
						<th>操作</th>
						<th>查看</th>
					</tr>
				</thead>
				<tbody>
					<%
						UserService userService = new UserService();
						List<Interface> interfaces = (List<Interface>) request
								.getAttribute("interfaces");
						for (int i = 0; i < interfaces.size(); i++) {
							Interface inter = interfaces.get(i);
							int userId = inter.getCreateUser();
							User user = userService.findUserById(userId);
					%>
					<tr target="sid_user" rel="<%=inter.getId()%>">
						<th width="50"><%=inter.getId()%></th>
						<th><%=inter.getInterfaceName()%></th>
						<th width="580"><%=inter.getInterfaceAddress()%></th>
						<th><%=inter.getRequestMode() == 1 ? "get" : "post"%></th>
						<th><%=user.getAlias()%></th>
						<th><%=inter.getCreateDate()%></th>
						<th>
							<%
								if (interfaces.size() == 1) {

									} else if (i == 0) {
							%> <a target="ajaxTodo" title="确定要向下移动吗?" rel="interface_down"
							href="interface_down?interface_id=<%=inter.getId()%>&task_id=<%=taskId%>"><span>向下移动</span>
						</a> <%
 	} else if (i == interfaces.size() - 1) {
 %> <a target="ajaxTodo" title="确定要向上移动吗?" rel="interface_up"
							href="interface_up?interface_id=<%=inter.getId()%>&task_id=<%=taskId%>"><span>向上移动</span>
						</a> <%
 	} else {
 %> <a target="ajaxTodo" title="确定要向上移动吗?" rel="interface_up"
							href="interface_up?interface_id=<%=inter.getId()%>&task_id=<%=taskId%>"><span>向上移动</span>
						</a> &nbsp;&nbsp;&nbsp; <a target="ajaxTodo" title="确定要向下移动吗?"
							rel="interface_down"
							href="interface_down?interface_id=<%=inter.getId()%>&task_id=<%=taskId%>"><span>向下移动</span>
						</a> <%
 	}
 %>
						</th>
						<th><a href="show_interface?interface_id=<%=inter.getId()%>"
							target="navTab" rel="show_interface_<%=inter.getId()%>"><span>接口详情</span>
						</a>
						</th>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<div class="panel">
		<%
			if (taskResult != null) {
				ResultService resultService = new ResultService();
				List<Result> results = resultService
						.findResultByResultIds(taskResult.getResultIds());
				String executeUser = new UserService().findUserById(
						taskResult.getUserId()).getAlias();
				int taskPass = taskResult.getTaskPass();
		%>
		<h1>
			执行结果:<%=taskPass == 1 ? "<span style='color:green;'>任务执行通过</span>"
						: "<span style='color:red;'>任务执行失败</span>"%></h1>
		<div>
			<table class="list" width="98%">
				<thead>
					<tr>
						<th>接口ID</th>
						<th>结果ID</th>
						<th>结论</th>
						<th>执行结果</th>
						<th>执行人</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Result r : results) {
								Interface inter = new InterfaceService()
										.findInterfaceById(r.getInterfaceId());
					%>
					<tr>
						<th><%=r.getInterfaceId()%></th>
						<th><%=r.getId()%></th>
						<th><span style="color:blue;">执行时间为：<%=r.getExecuteDate()%></span><br />
							<%
								if (r.getIsSuccess() == 1) {
							%> <span style="color:green;">请求访问通过，HttpCode为<%=r.getReturnStatus()%>,期望的HttpCode为<%=inter.getHttpCode()%></span><br />
							<%
								} else {
							%> <span style="color:red;">请求访问未通过，HttpCode为<%=r.getReturnStatus()%>,期望的HttpCode为<%=inter.getHttpCode()%></span><br />
							<%
								}

										if (r.getIsPass() == 1) {
							%> <span style="color:green;">所有检查点均通过验证</span><br /> <%
 	} else {
 %> <span style="color:red;">检查点未通过验证，详情请看执行结果</span><br /> <%
 	}
 %>
						</th>
						<th><%=r.getExecuteResult()%></th>
						<th><%=executeUser%></th>
						<th><a
							href="show_result?result_id=<%=r.getId()%>"
							target="navTab" rel="show_result_<%=r.getId()%>"><span>详情-<%=r.getId()%></span>
						</a></th>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<%
			} else {
		%>
		<span style="font-size:30px">该任务还未执行过</span>
		<%
			}
		%>
	</div>

</div>

