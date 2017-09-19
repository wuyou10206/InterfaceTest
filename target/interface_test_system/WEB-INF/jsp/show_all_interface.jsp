<%@ page language="java" import="java.util.*,entity.*,service.*" pageEncoding="utf-8"%>
<div class="accordion" fillSpace="sidebar">

	<div class="accordionHeader">
		<h2>
			<span>Folder</span>模块&接口
		</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<%
				ProjectService projectService = new ProjectService();
				List<Project> projects = projectService.findProject();
				ModuleService moduleService = new ModuleService();
				UserService userService = new UserService();
				InterfaceService interfaceService = new InterfaceService();
				for (Project project : projects) {
					int projectId = project.getId();
			%>
			<li><a href="show_project_module?project_id=<%=projectId%>"
				target="navTab" rel="show_project_module_<%=projectId%>"><%=project.getProjectName()%></a>
				<ul>
					<%
						List<Module> modules = moduleService
									.findModuleByProject(projectId);
							if (modules == null || modules.size() == 0) {
					%>
					<li><a href="javascript:;">该项目下没有模块，请添加</a>
					</li>
					<%
						} else {
								for (Module module : modules) {
									int moduleId = module.getId();
					%>
					<li><a href="interface_list?module_id=<%=moduleId%>"
						target="navTab" rel="show_module_<%=moduleId%>"><%=module.getModuleName()%></a>
						<ul>
							<%
								List<Interface> interfaces = interfaceService
													.findInterfaceByModule(moduleId);
											if (interfaces == null || interfaces.size() == 0) {
							%>
							<li><a href="javascript:;">该模块没有接口，请添加</a>
							</li>
							<%
								} else {
												for (Interface inter : interfaces) {
							%>
							<li><a
								href="show_interface?interface_id=<%=inter.getId()%>"
								target="navTab" rel="show_interface_<%=inter.getId()%>"><%=inter.getInterfaceName()%></a>
							</li>
							<%
								}
											}
							%>
						</ul></li>
					<%
						}
							}
					%>
				</ul></li>
			<%
				}
			%>
		</ul>
	</div>
	<div class="accordionHeader">
		<h2>
			<span>Folder</span>业务列表
		</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<%
				TaskService taskService = new TaskService();

				for (Project project : projects) {
					int projectId = project.getId();
			%>
			<li><a href="show_task_list?project_id=<%=projectId%>"
				target="navTab" rel="show_project_task_<%=projectId%>"><%=project.getProjectName()%></a>
				<ul>
					<%
						List<Task> tasks = taskService.findTaskByProject(projectId);
							if (tasks == null || tasks.size() == 0) {
					%>
					<li><a>该项目下没有任务，请添加</a>
					</li>
					<%
						} else {
								for (Task task : tasks) {
									int taskId = task.getId();
					%>
					<li><a href="show_task_interface?task_id=<%=taskId%>"
						target="navTab" rel="show_task_<%=taskId%>"><%=task.getTaskName()%></a>

					</li>
					<%
						}
							}
					%>
				</ul></li>
			<%
				}
			%>
		</ul>
	</div>
<!-- 	<div class="accordionHeader"> -->
<!-- 		<h2> -->
<!-- 			<span>Folder</span>操作 -->
<!-- 		</h2> -->
<!-- 	</div> -->
<!-- 	<div class="accordionContent"> -->
<!-- 		<ul class="tree treeFolder"> -->
			<!-- 							<li><a href="module_list.jsp" target="navTab" rel="module_list">模块列表</a></li> -->
			<!-- 							<li><a href="show_task_list" target="navTab" rel="task_list">任务列表</a></li> -->
<!-- 			<li><a href="project_list" target="navTab" rel="project_list">项目列表</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
<!-- 	</div> -->
</div>
