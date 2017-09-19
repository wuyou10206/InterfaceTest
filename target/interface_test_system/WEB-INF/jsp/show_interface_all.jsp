<%@ page language="java" import="java.util.*,entity.*,service.*" pageEncoding="utf-8"%>

					<div class="accordionContent">
						<ul class="tree treeFolder treeCheck">
						<%
							InterfaceService interfaceService = new InterfaceService();
							List<Module> modules=(List<Module>)request.getAttribute("modules");
							
							for(Module module:modules){
								int moduleId = module.getId();
						%>
							<li><a><%=module.getModuleName() %></a>
								<ul>
								<%
								List<Interface> interfaces =  interfaceService.findInterfaceByModule(moduleId);
								if(interfaces==null||interfaces.size()==0){
								%>
								<li><a href="javascript:;">该模块没有接口</a></li>
								<%
								}else{
									for(Interface inter:interfaces){
									%>
										<li value="<%=inter.getId() %>"><a><%=inter.getInterfaceName() %>-<%=inter.getId() %></a></li>
									<%
									} 
								}
								%>
								</ul>
							</li>
						<%} %>
							
						</ul>
					</div>
<div class="pageHeader">
<!--    <form rel="pagerForm" method="post" action="demo/database/dwzOrgLookup2.html"-->
<!--          onsubmit="return dwzSearch(this, 'dialog');">-->
        <div class="searchBar">
            <div class="subBar">
                <ul>

                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button id="bringback" type="button" >确定</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" class="close">取消</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
<!--    </form>-->
</div>


<script type="text/javascript">
        $(function () {
            $("#bringback").click(function () {
                var str="";
                var checkedList = $('input[type="checkbox"]:checked');
                checkedList.each(function (i, e) {
                    eVlue = e.parentNode.parentNode.parentNode.value;
                    if(eVlue>0){
                        str += eVlue +",";
                    }
                });
                str = str.substring(0, str.length - 1);
                if(str==""){
                    alert("请至少选择一个接口！")
                }else{
                    //关闭窗口，返回str
                //   alert(str);
               	     $.pdialog.closeCurrent();
                    location.href= "javascript:$.bringBack({interfaceIds:'"+str+"'})";
                }
            });
        });
    </script>