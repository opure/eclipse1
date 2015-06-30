<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
<script src="${base}/jquery/jquery.tmpl.min.js"></script>
</head>
<body><!----------------------------------- bootstarp 布局容器 开始-------------------------------->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					${modelTitle?default("栏目")}管理
					<small>${modelTitle?default("栏目")}管理页面，设计${modelTitle?default("栏目")}</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row ">
			<!--功能按键部分 开始-->
			<div class="form-group">
				<button type="button" class="btn btn-success col-md" id="addColumnButton">新增${modelTitle?default("栏目")}&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
			</div>
		</div>
		<!--列表开始-->
		<table id="columnListTable" class="table table-bordered">
		        <thead>
			        <tr>
			        	<td colspan="12" class="text-left">
		                     	<i class="glyphicon glyphicon-pushpin"></i><strong>${modelTitle?default("栏目")}名</strong>&nbsp;>&nbsp;<strong>${modelTitle?default("栏目")}列表</strong>
			        	</td>
			        </tr>	
			        <tr>
			            <th class="text-center col-md-1">编号</th>
			            <th class="col-md-10 text-left">标题</th>
			            <th class="col-md-1 text-center">操作</th>
		          	</tr>
		        </thead>
		        <tbody  id="columnShowList">
			       <tr class="addColumnPrompt" class="text-center">
				        	<td colspan="3" class="text-center">
				            	<p class="alert alert-info" role="alert" style="margin:0">
				            		<span class="glyphicon glyphicon-plus"></span>
				            		<a href="#" class="alert-link">
				            		您还没有${modelTitle?default("栏目")}，点击添加${modelTitle?default("栏目")}
				            		</a>
							  	</p>
						</td>
				    </tr>
		        	
		        </tbody>    		
		</table>			    
		<!--列表结束-->
	</div>
	
	<!--页面刷新加载效果开始-->
	<div id="loadingEffect" style="display:none;" >
		<img src='${base}/images/lodingDit.gif'/>
	</div>
	<!--页面提示信息-->
	<div class="alertWindows" style="min-width:240px; height:65px; background-color:green; color:white; font-size:14px; 
			text-align:center; border:1px solid red; position:absolute; top:45%; left:37%; line-height:65px;
			opacity:0.8; border-radius:10px; display:none;">
	</div>
	
	<script id="categoryListTmp" type="text/x-jquery-tmpl">
		{{if categoryCategoryId==0}}
		<tr class="categoryRow${"$"}{categoryId}" data-parent="categoryRow${"$"}{categoryCategoryId}" level="0">
			<td class="text-center">${"$"}{categoryId}</td>
			<td class="col-md-2 text-left categoryTitle" data-id="${"$"}{categoryId}"><i class="black58"></i><span class="glyphicon glyphicon-folder-close"></span>&nbsp;${"$"}{categoryTitle}</td>
			<td class="text-center">
				<a class="btn btn-xs red tooltips add-btn" data-id="${"$"}{categoryId}"  data-toggle="tooltip"   data-original-title="增加子栏目" href="#" target="main" style="padding-left:0px; color:#428bca;">
					<i class="glyphicon glyphicon-plus"></i>
				</a>			
				<a class="btn btn-xs red tooltips del-btn"  data-id="${"$"}{categoryId}" data-toggle="tooltip" data-original-title="删除" href="#" target="main" style="padding-left:0px; color:#428bca;">
					<i class="glyphicon glyphicon-trash" ></i>
				</a>
				<a class="btn btn-xs red tooltips edit-btn" data-id="${"$"}{categoryId}"  data-toggle="tooltip"   data-original-title="修改" href="#" target="main" style="padding-left:0px; color:#428bca;">
					<i class="glyphicon glyphicon-pencil"></i>
				</a>
			</td>
		</tr>
		{{/if}}
	</script>
	<script id="categoryChildListTmp" type="text/x-jquery-tmpl"> 
		<tr class="categoryRow${"$"}{categoryId}" data-parent="categoryRow${"$"}{categoryCategoryId}" level="0">
			<td class="text-center">${"$"}{categoryId}</td>
			<td class="col-md-2 text-left categoryTitle" data-id="${"$"}{categoryId}"><i class="black58"></i><span class="glyphicon glyphicon-folder-close"></span>&nbsp;${"$"}{categoryTitle}</td>
			<td class="text-center">
				<a class="btn btn-xs red tooltips add-btn" data-id="${"$"}{categoryId}"  data-toggle="tooltip"   data-original-title="增加子栏目" href="#" target="main" style="padding-left:0px; color:#428bca;">
					<i class="glyphicon glyphicon-plus"></i>
				</a>			
				<a class="btn btn-xs red tooltips del-btn"  data-id="${"$"}{categoryId}" data-toggle="tooltip" data-original-title="删除" href="#" target="main" style="padding-left:0px; color:#428bca;">
					<i class="glyphicon glyphicon-trash" ></i>
				</a>
				<a class="btn btn-xs red tooltips edit-btn" data-id="${"$"}{categoryId}"  data-toggle="tooltip"   data-original-title="修改" href="#" target="main" style="padding-left:0px; color:#428bca;">
					<i class="glyphicon glyphicon-pencil"></i>
				</a>
			</td>
		</tr>
	</script>
	<@warnModal modalName="deleteModel"/>
		
		<script>
		    
			$(function() {
			
				//获取json数据
				var categoryJson=${categoryJson}; //展开的栏目集合
				//var categoryCategoryId=“${categoryCategoryId?if_exists}”;//如果有值的话就需要判断是否有要展开的栏目
				
				$('#categoryListTmp').tmpl(categoryJson).appendTo('#columnShowList'); 
				
				//栏目添加按钮
				$("#addColumnButton,.addColumnPrompt a").click(function(){
				 	location.href = base+"/manager/category/add.do?modelId=${modelId?default(0)}&modelTitle="+encodeURIComponent('${modelTitle?default("栏目")}');
				});
				
				//栏目添加按钮
				$("#columnShowList").delegate('a.add-btn','click',function() {
				 	location.href = base+"/manager/category/add.do?modelId=${modelId?default(0)}&modelTitle="+encodeURIComponent('${modelTitle?default("栏目")}')+"&categoryCategoryId="+$(this).attr("data-id"); 
				});				
				
				//删除图标点击事件
				$("#columnShowList").delegate('a.del-btn','click',function() {
					warndeleteModel('确认要删除该分类','分类删除','deleteCategory("'+$(this).attr("data-id")+'")');
				 });
				 
				 //编辑图标点击事件
				$("#columnShowList").delegate('a.edit-btn','click',function() {
						location.href = base+"/manager/category/"+$(this).attr("data-id")+"/edit.do?modelId=${modelId?default(0)}&modelTitle="+encodeURIComponent('${modelTitle?default("栏目")}');
				 });
				 				 
				 //点击标题展开
				$("#columnShowList").delegate('td.categoryTitle','click',function() {
						var obj = $(this); 
						var tr = obj.parent("tr:first"); //行
						//切换图片的状态
						if (obj.children("span.glyphicon").attr("class").indexOf("glyphicon-folder-open")>0) {
							obj.children("span.glyphicon").attr('class','glyphicon glyphicon-folder-close');
							//影藏子分类
							$("#columnShowList").find("tr.c"+obj.attr("data-id")).fadeOut();
							
						} else {
							obj.children("span.glyphicon").attr('class','glyphicon glyphicon-folder-open'); //图标打开
							var level = eval(parseInt(tr.attr("level"))+1); //层数提供层级缩进
							
							//展开隐藏的规则：通过样式实现 例如 .A -> .A .A1->.A .A1 .A11
							var classes = tr.attr("class").split(" ");
							var newClass = "";
							for (i=1;i<classes.length;i++) {
								newClass+= " "+classes[i];
							}
							for (k=0;k<categoryJson.length;k++) {
								if (obj.attr("data-id")==categoryJson[k].categoryCategoryId) {
									var newTr = $('#categoryChildListTmp').tmpl(categoryJson[k]).insertAfter(tr).addClass(newClass+" c"+obj.attr("data-id")).attr("level",level).hide();;
									newTr.fadeIn();
									newTr.find(".categoryTitle").css("padding-left",level*20+"px");
								}
								//显示小图标提示信息
								$("[data-toggle='tooltip']").tooltip();											
							}

						}
				 });
				 
				 showDataEmptyPrompt();
				
			});
			
			//删除确认按钮事件开始
			function deleteCategory(categoryId) {
					closewarndeleteModel();
			    	$.ajax ({
							type :"POST",
							dataType:"json",
							url : base+"/manager/category/"+categoryId+"/delete.do",
							success:function(msg){  
								var columnJson = $.parseJSON(msg.resultMsg);
								if(columnJson==false){
									alert("请先删除子栏目");
								}else{
									alert("删除成功");
									$(".categoryRow"+categoryId).remove();
									showDataEmptyPrompt();
								}
							}
					});
		    }
		    
		    //判断是否有栏目数据，如果没有数据显示提示样式
			function showDataEmptyPrompt(){
				var num = $("#columnListTable tr").size();
				if(num == 3){
					$(".addColumnPrompt").show();
				}else{
					$(".addColumnPrompt").hide();
				}
			}
		
		
		</script>
	
</body>
</html>
