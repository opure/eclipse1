<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/> <!--调用head内样式信息-->
<script type="text/javascript" src="${base}/jquery/zTree_v3/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="${base}/jquery/zTree_v3/zTreeStyle.css" type="text/css">
</head>
<style>
	.container{margin:0;padding:0;width:auto}
	hr{margin:0;padding:0;}
	.ms-button-group{padding:0px 0px 8px 0px}
	.margin20{margin-top:20px;}
	.row {margin-left:0;margin-right:0;padding-bottom:4px;}
	.form-horizontal .form-group{margin-left:0;margin-right:0}
	.popover-content{min-width:205px;line-height:20px;}
	.control-label{width:15%;}
	.padding0{padding: 0;}
	#addColumn{font-size: 22px;line-height: 33px;cursor: pointer;}
	#columnList form .panel-heading label,#columnList form .panel-body label{padding-top: 0;}

	#menuBtn{margin:0}
	#menuContent {
		overflow: auto;
		max-height: 240px;
		width: 88%;
		z-index: 999;
		float: left;
		padding: 5px 0;
		margin: 2px 0 0;
		background-color: #ffffff;
		border: 1px solid rgba(0, 0, 0, 0.15);
		border-radius: 4px;
		-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
		box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
		background-clip: padding-box;
	}
	.red{
		color:red;
	}
	#menuBtn{
		cursor: pointer;
		background: white;
	}
	
	#treeDemo{
	overflow: auto; max-height:240px; width: 100%; 
	display:none;
	z-index:999;
	position: absolute;
	top: 100%;
	left: 0;
	float: left;
	padding: 5px 0;
	margin: 2px 0 0;
	background-color: #ffffff;
	border: 1px solid #cccccc;
	border: 1px solid rgba(0, 0, 0, 0.15);
	border-radius: 4px;
	-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
	background-clip: padding-box;
				
	}
	
</style>

<body>
	<!--顶部开始-->
	<div class="container-fluid">
		<div class="row">
		
				<div class="col-md-10 col-xs-10">
				<h3 class="page-title bottomLine"><#if categoryTitle?has_content>${categoryTitle}<#else><#if !isEditCategory>新增<#else>编辑</#if>
						<#if article?has_content>
							<small>编辑文章</small>
						<#else>
							<small>添加文章</small>
						</#if>
						</#if>
					</h3>
				</div>
			<div class="col-md-2 col-xs-2 white padding0 ">
				<#if !categoryTitle?has_content><button class="btn btn-default pull-right" role="button" onclick="javascript:history.go(-1)">返回文章列表</button></#if>
			</div>
		</div>
		<hr>
		<div class="row margin20" >
			<form id="articleForm" class="form-horizontal" role="form" action="">
				<!--网站标题-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3">文章标题:</label>
					<div class="col-md-4  col-xs-6">
					<#if isEditCategory && !article?exists>
						<input type="text" class="form-control" name="basicTitle" placeholder="请输入文章标题" value="${categoryTitle}">
					<#else>
						<input type="text" class="form-control" name="basicTitle" placeholder="请输入文章标题">
					</#if>
					</div>
				</div>
				<#if !isEditCategory>
				<div class="form-group">
					<label for="ArticleList" class="col-md-1 control-label col-xs-3">自定义顺序:</label>
					<div class="col-md-1  col-xs-1">
						<input type="text" class="form-control" placeholder="0" name="articleFreeOrder" id="ArticleList" value="0">
					</div>
				</div>
				</#if>
				<!--域名:-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3">文章属性:<br/></label>
					<div class="col-sm-10 col-xs-9" id="typeArticle">
					    <#if articleType?has_content>
		        			<#list articleType as articleType>
		        				<label class="checkbox-inline">
  									<input type="checkbox" name="checkbox" value="${articleType.key}">${articleType.value}(${articleType.key})
								</label>
		        			</#list>
		        		</#if>
		        		
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3">缩略图:</label>
					<div class="col-md-4 uploadImg  col-xs-6">
							<#if article?has_content>
								<@uploadImg path="/upload/article/${websiteId}/" inputName="basicThumbnails" size="1" filetype="" msg="提示:文章缩略图"  maxSize="2" imgs="${article.basicThumbnails?default('')}" />
							<#else>
								<@uploadImg path="/upload/article/${websiteId}/" inputName="basicThumbnails" size="1" filetype="" msg="提示:文章缩略图"  maxSize="2" imgs="" />
							</#if>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3" >文章来源:</label>
					<div class="col-md-3 col-xs-4">
						<input type="text"  class="form-control" placeholder="请输入文章来源" name="articleSource">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-3">文章作者:</label>
					<div class="col-md-3   col-xs-3">
						<input type="text" class="form-control"  name="articleAuthor" placeholder="请输入文章作者">
					</div>
				</div>
				
				<div class="form-group" id="UrlLink" style="display:none">
					<label class="col-md-2 control-label col-xs-3" >跳转网址:</label>
					<div class="col-md-4  col-xs-6">
						<!--用于在后台对在网站内加入其他网址的链接，选择跳转属性后直接跳转 -->
						<input type="text"  class="form-control" name="articleUrl" placeholder="http://">
					</div>
				</div>
				<#if !isEditCategory><!-- 如果不是编辑栏目 -->
				<div class="form-group">
				    <label for="inputEmail3" class="col-md-3 control-label col-xs-3">所属栏目:</label>
				    <div class="col-md-4  col-xs-5">
					<div class="dropdown">
						<label  id="columnSuperTitle" role="button" data-toggle="dropdown" data-toggle="popover" data-target="#" class="form-control" data-placement="right" data-content=""><#if categoryTitle?has_content>${categoryTitle?default("")}<#else>选择栏目</#if></label>
						<input type="hidden" id="categoryCategoryId" name="basicCategoryId" value="${categoryId?default(0)}">
						<div id="treeDemoContent">
							<ul class="ztree"  role="menu" aria-labelledby="dLabel" id="treeDemo"></ul>
				    	</div>
					</div>
			    </div>
			  </div>
			  <#else>
			 	 <input type="hidden" id="categoryCategoryId" name="basicCategoryId" value="${categoryId?default(0)}">
			  </#if>
				
				<!--描述-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-4">描述:</label>
					<div class="col-md-6  col-xs-9">
						<textarea class="form-control " rows="4" name="basicDescription" placeholder="请输入文章描述"><#if article?has_content>${article.basicDescription?default("")}</#if></textarea>
					</div>
				</div>
				<div id="hiddenInput"></div>
				<!--关键字-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-4">关键字:</label>
					<div class="col-md-6  col-xs-9">
						<textarea class="form-control" rows="4" name="articleKeyword" placeholder="请输入文章关键字"><#if article?has_content>${article.articleKeyword?default("")}</#if></textarea>
					</div>
				</div>
				
				<!--新填字段内容开始-->
				<div id="addFieldForm">
						
				</div>
				<!--新填字段结束-->
				
				<!--文章内容-->
				<div class="form-group">
					<label class="col-md-2 control-label col-xs-4">文章内容:</label>
					<div class="col-md-10  col-xs-9">
						<#if article?has_content>
							<@smallUedit inputName="articleContent" width="700"  height="400" content="${article.articleContent?default('')}"/>
						<#else>
							<@smallUedit inputName="articleContent" width="700"  height="400" content=""/>
						</#if>
					</div>
				</div>
				
				<!--按钮组-->
				<div class="form-group">
					<div class="col-xs-2 col-md-2"></div>
					<div class="col-xs-10  col-md-10 padding0">
						 <#if article?has_content>
	    					<button type="button" class="btn btn-success" id="updateArticle">修改</button>
	    				<#else>
							<button type="button" class="btn btn-success" id="saveArticle">保存</button>
						</#if>
	    			</div>
				</div>
			</form>
		</div>
	</div>
	
	<!--树形结构-->
	<script>
		//有关下拉列表的函数
		function beforeClick(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		var setting;
		
		//引入表单验证框架
		var bootstrapValidator;
		
		function bindValidate(obj){
				bootstrapValidator = obj.bootstrapValidator({
					feedbackIcons: {
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
			       	fields: {
			       		//文章标题
			            basicTitle: {
			                validators: {
			                    notEmpty: { message: '文章标题不能为空'},
			                    stringLength: {min: 1,max: 300,message: '文章标题长度介于1-300个字符'},
			                }
			            },
			            //自定义排序
			            articleFreeOrder: {
			                validators: {
			                    digits: { message: '请输入数字'},
			                    stringLength: {min: 1,max:12,message: '昵称长度介于1-12个字符'},
			                }
			            },
			            articleAuthor: {
			                validators: {
			                    stringLength: { max: 12,message: '作者名长度不能超过12个字符'}
			                }
			            },
			            articleUrl: {
			                validators: {
			                    stringLength: {max: 300,message: '网址长度不能超过300个字符'}
			                }
			            },
			            articleSource: {
			                validators: {
			                    stringLength: { max: 300,message: '文章来源长度不能超过300个字符'}
			                }
			            }
			            <#if !isEditCategory>,
		            	basicCategoryId:{
		            		 validators: {
		            		 	digits: { message: '请选择栏目'},
		            		 }
		            	}</#if>,
			        }
		     	});
			}
			
		<#if !isEditCategory>//编辑封面文章是没有选择分类功能			
		//zTree框架	
		var setting = {
			view: {
				dblClickExpand: dblClickExpand 
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		function dblClickExpand(treeId, treeNode) {
			return treeNode.level > 0;
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});

		//获取栏目节点列表
		var listColumn=${listColumn};
		var zNodes = new Array();
		
		//遍历节点，添加到数字中
		for( var i = 0 ; i < listColumn.length; i++){
			if(listColumn[i].columnType==1){
				zNodes[i] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(列表)", open:true,type:listColumn[i].columnType};
			}else{
				zNodes[i] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(封面)", open:true,type:listColumn[i].columnType};
			}
			
			
		}
		</#if>
		
		$(function() {
			//文章属性
			var articleId = 0;
			<#if article?has_content>
				articleId=${article.basicId?c?default(0)};
				$("#hiddenInput").html("<input type='hidden' name='basicId'/>");
				//文章标题
				$("input[name='basicTitle']").val("${article.basicTitle?default('')}");
				//文章自定义排序
				$("input[name='articleFreeOrder']").val(${article.articleFreeOrder?c?default(0)});
				//文章来源
				$("input[name='articleSource']").val("${article.articleSource?default('')}");
				//文章作者
				$("input[name='articleAuthor']").val("${article.articleAuthor?default('')}");
				//文章跳转链接
				$("input[name='articleUrl']").val("${article.articleUrl?default('')}");
				//文章所属栏目标题
				//文章所属栏目Id	
				$("input[name='basicCategoryId']").val(${article.basicCategoryId?c?default(0)});
				$("input[name='basicId']").val("${article.basicId?c?default(0)}");
				var type="${article.articleType?default('')}";
				var articleType = new Array;
				$("#typeArticle input[name='checkbox']").each(function(){
					if(type!=""){
						 articleType = type.split(",");
						  for(i=0;i<articleType.length;i++){
							 if($(this).val()==articleType[i]){
								 $(this).attr("checked",'true');
							}
						}
					}
				});
				//根据栏目id判断是否存在新增字段
				 // ajax动态获取表单样式
				$.ajax({
					type: "post",
					url:"${base}/manager/cms/field/"+${article.basicCategoryId?c?default(0)}+"/queryFieldList.do",
					data:"basicId="+articleId,
					success:function(msg){
						
						$("#addFieldForm").html(msg);
					}
				});
			
			//修改表单
			$("#updateArticle").click(function(){
				bindValidate($("#articleForm"));
				var flag = true;
				// 对不能为空的的表单进行判断
				$("input[data-type='0']").each(function(){
					if($(this).val()==""){
						alert($(this).attr("data-name")+"不能为空");
						flag = false;
					}
 				 });
 				 
				//将表单序列化
				var updateArticle = $("#articleForm").serialize();
				var articleFreeOrder=$("input[name='articleFreeOrder']").val();
				var basicCategoryId=$("input[name='basicCategoryId']").val();
				//文章类型
				var checkboxType="";
				$("#typeArticle input[name='checkbox']").each(function(){
					if($(this).is(":checked")){
						checkboxType+=$(this).val()+",";
					}
				});
				
				if(bootstrapValidator.data('bootstrapValidator').validate().isValid()&&flag){
					$.ajax({
				   		type: "post",
				   		url:  base+"/manager/cms/article/"+articleId+"/update.do",
				   		beforeSend:function(){
				   				$("#updateArticle").text("更新中....");
				   		},
				   		data: updateArticle+"&checkboxType="+checkboxType,
				   		dataType:"Json",
				   		success: function(msg){
				   			if(msg.result){
						   		var obj = msg;
								$.ajax({
									   type: "post",
									   url:  base+"/manager/cms/generate/"+obj.resultMsg+"/genernateForArticle.do",
									   dataType:"Json",
								   		beforeSend:function(){
								   			$("#updateArticle").text("生成中....");
								   		},									   
									   success: function(re){
									   		if(re.result){
									   			alert("文章更新成功，并已生成"); 
									   			if (obj.resultData!="") {
									   				location.href=base+obj.resultData;
									   			} else {
									   				$("#updateArticle").text("修改");
									   			}
									   		}else{
									   			alert("生成文件失败")
									   		}
									   },
									});						   				
				   			}else{
				   				alert(msg.resultMsg);
				   			}
				   		}
					});
				}
				
			});
			</#if>
			
			
			$(".del-btn").on("click",function(){
				$("#basicThumbnails").val(null);
			});
			
			//获取自定义字段
			 $.ajax({
				type: "post",
				url:"${base}/manager/cms/field/${categoryId}/queryFieldList.do",
				data:"basicId="+articleId,
				success:function(msg){
					$("#addFieldForm").html(msg);
				}
			})
			
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
					 var strTitle = $(this).html();
					 
					 if(node.type==1){
					 	$("#columnSuperTitle").html(strTitle);
						$("#categoryCategoryId").val(node.id);
						$("#treeDemo").css("display","none");
					 }else{
					 	
					 	$.ajax({
							type: "post",
							url:base+"/manager/cms/article/"+node.id+"/queryColumnArticle.do",
							dataType:"json",
							success:function(msg){
								if(msg.result){
									$("#columnSuperTitle").html(strTitle);
									$("#categoryCategoryId").val(node.id);
									$("#treeDemo").css("display","none");
								}else{
									alert("该单页栏目已经有文章了！")
								}
							}
						})
					 }
					 
					  // ajax动态获取表单样式
					 $.ajax({
						type: "post",
						url:"${base}/manager/cms/field/"+node.id+"/queryFieldList.do",
						data:"basicId="+articleId,
						success:function(msg){
							$("#addFieldForm").html(msg);
						}
					})
					 
				});
			
		$("input[name='checkbox']").click(function(){
			$("input[name='checkbox']").each(function(){
				//跳转
				if( $(this).val() == 'j'){
					if($(this).is(":checked") ){
						$("#UrlLink").show();
						}else{
							$("#UrlLink").hide();
						}
				}
			})
		});
	
	//保存表单
	$("#saveArticle").click(function(){
		bindValidate($("#articleForm"));
		var flag = true;
		// 对不能为空的的表单进行判断
		$("input[data-type='0']").each(function(){
			if($(this).val()==""){
				alert($(this).attr("data-name")+"不能为空");
				flag = false;
			}
 		});
 		
		if(bootstrapValidator.data('bootstrapValidator').validate().isValid()&& flag){
			if($("input[name='basicCategoryId']").val()!=0){
				//将表单序列化
				var saveArticle = $("#articleForm").serialize();
				//文章属性
				var checkboxType="";
				$("#typeArticle input[name='checkbox']").each(function(){
					if($(this).is(":checked")){
						checkboxType+=$(this).val()+",";
					}
				})
				$.ajax({
					type: "post",
					url: "${base}/manager/cms/article/save.do",
					data: saveArticle+"&checkboxType="+checkboxType,
					dataType:"json",
					beforeSend:function(){
				   				$("#saveArticle").text("保存中....");
				   		},
					success: function(msg){
						if(msg.result){
						   		var obj = msg;
								$.ajax({
									   type: "post",
									   url:  base+"/manager/cms/generate/"+obj.resultMsg+"/genernateForArticle.do",
									   dataType:"Json",
								   		beforeSend:function(){
								   			$("#saveArticle").text("生成中....");
								   		},									   
									   success: function(re){
									   		if(re.result){
									   			alert("添加文章成功，并已生成" );
									   			if (obj.resultData!="") {
									   				location.href=obj.resultData;
									   			} else {
									   				$("#saveArticle").html("修改");
									   			}
									   		}else{
									   			alert("生成文件失败")
									   		}
									   },
									});	
						}else{
						   	alert(msg.resultMsg);
						}
						
					}
				});
			}else{
					alert("请选择文章所属栏目");
			}
		}
	});
});
	</script>
</body>
</html>
	