<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="execute_interface_performance" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<%
				int interfaceId = (Integer)request.getAttribute("interface_id");
			%>
			<p>
				<label>接口ID：</label>
				<input name="interface_id" value="<%=interfaceId %>" readonly="readonly" type="text" size="30"/>
			</p>
			<p>
				<label>线程数：</label>
				<input name="thread_number" value="" class="required digits" type="text" size="30"/>
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
