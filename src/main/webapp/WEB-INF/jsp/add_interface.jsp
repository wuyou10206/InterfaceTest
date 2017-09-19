<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="pageContent">
	<%
		int moduleId = (Integer)request.getAttribute("module_id");
	%>
	<form method="post" action="save_interface" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>接口名字：</label>
				<input type="text" name="interface_name" class="required" />
			</p>
			<p>
				<label>接口地址：</label>
				<input type="text" name="interface_address" class="url" />
			</p>
			<p>
				<label>请求方式：</label>
				<select name="request_mode" class="required combox">
					<option value="1" selected>get</option>
					<option value="2">post</option>
					<option value="3">post-JSON</option>
					<option value="4">put</option>
					<option value="5">delete</option>
				</select>
			</p>
			<p>
				<label>正确时返回的状态码：</label>
				<input type="text" name="http_code" class="required digits" />	
			</p>
			<p>
				<label>模块ID：</label>
				<input type="text" name="module_id" value="<%=moduleId %>" readonly="readonly"/>	
			</p>
			
			<div class="divider"></div>
			<p>
                <button type="button" id="AddParameter">增加参数</button>
            </p>
            <p>
                <button type="button" id="DeleteParameter">删除参数</button>
            </p>
            <p>
                
            </p>
            <input name="parameterid" id="parameterid" type="hidden" value="1"/>
            
            <div id="parameter">
			

            </div>
            <div class="divider"></div>
			<p>
                <button type="button" id="AddHead">增加Head</button>
            </p>
            <p>
                <button type="button" id="DeleteHead">删除Head</button>
            </p>
            <p>
                
            </p>
            <input name="headid" id="headid" type="hidden" value="1"/>
            
            <div id="head">
			

            </div>
            <div class="divider"></div>
			<p>
                <button type="button" id="AddCheck">增加检查点</button>
            </p>
            <p>
                <button type="button" id="DeleteCheck">删除检查点</button>
            </p>
            <p>
                
            </p>
            <input name="checkid" id="checkid" type="hidden" value="1"/>
            <div id="check">
			

            </div>
            <div class="divider"></div>
			<p>
                <button type="button" id="AddOutValue">增加传出值</button>
            </p>
            <p>
                <button type="button" id="DeleteOutValue">删除传出值</button>
            </p>
            <p>
                
            </p>
            <input name="outValueid" id="outValueid" type="hidden" value="1"/>
            <div id="outValue">
			

            </div>
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

<script type="text/javascript">
    $(function () {
		
        //增加参数
        $("#AddParameter").click(function () {
            //变量什默认为1
            var i = Number($("#parameterid").val());
            var html = "<p id='add_parameter'><label>参数的Key：</label>";
            html += '<input name="parameter_key' + i + '" id="parameter_key' + i + '" class="required" type="text" size="30" value=""/></p>';
            html += "<p id='delete_parameter'><label>参数的Value：</label>";
            html += '<input name="parameter_value' + i + '" id="parameter_value' + i + '"  type="text" size="30" value=""/></p>';
            html += "<p id='desc_parameter'><label>变量的值：</label>";
            html += '<input name="parameter_variable' + i + '" id="parameter_variable' + i + '"  type="text" size="30" value=""/></p>';
            $("#parameter").append(html);
            i = 1 + Number($("#parameterid").val());
            $("#parameterid").val(i)
        });
        $("#DeleteParameter").click(function () {
            var i = Number($("#parameterid").val());
            if (i > 1) {
                $("#parameterid").val(i - 1)
                $("#add_parameter:last").remove();
                $("#delete_parameter:last").remove();
                $("#desc_parameter:last").remove();
            }
        });
      	//增加Head
        $("#AddHead").click(function () {
            //变量什默认为1
            var i = Number($("#headid").val());
            var html = "<p id='add_head'><label>Head的Key：</label>";
            html += '<input name="head_key' + i + '" id="head_key' + i + '" class="required" type="text" size="30" value=""/></p>';
            html += "<p id='delete_head'><label>Head的Value：</label>";
            html += '<input name="head_value' + i + '" id="head_value' + i + '"  type="text" size="30" class="required" value=""/></p>';
            html += "<p id='desc_head'><label>变量的值：</label>";
            html += '<input name="head_variable' + i + '" id="head_variable' + i + '"  type="text" size="30" value=""/></p>';
            $("#head").append(html);
            i = 1 + Number($("#headid").val());
            $("#headid").val(i)
        });
        $("#DeleteHead").click(function () {
            var i = Number($("#headid").val());
            if (i > 1) {
                $("#headid").val(i - 1)
                $("#add_head:last").remove();
                $("#delete_head:last").remove();
                $("#desc_head:last").remove();
            }
        });
        //增加检查点
        $("#AddCheck").click(function () {
            //变量什默认为1
            var i = Number($("#checkid").val());
            var html = "<p id='add_check'><label>检查方式：</label>";
            html += "<select name='check_mode"+i+"' id='check_mode"+i+"' class='required combox' style='width:200px' onchange='check_alert(this.value,"+i+")'>";
			html += "<option value='1' selected>json:包含某一个key</option>";
			html += "<option value='2'>json:比较某一个key(int)的值</option>";
			html += "<option value='3'>text:包含某一个值</option>";
			html += "<option value='4'>json:比较某一个key(String)的值不是空字符串</option>";
			html += "<option value='5'>json:比较某一个key(String)的值与预期值</option></select></p>";
			
            html += "<p id='delete_check'><label>检查点：</label>";
            html += '<input name="check_point' + i + '" id="check_point' + i + '"  type="text" class="required" size="30" value=""/><span class="info" id="check_message_'+i+'">输入一个key</span></p>';
            html += "<p id='desc_check'><label>失败提示：</label>";
            html += '<input name="check_desc' + i + '" id="check_desc' + i + '"  type="text" class="required" size="30" value=""/></p>';
            $("#check").append(html);
            i = 1 + Number($("#checkid").val());
            $("#checkid").val(i);
        });
        $("#DeleteCheck").click(function () {
            var i = Number($("#checkid").val());
            if (i > 1) {
                $("#checkid").val(i - 1)
                $("#add_check:last").remove();
                $("#delete_check:last").remove();
                $("#desc_check:last").remove();
            }
        });
        //增加输出值
        $("#AddOutValue").click(function () {
            //变量什默认为1
            var i = Number($("#outValueid").val());
            var html = "<p id='value_space_delete'><label>取值范围：</label>";
            html += "<select name='value_space"+i+"' id='value_space"+i+"' class='required combox' style='width:200px'>";
			html += "<option value='1' selected>返回的cookie中</option>";
			html += "<option value='2'>返回的结果中-json</option>";
			html += "<option value='3'>返回的结果中-text</option>";
			html += "</select></p>";
			
            html += "<p id='out_value_key_delete'><label>key或正则表达式：</label>";
            html += '<input name="out_value_key' + i + '" id="out_value_key' + i + '"  type="text" class="required" size="30" value=""/></p>';
            html += "<p id='out_value_name_delete'><label>变量名：</label>";
            html += '<input name="out_value_name' + i + '" id="out_value_name' + i + '"  type="text" class="required" size="30" value=""/></p>';
            $("#outValue").append(html);
            i = 1 + Number($("#outValueid").val());
            $("#outValueid").val(i);
        });
        $("#DeleteOutValue").click(function () {
            var i = Number($("#outValueid").val());
            if (i > 1) {
                $("#outValueid").val(i - 1)
                $("#value_space_delete:last").remove();
                $("#out_value_key_delete:last").remove();
                $("#out_value_name_delete:last").remove();
            }
        });
    });
    function check_alert(check_mode,check_index){
        if(check_mode==1){
        	$("#check_message_"+check_index).html("请输入一个key");
        }else if(check_mode==2){
        	$("#check_message_"+check_index).html("填写格式：key>value,例如：id=1或id<3");
        }else if(check_mode==3){
        	$("#check_message_"+check_index).html("请填写包含的值");
        }else if(check_mode==4){
        	$("#check_message_"+check_index).html("请填写一个key");
        }else if(check_mode==5){
        	$("#check_message_"+check_index).html("填写格式：key=value,无引号");
        }
    }
</script>


