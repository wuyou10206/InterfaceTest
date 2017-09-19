<%@ page language="java" import="java.util.*,service.*,entity.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>接口测试平台</title>

<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<style type="text/css">
	#header{height:85px}
	#leftside, #container, #splitBar, #splitBarProxy{top:90px}
</style>

<!--[if lt IE 9]><script src="js/speedup.js" type="text/javascript"></script><script src="js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>

<script src="bin/dwz.min.js" type="text/javascript"></script>
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>


<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
		//	setTimeout(function() {$("#sidebar .toggleCollapse div").trigger("click");}, 10);
		}
	});
});
</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<img src="img/logo-bt.png" width="100px" height="50px" />
				<ul class="nav">
					
					<li><a href="logout">退出</a></li>
				</ul>
<!-- 				<ul class="themeList" id="themeList"> -->
<!-- 					<li theme="default"><div class="selected">蓝色</div></li> -->
<!-- 					<li theme="green"><div>绿色</div></li> -->
<!-- 					<li theme="purple"><div>紫色</div></li> -->
<!-- 					<li theme="silver"><div>银色</div></li> -->
<!-- 					<li theme="azure"><div>天蓝</div></li> -->
<!-- 				</ul> -->
			</div>
		
			<div id="navMenu">
				<ul>
					<li><a href="show_me_interface" rel="show_me_interface"><span>我的接口</span></a></li>
					<li class="selected"><a href="show_all_interface"><span>所有接口</span></a></li>
<!-- 					<li><a href="sidebar_2.html"><span>会员管理</span></a></li> -->
<!-- 					<li><a href="sidebar_1.html"><span>服务管理</span></a></li> -->
<!-- 					<li><a href="sidebar_2.html"><span>系统设置</span></a></li> -->
				</ul>
			</div>
		</div>

		<div id="leftside">
			
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					
					<div class="accordionHeader">
						<h2><span>Folder</span>模块&接口</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<%
							ProjectService projectService = new ProjectService();
							List<Project> projects = projectService.findProject();
							ModuleService moduleService = new ModuleService();
							UserService userService = new UserService();
							InterfaceService interfaceService = new InterfaceService();
							for(Project project:projects){
								int projectId = project.getId();
								%>
							<li><a href="show_project_module?project_id=<%=projectId %>" target="navTab" 
										rel="show_project_module_<%=projectId %>"><%=project.getProjectName() %></a>
								<ul>
								<%
							List<Module> modules= moduleService.findModuleByProject(projectId);
							if(modules==null||modules.size()==0){
							%>
							<li><a href="javascript:;">该项目下没有模块，请添加</a></li>
							<%
							}else{
							for(Module module:modules){
								int moduleId = module.getId();
						%>
							<li><a href="interface_list?module_id=<%=moduleId %>" target="navTab" 
										rel="show_module_<%=moduleId %>"><%=module.getModuleName() %></a>
								<ul>
								<%
								List<Interface> interfaces =  interfaceService.findInterfaceByModule(moduleId);
								if(interfaces==null||interfaces.size()==0){
								%>
								<li><a href="javascript:;">该模块没有接口，请添加</a></li>
								<%
								}else{
									for(Interface inter:interfaces){
									%>
										<li><a href="show_interface?interface_id=<%=inter.getId() %>" target="navTab" 
											rel="show_interface_<%=inter.getId() %>"><%=inter.getInterfaceName() %></a></li>
									<%
									} 
								}
								%>
								</ul>
							</li>
						<%} 
						}
						%>
							</ul>
							</li>
						<%} %>	
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>业务列表</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						<%
							
							TaskService taskService = new TaskService();
							
							for(Project project:projects){
								int projectId = project.getId();
						%>
							<li><a href="show_task_list?project_id=<%=projectId %>" target="navTab" rel="show_project_task_<%=projectId%>"><%=project.getProjectName() %></a>
								<ul>
								<%
									List<Task> tasks = taskService.findTaskByProject(projectId);
									if(tasks==null||tasks.size()==0){
								%>
								<li><a>该项目下没有任务，请添加</a></li>
								<%
									}else{
									for(Task task:tasks){
									int taskId = task.getId();
								%>
									<li><a href="show_task_interface?task_id=<%=taskId %>" target="navTab" rel="show_task_<%=taskId%>"><%=task.getTaskName() %></a>
									
									</li>
								<%} 
								}
								%>
								</ul>
							</li>
						<%} %>	
						</ul>
					</div>
<!-- 					<div class="accordionHeader"> -->
<!-- 						<h2><span>Folder</span>操作</h2> -->
<!-- 					</div> -->
<!-- 					<div class="accordionContent"> -->
<!-- 						<ul class="tree treeFolder"> -->
<!-- 							<li><a href="module_list.jsp" target="navTab" rel="module_list">模块列表</a></li> -->
<!-- 							<li><a href="show_task_list" target="navTab" rel="task_list">任务列表</a></li> -->
<!-- 							<li><a href="project_list" target="navTab" rel="project_list">项目列表</a></li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
				</div>

			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<h2>如有问题请联系：</h2>
								QQ：307517716
							</div>
							
							<p><span style="font-size:30px;">欢迎使用接口测试平台</span></p>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2016 zw</div>

</body>
</html>