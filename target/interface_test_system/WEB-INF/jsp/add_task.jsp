<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="save_task" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<%
	int projectId = (Integer)request.getAttribute("project_id");

			%>
			<p>
				<label>任务名称：</label>
				<input name="task_name" class="required" type="text" size="30"/>
			</p>
			<p>
				<label>所包的接口ID：</label>
				<input type="text" class="required" readonly="readonly" name="interfaceIds" value="" lookupGroup="" />
				<a class="btnLook" href="show_interface_all?project_id=<%=projectId %>" lookupGroup="">选择该任务包含的接口</a>		
			</p>
			<p>
				<label>项目ID：</label>
				<input name="project_id" class="required" value="<%=projectId %>" readonly="readonly" type="text" size="30"/>
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
