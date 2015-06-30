<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<style>
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}

	.container{margin:0;padding:0;width:auto}
	hr{margin:0;padding:0;}
	.ms-button-group{padding:0px 0px 8px 0px}

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
	
	
	.control-label{font-weight:normal;font-size:14px;}
	.popover-content{min-width:205px;line-height:20px;}
	.control-label{width:15%;}
	
	hr{margin:0;padding:0;}
	.ms-button-group{padding:0px 0px 8px 0px}
	.margin20{margin-top:20px;}
	.row {margin-left:0;margin-right:0;padding-bottom:4px;}
	.form-horizontal .form-group{margin-left:0;margin-right:0}
	.control-label{font-weight:normal;font-size:14px;}
	.control-label{width:17%;}
	.padding0{padding: 0;}
	.tip{color:red;display:none;line-height:20px;}
	
	.red{
		color:red;
	}
	.has-error .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#A94442;}
	.has-success .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#3C763D;}
</style>

<body>

	
			  	

	<!--顶部开始-->
	<div class="container-fluid" id="columnSaveForm">
		<div class="row margin20">
			<div class="col-md-10 col-xs-9">
				<h3 class="page-title bottomLine">${Session.model_title_session?default("栏目")}管理&nbsp;<small>添加${Session.model_title_session?default("栏目")}</small></h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button">返回${Session.model_title_session?default("栏目")}列表</button>
			</div>
		</div>
		<hr>

		<div class="row margin20">	
		<!--栏目添加表单 开始-->
		<form class="form-horizontal" role="form" id="saveForm">
			  <div class="form-group">
			    <label for="inputEmail3" class="col-md-3 control-label col-xs-2">${Session.model_title_session?default("栏目")}名称:</label>
			    <div class="col-md-4  col-xs-5">
			      <input type="text" class="form-control" placeholder="${Session.model_title_session?default("栏目")}名称" name="categoryTitle" maxlength="30" data-toggle="popover" data-placement="right" data-content="" />
			    </div>
			  </div>
				
			  <div class="form-group">
				    <label for="inputEmail3" class="col-md-3 control-label col-xs-3">选择父${Session.model_title_session?default("栏目")}:</label>
				    <div class="col-md-4  col-xs-5">
							 
							 <div class="dropdown">
									  <label  id="columnSuperTitle" role="button" data-toggle="dropdown" data-toggle="popover" data-target="#" class="form-control" data-placement="right" data-content="">请选择父${Session.model_title_session?default("栏目")}</label>
									 <input type="hidden" id="categoryCategoryId" name="categoryCategoryId" value="0">
									 <div id="treeDemoContent">
									 <ul class="ztree"  role="menu" aria-labelledby="dLabel" id="treeDemo">
								 	 	
									  </ul>
									  </div>
							</div>
			    </div>
			  </div>


			  
			 <div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-md-3 control-label col-xs-3" >${Session.model_title_session?default("栏目")}关键字:</label>
			    <div class="col-md-5  col-xs-6">
			      <textarea type="text" maxlength="300"  rows="4" class="form-control" placeholder="${Session.model_title_session?default("栏目")}关键字，有助于搜索" name="columnKeyword" data-toggle="popover" data-placement="right" data-content=""></textarea>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-md-3 control-label col-xs-3">${Session.model_title_session?default("栏目")}描述:</label>
			    <div class="col-md-5  col-xs-6">
			      <textarea type="text" maxlength="500"  rows="4" class="form-control" placeholder="${Session.model_title_session?default("栏目")}描述，对${Session.model_title_session?default("栏目")}关键字的扩展" name="columnDescrip" data-toggle="popover" data-placement="right" data-content=""></textarea>
			    </div>
			  </div>
			  <div class="form-group" id="columnTypeSelect">
			    <label for="inputEmail3" class="col-md-3 control-label col-xs-3" >${Session.model_title_session?default("栏目")}属性:</label>
			      <div class="col-md-9 col-xs-9">
      				<div class="radio">
      					<label>
				          <input type="radio" name ="columnType" checked="checked" value="1" class="columnListUrl"  data-toggle="popover" data-placement="right" data-content=""> 列表
				         </label>
			         </div>
			         <div class="radio">
      					<label>
				          <input type="radio" name="columnType" value="2" class="columnFrontCover"data-toggle="popover" > 单篇
				         </label>
			         </div>
			        </div>
			  </div>
			  
			  <!--表单类型-->
			  <div class="form-group">
					<label for="inputEmail3" class="col-md-3 control-label col-xs-3">${Session.model_title_session?default("栏目")}模型:</label>
					<div class="col-md-3  col-xs-4">
						<select class="form-control " name="columnContentModelId">
							<#if listCm?has_content>
								<option value="0">普通文章</option>
								<#list listCm as listCm>
									<option value="${listCm.cmId}">${listCm.cmTipsName?default("")}</option>
								</#list>
								
									
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
			  <div class="form-group">
			    <div for="inputEmail3" class="col-md-3 control-label col-xs-3" >
			    </div>
			    	<div class="col-xs-5 col-md-4" >
			      		<input type="button" id="saveFormButton" value="保存" class="btn btn-success col-md"/> 
			    	</div>
			  </div>
	</form>
	</div>
	</div>	
	     
	<!--栏目添加表单结束-->
	<script>
	$(document).ready(function(){
		$.ajax({
		   type: "post",
		   dataType: "json",
		   url:  "${base}/manager/cms/templet/queryTempletFileForColumn.do",
		   success: function(msg){
		     	if(msg.length != 0 && ($(".columnListUrl").html() == "" || $(".columnUrl").html() == "")){
		   			for(var i=0; i<msg.length; i++){
			   			$(".columnListUrl").append("<option>"+msg[i]+"</option>")
			   			$(".columnUrl").append("<option>"+msg[i]+"</option>")
			   		}
		   		} else {
		   			$(".columnListUrl").append("<option>暂无文件</option>");
		   			$(".columnUrl").append("<option>暂无文件</option>");
		   		}
		   		$(".columnListUrl").select2();
				$(".columnUrl").select2();
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
		                    notEmpty: { message: '${Session.model_title_session?default("栏目")}标题不能为空'},
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

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
			//获取栏目节点列表
			var listColumn=${listColumn};
			var zNodes = new Array();
			zNodes[0]={ id:0, pId:0,name:'顶级${Session.model_title_session?default("栏目")}', open:true};
			//遍历节点，添加到数字中
			for( var i = 0 ; i < listColumn.length; i++){
				if(listColumn[i].columnType==1){
					zNodes[i+1] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(列表)", open:true};
				}else{
					zNodes[i+1] = { id:listColumn[i].categoryId, pId:listColumn[i].categoryCategoryId,name:listColumn[i].categoryTitle+"(单篇)", open:true};
				}
				
			}
			
		//页面加载完毕执行
			$(function(){
			
			
			//父栏目选择下拉点击事件
				$("#columnSuperTitle").click(function(event){
					$("#treeDemo").toggle();
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
				$("#columnSaveForm").on('click','button.btn-default',function(){
					location.href = base+"/manager/cms/column/list.do"; 
				});
			
			
				
				//栏目属性选择事件开始
				
				var columnFrontCoverStr=""; //保存封面地址
				var columnTypeListStr=""; //保存列表链接地址
				var columnUrlStr=""; //保存内容地址
				
				 $("input[name=columnType]").click(function(){
				  showCont();
				 });
				 
				function showCont(){
					
					//栏目属性判断，表单样式切换
						switch($("input[name=columnType]:checked").attr("class")){
						  case "columnListUrl":
						  		columnFrontCoverStr = $("#columnUrlShow input").val();
						  		$("#columnUrlListShow").show();
						  		$("#columnUrlListShow input").val(columnTypeListStr);
						  		$("#columnUrlShow label").html("内容链接:");
						  		$("#columnUrlShow input").attr("placeholder","请输入内容链接地址");
						  		$("#columnUrlShow input").val(columnUrlStr);
			   					break;
						  case "columnFrontCover":
						  		columnTypeListStr = $("#columnUrlListShow input").val();
						  		columnUrlStr = $("#columnUrlShow input").val();
						  		$("#columnUrlListShow").hide();
						  		$("#columnUrlListShow input").val("");
						  		$("#columnUrlShow label").html("单篇模版:");
						  		$("#columnUrlShow input").attr("placeholder","请输入单篇模版地址");
						  		$("#columnUrlShow input").val(columnFrontCoverStr);
						   break;
						  default:
						   break;
					 }
					 
				}
				
				//栏目属性选择结束
				
				
			//增加表单验证
			checkForm("#saveForm");
			
				//点击保存时,先判断验证是否通过,再提交信息
				$("#saveFormButton").click(function(){
					if($("#saveForm").data('bootstrapValidator').validate().isValid()){
						if($(".columnListUrl").find("option:selected").text()=="暂无文件"){
							$(".columnListUrl").find("option:selected").text("");
						}
						if($(".columnUrl").find("option:selected").text()=="暂无文件"){
							$(".columnUrl").find("option:selected").text("");
						}
						var formdata = $("#saveForm").serialize();
						$.ajax({
						   	type: "post",
						   	url: "${base}/manager/cms/column/save.do",
						   	data: formdata,
						   	dataType:"json",
						   	success: function(msg){
						    	if (msg.result) {
						     			alert("添加成功");
						    			location.href=base+"/manager/cms/column/list.do?categoryId="+msg.resultData;
						    		}else{
						    			alert(msg.resultMsg);
						    		}
						   	}
						});
					}
					
				});
			
			})
			
		
		
			
		</script>
	
</body>
</html>
