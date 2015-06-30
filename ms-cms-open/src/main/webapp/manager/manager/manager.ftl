<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
<style>
	.control-label{font-weight:normal;font-size:14px;}
</style>
</head>
<body>
	<!-- bootstarp 布局容器 开始 -->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					管理员管理
					<small>管理员列表</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--内容 部分 开始-->
		<div class="row">
			<!--功能按键部分 开始-->
			<div class="form-group">
				<!--新增按钮 开始-->
				<button type="button"class="btn btn-success btn-md saveModelBtn">新增管理员&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
				<button type="button" class="btn btn-danger btn-md" id="allDelete">批量删除 <span class="glyphicon glyphicon-trash"></span></button>
			</div>
			<!--功能按键部分 结束-->
			<!--搜索框  开始-->
			
			<!--列表 开始-->
			<table class="table table-bordered">
				<!--表格栏目属性 开始-->
		        <thead>
		        	<tr>
		        		<td colspan="12" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	<a href="">栏目名</a>&nbsp;>&nbsp;<a href=""><strong>用户管理</strong></a>
		        		</td>
		        	</tr>
			        <tr>
			        	<th class="col-md-1 text-center">
			        		<label class="checkbox-inline"  data-toggle="tooltip" title="" data-original-title="点击全选">
			        			<input type="checkbox" id="allCheck">
			        		</label>
			        	</th>
			            <th class="col-md-1 text-center">管理员ID</th>
			            <th class="col-md-1 text-center">昵称</th>
			            <th class="col-md-1 text-center">帐号</th>
			            <th class="col-md-2 text-center">密码</th>
			            <th class="col-md-2 text-center">角色名称</th>
			            <th class="col-md-2 text-center">添加时间</th>
			            <th class="col-md-2 text-center">操作</th>
			        </tr>
		        </thead>
		        <!--表格栏目属性 结束-->
		        
		        <!--表格内容  开始-->
		        <tbody>
		            <#if listManager?has_content>
			           <#list listManager as manager>
			          <tr>
			          	<td class="text-center">
			            	<label class="checkbox-inline">
			            		<#if manager.managerName != managerSession.managerName>
  								<input type="checkbox" name="checkbox" value="${manager.managerId?c?default(0)}">
  								</#if>
							</label>
			            </td>
			            <td class="text-center managerId">${manager.managerId?c?default(0)}</td>		          	
			            <td class="text-center">${manager.managerNickName?default("暂无")}</td>
			            <td class="text-center">${manager.managerName?default("暂无")}</td>
			 			<td class="text-center">${manager.managerPassword?default("暂无")}</td>
			            <td class="text-center">${manager.roleName?default("暂无")}</td>
			            <td class="text-center">${manager.managerTime?string("yyyy-MM-dd HH:mm:ss")}</td>
			            <td class="text-center operate">
			            	<a class="btn btn-xs tooltips  addPage" data-toggle="tooltip" data-id="${manager.managerId?c?default(0)}" data-original-title="编辑">
		                     		<i class="glyphicon glyphicon-plus"></i>
		                    </a>
			            	<#if manager.managerName != managerSession.managerName>
			                    <a class="btn btn-xs tooltips delete" data-toggle="tooltip" data-id="${manager.managerId?c?default(0)}" data-original-title="删除">
			                        <i class="glyphicon glyphicon-trash"></i>
			                    </a>
			                    <a class="btn btn-xs tooltips updateModalBtn" data-toggle="tooltip" data-id="${manager.managerId?c?default(0)}" data-original-title="编辑">
		                     		<i class="glyphicon glyphicon-pencil"></i>
		                    	</a>
	                    	</#if>
	                    	 
	                    	
						</td>
			          </tr>
			           </#list>
					<#else>
			          <tr>
			            <td colspan="12" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0; cursor:pointer;">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a class="alert-link saveModelBtn">
			            		您还没有添加管理员，点击添加管理员
			            		</a>
						  	</p>
						</td>
			          </tr>				           
			         </#if>
		        </tbody>
		        <!--表格内容  结束-->
			</table>
			<!--弹出框内的内容  结束-->
		</div>
		<!--分页样式 开始-->
		<@showPage page=page/>
		<!--分页样式 结束-->
		<!--删除管理员模态框-->
		<@warnModal modalName="deleteModel"/>
		<!--增加/更新管理员模态框-->
		<@modalDialog modalName="addModel"/>
		<!--增加/更新管理员主界面模态框-->
		<@modalDialog modalName="addPageModel"/>
	</div>
	<!-- bootstarp 布局容器 结束 -->	
	<!--增加管理员开始-->
  	<div class="row" style="display:none;" id="managerModel">	
		  <form action="" class="form-horizontal" role="form" id="managerForm">
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="managerName">帐号:</label>
				<div class="col-md-4  col-xs-4">
					<input type="text" class=" form-control managerName" placeholder="请输入帐号" name="managerName">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="nickName">昵称:</label>
				<div class="col-md-4  col-xs-4">
					<input type="text" class=" form-control managerNickName" placeholder="请输入昵称" name="managerNickName">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="password">密码:</label>
				<div class="col-md-4  col-xs-4">
					<input type="password" class=" form-control managerPassword " placeholder="请输入密码" name="managerPassword">
				</div>
			</div>
			<div class="form-group">
			    <label for="selectRole" class="col-md-4 control-label">选择角色:</label>
			    <div class="col-md-4">
			      <select class="form-control managerlistRole" name="managerRoleID"></select>
			    </div>
			</div>
			<input type="hidden" class="managerPeopleID" name="managerPeopleID" value="0"/>
			<input type="hidden" class="oldManagerName" name="oldManagerName" value=""/>
		</form>
        <button type="button" id="save" class="btn btn-primary" style="float:right;">保存</button>
        <button type="button" class="btn btn-default closeModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
        <div style="clear:both;"></div>
	</div>
	<!--增加管理员结束-->
	
	<!----增加管理员主界面开始--->
		<div class="row" style="display:none;" id="managerPageModel">	
		  	<form action="${base}/manager/managerModelPage/save.do" class="form-horizontal" role="form" id="managerPageForm">
		  		<input type="hidden"  name="managerModelPagemanagerId">
				<div class="form-group">
					<label class="col-md-3 control-label col-xs-3" for="managerName">主界面地址:</label>
					<div class="col-md-6  col-xs-6">
						<input type="text" class=" form-control managerName" placeholder="请输入主界面地址" name="managerModelPageUrl">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label col-xs-3" for="managerName">模块选择:</label>
					<div class="col-md-6  col-xs-6">
						<select name="managerModelPageModelId" class="form-control">
							<option selected value="0">请选择模块</option>
						</select>
					</div>
				</div>
			</form>
			 <button type="button" class="btn btn-primary" style="float:right;" id="saveOrUpdatePage">保存</button>
        	<button type="button" class="btn btn-default closeModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
		</div>
	<!----增加管理员主界面结束--->
	<script type="text/javascript">				
		//引入表单验证框架
		var bootstrapValidator;
		
		//表单验证
		function bindValidate(){
			$("#managerForm").bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		            managerName: {
		                validators: {
		                    notEmpty: { message: '帐号不能为空'},
		                    stringLength: {min: 1,max: 20,message: '帐号长度介于1-20个字符'},
		                    regexp: {regexp: /^[a-zA-Z0-9_\.]+$/,message: '帐号只能由英文字母，数字，点和下划线组成'}
		                }
		            },
		            managerNickName: {
		                validators: {
		                    notEmpty: {message: '昵称不能为空'},
		                    stringLength: {min: 2,max: 8,message: '昵称长度介于2-8个字符'}
		                }
		            },
		            managerPassword: {
		                validators: {
		                    notEmpty: { message: '密码不能为空'},
		                    stringLength: {min: 1, max: 16,message: '密码长度介于1-16个字符'},
		                    regexp: {regexp: /^[a-zA-Z0-9_\.]+$/,message: '密码只能由英文字母，数字，点和下划线组成'}
		                }
		            }
		        }
		     });
			bootstrapValidator = $("#addModelDialog").find("form:first").data('bootstrapValidator');
		}
		
		
		//通用增加/更新管理员模态框
		function addModel(name,flag){
			$(name).on("click",function(){
				var editManagerId = ""; var URL = "";
				if(flag == true){
					$(".closeModal").click();
					openaddModel($("#managerModel"),"增加管理员");
					URL = base+"/manager/manager/add.do"
				} else {
					$("#save").html("更新");
					openaddModel($("#managerModel"),"更新管理员");
					editManagerId = $(this).attr("data-id");
					URL = base+"/manager/manager/"+editManagerId+"/edit.do"
				}
				//管理员表单验证
				bindValidate();			
				$.ajax({
				   type: "post",
				   dataType:"json",
				   url:  URL,
				   data: "managerId=" + editManagerId,
				   success: function(msg){
				   		//若为更新管理员则执行
				   		if(msg.manager != null){
					   		$(".managerId").val(msg.manager.managerId);
					   		$(".managerName").val(msg.manager.managerName);
					   		$(".oldManagerName").val(msg.manager.managerName);
					   		$(".managerNickName").val(msg.manager.managerNickName);
				   		}
					   if(msg.listRole != null && $(".managerlistRole").html() == ""){
					    	for(var i=0; i<msg.listRole.length; i++){
					   			var roleId = msg.listRole[i].roleId;
					   			var roleName = msg.listRole[i].roleName;
					   			$(".managerlistRole").append("<option value="+roleId+">"+roleName+"</option>")
					   		}
					   	} else {
				   			$(".managerlistRole").append("<option>暂无角色</option>");
				   		}
				   },error: function(){
				   	  alert("系统异常");
				   }
				});
			});
		}
		 
		 //增加/更新管理员
		 function addEdit(modelIds){
		 	var formData = $("#managerForm").serialize();
			var URL = "";
			if($("#save").html() == "保存"){
				URL = base+"/manager/manager/save.do";
			} else {
				URL = base+"/manager/manager/update.do?managerId="+$('.managerId').html();
			}
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  URL,
			   data: formData, 
			   success: function(msg){ 
			     	if(msg.result) {
			    		$(".closeModal").click();
			     		if($("#save").html() == "保存"){
			     			alert("保存管理员成功");
			     		} else {
			     			alert("更新管理员成功");
			     		}
			     		location.href = base+"/manager/manager/queryList.do?pageNo="+parseInt(msg.resultMsg);
			    	} else {
			    		alert(msg.resultMsg); 
			    		if(msg.resultMsg.indexOf("尚未更新管理员") > 0){
			    			$(".closeModal").click();
			    		}
			    	}
			   },error: function(){
			   	  alert("很抱歉，请先增加角色");
			   	  location.href = base+"/manager/role/add.do";
			   }
			});
		 }
		
		//删除管理员 
		function deleteManager(deleteManagerId){ 
			$(".warndeleteModelOk").attr("disabled","true");
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  base+"/manager/manager/"+deleteManagerId+"/delete.do",
			   data: "managerId=" + deleteManagerId,
			   success: function(msg){
			    	if(msg != 0) {
				    	$(".closeModal").click();
			    		$("input[name="+deleteManagerId+"]").parent().remove();
						alert("删除管理员成功");
			    		if($("tbody tr").length==0 && msg != 1){
			    			location.href = base+"/manager/manager/queryList.do?pageNo="+(msg-1);
						}else{
							location.href = base+"/manager/manager/queryList.do?pageNo="+msg;
						}
			    	} else {
						alert("删除管理员失败");
				    	$(".closeModal").click();
			    	}
			   },error: function(){
			   	  alert("系统异常");
			   }
			});
		}
		
		//批量删除管理员
		function batchDelete(){
			$(".warndeleteModelOk").attr("disabled","true");
			var count = 0;
			var checkboxData = $("input[name='checkbox']").serialize();
			$("input[name='checkbox']").each(function(){
				if(this.checked==true){
					count++;
				}
			});
			if(checkboxData.length != 0) {
				$.ajax({
				   type: "post",
				   dataType: "json",
				   url:  base+"/manager/manager/allDelete.do",
				   data: checkboxData,
				   success: function(msg){
				    	$(".closeModal").click();
						alert("批量删除管理员成功");
				   		if($("tbody tr").length == count && msg != 1){
				     		location.href = base+"/manager/manager/queryList.do?pageNo="+(msg-1);
						}else{
				     		location.href = base+"/manager/manager/queryList.do?pageNo="+msg;
						}
				   },error: function(){
				   	  alert("批量删除管理员失败");
				      $(".closeModal").click();
				   }
				});
			 } else {
				alert("删除失败，请先选择管理员");
				$(".closeModal").click();
			 }
		 }
		
		$(function () {			
			//前端验证帐号是否存在
			$("input[name='managerName']").blur(function(){
				var oldManagerName = $(".oldManagerName").val();
				var managerName = $(this).val();
				if((oldManagerName.length != 0 && oldManagerName != managerName) || (oldManagerName.length == 0)){
					$.ajax({
					   type: "post",
					   dataType: "json",
					   url:  base+"/manager/manager/judgeManagerNameExist.do",
					   data: "managerName=" + managerName,
					   success: function(msg){
					     	if(msg){
					     		alert("该帐号已存在，请再次输入");
					     		$("input[name='managerName']").val(null);
					     	} 
					   }
					});
				}
			});
			//管理员表单验证
			bindValidate();
			
			//增加管理员模态框
			addModel(".saveModelBtn",true);
			//更新管理员模态框
			addModel(".updateModalBtn",false);
			
			//增加/更新管理员
			$("#addModelDialog").delegate("#save","click",function(){
				var password = $(".managerPassword").val();
				//若密码值为空，则不验证密码框
				if(password == ""){
					bootstrapValidator.removeField('managerPassword');
					if(bootstrapValidator.validate().isValid()){
						$(this).attr("disabled","true");
						addEdit();
					}
				} else if(bootstrapValidator.validate().isValid()){
					$(this).attr("disabled","true");
					addEdit();
				}
			});
			
			//删除管理员，提示其为哪个站点下的管理员
			$(".delete").on("click",function(){
				var deleteManagerId = $(this).attr("data-id");
				warndeleteModel("确定删除该管理员吗？","删除管理员","deleteManager("+deleteManagerId+")");
			});			
			
			//全选
		   	$("#allCheck").on("click",function(){   
				if(this.checked){$("input[name='checkbox']").each(function(){this.checked=true;});
				}else{$("input[name='checkbox']").each(function(){this.checked=false;});   
				}   
			}); 
			
			//批量删除
			$("#allDelete").on("click",function(){
				warndeleteModel("由于此操作会删除多个管理员，确定删除吗？","批量删除管理员","batchDelete()");
			});			
				
			//取消按钮
			$("#addModelDialog").delegate(".closeModal","click",function(){
				$(".managerName").val(null);
		   		$(".managerNickName").val(null);
		   		$(".managerPassword").val(null);
		   		$(".oldManagerName").val(null);
		   		$(".managerlistRole").html(null);
		   		bootstrapValidator.resetForm();
			});
			
			//点击弹出管理员主界面新增或更新弹出框
			$(".addPage").click(function(){
					$("input[name='managerModelPagemanagerId']").val($(this).attr("data-id"));
					//查询该管理员的模块信息
					$.ajax({
					   type: "post",
					   dataType: "json",
					   url:  base+"/manager/model/"+$(this).attr("data-id")+"/queryModelByRoleId.do",
					  data:$("#managerPageForm").serialize(),
					   success: function(msg){
					     	if(msg.result){
					     		var modelJson =jQuery.parseJSON( msg.resultMsg);
					     	
					     		for (i=0;i<modelJson.length;i++) {
  									if (modelJson[i].modelModelId==0) {
  										$("select[name='managerModelPageModelId']").append("<option  value="+modelJson[i].modelId+">"+modelJson[i].modelTitle+"</option>");
  									}
  								}
					     	} 
					   }
					});
					//查询该管理员是否绑定主界面
					var modelId=0;
					queryModelPage(modelId)
					openaddPageModel($("#managerPageModel"),"主界面绑定");
			});
			
			//点击保存时，开始主界面信息的保存
			$("body").delegate("#saveOrUpdatePage","click",function(){
				$.ajax({
					   type: "post",
					   dataType: "json",
					   url:  base+"/manager/managerModelPage/save.do",
					  data:$("#managerPageForm").serialize(),
					   success: function(msg){
					     	if(msg){
					     		alert("保存成功");
					     		location.href = base+"/manager/manager/queryList.do";
					     	} 
					   }
					});
			});
				//当切换模块时
				$("body").delegate("select[name='managerModelPageModelId']","change",function(){
			
						var modelId = $(this).children('option:selected').val();
						$("input[name='managerModelPageUrl']").val("");
						queryModelPage(modelId);
				});
		});
		//查询模块是否绑定主界面
		function queryModelPage(modelId){
			$.ajax({
					   type: "post",
					   dataType: "json",
					   url:  base+"/manager/managerModelPage/"+modelId+"/getEntity.do",
					   success: function(msg){
					     	if(msg.result){
					     		var json =jQuery.parseJSON( msg.resultMsg);
					     		$("input[name='managerModelPageUrl']").val(json.managerModelPageUrl);
					     	} 
					   }
					});
		}
	</script>

</body>
</html>
