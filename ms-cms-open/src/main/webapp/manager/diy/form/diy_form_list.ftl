<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<style>
a:link,.link-style a:visited{
COLOR: #009;}
</style>
<body>
	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					自定义表单管理
					<small>表单列表</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row">
			<!--功能按键部分 开始-->			
			<table class="table table-bordered">
				<thead>
		        	<tr>
		        		<td colspan="12" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	表单列表
		        		</td>
		        	</tr>
			        <tr>
			            <th class="col-md-1 text-center">编号</th>
			            <th class="col-md-9 text-center">名称</th>
			            <th class="col-md-2 text-center">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if list?has_content>
		           		<#list list as form>
		        	<tr> 
			           	<td class="text-center basicId">${form.diyFormId?c?default(0)}</td>
			            <td class="text-left" id="name">${form.diyFormTipsName?default(0)}</td>
			            <td class="text-left">
                    		<a href="${base}/manager/diy/form/${form.diyFormId?c?default(0)}/query.do?title=${form.diyFormTipsName?default(0)}" class="btn btn-xs red tooltips" data-toggle="tooltip" data-original-title="查看记录" >
	                     		<i class="glyphicon glyphicon-comment"></i>
	                    	</a>
	                    	<a href="${base}/manager/diy/form/${form.diyFormId?c?default(0)}/edit.do?title=${form.diyFormTipsName?default(0)}" class="btn btn-xs red tooltips " data-toggle="tooltip" data-original-title="编辑表单" >
	                     		<i class="glyphicon glyphicon-pencil"></i>
	                    	</a>
	                    	<a href="javascript:(0)" class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-original-title="删除表单"  data-id="${form.diyFormId?c?default(0)}">
	                     		<i class="glyphicon glyphicon-trash"></i>
	                    	</a>
						</td>
			        </tr>
			        </#list>
		           	<#else>
			           	<td colspan="12" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a href="${base}/manager/diy/form/add.do" class="alert-link">
			            		您还没有添加表单，点击添加表单
			            		</a>
						  	</p>
						</td>
		          	</#if>
		        </tbody>
			</table>
		</div>
	</div>
	<!--删除字段模态框-->
	<@warnModal modalName="deleteModel"/>
</body>
	<script>
		//进行自定义表单删除的函数
				function deleteDiyForm(diyFormId){
					$.ajax({
			   			type: "post",
					   	dataType: "json",
					  	 url:"${base}/manager/diy/form/"+diyFormId+"/delete.do",
					   	success: function(msg){
					    	if(msg.result) {
						    	$(".closeModal").click();
								alert("删除成功");
					    	} else {
								alert("删除失败");
						    	$(".closeModal").click();
					    	}
					    	location.reload();
					   },error: function(){
					   	  alert("删除失败");
					   }
					});
				}
		$(function(){
				$(".del-btn").click(function(){
					var diyFormId = $(this).attr("data-id");
					warndeleteModel("确定删除该字段吗？","删除字段","deleteDiyForm("+diyFormId+")");
				});
		});
	</script>
</html>

