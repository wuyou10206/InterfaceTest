<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="modify_save_project" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<%
				Project project = (Project)request.getAttribute("project");
			%>
			<p>
				<label>项目ID：</label>
				<input name="project_id" value="<%=project.getId() %>" readonly="readonly" type="text" size="30"/>
			</p>
			<p>
				<label>项目名称：</label>
				<input name="project_name" value="<%=project.getProjectName() %>" class="required" type="text" size="30"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
