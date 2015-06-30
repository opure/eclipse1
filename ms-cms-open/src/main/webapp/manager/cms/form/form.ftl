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
	.zhu{ height:30px; line-height:30px; color:#F00;}
	.fieldRadio{float:left;width:150px;height:25px;}
</style>
</head>
<body>
	<!-- bootstarp 布局容器 开始 -->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row">
			<div class="col-md-10">
				<h3 class="page-title bottomLine">
					内容模型管理  <small><#if flag == true>增加<#else>更新</#if>内容模型</small></h3>
				</h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button" onclick="location.href='${base}/manager/cms/contentModel/list.do'">返回内容模型列表</button>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--内容 部分 开始-->
		<div class="row margin20">
			<div class="form-group">
				<label class="col-md-2 control-label col-xs-2 "><h4><strong>表单信息</strong></h4></label>
			</div>
			<form action="" class="form-horizontal" id="contentModelFrom">
				<div class="form-group">
					<label class="col-md-2 control-label marginleft70 col-xs-2">提示文字:</label>
					<div class="col-md-3  col-xs-3">
						<input type="text" class=" form-control" <#if flag == true>placeholder="请输入表名提示文字"<#else>value="${contentModel.cmTipsName}"</#if> name="cmTipsName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label marginleft70 col-xs-2" for="nickName">表单名称:</label>
					<div class="col-md-3  col-xs-3">
						<input type="text" class=" form-control" <#if flag == true>placeholder="请输入表单名称"<#else>value="${contentModel.cmTableName}" readonly</#if> name="cmTableName">
					</div>
					<#if flag == true><label class="col-md-4 col-xs-4 zhu">（注：表单名称一旦保存不能再更改）</label></#if>
				</div>
				<#if flag == false><input type="hidden" name="cmId" value="${contentModel.cmId}"/></#if>
				<!--保存表单信息按钮-->
				<div class="form-group">
					<div class="col-xs-1 col-md-1" ></div>
					<div class="col-xs-2 col-md-2" style="margin-left:23px;">
						<button  type="button"  class="btn btn-primary <#if flag=true>saveContentModel<#else>updateContentModel</#if>"><#if flag=true>保存<#else>更新</#if>表单</button>
					</div>
				</div>
			</form>	
			
			<#if flag == false>
			<hr>
			<div class="form-group">
				<label class="col-md-2 control-label col-xs-2"><h4><strong>字段信息</strong></h4></label>
			</div>
			<!--功能按键部分 开始-->
			<div class="form-group">
				<!--新增按钮 开始-->
				<div class="col-md-1 control-label col-xs-1 ">
					<button type="button"class="btn btn-success" id="addField" >新增字段&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
				</div>
				
			</div>
			<!--功能按键部分 结束-->
			
				
			
			<!--列表 开始-->
			<table class="table table-bordered">
				<!--表格栏目属性 开始-->
		        <thead>
			        <tr>
			            <th class="col-md-1 text-center">编号</th>
			            <th class="col-md-3 text-center">字段提示文字</th>
			            <th class="col-md-3 text-center">字段名称</th>
			            <th class="col-md-3 text-center">字段类型</th>
			            <th class="col-md-2 text-center">操作</th>
			        </tr>
		        </thead>
		        <!--表格栏目属性 结束-->
		       
		        <!--表格内容  开始-->
		        <tbody>
			          <tr class="remarks">
			            <td colspan="12" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0; cursor:pointer;">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a class="alert-link" id="saveNewField">
			            		您还没有添加自定义字段，点击添加字段
			            		</a>
						  	</p>
						</td>
			          </tr>	
		        </tbody>
		        
		        <!--表格内容  结束-->
			</table>
			<!--分页样式 结束-->
			
			</#if>
		</div>
	</div>
	
	<!--编辑和新增字段模态框内容-->
	<div class="row" style="display:none;" id="fieldModel">
		<form action="" class="form-horizontal" role="form" id="fieldFrom">
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="fileTipsName">表单提示文字:</label>
				<div class="col-md-4  col-xs-4">
					<input type="text" class=" form-control " placeholder="" name="fieldTipsName">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="fileFieldName">字段名称:</label>
				<div class="col-md-4  col-xs-4">
					<input type="text" class=" form-control " placeholder="" name="fieldFieldName">
					<input type="hidden" name="fieldCmid" value="${cmId}"/>
				</div>
				<div styel="display:none;" id="hideFieldId"></div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="fileType">数据类型:</label>
				<div class="col-md-8  col-xs-8" id="fieldTypeInfo"></div>
				
			</div>
			<!---->
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="fieldDefault">是否是必填字段:</label>
				<div class="col-md-2  col-xs-2" style="line-height:28px;">
					<input value='0' name="fieldIsNull" type="radio"/>必填
				</div>
				<div class="col-md-2  col-xs-2" style="line-height:28px;">
					<input value='1' type="radio" name="fieldIsNull"/>可选
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="fieldDefault">字段默认值:()</label>
				<div class="col-md-6  col-xs-5">
					<textarea  name="fieldDefault" rows="5" class="form-control" ></textarea>
				</div>
			</div>
			
			<button type="button" id="saveOrUpdate" class="btn btn-primary" style="float:right;">保存</button>
        	<button type="button" class="btn btn-default closeModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
		</form>
	</div>
	
	<!-- bootstarp 布局容器 结束 -->
	<!--删除字段模态框-->
	<@warnModal modalName="deleteModel"/>
	<!--编辑和新增字段模态框-->
	<@modalDialog modalName="fieldModel"/>
	<script type="text/javascript">
		//表单验证绑定函数
		function bindValidate(obj){
			bootstrapValidator = obj.bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		            fieldTipsName: {
		                validators: {
		                    notEmpty: { message: '字段提示文字不能为空'},
		                    stringLength: {min: 1,max: 30,message: '字段提示文字长度介于1-30个字符'}
		                }
		            },
		            fieldFieldName: {
		                validators: {
		                    notEmpty: {message: '字段名称不能为空'},
		                    stringLength: {min: 1,max: 20,message: '字段长度介于1-20个字符'},
		                    regexp: {regexp: /^[a-zA-Z0-9]+$/,message: '字段名只能由英文字母，数字组成'}
		                }
		            },
		        }
		     });
		}
	
		<#if flag == false>
		//字段列表
		function queryFieldList(){
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  base+"/manager/cms/field/list.do",
			   data: "cmId=" + ${cmId},
			   success: function(msg){
			   		if(msg.fieldList.length != 0){
			    		$("tbody").html("");
			    		//获取字段列表信息
			    		for(var i=0; i<msg.fieldList.length; i++){	    					
	    					var fieldTypeC =  msg.fieldType[msg.fieldList[i].fieldType];
	    					$("tbody").append("<tr class='fieldList'><td class='text-center'>"+msg.fieldList[i].fieldId+"</td>"+		          	
							            "<td class='text-center'>"+msg.fieldList[i].fieldTipsName+"</td>"+
							            "<td class='text-center'>"+msg.fieldList[i].fieldFieldName+"</td>"+
							 			"<td class='text-center'>"+fieldTypeC+"</td>"+
							            "<td class='text-center'>"+
					                    "<a class='btn btn-xs tooltips delete' data-toggle='tooltip' data-id='"+msg.fieldList[i].fieldId+"' data-original-title='删除'>"+
					                    "<i class='glyphicon glyphicon-trash'></i></a>"+
					                    "<a class='btn btn-xs tooltips edit' data-toggle='tooltip' data-id='"+msg.fieldList[i].fieldId+"' data-original-title='编辑'>"+
				                     	"<i class='glyphicon glyphicon-pencil'></i></a></td></tr>");
			    		}
			    	}
			    	//动态获取字段属性			    	
			    	if(msg.fieldType != null){
			    		for(var k=1; k<=msg.fieldNum; k++){
			    			$("#fieldTypeInfo").append("<div class='fieldRadio'><input type='radio' class='text-center' name='fieldType' value='"+k+"'>"+msg.fieldType[k]+"</div>");
			    		}
			    	}
			   }
			});
		}
		</#if>
		//删除字段
		function deleteField(fieldId){
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  base+"/manager/cms/field/"+fieldId+"/delete.do",
			   data: "fieldId=" + fieldId,
			   success: function(msg){
			    	if(msg != 0) {
				    	$(".closeModal").click();
						alert("删除字段成功");
			    		if($("tbody tr").length==0 && msg != 1){
			    			location.href = base+"/manager/cms/contentModel/add.do";
						}else{
							location.href = base+"/manager/cms/contentModel/${cmId}/edit.do";
						}
			    	} else {
						alert("删除字段失败");
				    	$(".closeModal").click();
			    	}
			   },error: function(){
			   	  alert("删除字段失败");
			   }
			});
		}
		
		// 点击新增字段时弹出新增字段弹出框
		function saveField(){
			bindValidate($("#fieldFrom"));
			$("#saveOrUpdate").html("保存");
			$("#fieldFrom input:text").val("");
			$("input:radio[value='1']").attr("checked", true);
			var cmTableName=$("input[name='cmTableName']").val();
			$("#fieldFrom").attr("action","${base}/manager/cms/field/"+cmTableName+"/save.do");
			openfieldModel($("#fieldModel"),"新增字段");
		}
		
		// 用于判断编辑时用户是否改变了字段名称的值
		var oldFielName =$("input[name='fieldFieldName']").val();
		$(function(){
		
		
		
			//更新内容模型函数
			function updateContentModel(){
				$.ajax({
					type: "post",
					dataType:"json",
					url:"${base}/manager/cms/contentModel/update.do",
					data:$("#contentModelFrom").serialize(),
					success:function(msg){
						if(msg.result){
							alert("更新成功");
						}else {
							alert("更新失败");
								
						}
					}
				});
			}
			//点击更新表单按钮
		 	$(".updateContentModel").on("click",function(){
					updateContentModel();
		 	});
			//字段列表查询
			<#if flag == false>
			
			//删除字段
			$("tbody").delegate(".delete","click",function(){
				var fieldId = $(this).attr("data-id");
				warndeleteModel("确定删除该字段吗？","删除字段","deleteField("+fieldId+")");
			}); 
			
			
			//点击新增字段按钮,弹出新增字段的模态框
			$("#addField").on("click",function(){
				saveField();
			});
			$("#saveNewField").on("click",function(){
				saveField();
			});
			
			//前端判断同一个表单中是否存在相同的字段名
			$("input[name='fieldFieldName']").blur(function(){
				var fieldFieldName = $(this).val();
				var fieldCmId = $("input[name='fieldCmid']").val();
				if(oldFielName!=$("input[name='fieldFieldName']").val() && $("input[name='fieldFieldName']").val()!=""){
					$.ajax({
						   type: "post",
						   dataType: "json",
						   url:"${base}/manager/cms/field/"+fieldFieldName+"/checkFieldNameExist.do",
						   data: "fieldCmId=" + fieldCmId,
						   success: function(msg){
						     	if(msg){
						     		alert("字段名已存在，请再次输入");
						     		$("input[name='fieldFieldName']").val("");
						     	} 
						   }
					});
				}
			});
			
			
			
			// 点击模态框保存按钮时进行ajax请求保存数据
			
			$("#fieldModelDialog").delegate("#saveOrUpdate", "click", function(){
				var fieldType = $("input[name='fieldType']:checked").val();
				var flag = true;
				// 当用户选择的是数字类型时,默认值只能为数字
				if(fieldType=="6"||fieldType =="7"){
					if((isNaN($("textarea[name='fieldDefault']").val()))){
						$($("textarea[name='fieldDefault']")).val("");
						flag = false;
						alert("字段类型为数字类型,默认值只能为数字")
					}
				}
				
				if(bootstrapValidator.data('bootstrapValidator').validate().isValid()&&flag){
					$.ajax({
						type: "post",
						url:$("#fieldFrom").attr("action"),
						dataType:"json",
						data: $("#fieldFrom").serialize(),
						success: function(msg){
							var fieldCmid = $("input[name='fieldCmid']").val();
							if(msg.result){
								if($("#saveOrUpdate").html()=="更新"){
									alert("更新成功");
									location.href="${base}/manager/cms/contentModel/"+fieldCmid+"/edit.do";
								}else{
									alert("保存成功");
									location.href="${base}/manager/cms/contentModel/"+fieldCmid+"/edit.do";
								}
								
							}else{
								alert(msg.resultMsg);
							}
							$("#saveOrUpdate").attr('disabled',true);
						}
					});
				}
			});
			
			// 点击编辑按钮弹出更新模态框
			$("tbody").delegate(".edit","click",function(){
				$("#saveOrUpdate").attr('disabled',false);
				var fieleId = $(this).attr("data-id");
				bindValidate($("#fieldFrom"));
				$("#hideFieldId").html("<input name='fieldId'type='hidden'/>");
				$("input[name='fieldId']").val(fieleId);
				$("#saveOrUpdate").html("更新");
				$("#fieldFrom").attr("action","${base}/manager/cms/field/update.do");
				$.ajax({
					type: "post",
					url:"${base}/manager/cms/field/"+fieleId+"/edit.do",
					dataType:"json",
					success: function(msg){
				     	$("input[name='fieldTipsName']").val(msg.field.fieldTipsName);
				     	$("input[name='fieldFieldName']").val(msg.field.fieldFieldName);
				     	$("textarea[name='fieldDefault']").val(msg.field.fieldDefault);
				     	var fieldType = msg.field.fieldType;
				     	oldFielName = msg.field.fieldFieldName;
				     	$("[name='fieldType'][value="+fieldType+"]").attr("checked", true);
				     	$("[name='fieldIsNull'][value="+msg.field.fieldIsNull+"]").attr("checked", true);
					}
				});
				openfieldModel($("#fieldModel"),"更新字段");
			});
			queryFieldList();
		<#else>
		
			
			//新增内容模型函数
			function saveContentModel(){
				if(bootstrapValidator.data('bootstrapValidator').validate().isValid()){
					$.ajax({
						type: "post",
						url:"${base}/manager/cms/contentModel/save.do",
						dataType:"json",
						data: $("#contentModelFrom").serialize(),
						beforeSend:function() {
			   				$(".saveContentModel").html("正在保存...");
			   				$(".saveContentModel").attr("disabled","disabled");
			   				$(".saveContentModel").unbind("click");
			  			 },
						success: function(msg){
							if(msg.result){
								$("input[name='cmTableName']").attr("readonly","readonly");
								$("input[name='fieldCmid']").val(msg.resultMsg);
								alert("表单信息保存成功");
								$(".saveContentModel").html("更新表单");
								$(".saveContentModel").removeAttr("disabled");
							 	$(".saveContentModel").bind("click",function(){
							 		updateContentModel();
							 	});	
								location.href="${base}/manager/cms/contentModel/"+msg.resultMsg+"/edit.do";
							}else{
								alert(msg.resultMsg);
							}
						}
					});
				}
			}
		
			//点击保存表单按钮
			$(".saveContentModel").bind("click",function(){
				saveContentModel();
			});
			
			
			//对内容模型的表单进行验证
			bindValidate($("#contentModelFrom"));
			// 前端验证内容模型的表名是否相同
			$("input[name='cmTableName']").blur(function(){
				var cmTableName= $(this).val();
				if(cmTableName!=""){
					$.ajax({
						type: "post",
						dataType: "json",
						url:"${base}/manager/cms/contentModel/"+cmTableName+"/checkcmTableNameExist.do",
						success: function(msg){
							if(msg){
							     alert("表名已存在，请重新输入");
							     $("input[name='cmTableName']").val("");
							} 
						}
					});
				}
			});
		</#if>
		});
	</script>

</body>
</html>
