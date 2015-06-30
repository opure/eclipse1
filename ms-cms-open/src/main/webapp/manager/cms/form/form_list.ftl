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
	<!-- bootstarp 布局容器 开始 -->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					内容模型管理 <small>内容模型列表</small>
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
				<a href="${base}/manager/cms/contentModel/add.do"><button type="button"class="btn btn-success btn-md saveModelBtn">新增内容模型&nbsp;<span class="glyphicon glyphicon-plus"></span></button></a>
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
	                     	<a href="">栏目名</a>&nbsp;>&nbsp;<a href=""><strong>内容模型列表</strong></a>
		        		</td>
		        	</tr>
			        <tr>
			        	<th class="col-md-1 text-center">编号</th>
			            <th class="col-md-9 text-center">内容模型名称</th>
			            <th class="col-md-2 text-center">操作</th>
			        </tr>
		        </thead>
		        <!--表格栏目属性 结束-->
		        
		        <!--表格内容  开始-->
		        <tbody>
		            <#if listContentModel?has_content>
			           <#list listContentModel as model>
			          <tr>
			            <td class="text-center">${model.cmId?c?default(0)}</td>		          	
			            <td class="text-center">${model.cmTipsName?default("暂无")}</td>
			            <td class="text-center operate">
		                    <a class="btn btn-xs tooltips delete" data-toggle="tooltip" data-id="${model.cmId?c?default(0)}" data-original-title="删除">
		                        <i class="glyphicon glyphicon-trash"></i>
		                    </a>
		                    <a class="btn btn-xs tooltips updateModalBtn" href="${base}/manager/cms/contentModel/${model.cmId?c?default(0)}/edit.do" data-toggle="tooltip" data-original-title="编辑">
	                     		<i class="glyphicon glyphicon-pencil"></i>
	                    	</a>
						</td>
			          </tr>
			           </#list>
					<#else>
			          <tr>
			            <td colspan="12" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0; cursor:pointer;">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a class="alert-link saveModelBtn" href="${base}/manager/cms/contentModel/add.do">
			            		您还没有添加内容模型，点击添加内容模型
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
		<!--删除表单模态框-->
		<@warnModal modalName="deleteModel"/>
	</div>
	<!-- bootstarp 布局容器 结束 -->	
	
	<script type="text/javascript">	
	
		//删除内容模型
		function deleteForm(cmId){
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  base+"/manager/cms/contentModel/"+cmId+"/delete.do",
			   data: "cmId=" + cmId,
			   success: function(msg){
			    	if(msg != 0) {
				    	$(".closeModal").click();
						alert("删除内容模型成功");
			    		if($("tbody tr").length==0 && msg != 1){
			    			location.href = base+"/manager/cms/contentModel/list.do?pageNo="+(msg-1);
						}else{
							location.href = base+"/manager/cms/contentModel/list.do?pageNo="+msg;
						}
			    	} else {
						alert("删除内容模型失败");
				    	$(".closeModal").click();
			    	}
			   },error: function(){
			   	  alert("删除内容模型失败");
			   }
			});
		}
		
		$(function(){
			//删除内容模型
			$(".delete").on("click",function(){
				var cmId = $(this).attr("data-id");
				warndeleteModel("确定删除该内容模型吗？","删除内容模型","deleteForm("+cmId+")");
			});	
		});
	</script>

</body>
</html>
