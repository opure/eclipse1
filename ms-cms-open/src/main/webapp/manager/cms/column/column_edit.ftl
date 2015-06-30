<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<style>
	
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
	#columnSuperSelect,categoryCategoryId{
		cursor:pointer;text-decoration:none;
		overflow-x:auto;white-space:nowrap;
	}

	
	.form-control{
		resize:none; 
	} 
	
	ul#treeDemo{
		 overflow: auto; max-height:240px; width: 100%; 
		 position: absolute;
		 display:none;
		  background-color:#FFFFFF;
		z-index:5555;
		position: absolute;
		top: 100%;
		left: 0;
		z-index: 1000;
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
	.container{margin:0;padding:0;width:auto}
	hr{margin:0;padding:0;}
	.ms-button-group{padding:0px 0px 8px 0px}
	.margin20{margin-top:20px;}
	.row {margin-left:0;margin-right:0;padding-bottom:4px;}
	.form-horizontal .form-group{margin-left:0;margin-right:0}
	.control-label{font-weight:normal;font-size:14px;}
	.control-label{width:17%;}
	.padding0{padding: 0;}
	.tip{color:red;display:none;line-height:20px;}
	.has-error .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#A94442;}
	.has-success .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#3C763D;}

</style>

<body>
	<!--顶部开始-->
	<div class="container-fluid" id="columnUpdateForm">
		<div class="row margin20">
			<div class="col-md-10 col-xs-9">
				<h3 class="page-title bottomLine">${Session.model_title_session?default("栏目")}管理&nbsp;<small>更新${Session.model_title_session?default("栏目")}</small></h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button">返回${Session.model_title_session?default("栏目")}列表</button>
			</div>
		</div>
		<hr>
		<div class="row margin20">	
		<!--栏目修改表单 开始-->
			<form class="form-horizontal" role="form" id="updateForm" >
				<input type="hidden"  name="categoryId" <#if column.categoryId?has_content>value="${column.categoryId?default("0")}"</#if> />
				<!--栏目名称-->
				<div class="form-group">
			    	<label class="col-md-3 control-label col-xs-2">${Session.model_title_session?default("栏目")}名称:</label>
			    	<div class="col-md-4 col-xs-4">
			      		<input type="text" class="form-control" maxlength="30"  placeholder="${Session.model_title_session?default("栏目")}名称" name="categoryTitle" <#if column.categoryTitle?has_content>value="${column.categoryTitle?default("")}"</#if> data-toggle="popover" data-placement="right" data-content="" />
			    	</div>
			  	</div>
			  	
			   <div class="form-group">
				    <label for="inputEmail3" class="col-md-3 control-label col-xs-3">选择父${Session.model_title_session?default("栏目")}:</label>
				    <div class="col-md-4  col-xs-5">
							 <div class="dropdown">
									  <label  id="columnSuperTitle" role="button" data-toggle="dropdown" data-target="#" class="form-control" data-toggle="popover" data-placement="right" data-content=""><#if column.categoryCategoryId==0>顶级${Session.model_title_session?default("栏目")}<#else>${columnSuper.categoryTitle?default("")}<#if columnSuper.columnType==1>(列表)<#else>(单篇)</#if></#if></label>
									 <input type="hidden" id="categoryCategoryId" name="categoryCategoryId" value="${column.categoryCategoryId?default("0")}" />
									 <div id="treeDemoContent">
									 <ul class="ztree"  role="menu" aria-labelledby="dLabel" id="treeDemo">
								 	 	
									  </ul>
									  </div>
							</div>
			    </div>
			  </div>
			  
			  
			  <!--栏目关键字-->
			  <div class="form-group">
			  	<label  class="col-md-3 control-label col-xs-3" >${Session.model_title_session?default("栏目")}关键字:</label>
			    <div class="col-md-5  col-xs-6">
			      <textarea type="text" class="form-control" rows="4" maxlength="300" placeholder="${Session.model_title_session?default("栏目")}关键字，有助于搜索" name="columnKeyword"  data-toggle="popover" data-placement="right" data-content="" ><#if column.columnKeyword?has_content>${column.columnKeyword?default("")}</#if></textarea>
			    </div>
			  </div>
			  <!--栏目描述-->
			  <div class="form-group">
			    <label  class="col-md-4 control-label col-xs-3">${Session.model_title_session?default("栏目")}描述:</label>
			    <div class="col-md-5  col-xs-6">
			      <textarea type="text" class="form-control"  rows="4" maxlength="300" placeholder="${Session.model_title_session?default("栏目")}描述，对${Session.model_title_session?default("栏目")}关键字的扩展" name="columnDescrip" data-toggle="popover" data-placement="right" data-content="" ><#if column.columnDescrip?has_content>${column.columnDescrip?default("")}</#if></textarea>
			    </div>
			  </div>
			  <!--栏目属性-->
			  <div class="form-group" id="columnTypeSelect">
			  	<label  class="col-md-2 control-label col-xs-2" >${Session.model_title_session?default("栏目")}属性:</label>
			    <div class=" col-md-9 col-xs-9">
			    	
		      			<div class="radio">
							<input type="radio" name ="columnType" value="1" class="columnListUrl" <#if column.columnType==1> checked="checked"</#if>> 文章列表
						</div>
						<div class="radio">
							<input type="radio" name="columnType" value="2" class="columnFrontCover" <#if column.columnType==2> checked="checked"</#if>> 单篇文章
						</div>
						
			     </div>
			  </div>
			  
			  	<!--表单类型-->
			  <div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label col-xs-3">${Session.model_title_session?default("栏目")} 模型:</label>
					<div class="col-md-3  col-xs-4">
						<select class="form-control " name="columnContentModelId">
							<#if listCm?has_content>
								<#list listCm as listCm>
									<option value="${listCm.cmId}">${listCm.cmTipsName?default("")}</option>
								</#list>
								<option value="0">普通文章</option>
							</#if>
						</select>
					</div>
			  </div>
			  <!--属性样式 -->
	  			<div class="form-group" id="columnUrlListShow">
				    <label for="inputEmail3" class="col-md-3 control-label col-xs-3" >列表模版:</label>
				 		<div class="col-md-4  col-xs-5">
				 			<select class="form-control columnListUrl" name="columnListUrl"></select>
				    	</div>
				</div>
				<div class="form-group" id="columnUrlShow">
				    <label for="inputEmail3" class="col-md-3 control-label col-xs-3">内容模版:</label>
				    <div class="col-md-4  col-xs-5">
				    	<select class="form-control columnUrl" name="columnUrl"></select>
				    </div>
			    </div>
			  <!--属性样式 -->
			  
			  <!--按钮组-->
			  <div class="form-group">
			  	<div for="inputEmail3" class="col-md-3 control-label col-xs-3"></div>
			    <div class="col-xs-5 col-md-4" >
			     	<input type="button" value="保存" id="updateFormButton" class="btn btn-success col-md"/>
			    </div>
			  </div>
		</form>
	<!--栏目修改表单结束-->
	</div>
</div>
		
		<script>
		var columnListUrlSes ='${column.columnListUrl}';
		var columnUrlSes = '${column.columnUrl}';
		$(document).ready(function(){
			$("select[name='columnContentModelId']").find("option[value='${column.columnContentModelId?c?default(0)}']").attr("selected",true);
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  "${base}/manager/cms/templet/queryTempletFileForColumn.do",
			   success: function(msg){
			     	if(msg.length != 0 && ($(".columnListUrl").html() == "" || $(".columnUrl").html() == "")){
			   			for(var i=0; i<msg.length; i++){
			   				if(msg[i] == columnListUrlSes) {
			   					$(".columnListUrl").append("<option selected>"+msg[i]+"</option>")
			   				}else{
			   					$(".columnListUrl").append("<option>"+msg[i]+"</option>")
			   				}
				   			if( msg[i] == columnUrlSes){
				   				$(".columnUrl").append("<option selected>"+msg[i]+"</option>")
				   			}else{
				   				$(".columnUrl").append("<option>"+msg[i]+"</option>")
				   			}
				   		}
			   		} else {
			   			$(".columnListUrl").append("<option>暂无文件</option>");
			   			$(".columnUrl").append("<option>暂无文件</option>");
			   		}
			   }
			});	
		});	
		
		
			//表单验证
		function checkForm(id){
			$(id).bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		       		//栏目标题
		            categoryTitle: {
		                validators: {
		                    notEmpty: { message: '栏目标题不能为空'},
		                    stringLength: {min: 1,max: 30,message: '${Session.model_title_session?default("栏目")}标题长度介于1-30个字符'},
		                }
		            },
		          
		            columnKeyword: {
		                validators: {
		                    stringLength: { max: 300,message: '${Session.model_title_session?default("栏目")}简介不超过300个字符'}
		                }
		            },
		            columnDescrip: {
		                validators: {
		                    stringLength: { max: 500,message: '${Session.model_title_session?default("栏目")}描述不超过500个字符'}
		                }
		            },
		        }
		     });
		}
		
		//栏目地址验证
		function a(str){
		                        	
                    	if(str.length>5){
							  	if(str.substr(str.length-5,5)==".html" || str.substr(str.length-4,4)==".htm"){
				                  		return true;
				                }else{
				                  		return false;
				              	}
							}else if(str.length==5){
								if(str.substr(str.length-4,4)==".htm"){
				                  		return true;
				                }else{
				                  		return false;
				              	}
							}else{
								return false;
							}
                    }
			
		
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

		//获取修改的栏目属性
			var currentColumnTitle;
			<#if column.columnType==1>
				currentColumnTitle = '${column.categoryTitle}'+"(列表)";
			<#else>
				currentColumnTitle = '${column.categoryTitle}'+"(单篇)";
			</#if>
			var currentColumnId = '${column.categoryId}';
			//获取栏目节点列表
			var listColumn=${listColumn};
			var zNodes = new Array();
			zNodes[0]={ id:0, pId:0,name:'顶级${Session.model_title_session?default("栏目")}', open:true};
			//遍历节点，添加到数字中
			for( var i = 0 ; i < listColumn.length; i++){
				if(listColumn[i].columnType==1){
						if(listColumn[i].categoryId==currentColumnId){
							zNodes[i+1] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:"notSelectThisColumn", open:true};
						}else{
							zNodes[i+1] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(列表)", open:true};
						}
				}else{
					if(listColumn[i].categoryId==currentColumnId){
							zNodes[i+1] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:"notSelectThisColumn", open:true};
						}else{
							zNodes[i+1] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(单篇)", open:true};
						}
				}
				
			}
		
			$(function(){
			
			//增加表单验证
			checkForm("#updateForm");
			
			//生成节点树
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		
			//栏目不能选择自己及其子栏目为父栏目的事件
			$("a[title=notSelectThisColumn]").parent().on('click','a',function(event){
				event.stopPropagation();
				alert("不能选择该栏目作为父栏目");
			});
			
			//节点的值复制回去
			$("a[title=notSelectThisColumn]").children('span:last').html(currentColumnTitle);
			$("a[title=notSelectThisColumn]").attr("title",currentColumnTitle);

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
					$("#columnSuperTitle").html($(this).html());
					
					 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					 var node = zTree.getNodeByTId($(this).parent().attr("id"));
					$("#categoryCategoryId").val(node.id);
					
					$("#treeDemo").css("display","none");
				});
			
					
				//返回按钮事件
				$("#columnUpdateForm").on('click','button.btn-default',function(){
					var categoryId = $("input[name='categoryId']").val(); 
					location.href = base+"/manager/cms/column/list.do?categoryId="+categoryId; 
				});
			
			
				//栏目属性选择开始
				
				var columnFrontCoverStr; //保存封面地址
				var columnTypeListStr; //保存列表链接地址
				var columnUrlStr; //保存内容地址
				
			   <#if column.columnType==1>
				   	columnTypeListStr='${column.columnListUrl?default("")}'; 
					columnUrlStr='${column.columnUrl?default("")}'; 
			   <#else>
			   		columnFrontCoverStr='${column.columnUrl?default("")}';
			   </#if>
				
				
				
				showCont();
				 $("input[name=columnType]").click(function(){
				  showCont();
				 });
				 
				function showCont(){
					
					//栏目属性判断，表单样式切换
						switch($("input[name=columnType]:checked").attr("class")){
						  case "columnListUrl":
						  		columnFrontCoverStr = $("#columnUrlShow input").val();
						  		$("#columnUrlListShow").css("display","block");
						  		$("#columnUrlListShow input").val(columnTypeListStr);
						  		$("#columnUrlShow label").html("内容链接:");
						  		$("#columnUrlShow input").attr("placeholder","请输入内容链接地址");
						  		$("#columnUrlShow input").val(columnUrlStr);
			   					break;
						  case "columnFrontCover":
						  		columnTypeListStr = $("#columnUrlListShow input").val();
						  		columnUrlStr = $("#columnUrlShow input").val();
						  		$("#columnUrlListShow").css("display","none");
						  		$("#columnUrlListShow input").val("");
						  		$("#columnUrlShow label").html("封面链接:");
						  		$("#columnUrlShow input").attr("placeholder","请输入封面链接地址");
						  		$("#columnUrlShow input").val(columnFrontCoverStr);
						   break;
						  default:
						   break;
					 }
					 
				}
				
				
				//栏目保存提交事件
				$("#updateFormButton").click(function(){
				
				if($("#updateForm").data('bootstrapValidator').validate().isValid()){
						if($(".columnListUrl").find("option:selected").text()=="暂无文件"){
							$(".columnListUrl").find("option:selected").text("");
						}
						if($(".columnUrl").find("option:selected").text()=="暂无文件"){
							$(".columnUrl").find("option:selected").text("");
						}
						var formdata = $("#updateForm").serialize();
						$.ajax({
						   	type: "post",
						   	url: "${base}/manager/cms/column/update.do",
						   	data: formdata,
						   	dataType:"json",
						   	success: function(msg){
						    	if (msg.result) {
						     			alert("更新成功");
						    			location.href=base+"/manager/cms/column/list.do?categoryId="+msg.resultData;
						    		}else{
						    			alert("qqq");
						    		}
						   	}
						});
					}
					
				});
				
			})
		</script>
	
</body>
</html>
