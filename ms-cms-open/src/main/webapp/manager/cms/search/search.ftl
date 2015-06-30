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
	.marginleft35{ margin-left:-35px;}
	.has-error .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#A94442;}
	.has-success .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#3C763D;}
	.form-control{ padding-right:22px;} 
	.basicInfo input{ margin-top:-2px; margin-left:15px;}
	#treeDemo{
	overflow: auto; max-height:240px; width: 100%; 
	display:none;	z-index:999;	position: relative;	top: 100%;	left: 0;
	float: left;	padding: 5px 0;	margin: 2px 0 0;	background-color: #ffffff;
	border: 1px solid #cccccc;	border: 1px solid rgba(0, 0, 0, 0.15);
	border-radius: 4px;	-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);	background-clip: padding-box;		
	}
</style>
</head>
<body>
	<!-- bootstarp 布局容器 开始 -->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row">
			<div class="col-md-10">
				<h3 class="page-title bottomLine">搜索管理  <small><#if flag=true>创建<#else>更新</#if>搜索</small></h3>
			</div>
			<div class="col-md-2 col-xs-2 white padding0 ">
				<button class="btn btn-default pull-right" role="button" onclick="location.href=’${base}/manager/cms/search/list.do‘">返回搜索列表</button>
			</div>			
		</div>		
		<!--顶部  结束-->
		<hr>
		<!--内容 部分 开始-->				
			<form action="" class="form-horizontal" id="searchForm">	
				<div class="form-group">
						<label class="col-md-2 control-label marginleft35 col-xs-2">搜索名称：</label>
						<div class="col-md-3  col-xs-3">
							<input type="text" class=" form-control searchName" <#if flag=true>placeholder="请输入表单名称"<#else>value="${search.searchName}"</#if> name="searchName">
						</div>
						<label class="col-md-4 col-xs-4" style=" height:30px; line-height:30px; color:#F00;">（注：搜索名称一旦保存不能再更改）</label>
				</div>	
				<#if flag=false><input type="hidden" value="${search.searchId}" name="searchId" class="searchId"/></#if>
				<div class="form-group">
					<label  class="col-md-2 marginleft35 control-label col-xs-2">搜索结果模板：</label>
					<div class="col-md-3  col-xs-3">
						<select class="col-md-4 form-control searchTemplets" name="searchTemplets"><#if flag=true><#else><option value="${search.searchTemplets}">${search.searchTemplets}</option></#if></select>
					</div>
				</div>		
				<div class="form-group">
					<label  class="col-md-2 marginleft35 control-label col-xs-2"></label>
					<button type="button" class="btn btn-primary <#if flag=true>saveSearchForm<#else>updateSearchForm</#if>" style="margin-left:15px;"><#if flag=true>保存<#else>更新</#if>搜索</button>
				</div>
			</form>
			<form action="" class="form-horizontal" id="fieldForm" style="<#if flag=true>display:none;<#else>display:block;</#if>">	
				<hr>
				<div class="form-group">
					<label class="col-md-2 control-label marginleft35 col-xs-2 "><h4><strong>搜索生成器：</strong></h4></label>
				</div>			
				<div class="form-group basicInfo" style="font-size:14px;">
					<div class="col-md-2 marginleft35 control-label col-xs-2">基本搜索：</div>
					<div class="col-md-10 col-xs-10 padding-zero" >
						<input type="hidden" name="searchId" <#if flag=false>value="${search.searchId}"</#if>/>
						<input type="checkBox" value="1"  name="basic_title">标题
						<input type="checkBox" value="10" name="article_type">属性
						<input type="checkBox" value="7" name="basic_thumbnails">缩略图
						<input type="checkBox" value="1" name="article_source">来源
						<input type="checkBox" value="4" name="article_freeorder">自定义顺序
					</div>
					<div class="col-md-2 marginleft35  col-xs-2"></div>
					<div class="col-md-10  col-xs-10 padding-zero">
						<input type="checkBox" value="1" name="article_author">作者
						<input type="checkBox"  value="2" name="basic_description">描述
						<input type="checkBox" value="2" name="article_keyword">关键字
						<input type="checkBox" value="2" name="article_content">内容
					</div>
				</div>
				<div class="form-group">
					    <label  class="col-md-2 marginleft35 control-label col-xs-2">高级搜索：</label>
					    <div class="col-md-3  col-xs-3">
							<div class="dropdown">
								<label  id="columnSuperTitle" role="button" data-toggle="dropdown" data-toggle="popover" data-target="#" class="form-control" data-placement="right" data-content=""><#if column?has_content>${column.categoryTitle?default("")}<#if column.columnType==1>(列表)<#else>(封面)</#if><#else>选择栏目</#if></label>
								<input type="hidden" id="categoryCategoryId" name="basicCategoryId" value="">
								<div id="treeDemoContent" >
									<ul class="ztree"  role="menu" aria-labelledby="dLabel" id="treeDemo"></ul>
						    	</div>
							</div>
				   		 </div>
				 </div>
				 <div class="form-group" style="font-size:14px;"><label  class="col-md-2 marginleft35 control-label col-xs-2"></label><div id="addField"></div></div>
				<div class="form-group">
					<label  class="col-md-2 marginleft35 control-label col-xs-2"></label>
					<button type="button" class="btn btn-primary saveField" style="margin-left:15px;">生成查询表单代码</button>
				</div>
			</form>			
		</div>
	</div>
	<!--HTML源码模态框-->
	<@warnModal modalName="htmlModel"/>
	<script type="text/javascript"/>
	    var categoryId = 0;
	    
		//有关下拉列表的函数
		function beforeClick(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		
		//zTree框架	
		var setting = {
			view: {dblClickExpand: dblClickExpand },
			data: {simpleData: {enable: true}}
		};
		function dblClickExpand(treeId, treeNode) {
			return treeNode.level > 0;
		}
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		//获取栏目节点列表
		var listColumn=${columnList};
		var zNodes = new Array();
		//遍历节点，添加到数字中
		for( var i = 0 ; i < listColumn.length; i++){
			if(listColumn[i].columnType==1){
				zNodes[i] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(列表)", open:true,type:listColumn[i].columnType};
			}else{
				zNodes[i] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(封面)", open:true,type:listColumn[i].columnType};
			}			
		}
		
		//父栏目选择下拉点击事件
			$("#columnSuperTitle").click(function(event){	
				if($("#treeDemo").css("display")=="none"){
					$("#treeDemo").css("display","block");
				}else{
					$("#treeDemo").css("display","none");
				}			
			});
	   //鼠标点击事件不在节点上时隐藏下拉框 	
			$("body").bind(
	        "mousedown",  
	        function(event) {  
	            if (!(event.target.id == "columnSuperTitle" || $(event.target)  
	                    .parents("#treeDemoContent").length > 0)) {  
	                $("#treeDemo").css("display","none");
	            }  
	        });
	        
	 //下拉框选中数据事件
		$("#treeDemo").on('click','a',function(){
			 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			 var node = zTree.getNodeByTId($(this).parent().attr("id"));
			 categoryId = node.id;
			 var strTitle = $(this).html();					 
		 	$("#columnSuperTitle").html(strTitle);
			$("#categoryCategoryId").val(node.id);
			$("#treeDemo").css("display","none");		
			 // ajax动态获取表单样式
			 $.ajax({
				type: "post",
				dataType:"json",
				url:base+"/manager/cms/search/"+node.id+"/queryFieldName.do",
				data:"columnId="+node.id,
				success:function(msg){
					if(msg.listField.length != 0){
						$("#addField").html("");
						for(var i=0; i<msg.listField.length; i++){
					     	$("#addField").append("<input type='checkBox' id='"+msg.listField[i].fieldTipsName+"' class='diyField' style='margin-top:-2px; margin-left:15px;' value="+msg.listField[i].fieldType+"  name="+msg.listField[i].fieldFieldName+">"+msg.listField[i].fieldTipsName);
					     }
				     } else {
				     	 $("#addField").html("");
				     }
				}
			  })
		   });	
		   
		  	//点击保存搜索按钮
		 	$(".saveSearchForm").bind("click",function(){
		 		saveSearchForm();
		 	});
		 	
		 	function saveSearchForm() {
			 		 $.ajax({
						type: "post",
						dataType:"json",
						url:"${base}/manager/cms/search/save.do",
						data:$("#searchForm").serialize(),
						 beforeSend:function() {
			   				$(".saveSearchForm").html("正在保存...");
			   				$(".saveSearchForm").attr("disabled","disabled");
			   				$(".saveSearchForm").unbind("click");
			  			 },
						success:function(msg){
							if(msg.result){
								$("#fieldForm").css("display","block");
								$("input[name='searchId']").val(msg.resultMsg);
								$(".searchName").attr("readonly","readonly");
								$(".saveSearchForm").html("更新搜索");
								$(".saveSearchForm").removeAttr("disabled");
							 	$(".saveSearchForm").bind("click",function(){
							 		updateSearchForm();
							 	});								
							}else {
								alert(msg.resultMsg);
							}
						}
					})	 		
		 	}
		 	
		 	//点击更新搜索按钮
		 	$(".updateSearchForm").on("click",function(){
					updateSearchForm();
		 	});
		 	
		 	function updateSearchForm() {
		 		 $.ajax({
						type: "post",
						dataType:"json",
						url:"${base}/manager/cms/search/update.do",
						data:$("#searchForm").serialize(),
						success:function(msg){
							if(msg.result){
								alert("更新成功");
							}else {
								alert("更新失败");
								
							}
						}
					})		 	
		 	}
		 	
		 //点击保存生成器按钮
		 	$(".saveField").on("click",function(){
		 		 //生成搜索表的html代码
		 		 $.ajax({
						type: "post",
						url:"${base}/manager/cms/search/generateSreachFormHtml.do",
						data:$("#fieldForm").serialize(),
						success:function(msg){
							warnhtmlModel("","查询表单代码","");
							$("#warnhtmlModelBody").html("<textarea style='width:100%;height:500px' >"+msg+"</textarea>");
							$("#searchDataForm").prepend("<input type='hidden' name='basicCategoryId' value="+categoryId+"/>");
						}
					})
		 	});
		 	
		 	//获取模板下的htm文件
				$.ajax({
						type: "post",
						url:"${base}/manager/cms/templet/queryTempletFileForColumn.do",
						dataType:"json",
						success:function(msg){
							if(msg.length==0){
								$(".searchTemplets").append("<option value='' >暂无模板文件</option>")
							}
							for(var i = 0;i<msg.length;i++){
								$(".searchTemplets").append("<option value="+msg[i]+">"+msg[i]+"</option>");
							}							
						}
				});   
	</script>
</body>
</html>
