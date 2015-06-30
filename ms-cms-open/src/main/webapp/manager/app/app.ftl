<!DOCTYPE html>
<html lang="en">
<head>
	<#include "/manager/include/meta.ftl"/>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-10 col-xs-10">
				<h3 class="page-title bottomLine">站点管理&nbsp;
					<#if app?has_content>
					<small>编辑站点</small>
					<#else>
					<small>添加站点</small>
					</#if>
				</h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button" onclick="javascript:history.go(-1)">返回站点列表</button>
			</div>
		</div>
		<hr>
		<div class="row margin20">	
			<form action="" class="form-horizontal" role="form" id="appForm">
				<!--网站标题-->
				<div class="form-group">
					<#if app?has_content>
					<input type="hidden" class="form-control" name="appId"  value="">
					<input type="hidden" name="appManagerId" value="0"/>
					<#else>
					</#if>
					<label class="col-md-3 control-label col-xs-2" for="title">网站标题:</label>
					<div class="col-md-5  col-xs-5">
						<input type="text" class="form-control"  name="appName"  data-toggle="popover" data-placement="right" data-content="网站标题，在30字以内">
					</div>
				</div>
				<!--网站logo-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3">网站logo:</label>
					<div class="col-md-4 uploadImg  col-xs-6">
						<#if app?has_content> 
							<@uploadImg path="upload/app/${app.appId?default(0)}/" inputName="appLogo" size="1" filetype="" msg="提示:网站logo图片"  maxSize="1" imgs="${app.appLogo?default('')}" />
						<#else> 
							<@uploadImg path="upload/app/" inputName="appLogo" size="1" filetype="" msg="提示:网站logo图片"  maxSize="1" imgs="" />
						</#if>
					</div>
				</div>
				<!--域名:-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3" for="url">域 名:</label>
					<div class="col-md-4  col-xs-4">
						<textarea type="text" id="appUrl" rows="4" class="form-control" name="appUrl" placeholder="使用回车换行可以输入多个域名地址，不需要加http://" ><#if app?has_content>${app.appUrl}</#if></textarea>
					</div>
				</div>
				<#if appStyle?has_content> 
				<#else>
					<!--模板风格-->
					<div class="form-group">
						<label class="col-md-2 control-label col-xs-3" for="style">模板风格:</label>
						<div class="col-md-3  col-xs-4">
							<input type="hidden" name="appStyle"/>
							<select class="form-control appStyle" ></select>
						</div>
					</div>
				</#if>
				<!--关键字-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3" for="keyword">关键字:</label>
					<div class="col-md-6  col-xs-9">
						<textarea type="text" id="keyword" rows="4" class="form-control" name="appKeyword" ><#if app?has_content>${app.appKeyword?default('')}</#if></textarea>
					</div>
				</div>
				<!--描述-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-4">描述:</label>
					<div class="col-md-6  col-xs-9">
						<textarea class="form-control" rows="4" name="appDescription"><#if app?has_content>${app.appDescription?default('')}</#if></textarea>
					</div>
				</div>
				<!--版权信息-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-4">版权信息:</label>
					<div class="col-md-6  col-xs-9">
						<textarea class="form-control" rows="4" name="appCopyright"><#if app?has_content>${app.appCopyright?default('')}</#if></textarea>
					</div>
				</div>
				<!--按钮组-->
				<div class="form-group">
					<div class="col-xs-2 col-md-2"></div>
					<div class="col-xs-2 col-md-1 padding0">
						<#if app?has_content>
							<button id="updateapp" type="button"  class="btn btn-success">修改</button>
						<#else>
							<button id="saveapp" type="button"  class="btn btn-success">保存</button>
						</#if>
	    				
	    			</div>
	    			
				</div>
			<form>
		</div>
	</div>
	
</body>
</html>
<script>

	//表单验证
	function checkForm(id){
		$(id).bootstrapValidator({
			feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
	       	fields: {
	       		//标题
	           appName: {
	                validators: {
	                    notEmpty: { message: '网站标题不能为空'},
	                    stringLength: {min: 1,max: 50,message: '网站标题长度介于1-50个字符'},
	                }
	            },
	            appUrl: {
	                validators: {
	                	notEmpty: {message: '域名地址'},
	                	stringLength: {min:10,max:100,message: '网站域名长度介于10-100个字符'}
	                }
	            },
	        }
	     });
	}
	$(function() {
		//给各文本框赋值
		<#if app?has_content>
			$("input[name='appId']").val("${app.appId?default()}");
			$("input[name='appManagerId']").val("${app.appManagerId?default()}");
			//网站标题
			$("input[name='appName']").val("${app.appName?default('')}");
			//网站模板风格
			$(".appStyle").find("option:selected").text("${app.appStyle?default('')}");
			$("input[name='appStyle']").val("${app.appStyle?default('')}");
		</#if>
		<#if appStyle?has_content> 
		<#else>
			//ajax请求让用户要选择的模板风格
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  "${base}/manager/cms/templet/queryAppTempletSkin.do",
			   success: function(msg){
			     	if(msg.fileName != null && msg.fileName.length != 0 && $(".appStyle").html() == ""){
			   			for(var i=0; i<msg.fileName.length; i++){
			   				if ("${app.appStyle?default('')}"==msg.fileName[i]) {
			   					$(".appStyle").append("<option value="+msg.fileName[i]+" selected>"+msg.fileName[i]+"</option>")
			   				} else {
				   				$(".appStyle").append("<option value="+msg.fileName[i]+">"+msg.fileName[i]+"</option>")
				   			}
				   		}
			   		} else {
			   			$(".appStyle").append("<option>暂无模版</option>");
			   		}
			   }
			});
		</#if>
		
		//判断域名是否重复
		$("input[name='appUrl']").blur(function(){
			if( $("input[name='appUrl']").val()!=""){
				var appUrl= $("input[name='appUrl']").val();
				$.ajax({
				   	type: "post",
				   	url: "${base}/manager/app/checkUrl.do",
				   	dataType:"json",
				   	data:"appUrl="+appUrl,
				   	success: function(msg){
				   		if(msg){
				   			alert("域名已存在,请重新输入");
				   			$("input[name='appUrl']").val("");
				   		}
				   	}
				});
			}
			
		});
		checkForm("#appForm");
		$("#updateapp").click(function(){
			if($("#appForm").data('bootstrapValidator').validate().isValid()){
				if($(".appStyle").find("option:selected").text()=="暂无模版"){
					$("input[name='appStyle']").val();
				}else{
					$("input[name='appStyle']").val($(".appStyle").find("option:selected").text());
				}
				var formdata = $("#appForm").serialize();
				$.ajax({
				   	type: "post",
				   	url: "${base}/manager/app/update.do",
				   	data: formdata,
				   	dataType:"json",
				   	success: function(msg){
				   		
				   		if(msg.result){
				   			
				   			alert("更新成功");
				   			$("#updateapp").attr("disabled", true);
				   			if(msg.resultData==1){
				    			location.href="${base}/manager/app/list.do?pageNo="+msg.resultMsg;
				    		}else{
				    			location.href="${base}/manager/app/-1/edit.do";
				    		}
				   		}else{
				   			alert(msg.resultMsg);
				   		}
				    	
				   	}
				});
			}
		});
		
		
		//点击保存时,先判断验证是否通过,再提交信息
		$("#saveapp").click(function(){	
			if($("#appForm").data('bootstrapValidator').validate().isValid()){
				if($(".appStyle").find("option:selected").text()=="暂无模版"){
					$("input[name='appStyle']").val();
				}else{
					$("input[name='appStyle']").val($(".appStyle").find("option:selected").text());
				}
				var formdata = $("#appForm").serialize();
				$.ajax({
			   		type: "post",
			   		url: "${base}/manager/app/save.do",
			   		dataType:"json",
			   		data: formdata,
			   		success: function(msg){
			     		if (msg.result) {
			     			alert("保存成功");
			     			$("saveapp").attr("disabled", true);
			    			location.href="${base}/manager/app/list.do";
			    		}else {
			    			alert(msg.resultMsg);
			    		}
			   		}
				});
			}
		});
	});
</script>
