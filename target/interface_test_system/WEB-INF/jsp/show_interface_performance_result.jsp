<%@ page language="java" import="java.util.*,entity.*,service.*"
	pageEncoding="utf-8"%>
<div class="pageContent sortDrag" selector="h1" layoutH="42">
	<%
		int interfaceId = (Integer) request.getAttribute("interface_id");
		InterfacePerformance ip = (InterfacePerformance) request
				.getAttribute("ip");
		String userName = new UserService().findUserById(ip.getUserId())
				.getAlias();
		String interfaceName = new InterfaceService().findInterfaceById(
				ip.getInterfaceId()).getInterfaceName();
	%>
	<div class="panelBar">
		<ul class="toolBar">

			<li class="line">line</li>
			<li><a class="icon" rel="show_interface_performance_history"
				href="show_interface_performance_history?interface_id=<%=interfaceId%>"
				target="navTab" title="查看历史记录"><span>查看历史记录</span> </a></li>

		</ul>
	</div>
	<div class="panel">
		<h1>接口性能测试报告</h1>
		<div class="row">
			<div class="col-md-12 col-sm-12">

				<div class="panel collapse">
					<h1>简要报告</h1>
					<div>
						<table class="list" width="100%">
							<thead>
								<tr>
									<th>ID</th>
									<th>Interface</th>
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
									<th><%=ip.getId()%></th>
									<th><%=interfaceName%>-<%=ip.getInterfaceId()%></th>
									<th><%=ip.getThreadNumber()%></th>
									<th><%=ip.getTotalTime()%>毫秒</th>
									<th><%=ip.getAvgTime()%>毫秒</th>
									<th><%=ip.getMinTime()%>毫秒</th>
									<th><%=ip.getMaxTime()%>毫秒</th>
									<th><%=ip.getTime5()%>毫秒</th>
									<th><%=ip.getTime9()%>毫秒</th>
									<th><%=String.format("%.2f", ip.getTps())%></th>
									<th><%=ip.getThroughput()%></th>
									<th><%=userName%></th>
									<th><%=ip.getAddTime()%></th>
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
									<th>返回的状态码</th>
									<th>是否成功</th>
									<th>是否通过</th>
									<th>执行结果</th>
									<th>执行时间</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>使用时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<ResultPerformance> rps = (List<ResultPerformance>) request
											.getAttribute("rps");
									for (int i = 0; i < rps.size(); i++) {
										ResultPerformance rp = rps.get(i);
								%>
								<tr>
									<th><%=i + 1%></th>
									<th><%=rp.getId()%></th>
									<th><%=rp.getReturnStatus()%></th>
									<th><%=rp.getIsSuccess() == 1 ? "<span style='color:green;'>成功</span>"
						: "<span style='color:red;'>失败</span>"%></th>
									<th><%=rp.getIsPass() == 1 ? "<span style='color:green;'>通过</span>"
						: "<span style='color:red;'>未通过</span>"%></th>
									<th><%=rp.getExecuteResult()%></th>
									<th><%=rp.getExecuteDate()%></th>
									<th><%=rp.getBeginDate()%></th>
									<th><%=rp.getEndDate()%></th>
									<th><%=rp.getUseDate()%>毫秒</th>
									<th><a
										href="show_result_performance?result_performance_id=<%=rp.getId()%>"
										target="navTab" rel="show_result_performance_<%=rp.getId()%>"><span>详情-<%=rp.getId() %></span>
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
