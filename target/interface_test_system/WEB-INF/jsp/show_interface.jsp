<%@ page language="java" import="java.util.*,entity.*,service.*"
	pageEncoding="utf-8"%>
<div class="pageContent sortDrag" selector="h1" layoutH="42">
	<%
		Interface inter = (Interface) request.getAttribute("inter");
		Result result = (Result) request.getAttribute("result");
		InterfacePerformance ip = (InterfacePerformance) request.getAttribute("ip");
	%>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="edit" target="dialog" width="1200" height="600" mask=true resizable=false minable=false maxable=false drawable=false href="modify_interface?interface_id=<%=inter.getId()%>"><span>修改</span> </a>
			</li>
			<li class="line">line</li>
			<li><a class="icon"
				href="execute_interface?interface_id=<%=inter.getId()%>"
				target="ajaxTodo" title="确定要执行该接口么?"><span>执行该接口</span> </a>
			</li>
		<%if(result!=null){ %>
			<li class="line">line</li>
			<li><a class="add" rel="show_execute_history"
				href="show_execute_history?interface_id=<%=inter.getId()%>"
				target="navTab" title="查看历史记录"><span>查看历史记录</span> </a>
			</li>
		<%} %>
			<li class="line">line</li>
			<li><a class="edit"
				href="test_performance?interface_id=<%=inter.getId()%>"
				target="dialog" title="测试性能"><span>测试性能</span> </a>
			</li>
		<%if(ip!=null){ %>
			<li class="line">line</li>
			<li><a class="icon"
				href="show_interface_performance_result?interface_id=<%=inter.getId()%>"
				target="navTab" title="查看测试性能结果"><span>查看测试性能结果</span> </a>
			</li>
		<%} %>
		</ul>
	</div>
	<div class="panel">
		<h1>接口信息</h1>
		<div>
			<table class="list" width="98%">
				<thead>
					<tr>
						<th width="50">ID</th>
						<th>接口名字</th>
						<th width="580">接口地址</th>
						<th>请求方式</th>
						<th>HttpCode</th>
						<th>创建者</th>
						<th>创建时间</th>
					</tr>
				</thead>
				<tbody>
					<%
						UserService userService = new UserService();
						int userId = inter.getCreateUser();
						User user = userService.findUserById(userId);
					%>
					<tr>
						<th width="50"><%=inter.getId()%></th>
						<th><%=inter.getInterfaceName()%></th>
						<th width="580"><%=inter.getInterfaceAddress()%></th>
					<%
						int requestMode = inter.getRequestMode();
						String modeStr = "";
						if(requestMode==1){
							modeStr = "get";
						}else if(requestMode==2){
							modeStr = "post";
						}else if(requestMode==3){
							modeStr = "post-JSON";
						}else if(requestMode==4){
							modeStr = "put";
						}else if(requestMode==5){
							modeStr = "delete";
						}
					%>
						<th><%=modeStr%></th>
						<th><%=inter.getHttpCode()%></th>
						<th><%=user.getAlias()%></th>
						<th><%=inter.getCreateDate()%></th>
					</tr>
				</tbody>
			</table>
			<div class="row">
				<div class="col-md-12 col-sm-12">

					<div class="panel collapse">
						<h1>参数列表</h1>
						<div>
							<table class="list" width="100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>参数名字</th>
										<th>参数的值</th>
										<th>变量值</th>
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<Parameter> parameters = (List<Parameter>) request
												.getAttribute("parameters");
										for (Parameter p : parameters) {
									%>
									<tr>
										<th><%=p.getId()%></th>
										<th><%=p.getParameterKey()%></th>
										<th><%=p.getParameterValue()%></th>
										<th><%=p.getParameterVariable()%></th>
										<th><%=p.getCreateDate()%></th>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>

				</div>
				<div class="col-md-12 col-sm-12">

					<div class="panel collapse">
						<h1>Head列表</h1>
						<div>
							<table class="list" width="100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>head的key</th>
										<th>head的value</th>
										<th>变量值</th>
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<Head> heads = (List<Head>) request.getAttribute("heads");
										for (Head h : heads) {
									%>
									<tr>
										<th><%=h.getId()%></th>
										<th><%=h.getHeadKey()%></th>
										<th><%=h.getHeadValue()%></th>
										<th><%=h.getHeadVariable()%></th>
										<th><%=h.getCreateDate()%></th>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>

				</div>
				<div class="col-md-12 col-sm-12">

					<div class="panel collapse">
						<h1>检查点列表</h1>
						<div>
							<table class="list" width="100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>检查方式</th>
										<th>检查点</th>
										<th>错误提示</th>
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<InterfaceCheck> checks = (List<InterfaceCheck>) request
												.getAttribute("checks");
										for (InterfaceCheck c : checks) {
									%>
									<tr>
										<th><%=c.getId()%></th>
										<%
											int checkMode = c.getCheckMode();
												if (checkMode == 1) {
										%>
										<th>json:包含某一个key</th>
										<%
											} else if (checkMode == 2) {
										%>
										<th>json:比较某一个key的值</th>
										<%
											} else if (checkMode == 3) {
										%>
										<th>text:包含某一个值</th>
										<%
											} else if (checkMode == 4) {
										%>
										<th>json:比较某一个key(String)的值不是空字符串</th>
										<%
											} else if (checkMode == 5) {
										%>
										<th>json:比较某一个key(String)的值与预期值</th>
										<%
											}
										%>
										<th><%=c.getCheckPoint()%></th>
										<th><%=c.getCheckDesc()%></th>
										<th><%=c.getCreateDate()%></th>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>

				</div>
				<div class="col-md-12 col-sm-12">

					<div class="panel collapse">
						<h1>输出值列表</h1>
						<div>
							<table class="list" width="100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>输出值的范围</th>
										<th>输出值的key或正则表达式</th>
										<th>输出值的变量名</th>
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<OutValue> outValues = (List<OutValue>) request
												.getAttribute("outValues");
										for (OutValue ov : outValues) {
									%>
									<tr>
										<th><%=ov.getId()%></th>
										<%
											int valueSpace = ov.getValueSpace();
												if (valueSpace == 1) {
										%>
										<th>返回的cookie中</th>
										<%
											} else if (valueSpace == 2) {
										%>
										<th>返回的结果中-json</th>
										<%
											} else if (valueSpace == 3) {
										%>
										<th>返回的结果中-text</th>
										<%
											} 
										%>
										
										<th><%=ov.getOutValueKey()%></th>
										<th><%=ov.getOutValueName()%></th>
										<th><%=ov.getCreateDate()%></th>
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

	<div class="panel collapse" minH="100">
		<h1>单个接口最近的执行结果</h1>
		
		<div>
			<fieldset>
				<legend>结论</legend>
				<p>
					<%if(result!=null){
					%>
					<span style="color:blue;">执行时间为：<%=result.getExecuteDate()%></span><br/>
						<%
						if(result.getIsSuccess()==1){
						%>
					<span style="color:green;">请求访问通过，HttpCode为<%=result.getReturnStatus() %>,期望的HttpCode为<%=inter.getHttpCode() %></span><br/>
					<%
						}else{
						%>
					<span style="color:red;">请求访问未通过，HttpCode为<%=result.getReturnStatus() %>,期望的HttpCode为<%=inter.getHttpCode() %></span><br/>
					<%
						}					
					
						if(result.getIsPass()==1){
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
					<%=result == null ? "该接口还未执行过" : (result.getExecuteResult()==""?"该接口没有检查点":result.getExecuteResult())%>
				</p>
			</fieldset>
			<fieldset>
				<legend>返回的数据</legend>
				<textarea id="RawJson" disabled="disabled"> <%=result == null ? "该接口还未执行过" : result.getReturnContent()%>
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