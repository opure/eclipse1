<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<style> 
	.container{margin:0;padding:0;width:auto}
	hr{margin:0;padding:0;}
	.ms-button-group{padding:0px 0px 8px 0px}

/*弹出窗口样式*/
	#WindowDialog .modal-dialog{width:auto;}
	.control-label{font-weight:normal;font-size:14px;}
	.redError{ color:red; font-size:12px;};

span{
		cursor:pointer;text-decoration:none;
	}
	
	.black0{
		padding-left:0px; 
	}
	
	.hover{
		background-color:#1C86EE;
	}

	a{
		color:#428bca;
	}
	
	.addColumnPrompt{
		display:none;
	}
	

	
</style>
<body><!----------------------------------- bootstarp 布局容器 开始-------------------------------->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					${Session.model_title_session?default("栏目")}管理
					<small>${Session.model_title_session?default("栏目")}管理页面，设计${Session.model_title_session?default("栏目")}</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row ">
			<!--功能按键部分 开始-->
			<div class="form-group">
				<button type="button" class="btn btn-success col-md" id="addColumnButton">新增${Session.model_title_session?default("栏目")}&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
			</div>
		</div>
		<!--列表开始-->
		<table id="columnListTable" class="table table-bordered">
		        <thead>
			        <tr>
			        	<td colspan="12" class="text-left">
		                     	<i class="glyphicon glyphicon-pushpin"></i><strong>${Session.model_title_session?default("栏目")}列表</strong>
			        	</td>
			        </tr>	
			        <tr>
			            <th class="text-center col-md-1">编号</th>
			            <th class="col-md-3 text-left">标题</th>
			            <th class="col-md-1 text-left">属性</th>
			            <th class="col-md-3 text-left">${Session.model_title_session?default("栏目")}链接标签</th>
			            <th class="col-md-1 text-left">列表地址</th>
			            <th class="col-md-1 text-left">内容地址</th>
			            <th class="col-md-1 text-left">封面地址</th>
			            <th class="col-md-1 text-center">操作</th>
		          	</tr>
		        </thead>
		        <tbody  id="columnShowList">
			       <tr class="addColumnPrompt" class="text-center">
				        	<td colspan="12" class="text-center">
				            	<p class="alert alert-info" role="alert" style="margin:0">
				            		<span class="glyphicon glyphicon-plus"></span>
				            		<a href="#" class="alert-link">
				            		您还没有${Session.model_title_session?default("栏目")}，点击添加${Session.model_title_session?default("栏目")}
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
	
	
		<@warnModal modalName="deleteModel"/>
		
		
		<script>
		
		//判断是否有栏目数据，如果没有数据显示提示样式
		function showDataEmptyPrompt(){
			var num = $("#columnListTable tr").size();
			if(num == 3){
				$(".addColumnPrompt").css("display","table-row");
			}else{
				$(".addColumnPrompt").css("display","none");
			}
		}
		//栏目ID
		var deleteColumnId=0;
			//删除确认按钮事件开始
			function deleteConfirm(){
						//$(".modal-footer .btn-default").click();
						closewarndeleteModel();
				    	$.ajax ({
								type :"POST",
								dataType:"json",
								url : base+"/manager/cms/column/"+deleteColumnId+"/delete.do",
								success:function(msg){  
									var columnJson = $.parseJSON(msg.resultMsg);
									if(columnJson==false){
										alert("请先删除子栏目");
									}else{
										$("#columnChild"+deleteColumnId).remove();
										alert("删除成功");
										showDataEmptyPrompt();
									}
								}
						});
			     }
			
			//删除确认按钮事件结束
			
		
			$(function(){
			//列表栏目链接标签头部
			var columnListStr;
			//封面栏目链接标签头部
			var columnCoverStr;
			var columnCoverUrlStr
			columnListStr=${columnRegexConstant}[0];
			columnCoverStr=${columnRegexConstant}[1];//+${columnRegexConstant}[2]+'/';
			columnCoverUrlStr = ${columnRegexConstant}[1];
			
			//页面加载正在操作的栏目节点展开操作
			var columnListJsonStr=${listColumn}; //展开的栏目集合
			var columnExpansionId = "columnShowList"; //当前操作的标签Id
			var firstExpansion = "true";
			//初始化栏目展开操作
			function columnExpansion(categoryCategoryId){  //栏目Id
			
				$("#columnChild"+categoryCategoryId+" span").attr('class','glyphicon glyphicon-folder-open');
				 for(var i=0;i<columnListJsonStr.length;i++){  //遍历展开的栏目集合				 	
				 	if(columnListJsonStr[i].categoryCategoryId == categoryCategoryId){  //展开同一栏目的子栏目
				 		var str = '<tr class="'+
								  'columnChild'+
								 categoryCategoryId+
								 '"id="'+
								  'columnChild'+
								 columnListJsonStr[i].categoryId+
								  '"data-pId="';
								  
								  if(columnListJsonStr[i].categoryCategoryId==0){
								  	str=str+columnListJsonStr[i].categoryId;
								  }else{
								  	str=str+$("#columnChild"+columnListJsonStr[i].categoryCategoryId).attr("data-pId")+"/"+columnListJsonStr[i].categoryId;
								  }
								 
								  str=str+'"><td class="text-center">'+columnListJsonStr[i].categoryId+'</td> <td class="col-md-2 text-left"><i class="black'+columnListJsonStr[i].categoryId+'"></i><span  class="glyphicon glyphicon-folder-close"></span>&nbsp'+columnListJsonStr[i].categoryTitle+'</td><td>';
								  //栏目属性
								  if(columnListJsonStr[i].columnType==1){
								  	str=str+"列表";
								  }else{
								  	str=str+"单篇";
								  }
								  	str=str+'</td><td>';
								  //栏目连接标签
							  		if(columnListJsonStr[i].categoryCategoryId==0){
							  			str=str+columnCoverStr+columnListJsonStr[i].categoryId+"/index.html";
							  		}else{
							  			str=str+columnCoverStr+$("#columnChild"+columnListJsonStr[i].categoryCategoryId).attr("data-pId")+"/"+columnListJsonStr[i].categoryId+"/index.html";
							  		}
								  str=str+'</td><td>';
									  //列表模板地址
									  
								   if(columnListJsonStr[i].columnType==1){
								  	 if(columnListJsonStr[i].columnListUrl != ""){
								   		str=str+columnListJsonStr[i].columnListUrl;
								   	}else{
								   	str=str+"未添加模板";
								   }
								  }
								  str=str+'</td><td>';
								  //内容模板地址
								  if(columnListJsonStr[i].columnType==1){
								  	 if(columnListJsonStr[i].columnUrl != ""){
								   		str=str+columnListJsonStr[i].columnUrl;
								   	}else{
								   	str=str+"未添加模板";
								   }
								  }
								   str=str+'</td><td>';
								   
								   //封面模板地址
								  if(columnListJsonStr[i].columnType==2){
								  	if(columnListJsonStr[i].columnUrl != ""){
								   		str=str+columnListJsonStr[i].columnUrl;
								   	}else{
								   	str=str+"未添加模板";
								   }
								  }
								  str=str+'</td>';
								  //操作
								  str=str+'<td class="text-center">';
								  
				                   str=str+ '<a class="btn btn-xs red tooltips del-btn" data-rid="" data-toggle="tooltip" data-original-title="删除" href="#" target="main" style="padding-left:0px; color:#428bca;" >'+
				                     '<i class="glyphicon glyphicon-trash"></i>'+
				                    '</a>'+
				                   
					                 '<a class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-original-title="修改" href="#" target="main" style="padding-left:0px; color:#428bca;" >'+
				                     '<i class="glyphicon glyphicon-pencil"></i>'+
				                    '</a>';
				                    if(columnListJsonStr[i].columnType==2){
								  	str=str+'<a class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-original-title="预览" href="#" target="main" style="padding-left:0px; color:#428bca;" >'+
				                   		 '</a>';
								  }
				                    
									str=str+'</td></tr>';
									
									if(firstExpansion == "true"){
										$("#"+columnExpansionId).append(str); //添加一行数据
										firstExpansion = "flase"
									}else{
										$("#"+columnExpansionId).after(str); //添加一行数据
									}
										
							  	
								columnExpansionId = "columnChild"+columnListJsonStr[i].categoryId; //重置最后添加的一行的Id
								
								if(categoryCategoryId!=0){ //如果不是顶级栏目则添加子栏目的阶梯显示样式
									 var paddint=0;
									var paddstr = $("#columnChild"+categoryCategoryId).find("i").css('padding-left');
								  	var paddint = parseInt(paddstr);
								  	paddint += 20;
								  var newpadd = paddint + "px";
								  $(".black"+columnListJsonStr[i].categoryId).css('padding-left',newpadd);
								}
								  
				 		
						//判断栏目是否还有子栏目需要展开
				 		if(haveChildcolumn(columnListJsonStr[i].categoryId)){
				 			columnExpansion(columnListJsonStr[i].categoryId);
				 		}
				 		
				 		
				 	}
				 }
				 
				 	//判断是否有数据，如果没有数据显示提示样式
				showDataEmptyPrompt();
			}
			
			//判断栏目是否还有子栏目需要展开 有返回true否则返回false
			function haveChildcolumn(categoryId){
				 for(var i=0;i<columnListJsonStr.length;i++){  //遍历展开的栏目集合	
				 	if(columnListJsonStr[i].categoryCategoryId==categoryId){
				 		return true;
				 	}
				 }	
				 return false;
			}
			
			//栏目展开
			columnExpansion(0);
			
			//栏目删除图标点击事件
			$("#columnListTable").on('click','a i.glyphicon-trash',function(){
				
				deleteColumnId = $(this).parent().parent().parent().children().eq(0).text();
				$.ajax ({
								type :"POST",
								dataType:"json",
								url : base+"/manager/cms/column/"+deleteColumnId+"/deleteConfirm.do",
								success:function(msg){  
									var columnJson = $.parseJSON(msg.resultMsg);
									if(columnJson==false){
										alert("请先删除子栏目");
									}else{
										warndeleteModel('确认要删除该栏目','栏目删除','deleteConfirm()');
									}
								}
						});
				
				
			
			});
			
			//封面栏目内容查看点击事件
			$("#columnListTable").on('click','a i.glyphicon-globe',function(){
				
				var ColumnId = $(this).parent().parent().parent().children().eq(0).text();
				window.open(base+"/manager/cms/generate/"+ColumnId+"/column.do");
			
			});
			
			//点击栏目编辑事件
			$("#columnListTable").on('click','a i.glyphicon-pencil',function(){
			
			var categoryId = $(this).parent().parent().parent().children().eq(0).text();
				location.href=base+"/manager/cms/column/"+categoryId+"/edit.do";
			});
			
			//栏目添加按钮
			$("#addColumnButton,.addColumnPrompt a").click(function(){
			 	location.href = base+"/manager/cms/column/add.do"; 
			 	
			});
				
			//打开子栏目操作
				$("#columnListTable").on('click','tr td span',function(){
					if($(this).attr("class")=="glyphicon glyphicon-folder-close"){
						$(this).attr('class','glyphicon glyphicon-folder-open');

						var categoryId = $(this).parent().parent().children().eq(0).text();
						$.ajax ({
							type :"POST",
							dataType:"json",
							 url : base+"/manager/cms/column/"+categoryId+"/childList.do",
							success:function(msg){  
								 var columnJson = $.parseJSON(msg.resultData);
								 
								 for(var i=0;i<columnJson.length;i++){
								 var str = '<tr class="'+
								  'columnChild'+
								 categoryId+
								 '"id="'+
								  'columnChild'+
								 columnJson[i].categoryId+
								 '"data-pId="';
								  
								  if(columnJson[i].categoryCategoryId==0){
								  	str=str+columnJson[i].categoryId;
								  }else{
								  	str=str+$("#columnChild"+columnJson[i].categoryCategoryId).attr("data-pId")+"/"+columnJson[i].categoryId;
								  }
								 
								  str=str+'"><td class="text-center">'+columnJson[i].categoryId+'</td <td class="col-md-2 text-left"> <td><i class="black'+columnJson[i].categoryId+'"></i><span  class="glyphicon glyphicon-folder-close"></span>&nbsp'+columnJson[i].categoryTitle+'</td><td>';
								  
								   if(columnJson[i].columnType==1){
								  	str=str+"列表";
								  }else{
								  	str=str+"封面";
								  }
								  	str=str+'</td><td>';
								  //栏目连接标签
						  		if(columnJson[i].categoryCategoryId==0){
						  			str=str+columnCoverStr+columnJson[i].categoryId+"/index.html";
						  		}else{
						  			str=str+columnCoverStr+$("#columnChild"+columnJson[i].categoryCategoryId).attr("data-pId")+"/"+columnJson[i].categoryId+"/index.html";
						  		}
								  str=str+'</td><td>';
								  
								  //列表模板地址
								  
								    if(columnJson[i].columnType==1){
								  	 if(columnJson[i].columnListUrl != ""){
								   		str=str+columnJson[i].columnListUrl;
								   	}else{
								   	str=str+"未添加模板";
								   }
								  }
								  str=str+'</td><td>';
								  //内容模板地址
								  if(columnJson[i].columnType==1){
								  	 if(columnJson[i].columnUrl != ""){
								   		str=str+columnJson[i].columnUrl;
								   	}else{
								   	str=str+"未添加模板";
								   }
								  }
								   str=str+'</td><td>';
								   
								   //封面模板地址
								  if(columnJson[i].columnType==2){
								  	if(columnJson[i].columnUrl != ""){
								   		str=str+columnJson[i].columnUrl;
								   	}else{
								   	str=str+"未添加模板";
								   }
								  }
								  str=str+'</td>';
								  str=str+'<td class="text-center">';
				                   str=str+ '<a class="btn btn-xs red tooltips del-btn" data-rid="" data-toggle="tooltip" data-original-title="删除" href="#" target="main" style="padding-left:0px; color:#428bca;" >'+
				                     '<i class="glyphicon glyphicon-trash"></i>'+
				                    '</a>'+
					                 '<a class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-original-title="修改" href="#" target="main" style="padding-left:0px; color:#428bca;" >'+
				                     '<i class="glyphicon glyphicon-pencil"></i>'+
				                    '</a>';
				                    if(columnJson[i].columnType==2){
								  		str=str+'<a class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-original-title="预览" href="#" target="main" style="padding-left:0px; color:#428bca;" >'+
				                     	'<i class="glyphicon glyphicon-globe"></i>'+
				                   		 '</a>';
								 	 }
				                    
									str=str+'</td></tr>';
								  $("#columnChild"+categoryId).after(str);
								  var paddstr = $("#columnChild"+categoryId).find("i").css('padding-left');
								  var paddint = parseInt(paddstr);
								  paddint += 20;
								  var newpadd = paddint + "px";
								   $(".black"+columnJson[i].categoryId).css('padding-left',newpadd);
								 }
					//显示小图标提示信息
				$("[data-toggle='tooltip']").tooltip();
								     
							}
							
						})	;					
					 		
						
					}else{
						
						closeChildColumn($(this).parent().parent());
					}
					
				});
				
					//显示小图标提示信息
				$("[data-toggle='tooltip']").tooltip();
				
			})
			
			//关闭子栏目的递归操作
			function closeChildColumn(columnId){
			 	
				var columnList = $("#columnListTable tr."+$(columnId).attr("id"));
				for(var index = 0; index < columnList.length; index++){  
				    var $container = $(columnList[index]); //循环遍历每一个dom节点  
				    closeChildColumn($container);
				}  
				$("#columnListTable tr."+$(columnId).attr("id")).remove();
				$(columnId).find("span").attr('class','glyphicon glyphicon-folder-close');
			}
			
		
		</script>
	
</body>
</html>
