<%@page import="service.ProjectService"%>
<%@ page language="java" import="java.util.*,service.*,entity.*" pageEncoding="utf-8"%>
<form id="pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="add_project" target="dialog" rel="add_project"><span>添加</span></a></li>
			<li><a class="delete" href="delete_project?project_id={sid_user}" rel="delete_project" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="modify_project?project_id={sid_user}" rel="modify_project" target="dialog"><span>修改</span></a></li>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">ID</th>
				<th width="120">项目名称</th>
				<th>创建者</th>
				<th width="100">创建时间</th>
			</tr>
		</thead>
		<tbody>
		<%
			UserService userService = new UserService();
			List<Project> projects= (List<Project>)request.getAttribute("projects");
			for(Project project:projects){
				int userId = project.getUserId();
				User user = userService.findUserById(userId);
		%>
			<tr target="sid_user" rel="<%=project.getId() %>">
				<td><%=project.getId() %></td>
				<td><%=project.getProjectName() %></td>
				<td><%=user.getAlias() %></td>
				<td><%=project.getAddTime() %></td>
				
			</tr>
		<%}	%>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
