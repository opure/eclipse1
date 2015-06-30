<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
<style>
	.container{margin:0;padding:0;width:auto}
	 hr{margin-top:9px;margin-bottom:9px;padding:0;}
	.ms-button-group{padding:0px 0px 8px 0px}
	.row {margin-left:0;margin-right:0}
	.form-horizontal .form-group{margin-left:0;margin-right:0}
	.form-group{overflow: hidden;}
	.bs-example>.dropdown>.dropdown-menu {position: static;margin-bottom: 5px;clear: left;}
	.bs-example>.dropdown>.dropdown-toggle {float: left;}
	.padding-zero{padding:0;}
	/*链接样式*/
	.link-style a:hover{color:#000;}
	.link-style a:visited{color:#000;}
	.operate a:visited{color:#428BCA;}
	.form-inline .form-group {display: inline-block;margin-bottom: 0;vertical-align: middle;}
	.dedeteRight{width: 32%;margin: 0 auto;overflow: hidden;}
	/*弹出窗口样式*/
	#WindowDialog .modal-dialog{width:auto;}
	.control-label{font-weight:normal;font-size:14px;}
	.margin20{ margin-top:20px;}
	.marginleft70{ margin-left:-70px;}
	.has-error .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#A94442;}
	.has-success .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#3C763D;}
	.form-control{ padding-right:22px;} 
</style>
</head>
<body>
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row">
			<div class="col-md-10">
				<h3 class="page-title bottomLine">角色管理 <small><#if flag == true>增加<#else>更新</#if>角色</small></h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button" onclick="javascript:history.go(-1)">返回角色列表</button>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--表单开始-->
		<form id="saveRoleFrom" class="form-horizontal" role="form" action="">
		  <div class="form-group">
		    <label for="roleName" class="col-md-2 control-label marginleft70">角色名称:</label>
		    <div class="col-md-2">
		      <input type="text" class="form-control" name="roleName" <#if flag == true>placeholder="请输入角色名称"<#else>value="${role.roleName}"</#if>>
		    </div>
		  </div>
		  <#if flag == false><input type="hidden" name="roleId" value="${role.roleId}"/><input type="hidden" id="oldRoleName" name="oldRoleName" value="${role.roleName}"/></#if>
		   <div class="row">
		  	   <label class="col-md-2 control-label marginleft70">所有的模块:</label>
		  	   <div class="col-md-3">	
				 	 <!--模块树型菜单开始-->
					  <div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
					  </div>
					  <!--模块树型菜单结束-->
				</div>
		  </div>
		  <div class="margin20"></div>
		  <div class="form-group">
		  	<div class="col-md-2 control-label marginleft70"></div>
		    <div class="col-md-1 col-xs-1">
		      <button type="button" id="Deal" class="btn btn-success"><#if flag == true>保存<#else>更新</#if></button>
		    </div>
		    <div class="col-md-1 col-xs-1">
		      <button type="button" class="btn btn-default cancel" onClick="">取消</button>
		    </div>
		  </div>
		</form>
		<!--表单结束-->
	</div>
	
	<script type="text/javascript">
			
		$(function () {
			//前端验证角色名称是否存在
			$("input[name='roleName']").blur(function(){
				var oldRoleName = $("#oldRoleName").length;
				var roleName = $(this).val();
				if((oldRoleName != 0 && $("#oldRoleName").val() != roleName) || (oldRoleName == 0)){
					$.ajax({
					   type: "post",
					   dataType: "json",
					   url:  "${base}/manager/role/judgeRoleNameExist.do",
					   data: "roleName=" + roleName,
					   success: function(msg){
					     	if(msg){
					     		alert("该角色名称已存在，请再次输入");
					     		$("input[name='roleName']").val(null);
					     	} 
					   }
					});
				}
			});
			
			//表单验证
			$("#saveRoleFrom").bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		            roleName: {
		                validators: {
		                    notEmpty: { message: '角色名称不能为空'},
		                    stringLength: {min: 2,max: 8,message: '角色名称长度介于2-8个字符'},
		                }
		            }
		        }
		     });
		     
		    //树型菜单框架
			var zNodes = new Array();
			var modelId = new Array();
			var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
			//列出所有模块
		  	<#if listModel?has_content>
   				<#list listModel as model>
		  			zNodes[${model_index}] = {id:"${model.modelId}", pId:"${model.modelModelId}", name:"${model.modelTitle}", open:true }
			  	</#list>
			</#if>
			//更新角色时勾选出该角色所拥有的模块
			<#if listSelModel?has_content>
	   			<#list listSelModel as selModel>
	   				for(var i=0; i<zNodes.length; i++){
	   					if(zNodes[i].id == ${selModel.modelId}){
							zNodes[i] = {id:"${selModel.modelId}", pId:"${selModel.modelModelId}", name:"${selModel.modelTitle}", open:true, checked:true}
						}
			  		}
				</#list>
			</#if> 
		  	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		  	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
			//点击取消按钮时将所勾选的模块全部去掉勾选
			$(".cancel").on("click",function(){
				zTree.checkAllNodes(false);
			})
			
			//增加/更新角色
			$("#Deal").on("click",function(){
				if($("#saveRoleFrom").data('bootstrapValidator').validate().isValid()){
					$(this).attr("disabled","true");
					var nodes = zTree.getCheckedNodes(true);
					for(var i=0; i<nodes.length; i++){
						modelId[i] = nodes[i].id;
					}
					var URL = "${base}/manager/role/<#if flag == true>save<#else>update</#if>.do";
					$.ajax({
					   type: "post",
					   dataType: "json",
					   url:  URL,
					   data: $("#saveRoleFrom").serialize() + "&modelId=" + modelId,
					   success: function(msg){
					     	if(msg.result){
					     		alert("<#if flag == true>增加<#else>更新</#if>角色成功");
					   			location.href = "${base}/manager/role/queryList.do";
					    	} else {
					    		alert(msg.resultMsg);
					    		if(msg.resultMsg == "友情提示，您没有选择功能模块"){
					   				location.href = "${base}/manager/role/queryList.do";
					    		}
					    	}
					   },error: function(){
					   	  alert("操作失败");
					   	  location.href = "${base}/manager/role/queryList.do";
					   }
					});
				}
			});			
		});
	</script>
	
</body>
</html>
