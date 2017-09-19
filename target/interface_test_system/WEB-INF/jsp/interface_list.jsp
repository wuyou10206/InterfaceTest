<%@ page language="java" import="java.util.*,entity.*,service.*" pageEncoding="utf-8"%>

<div class="pageContent sortDrag" selector="h1" layoutH="42">
	<%
		int moduleId = (Integer)request.getAttribute("module_id");
	%>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" width="1200" height="600" mask=true resizable=false minable=false maxable=false drawable=false href="add_interface?module_id=<%=moduleId %>" target="dialog" rel="add_interface"><span>添加接口</span></a></li>
			<li><a class="delete" href="delete_interface?module_id=<%=moduleId %>&interface_id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
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
					</tr>
				</thead>
				<tbody>
				<%
					UserService userService = new UserService();
					List<Interface> interfaces = (List<Interface>)request.getAttribute("interfaces");
					for(Interface inter:interfaces){
						int userId = inter.getCreateUser();
						User user = userService.findUserById(userId);
				%>
					<tr target="sid_user" rel="<%=inter.getId() %>">
						<th width="50"><%=inter.getId() %></th>
						<th><%=inter.getInterfaceName() %></th>
						<th width="580"><%=inter.getInterfaceAddress() %></th>
						<th><%=inter.getRequestMode()==1?"get":"post" %></th>
						<th><%=user.getAlias() %></th>
						<th><%=inter.getCreateDate() %></th>
					</tr>
				<%} %>
				</tbody>
			</table>
		</div>
	</div>


</div>

