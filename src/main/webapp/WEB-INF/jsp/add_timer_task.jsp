<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="save_timer_task" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<%
				int taskId = (Integer)request.getAttribute("task_id");
			%>
			<p>
				<label>任务ID：</label>
				<input name="task_id" value="<%=taskId %>" readonly="readonly" type="text" size="30"/>
			</p>
			<p>
				<label>执行时间：</label>
				<input name="execute_time" value="" class="required date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" type="text" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>间隔时间:毫秒：</label>
				<input name="loop_time" value="" class="required digits" type="text" size="30"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
