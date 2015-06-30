<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<body>
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					模块自定义页面管理
					<small>模块自定义页面列表</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row" >
			<!--功能按键部分 开始-->
			<div class="form-group">
				<!--新增按钮 开始-->
				<button  type="button" class="btn btn-success col-md " id="addModel"  >新增模块页面&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
			</div>
			<table class="table table-bordered" id="modelTable">
				<thead>
		        	<tr>
		        		<td colspan="5" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	模块列表
		        		</td>
		        	</tr>
			        <tr>
			             <th class="col-md-3 text-center">名称</th>
			            <th class="col-md-3 text-center">模版路径</th>
			            <th class="col-md-3 text-center">访问路径</th>
			            <th class="col-md-2 text-center">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if list?has_content>
		           		<#list list as item>
		        	<tr>
			            <td class="text-center" >${item.modelTemplateTitle?default("")}</td>
			            <td class="text-center">${item.modelTemplatePath?default("")}</td>
			            <td class="text-left">${item.modelTemplateKey?default("")}</td>
			            <td class="text-center">
		                    <a class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-id="${item.modelTemplateId?c?default(0)}"  data-original-title="删除" href="#">
	                     		<i class="glyphicon glyphicon-trash"></i>
	                    	</a>
		                    <a class="btn btn-xs red tooltips editModel" data-rid="" data-toggle="tooltip" data-id="${item.modelTemplateId?c?default(0)}"  data-original-title="编辑" >
	                     		<i class="glyphicon glyphicon-pencil"></i>
	                    	</a>
						</td>
			        </tr>
			        </#list>
		           	<#else>
		           	<tr class="font15">
		           		
		           		<td colspan="4" class="text-center">
		            	<p class="alert alert-info" role="alert" style="margin:0">
		            		<span class="glyphicon glyphicon-plus" onclick="addModel()"></span>
		            		<a href="#" class="alert-link">
		            		您还没有添加数据！
		            		</a>
					  	</p>
					</td> 
		           	</tr>
		          	</#if>
		        </tbody>
			</table>
		</div>
	</div>
	



	<!--增加/编辑管理员开始-->
	<div class="modal fade" id="modelForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body">
			  <form action="" class="form-horizontal" role="form" id="modelFrom">
			 
			  	<!--模块标题--->
				<div class="form-group">
					
					<label class="col-md-4 control-label col-xs-3" for="managerName">标题:</label>
					<div class="col-md-4  col-xs-4">
						<input type="text" class="form-control modelTemplateTitle" placeholder="请输入标题" name="modelTemplateTitle" >
					</div>
				</div>
				<!--模块编码-->
				<div class="form-group">
					<label class="col-md-4 control-label col-xs-3" >访问路径:</label>
					<div class="col-md-4  col-xs-4">
						<input type="text" class="form-control" name="modelTemplateKey" placeholder="请输入访问路径">
					</div>
				</div>
				<!--模块父模块-->
				<div class="form-group">
				    <label for="selectRole" class="col-md-4 control-label col-xs-3">选择模版:</label>
				    <div class="col-md-5 col-xs-5" id="trees">
										<select class=" template templateSelect" style="width:100%" name="modelTemplatePath"></select>
				   </div>
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="saveOrUpdateBtn" class="btn btn-primary" style="float:right;">保存</button>
	        <button type="button" class="btn btn-default closeSaveModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	
	<!--增加管理员结束-->
	
	
	<!--删除模态框-->
	<@warnModal modalName="deleteWarn"/>
	<!--更新和新增弹出框-->
	<@modalDialog modalName="managerModel"/>
	<script>
	
	
		//引入表单验证框架
		var bootstrapValidator;
		
		//表单验证
		function bindValidate(){
		bootstrapValidator = 	$("#modelFrom").bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		            modelTemplateTitle: {
		                validators: {
		                    notEmpty: { message: '标题不能为空'},
		                    stringLength: {min: 1,max:20,message: '模块标题长度介于1-20个字符'}
		                }
		            },
		            modelTemplateKey: {
		                validators: {
		                    notEmpty: {message: '访问路径不能为空'},
		                    stringLength: {min: 1,max:100,message: '模路径长度介于1-100个字符'}
		                }
		            },
		        }
		     });
		}
		
	$(document).ready(function(){
		$.ajax({
		   type: "post",
		   dataType: "json",
		   url:  "${base}/manager/cms/templet/queryTempletFileForColumn.do",
		   success: function(msg){
		     	if(msg.length != 0 && ($(".template").html() == "" || $(".template").html() == "")){
		   			for(var i=0; i<msg.length; i++){
			   			$(".template").append($("<option>").val(msg[i]).text(msg[i]));
			   		}
		   		} else {
		   			$(".template").append("<option>暂无文件</option>");
		   		}
		   }
		});	
	});			
		
		
		//删除子模块函数
		$("#modelTable").delegate(".del-btn","click",function(){
			var actionUrl="${base}/manager/modeltemplate/"+$(this).attr("data-id")+"/delete.do";
			$.ajax({
				type: "post",
				url:actionUrl,
				dataType:"json",
				success:function(msg){
					alert("删除成功");
					location.href="${base}/manager/modeltemplate/list.do";
				}
			});
		});
		
		//关闭子模块
		function closeChildModel(modelId){
			var modelList =$("#modelTable tr."+$(modelId).attr("id"));
			for(var index = 0; index < modelList.length; index++){  
				    var $container = $(modelList[index]); //循环遍历每一个dom节点  
				    closeChildModel($container);
				}  
			$("#modelTable tr."+$(modelId).attr("id")).remove();
			$(modelId).find("span").attr('class','glyphicon glyphicon-folder-close');
		}
		
	
	
	$(function(){
		
		//编辑模块
		$("#editModelDialog").delegate("#editModel","click",function(){
			var formdata = $("#editModelFrom").serialize();
			$.ajax({
				type: "post",
				dataType:"json",
				url:  "${base}/manager/model/update.do",
				data: formdata,
				success:function(msg){
					if(msg.result){
						alert("更新成功");
				    	location.href="${base}/manager/modeltemplate/list.do";
					}
				}
			});
		});
		
		//新增模块
		$("#addModel").on("click",function() {
			bindValidate();
			$("#saveBtn").html("保存");
			$("#modelFrom").attr("action","${base}/manager/modeltemplate/save.do");
			$("#modelFrom input").val("");
			$("#modelForm").modal('show');
			$(".templateSelect").select2()
			$("#modelForm").find(".modal-title").html("新增模块页面");
		});

		
		//点击编辑按钮，弹出编辑弹框
		$("#modelTable").delegate(".editModel","click",function() {
			 var modelId=$(this).attr("data-id");
			bindValidate();
			$("#saveOrUpdateBtn").html("更新");
			$("#modelFrom").attr("action","${base}/manager/modeltemplate/"+modelId+"/update.do");
			$("#modelForm").modal('show');
			$("#modelForm").find(".modal-title").html("更新模块页面");
		   $(".templateSelect").select2()
				//ajax加载表单数据
				$.ajax({
					type: "post",
					url:"${base}/manager/modeltemplate/"+modelId+"/edit.do",
					dataType:"json",
					success:function(msg){
						var model = eval("("+msg.resultData+")");
						// 给表单赋值
						if(model!=null){
							$("input[name='modelTemplateId']").val(model.modelTemplateId);
							$("input[name='modelTemplateTitle']").val(model.modelTemplateTitle);
							$("input[name='modelTemplateKey']").val(model.modelTemplateKey); 
							$(".template").find("option[value='"+model.modelTemplatePath+"']").attr("selected",true);
						}		
					}
				});
				$("#saveOrUpdateBtn").html("更新");
		});
		
		// 点击保存模块
		$("#saveOrUpdateBtn").on("click",function(){
			if(bootstrapValidator.data('bootstrapValidator').validate().isValid()){
				$.ajax({
					type: "post",
					url:$("#modelFrom").attr("action"),
					dataType:"json",
					data: $("#modelFrom").serialize(),
					success: function(msg){
				     	if (msg.result) {
				     		if($("#saveOrUpdateBtn").html()=="保存"){
				     			alert("保存成功");
				     		}else{
				     			alert("更新成功");
				     		}
				     		$("#saveOrUpdateBtn").attr("disabled",true);
				    		location.href="${base}/manager/modeltemplate/list.do";
				    	}else{
				    		alert(msg);
				    	}
				    	
					}
				});
			}			
		});
		
					
	});
	</script>
	
</body>
</html>

