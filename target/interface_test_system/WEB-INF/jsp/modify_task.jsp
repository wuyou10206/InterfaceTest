<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="modify_save_task" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<%
			Task task = (Task)request.getAttribute("task");
			%>
			<p>
				<label>任务ID：</label>
				<input name="task_id" readonly="readonly" value="<%=task.getId() %>" class="required" type="text" size="30"/>
			</p>
			<p>
				<label>任务名称：</label>
				<input name="task_name" value="<%=task.getTaskName() %>" class="required" type="text" size="30"/>
			</p>
			<p>
				<label>所包的接口ID：</label>
				<input type="text" class="required" readonly="readonly" name="interfaceIds" value="<%=task.getInterfaceIds() %>" lookupGroup="" />
				<a class="btnLook" href="show_interface_all?project_id=<%=task.getProjectId() %>" lookupGroup="">选择该任务包含的接口</a>		
			</p>
			<p>
				<label>项目ID：</label>
				<input name="project_id" readonly="readonly" value="<%=task.getProjectId() %>" class="required" type="text" size="30"/>
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
