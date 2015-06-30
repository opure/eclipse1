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
	
	
	ul#categoryTree{
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
	<#assign action="/manager/category/save.do"/>
	<#assign name="添加"/>
	<#if category?has_content>
		<#assign action="/manager/category/update.do"/>
		<#assign name="更新"/>
	</#if>
	<div class="container-fluid" id="columnSaveForm">
		
		<!--顶部开始-->
		<div class="row margin20">
			<div class="col-md-10 col-xs-9">
				<h3 class="page-title bottomLine">${modelTitle?default("栏目")}管理&nbsp;<small>${name}${modelTitle?default("栏目")}</small></h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button">返回${modelTitle?default("栏目")}列表</button>
			</div>
		</div>
		<!--顶部结束-->
		
		<hr>
		<div class="row margin20">	
		<!--栏目添加表单 开始-->
			<form class="form-horizontal" role="form" id="saveForm" action="" >
						  <div class="form-group">
						    <label for="inputEmail3" class="col-md-3 control-label col-xs-2">名称:</label>
						    <div class="col-md-4  col-xs-5">
						      <input type="text" class="form-control" placeholder="类别名称" name="categoryTitle" maxlength="30" data-toggle="popover" data-placement="right" data-content="" value="<#if category?has_content>${category.categoryTitle?default(0)}</#if>" />
						    </div>
						  </div>
							
						  <div class="form-group"  <#if categoryCategoryId?has_content>style="display:none"</#if>><!--如果是直接通过栏目＋号新增分类。就不显示多级分类效果-->
							    <label for="inputEmail3" class="col-md-3 control-label col-xs-3">关联:</label>
							    <div class="col-md-4  col-xs-5">
										 <div class="dropdown">
												  <label  id="columnSuperTitle" role="button" data-toggle="dropdown" data-toggle="popover" data-target="#" class="form-control" data-placement="right" data-content="">请选择父级</label>
												  <input type="hidden"  name="categoryModelId" value="${modelId?default(0)}"/>
												  <input type="hidden" id="categoryCategoryId" name="categoryCategoryId" value="<#if category?has_content>${category.categoryCategoryId?default(0)}<#elseif categoryCategoryId?has_content>${categoryCategoryId}<#else>0</#if>"/>
												  <input type="hidden" id="categoryId" name="categoryId" value=" <#if category?has_content>${category.categoryId?default(0)}<#else>0</#if>"/>
												 <div id="treeDemoContent">
												 	<ul class="ztree"  role="menu" aria-labelledby="dLabel" id="categoryTree">
												 	</ul>
												  </div>
										 </div>
						    	</div>
						  </div>
						  
						  <div class="form-group">
						    	<div for="inputEmail3" class="col-md-3 control-label col-xs-3" ></div>
						    	<div class="col-xs-5 col-md-4" >
						      		<input type="button" id="saveFormButton" value="${name}" class="btn btn-success col-md"/> 
						    	</div>
						  </div>
			</form>
		<!--栏目添加表单结束-->
	    </div>
	    
	</div>	
	     
	
	<script>
			
			//表单验证
			function bindValidate(id){
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
			                    stringLength: {min: 1,max: 30,message: '栏目标题长度介于1-30个字符'},
			                }
			            },
			            columnKeyword: {
			                validators: {
			                    stringLength: { max: 300,message: '栏目简介不超过300个字符'}
			                }
			            },
			            columnDescrip: {
			                validators: {
			                    stringLength: { max: 500,message: '栏目描述不超过500个字符'}
			                }
			            }
			        }
			     });
			}
			
		　//页面加载完毕执行
			$(function(){
				//获取栏目节点列表
				var listCategory=${listCategory};
				
				//zTree框架	
				var zNodes = new Array();
				zNodes[0]={ id:0, pId:0,name:'顶级栏目', open:true};
				var setting = {
					view: {
						dblClickExpand: function(treeId, treeNode) {
							return treeNode.level > 0;
						} 
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
       				   onClick: function zTreeOnClick(event, treeId, treeNode){
							 if(parseInt($("#categoryId").val())>0 && parseInt($("#categoryId").val())==treeNode.id) {
					 			alert("不能选择自身作为子节点");
					 			return;
					 		}
					 		var curNode =  $.fn.zTree.getZTreeObj("categoryTree").getNodesByParam("id", $("#categoryId").val(), null);
						    var ids=[];
						    ids=getChildren(ids, curNode[0]);
						   //alert(ids + ">>>>>>>>" +treeNode.id );
						   if (parseInt($("#categoryId").val())>0) {
							   for (i=0;i<ids.length;i++) {
							   		if (ids[i]==treeNode.id) {
							   			alert("不能选择自身作为子节点");
							   			return;
							   		}
							   }							   
						   }

							$("#categoryCategoryId").val(treeNode.id);
							$("#columnSuperTitle").html(treeNode.name);
							$("#categoryTree").toggle();						   
					   }
				    }
				};
				
				//ids是一个数组 返回结果数组     treeNode是选中的节点
				function getChildren(ids,treeNode){
					ids.push(treeNode.id);
					 if (treeNode.isParent){
							for(var obj in treeNode.children){
								getChildren(ids,treeNode.children[obj]);
							}
					    }
					 return ids;
				}
				//ids是一个数组 返回结果数组     treeNode是选中的父亲节点
				function getParent(ids,treeNode){
					ids.push(treeNode.name);
					 if (treeNode.level>0){
					 		var parent = treeNode.getParentNode();
					 		getParent(ids,parent);
					    }
					 return ids;
				}						
				
				//遍历节点，添加到数字中
				for( var i = 0 ; i < listCategory.length; i++) {
						　//如果是编辑状态，关联菜单将不显示自身
							zNodes[i+1] = { id:listCategory[i].categoryId, pId:listCategory[i].categoryCategoryId,name:listCategory[i].categoryTitle, open:true};
				}
					 		 
			　 $.fn.zTree.init($("#categoryTree"), setting, zNodes);
			
				//下拉框选中数据事件
			/*	$("#categoryTree").on('click','a',function(){
					 var zTree = $.fn.zTree.getZTreeObj("categoryTree");
					 var node = zTree.getNodeByTId($(this).parent().attr("id"));
					getAllChildrenNodes(treeNode,str);
					 if(parseInt($("#categoryId").val())>0 && parseInt($("#categoryId").val())==node.id) {
					 	alert("不能选择自身作为子节点");
					 } else {
						$("#categoryCategoryId").val(node.id);
						$("#columnSuperTitle").html($(this).html());
						$("#categoryTree").toggle();
					}
				});*/
				
				function getAllChildrenNodes(treeNode,result){
				      if (treeNode.isParent) {
				        var childrenNodes = treeNode.children;
				        if (childrenNodes) {
				            for (var i = 0; i < childrenNodes.length; i++) {
				                result += ',' + childrenNodes[i].id;
				                result = getChildNodes(childrenNodes[i], result);
				            }
				        }
				    }
				    return result;
				}				
			
				//父栏目选择下拉点击事件
				$("#columnSuperTitle").click(function(event){
					$("#categoryTree").toggle();
				});
				
			    //鼠标点击事件不在节点上时隐藏下拉框 	
				$("body").bind(
                "mousedown",  
                function(event) {  
                    if (!(event.target.id == "columnSuperTitle" || $(event.target).parents("#categoryTree").length > 0)) {  
                        $("#treeDemo").hide();
                    }  
                }); 
						
				//返回按钮事件
				$("#columnSaveForm").on('click','button.btn-default',function(){
					location.href = base+"/manager/category/list.do?modelId=${modelId}&modelTitle=${modelTitle}";
				});

				//增加表单验证
				bindValidate("#saveForm");
				//点击保存时,先判断验证是否通过,再提交信息
				$("#saveFormButton").click(function(){
					if($("#saveForm").data('bootstrapValidator').validate().isValid()){
						var formdata = $("#saveForm").serialize();
						$.ajax({
						   	type: "POST",
						   	url: "${base}${action}",
						   	data: formdata,
						   	dataType:"json",
						   	success: function(msg){
						    	   if (msg.result) {
						     			alert("操作成功");
						    			location.href=base+"/manager/category/list.do?categoryCategoryId="+msg.resultData+"&modelId=${modelId}&modelTitle="+encodeURIComponent('${modelTitle?default("栏目")}');
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
