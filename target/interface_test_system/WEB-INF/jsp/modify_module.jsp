<%@ page language="java" import="java.util.*,entity.*" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="save_module" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<%
				Module module = (Module)request.getAttribute("module");
			%>
			<p>
				<label>模块ID：</label>
				<input name="module_id" value="<%=module.getId() %>" readonly="readonly" type="text" size="30"/>
			</p>
			<p>
				<label>模块名称：</label>
				<input name="module_name" value="<%=module.getModuleName() %>" class="required" type="text" size="30"/>
			</p>
			<p>
				<label>项目ID：</label>
				<input name="project_id" value="<%=module.getPid() %>" readonly="readonly" type="text" size="30"/>
			</p>
<!-- 			<p> -->
<!-- 				<label>父模块：</label> -->
<!-- 				<input type="hidden" name="orgLookup.id" value="${orgLookup.id}"/> -->
<!-- 				<input type="text" class="required" readonly="readonly" name="f_module_name" value="无" suggestFields="orgNum,orgName" suggestUrl="demo/database/db_lookupSuggest.html" lookupGroup="orgLookup" /> -->
<!-- 				<a class="btnLook" href="demo/database/dwzOrgLookup.html" lookupGroup="orgLookup">查找带回</a>		 -->
<!-- 			</p> -->
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
