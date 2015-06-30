<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
<style>
	
	 hr{margin-top:9px;margin-bottom:9px;padding:0;}
	.form-horizontal .form-group{margin-left:0;margin-right:0}
	.form-group{overflow: hidden;}
	.padding-zero{padding:0;}
	/*链接样式*/
	.link-style a:hover{color:#000;}
	.link-style a:visited{color:#000;}
	.form-inline .form-group {display: inline-block;margin-bottom: 0;vertical-align: middle;}
	/*弹出窗口样式*/
	#WindowDialog .modal-dialog{width:auto;}
	.control-label{font-weight:normal;font-size:14px;}
	.margin20{ margin-top:20px;}
	.marginleft70{ margin-left:-70px;}
	.form-control{ padding-right:22px;} 
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
					表单管理  <small><#if diyForm?has_content>更新<#else>增加</#if>自定义表单</small></h3>
				</h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button" onclick="location.href='${base}/manager/diy/form/list.do'">返回表单列表</button>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--内容 部分 开始-->
		<div class="row margin20">
			<div class="form-group">
				<label class="col-md-2 control-label col-xs-2 "><h4><strong>表单信息</strong></h4></label>
			</div>
			<form action="<#if diyForm?has_content>${base}/manager/diy/form/update.do<#else>${base}/manager/diy/form/save.do</#if>" class="form-horizontal" id="diyFormFrom">
				<div class="form-group">
					<label class="col-md-2 control-label marginleft70 col-xs-2">提示文字:</label>
					<div class="col-md-3  col-xs-3">
						<input type="text"  class=" form-control" <#if diyForm?has_content>value="${diyForm.diyFormTipsName?default("")}" <#else>placeholder="请输入表单名称"</#if>  name="diyFormTipsName"  required data-bv-notempty-message="不能为空" data-bv-stringlength="true" data-bv-stringlength-max="20" data-bv-stringlength-min="1" data-bv-stringlength-message="长度介于1-20个字符">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label marginleft70 col-xs-2" for="diyFormTableName">表单名称:</label>
					<div class="col-md-3  col-xs-3">
						<input type="text" class=" form-control"  required <#if diyForm?has_content>value="${diyForm.diyFormTableName?default("")}" readonly<#else>placeholder="请输入表单名称"</#if> name="diyFormTableName" required data-bv-notempty-message="不能为空" data-bv-stringlength="true" data-bv-stringlength-max="20" data-bv-stringlength-min="1" data-bv-stringlength-message="长度介于1-20个字符" data-bv-regexp  data-bv-regexp-regexp='^[A-Za-z0-9]+$' data-bv-regexp-message="字段名只能为字符">
					</div>
					<label class="col-md-4 col-xs-4 zhu">（注：表单名称一旦保存不能再更改）</label>
				</div>
				<div class="form-group">
					<div class="col-xs-1 col-md-1" ></div>
					<div class="col-xs-2 col-md-2" style="margin-left:23px;">
						<button  type="button"  class="btn btn-primary saveDiyForm"><#if diyForm?has_content>更新<#else>保存</#if>表单</button>
					</div>
				</div>
				<#if diyForm?has_content>
					<input type="hidden" name="diyFormId" value="${diyForm.diyFormId}"/>
				</#if>
			</form>	
			
			<#if diyForm?has_content>
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
			            		您还没有添加自定义字段
			            		</a>
						  	</p>
						</td>
			          </tr>	
		        </tbody>
		        
		        <!--表格内容  结束-->
			</table>
<!--删除字段模态框-->
	<@warnModal modalName="deleteModel"/>
	<!--编辑和新增字段模态框-->
	<@modalDialog modalName="fieldModel"/>
			</#if>

		</div>
	</div>
	
	<!--编辑和新增字段模态框内容-->
	<div class="row" style="display:none;" id="fieldModel">
		<form action="" class="form-horizontal" role="form" id="fieldFrom">
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="diyFormFieldTipsName">字段提示文字:</label>
				<div class="col-md-4  col-xs-4">
					<input type="text" class=" form-control " placeholder="" name="diyFormFieldTipsName" required data-bv-notempty-message="不能为空" data-bv-stringlength="true" data-bv-stringlength-max="100" data-bv-stringlength-min="1" data-bv-stringlength-message="长度介于1-100个字符">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="diyFormFieldFieldName">字段名称:</label>
				<div class="col-md-4  col-xs-4">
					<input type="text" class=" form-control " placeholder="" name="diyFormFieldFieldName" required data-bv-notempty-message="不能为空" data-bv-stringlength="true" data-bv-stringlength-max="100" data-bv-stringlength-min="1" data-bv-stringlength-message="长度介于1-100个字符" data-bv-regexp   data-bv-regexp-regexp="^[A-Za-z0-9]+$" data-bv-regexp-message="字段名只能为字符">
					<input type="hidden" name="fieldCmid" value=""/>
				</div>
				
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="diyFormFieldType">数据类型:</label>
				<div class="col-md-8  col-xs-8" id="fieldTypeInfo"></div>
				
			</div>
			<!---->
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="fieldDefault">是否是必填字段:</label>
				<div class="col-md-2  col-xs-2" style="line-height:28px;">
					<input value='0' name="diyFormFieldIsNull" type="radio"/>必填
				</div>
				<div class="col-md-2  col-xs-2" style="line-height:28px;">
					<input value='1' type="radio" name="diyFormFieldIsNull"/>可选
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="diyFormFieldDefault">字段默认值:()</label>
				<div class="col-md-6  col-xs-5">
					<textarea  name="diyFormFieldDefault" rows="5" class="form-control" ></textarea>
				</div>
			</div>
			<input type="hidden" name="diyFormFieldFormId" value=""/>
			<div id="hideFieldId" style="display:none;"></div>
			<button type="button" id="saveOrUpdate" class="btn btn-primary" style="float:right;">保存</button>
        	<button type="button" class="btn btn-default closeModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
		</form>
	</div>
	

	<script type="text/javascript">
		//进行字段删除的函数
				function deleteField(fieldId){
					$.ajax({
			   			type: "post",
					   	dataType: "json",
					  	 url:"${base}/manager/diy/formField/"+fieldId+"/delete.do",
					   	data: "fieldId=" + fieldId,
					   	success: function(msg){
					    	if(msg.result) {
						    	$(".closeModal").click();
								alert("删除字段成功");
					    	} else {
								alert("删除字段失败");
						    	$(".closeModal").click();
					    	}
					    	location.reload();
					   },error: function(){
					   	  alert("删除字段失败");
					   }
					});
				}
		
			$(function(){
				//自定义表单的表单验证
				$('#diyFormFrom').bootstrapValidator({
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh',
							autoFocus : true
						}
				});	
				//点击保存时，进行自定义表单的保存
				$(".saveDiyForm").click(function(){
						var thisHtml  = $(this).html();
						var vobj = $("#diyFormFrom").data('bootstrapValidator').validate();
						if(vobj.isValid()){
							$.ajax({
								type: "post",
								dataType: "json",
							 	url:$("#diyFormFrom").attr("action"),
							  	data:$("#diyFormFrom").serialize(),
							  	beforeSend:function() {
				   					$(".saveDiyForm").html(thisHtml+"中...");
				   					$(".saveDiyForm").attr('disabled',true);
				   					$(".saveDiyForm").unbind("click");
				  			 	},success: function(msg){
				  			 		$(".saveDiyForm").html(thisHtml);
				  			 		$(".saveDiyForm").attr('disabled',false);
				  			 		$(".saveDiyForm").bind("click");
							     	if(msg){
							     		alert(thisHtml+"成功");
							     		
							     	}
							     	
							     	location.href="${base}/manager/diy/form/"+msg.resultMsg+"/edit.do";
							     	
							   },error:function(){
					   				$(".saveDiyForm").attr('disabled',false);
					   				$(".saveDiyForm").html(thisHtml);
					   			}
							});
						}
				});
				//判断表明是否存在相同
				$("input[name='diyFormTableName']").blur(function(){
					var diyFormTableName = $(this).val();
					if(diyFormTableName!=""){
					
						$.ajax({
							type: "post",
							url:"${base}/manager/diy/form/"+diyFormTableName+"/checkTableNameExist.do",
							data:"diyFormTableName="+diyFormTableName,
							success: function(msg){
								var obj = jQuery.parseJSON(msg);
								if(obj.result){
							    	 alert("表名已存在，请重新输入");
							     	$("input[name='diyFormTableName']").val("");
								} 
							}
						});
					}
				});
				<#if diyForm?has_content>
					// 用于判断编辑时用户是否改变了字段名称的值
				var oldFielName =$("input[name='diyFormFieldFieldName']").val();
				$('#fieldFrom').bootstrapValidator({
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh',
							autoFocus : true
						}
				});		
					//获取字段列表信息
				function queryFieldList(diyFormId){
					$.ajax({
					   type: "post",
					   dataType: "json",
					   url: "${base}/manager/diy/formField/list.do",
					   data: "diyFormId=" + diyFormId,
					   success: function(msg){
					   		if(msg.fieldList.length != 0){
					    		$("tbody").html("");
					    		//获取字段列表信息
					    		for(var i=0; i<msg.fieldList.length; i++){	    					
			    					var fieldTypeC =  msg.fieldType[msg.fieldList[i].diyFormFieldType];
			    					$("tbody").append("<tr class='fieldList'><td class='text-center'>"+msg.fieldList[i].diyFormFieldId+"</td>"+		          	
									            "<td class='text-center'>"+msg.fieldList[i].diyFormFieldTipsName+"</td>"+
									            "<td class='text-center'>"+msg.fieldList[i].diyFormFieldFieldName+"</td>"+
									 			"<td class='text-center'>"+fieldTypeC+"</td>"+
									            "<td class='text-center'>"+
							                    "<a class='btn btn-xs tooltips delete' data-toggle='tooltip' data-id='"+msg.fieldList[i].diyFormFieldId+"' data-original-title='删除'>"+
							                    "<i class='glyphicon glyphicon-trash'></i></a>"+
							                    "<a class='btn btn-xs tooltips edit' data-toggle='tooltip' data-id='"+msg.fieldList[i].diyFormFieldId+"' data-original-title='编辑'>"+
						                     	"<i class='glyphicon glyphicon-pencil'></i></a></td></tr>");
					    		}
					    	}
					    	$("#fieldTypeInfo").html("");
					    	//动态获取字段属性			    	
					    	if(msg.fieldType != null){
					    		for(var k=1; k<=msg.fieldNum; k++){
					    			$("#fieldTypeInfo").append("<div class='fieldRadio'><input type='radio' class='text-center' name='diyFormFieldType' value='"+k+"'>"+msg.fieldType[k]+"</div>");
					    		}
					    	}
					   }
					});
				}
				//点击新增字段时，弹出新增字段的弹框
				$("body").delegate("#addField","click",function(){
					$("#fieldFrom input[type='text']").val("");
					$("#fieldFrom textarea").val("");
					$("input:radio[value='1']").attr("checked", true);
					$("#hideFieldId").html("");
					$("input[name='diyFormFieldFormId']").val("${diyForm.diyFormId}");
					var url = "${base}/manager/diy/formField/"+${diyForm.diyFormId}+"/save.do";
					$("#saveOrUpdate").html("保存");
					$("#fieldFrom").attr("action",url);
					openfieldModel($("#fieldModel"),"新增字段");
				});
				queryFieldList(${diyForm.diyFormId});
				//点击保存开始字段的保存
				$("body").delegate("#saveOrUpdate","click",function(){
						var fieldType = $("input[name='diyFormFieldType']:checked").val();
						var flag = true;
						// 当用户选择的是数字类型时,默认值只能为数字
						if(fieldType=="4"||fieldType =="5"){
							if((isNaN($("textarea[name='diyFormFieldDefault']").val()))){
								$($("textarea[name='diyFormFieldDefault']")).val("");
								flag = false;
								alert("字段类型为数字类型,默认值只能为数字")
							}
						}
						var vobj = $("#fieldFrom").data('bootstrapValidator').validate();
						var thisHtml = $(this).html();
						if(vobj.isValid()&&flag){
							$.ajax({
					   			type: "post",
					   			dataType: "json",
					   			url: $("#fieldFrom").attr("action"),
					   			data:$("#fieldFrom").serialize(),
					   				beforeSend:function() {
				   					$("#saveOrUpdate").html(thisHtml+"中...");
				   					$("#saveOrUpdate").attr('disabled',true);
				   					$("#saveOrUpdate").unbind("click");
				  			 	},success: function(msg){
					   				$("#saveOrUpdate").attr('disabled',false);
					   					if(msg.result){
					   						$("#saveOrUpdate").html(thisHtml);
					   						alert(thisHtml+"成功");
					   				}
					   				$("#saveOrUpdate").html(thisHtml);
					   				location.reload();
					   			},error:function(){
					   				$("#saveOrUpdate").attr('disabled',false);
					   				$("#saveOrUpdate").html(thisHtml);
					   			}
					   		});
						}
							
					});
				//点击保存开始字段的保存
				$("body").delegate(".edit","click",function(){
							$("#fieldFrom input[type='text']").val("");
							$("#fieldFrom textarea").val("");
							$("input:radio[value='1']").attr("checked", true);
							//表单id
							$("input[name='diyFormFieldFormId']").val("${diyForm.diyFormId}");
							var url = "${base}/manager/diy/formField/update.do";
							$("#fieldFrom").attr("action",url);
							var diyFormFieldId = $(this).attr("data-id");
							$("#saveOrUpdate").html("更新");
							$("#hideFieldId").html("<input name='diyFormFieldId' type='hidden'/>");
							$("input[name='diyFormFieldId']").val(diyFormFieldId);
							$.ajax({
					   			type: "post",
					   			dataType: "json",
					   			url: "${base}/manager/diy/formField/"+diyFormFieldId+"/edit.do",
					   			data:$("#fieldFrom").serialize(),
					   			success: function(msg){
					   				$("input[name='diyFormFieldTipsName']").val(msg.diyFormfield.diyFormFieldTipsName);
				     				$("input[name='diyFormFieldFieldName']").val(msg.diyFormfield.diyFormFieldFieldName);
				     				$("textarea[name='diyFormFieldDefault']").val(msg.diyFormfield.diyFormFieldDefault);
				     				var fieldType = msg.diyFormfield.diyFormFieldType;
				     				oldFielName = msg.diyFormfield.diyFormFieldFieldName;
				     				$("[name='diyFormFieldType'][value="+msg.diyFormfield.diyFormFieldType+"]").attr("checked", true);
				     				$("[name='diyFormFieldIsNull'][value="+msg.diyFormfield.diyFormFieldIsNull+"]").attr("checked", true);
					   				openfieldModel($("#fieldModel"),"更新字段");
					   			}
					   		});
					});
				//点击删除，弹出删除提示框
				$("body").delegate(".delete","click",function(){
					var fieldId = $(this).attr("data-id");
					warndeleteModel("确定删除该字段吗？","删除字段","deleteField("+fieldId+")");
				});
				
				//前端判断同一个表单中是否存在相同的字段名
					$("input[name='diyFormFieldFieldName']").blur(function(){
						var diyFormFieldFieldName = $(this).val();
						var diyFormFieldFormId = $("input[name='diyFormFieldFormId']").val();
						if(oldFielName!=$("input[name='diyFormFieldFieldName']").val() && $("input[name='diyFormFieldFieldName']").val()!=""){
							$.ajax({
								   type: "post",
								   dataType: "json",
								   url:"${base}/manager/diy/formField/"+diyFormFieldFieldName+"/checkFieldNameExist.do",
								   data: "diyFormFieldFormId=" + diyFormFieldFormId,
								   success: function(msg){
								     	if(msg){
								     		alert("字段名已存在，请再次输入");
								     		$("input[name='diyFormFieldFieldName']").val("");
								     		
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
