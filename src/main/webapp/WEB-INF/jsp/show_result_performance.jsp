<%@ page language="java" import="java.util.*,entity.*,service.*"
	pageEncoding="utf-8"%>
<div class="pageContent sortDrag" selector="h1" layoutH="42">
	<%
		ResultPerformance rp = (ResultPerformance)request.getAttribute("rp");
		Interface inter = (Interface)request.getAttribute("inter");
	%>
	<div class="panel collapse" minH="100">
		<h1><%=inter.getInterfaceName() %>-<%=inter.getId() %>的性能执行结果</h1>
		
		<div>
			<fieldset>
				<legend>结论</legend>
				<p>
					<%if(rp!=null){
					%>
					<span style="color:blue;">执行时间为：<%=rp.getExecuteDate()%></span><br/>
					<span style="color:blue;">开始时间为：<%=rp.getBeginDate()%></span><br/>
					<span style="color:blue;">结束时间为：<%=rp.getEndDate()%></span><br/>
					<span style="color:blue;">使用时间为：<%=rp.getUseDate()%>毫秒</span><br/>
						<%
						if(rp.getIsSuccess()==1){
						%>
					<span style="color:green;">请求访问通过，HttpCode为<%=rp.getReturnStatus() %>,期望的HttpCode为<%=inter.getHttpCode() %></span><br/>
					<%
						}else{
						%>
					<span style="color:red;">请求访问未通过，HttpCode为<%=rp.getReturnStatus() %>,期望的HttpCode为<%=inter.getHttpCode() %></span><br/>
					<%
						}					
					
						if(rp.getIsPass()==1){
						%>
					<span style="color:green;">所有检查点均通过验证</span><br/>
					<%
						}else{
						%>
					<span style="color:red;">检查点未通过验证，详情请看执行结果</span><br/>
					<%
						}					
					%>
					<%
					}else{
					%>
					该接口还未执行过
					<%
					} 
					%>
				</p>
			</fieldset>
			
			<fieldset>
				<legend>执行结果</legend>
				<p>
					<%=rp == null ? "该接口还未执行过" : (rp.getExecuteResult()==""?"该接口没有检查点":rp.getExecuteResult())%>
				</p>
			</fieldset>
			<fieldset>
				<legend>返回的数据</legend>
				<textarea id="RawJson" disabled="disabled"> <%=rp == null ? "该接口还未执行过" : rp.getReturnContent()%>
				</textarea>
				<div id="ControlsRow">
					<input type="Button" value="JSON格式化" onclick="Process()" /> <span
						id="TabSizeHolder"> 缩进量 <select id="TabSize"
						onchange="TabSizeChanged()">
							<option value="1">1</option>
							<option value="2" selected="true">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
					</select> </span> <label for="QuoteKeys"> <input type="checkbox"
						id="QuoteKeys" onclick="QuoteKeysClicked()" checked="true" /> 引号
					</label>&nbsp; <a href="javascript:void(0);" onclick="SelectAllClicked()">全选</a>
					&nbsp; <span id="CollapsibleViewHolder"> <label
						for="CollapsibleView"> <input type="checkbox"
							id="CollapsibleView" onclick="CollapsibleViewClicked()"
							checked="true" /> 显示控制 </label> </span> <span id="CollapsibleViewDetail">
						<a href="javascript:void(0);" onclick="ExpandAllClicked()">展开</a>
						<a href="javascript:void(0);" onclick="CollapseAllClicked()">叠起</a>
						<a href="javascript:void(0);" onclick="CollapseLevel(3)">2级</a> <a
						href="javascript:void(0);" onclick="CollapseLevel(4)">3级</a> <a
						href="javascript:void(0);" onclick="CollapseLevel(5)">4级</a> <a
						href="javascript:void(0);" onclick="CollapseLevel(6)">5级</a> <a
						href="javascript:void(0);" onclick="CollapseLevel(7)">6级</a> <a
						href="javascript:void(0);" onclick="CollapseLevel(8)">7级</a> <a
						href="javascript:void(0);" onclick="CollapseLevel(9)">8级</a> </span>
				</div>
				<div id="Canvas" class="Canvas"></div>
			</fieldset>
		</div>
	</div>

</div>
<script src="show_json/c.js" type="text/javascript"></script>
<link href="show_json/s.css" type="text/css" rel="stylesheet"></link>
<script src="http://www.google-analytics.com/urchin.js"
	type="text/javascript"></script>
<script type="text/javascript" src="show_json/m.js"></script>