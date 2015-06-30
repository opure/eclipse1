<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<body>
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					模块管理
					<small>模块列表</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row" >
			<!--功能按键部分 开始-->
			<div class="form-group">
				<!--新增按钮 开始-->
				<button  type="button" class="btn btn-success col-md " id="addModel"  >新增模块&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
			</div>
			<div class="alert alert-success" role="alert">
			模块编码:8位整型组成，00-00-00-00-00,表示：项目-模块-子功能-CURDO(查:0添:1删:2改:3等其他)
			<br/>
			特别说明： 通用模块编号：分类(99) 文章(98) 订单(97) 订单状态(96) 支付方式(95) 配送方式(94)  
			</div>
			<table class="table table-bordered" id="modelTable">
				<thead>
		        	<tr>
		        		<td colspan="6" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	模块列表
		        		</td>
		        	</tr>
			        <tr>
			            <th class="col-md-1 text-center">模块编号</th>
			             <th class="col-md-1 text-center">模块图标</th>
			            <th class="col-md-2 text-center">模块标题</th>
			            <th class="col-md-3 text-center" >模块编码(项目-模块-子功能-CURDO)</th>
			            <th class="col-md-3 text-center">模块链接地址</th>
			            <th class="col-md-2 text-center">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if parentModelList?has_content>
		           		<#list parentModelList as modelList>
		        	<tr id="modelChild${modelList.modelId?c?default(0)}">
			           
			            <td class="text-center" >${modelList.modelId?default(0)}</td>
			            <td class="text-center"><img width="25px" height="25px" src="${base}${modelList.modelIcon?default("/upload/model/wx.png")}"/></td>
			            <td class="text-left" class="modelTitle">
			            	<i class="black"></i>
			          		<span  class="glyphicon glyphicon-folder-close"></span>
			            	${modelList.modelTitle?default(0)}
			            </td>
			            <td class="text-center">${modelList.modelCode?default(0)}</td>
			            <td class="text-left">${modelList.modelUrl?default(0)}</td>
			            <td class="text-center">
		                    <a class="btn btn-xs red tooltips del-btn" data-toggle="tooltip" data-id="${modelList.modelId?c?default(0)}"  data-original-title="删除" href="#">
	                     		<i class="glyphicon glyphicon-trash"></i>
	                    	</a>
		                    <a class="btn btn-xs red tooltips editModel" data-rid="" data-toggle="tooltip" data-id="${modelList.modelId?c?default(0)}"  data-original-title="编辑" >
	                     		<i class="glyphicon glyphicon-pencil"></i>
	                    	</a>
						</td>
			        </tr>
			        </#list>
		           	<#else>
		           	<tr class="font15">
		           		
		           		<td colspan="5" class="text-center">
		            	<p class="alert alert-info" role="alert" style="margin:0">
		            		<span class="glyphicon glyphicon-plus" onclick="addModel()"></span>
		            		<a href="#" class="alert-link">
		            		您还没有添加模块哦!赶快来添加吧!
		            		</a>
					  	</p>
					</td> 
		           	</tr>
		          	</#if>
		        </tbody>
			</table>
		</div>
	</div>
	



	<!--增加/编辑管理员开始-->
	<div class="modal fade" id="modelForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body">
			  <form action="" class="form-horizontal" role="form" id="modelFrom">
			  	<!--模块标题--->
				<div class="form-group">
					
					<label class="col-md-4 control-label col-xs-3" for="managerName">模块标题:</label>
					<div class="col-md-4  col-xs-4">
						<div styel="display:none;" id="hideModelId"></div>
						<input type="text" class="form-control saveManagerName" placeholder="请输入模块标题" name="modelTitle" >
					</div>
				</div>
				<!--模块编码-->
				<div class="form-group">
					<label class="col-md-4 control-label col-xs-3" >模块编码:</label>
					<div class="col-md-4  col-xs-4">
						<input type="text" class="form-control" name="modelCode" placeholder="请输入模块编码">
					</div>
				</div>
				<!--模块图标-->
				<div class="form-group">
						<label class="col-md-4 control-label col-xs-3">模块图标:</label>
						<div class="col-md-5 uploadImg  col-xs-6">
							<@uploadImg path="upload/model" inputName="modelIcon" size="1" filetype="" msg="建议上传10M以下的图片" imgs="" maxSize="1"/>
						</div>
				</div>
				<!--模块链接地址-->
				<div class="form-group">
					<label class="col-md-4 control-label col-xs-3" >模块链接地址:</label>
					<div class="col-md-6  col-xs-6">
						<input type="text" class="form-control" name="modelUrl" placeholder="请输入模块链接地址">
					</div>
				</div>
				<!--模块父模块-->
				<div class="form-group">
				    <label for="selectRole" class="col-md-4 control-label col-xs-3">选择父模块:</label>
				    <div class="col-md-5 col-xs-5" id="trees">
				    	<div class="content_wrap">
							<div class="dropdown">
								<ul class="list">
									<li class="title">
										<label  role="button" data-toggle="dropdown" class="form-control menuBtn" onClick="showMenu($('#modelSel')); return false;">
											<span id="modelTitle" style="float: left;width: 85px;">请选择模块</span><span class="caret" style="float: right;margin-top: 8px;"></span>
										</label>
										<input id="modelSel" type="hidden"  value="" class="form-control modelModelTitle" />
										<input type="hidden" name="modelModelId" value="0"/>
									</li>
								</ul>
							</div>
						</div>
						<div id="menuContent" class="menuContent" style="display:none;" onmouseover="unbindTree()" onmouseout="bindTree()">
							<ul id="treeDemo" class="ztree" ></ul>
						</div>
				   </div>
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="saveOrUpdateBtn" class="btn btn-primary" style="float:right;">保存</button>
	        <button type="button" class="btn btn-default closeSaveModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	
	<!--增加管理员结束-->
	
	
	<!--删除模态框-->
	<@warnModal modalName="deleteWarn"/>
	<!--更新和新增弹出框-->
	<@modalDialog modalName="managerModel"/>
	<script>
		//引入表单验证框架
		var bootstrapValidator;
		
		//表单验证
		function bindValidate(){
		bootstrapValidator = 	$("#modelFrom").bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		            modelTitle: {
		                validators: {
		                    notEmpty: { message: '模块标题不能为空'},
		                    stringLength: {min: 2,max: 20,message: '模块标题长度介于2-20个字符'}
		                }
		            },
		            modelCode: {
		                validators: {
		                    notEmpty: {message: '模块编码不能为空'},
		                    stringLength: {min: 2,max: 20,message: '模块编码长度介于2-20个字符'}
		                }
		            },
		        }
		     });
		}
		
		//有关下拉列表的函数
		function beforeClick(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		
		//点击下拉列表中的值给对应的文本框赋值
		function onSaveClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			var parentId;
			var modelObj= $("input[name='modelModelId']");
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v = nodes[i].name;
				parentId=nodes[i].id;
			}
			e.stopPropagation();
			var cityObj = $("#modelSel");
			$("#modelTitle").html(v);
			modelObj.attr("value",parentId);
			cityObj.attr("value", v);
		}
		
		//实现下拉列表数据
		function showMenu(obj){
			var cityOffset = obj.offset();
			if($(".menuContent").css("display")=="none"){
				$(".menuContent").show("fast");
			}
		}
		
		function hideMenu() {
			$(".menuContent").fadeOut("fast");
		}
		
		function onBodyDown(event) {
			if (!(event.target.class == "menuBtn" || event.target.class == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		//展开子模块
		function modelExpansion(modelExpansionId){
			$.ajax({
					type:"post",
					dataType:"json",
					url:"${base}/manager/model/"+modelExpansionId+"/childlist.do",
					success:function(msg){
						var childModel = msg.chileModelList;
						for(var i =0;i<childModel.length;i++){
							var child='<tr class="'+'modelChild'+
								 modelExpansionId+
								 '"id="modelChild'+childModel[i].modelId+
								 '"><td class="text-center">'+childModel[i].modelId+
								 '</td><td class="text-center updateModelIcon"></td><td><i class="black'+childModel[i].modelId+'"></i><span class="glyphicon glyphicon-folder-close"></span>'+
								 childModel[i].modelTitle+'</td><td class="text-center updateModelCode">'+childModel[i].modelCode+'</td><td class="text-center updateModelUrl">'+childModel[i].modelUrl+'</td>';
							child=child+'<td class="text-center">'+
				                    '<a class="btn btn-xs red tooltips del-btn" data-rid="" data-toggle="tooltip" data-id="'+childModel[i].modelId+'" data-original-title="删除" href="#" target="main">'+
				                     '<i class="glyphicon glyphicon-trash"></i>'+
				                    '</a>'+
					                 '<a class="btn btn-xs red tooltips editModel" data-toggle="tooltip"  data-id="'+childModel[i].modelId+'" data-original-title="编辑" href="#" target="main">'+
				                     '<i class="glyphicon glyphicon-pencil"></i>'+
				                    '</a>'+
									'</td></tr>';
									$("#modelChild"+modelExpansionId).after(child);
									$("[data-toggle='tooltip']").tooltip();
							
							// 阶梯式显示子栏目
							$("#modelChild"+modelExpansionId).find("span").attr('class','glyphicon glyphicon-folder-open');
							var paddstr = $("#modelChild"+modelExpansionId).find("i").css('padding-left');
							var paddint = parseInt(paddstr);
							paddint += 20;
							var newpadd = paddint + "px";
							$(".black"+childModel[i].modelId).css('padding-left',newpadd);
						}
					}
				});
			
		}
		
		//树形菜单
		function unbindTree(){
			$("body").unbind("mousedown", onBodyDown);
        }
        function bindTree(){
        	$("body").bind("mousedown", onBodyDown);
        }
           
		//删除子模块函数
		function deleteModel(modelId){
			var actionUrl="${base}/manager/model/"+modelId+"/delete.do";
			$.ajax({
				type: "post",
				url:actionUrl,
				dataType:"json",
				success:function(msg){
					alert("删除成功");
					location.href="${base}/manager/model/list.do?modelId="+msg.resultMsg;
				}
			});
		}
		
		//关闭子模块
		function closeChildModel(modelId){
			var modelList =$("#modelTable tr."+$(modelId).attr("id"));
			for(var index = 0; index < modelList.length; index++){  
				    var $container = $(modelList[index]); //循环遍历每一个dom节点  
				    closeChildModel($container);
				}  
			$("#modelTable tr."+$(modelId).attr("id")).remove();
			$(modelId).find("span").attr('class','glyphicon glyphicon-folder-close');
		}
		
		//加载现有模块提供给下拉框用
		function loadModelForSelect(currentModelId,currentModel) {
				//页面加载时请求父模块数据
				$.ajax({
					type: "post",
					url:"${base}/manager/model/queryAll.do",
					dataType:"json",
					success:function(msg){
						var modelList = msg;
						var setting = {
									view: {
										dblClickExpand: false
									},
									data: {
										simpleData: {
											enable: true
										}
									},
									callback: {
										beforeClick: beforeClick,
										onClick: onSaveClick
									}	
						};
						var zNodes=new Array();
						var j = 1
						zNodes[0] ={pId:0,name:"顶级模块"};
						for(var i=0;i<modelList.length;i++){
							if(modelList[i].modelId==currentModelId){
								zNodes[j]={id:modelList[i].modelId,pId:modelList[i].modelModelId,name:"notSelectThisColumn",open:true};
							}else{
								zNodes[j]={id:modelList[i].modelId,pId:modelList[i].modelModelId,name:modelList[i].modelTitle,open:true};
							}
							j++;
						}
				 		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				 		//栏目不能选择自己及其子栏目为父栏目的事件
						$("a[title='notSelectThisColumn']").parent().on('click','a',function(event){
							event.stopPropagation();
							alert("不能选择该模块作为父模块");
						});
						//节点的值复制回去
						$("a[title=notSelectThisColumn]").children("span:last").html(currentModel);
						$("a[title=notSelectThisColumn]").attr("title",currentModel);
					}
			});			
		}
	
	
	$(function(){
			//根据模块Id展开模块
			<#if modelList?has_content>
           		<#list modelList as childModel>
           			var child='<tr class="modelChild${childModel.modelModelId?c?default(0)}" id="modelChild${childModel.modelId?c?default(0)}">'+
						 '<td class="text-center">${childModel.modelId?c?default(0)}'+
						 '</td><td class="text-center updateModelIcon"></td><td><i class="black${childModel.modelId}"></i><span class="glyphicon glyphicon-folder-close"></span>'+
						 '${childModel.modelTitle?default("")}</td><td class="text-center updateModelCode">${childModel.modelCode?default("")}</td><td class="text-center updateModelUrl">${childModel.modelUrl?default("")}</td>';
					child=child+'<td class="text-center">'+
		                    '<a class="btn btn-xs red tooltips del-btn" data-rid="" data-toggle="tooltip" data-id="${childModel.modelId?c?default(0)}"  data-original-title="删除" href="#" target="main">'+
		                     '<i class="glyphicon glyphicon-trash"></i>'+
		                    '</a>'+
			                 '<a class="btn btn-xs red tooltips editModel" data-toggle="tooltip" data-id="${childModel.modelId?c?default(0)}" data-original-title="编辑" href="#" target="main">'+
		                     '<i class="glyphicon glyphicon-pencil"></i>'+
		                    '</a>'+
							'</td></tr>';
					$("#modelChild${childModel.modelModelId?c?default(0)}").after(child);
					$("[data-toggle='tooltip']").tooltip();
					// 阶梯式显示子栏目
					var paddstr = $("#modelChild${childModel.modelModelId?c?default(0)}").find("i").css("padding-left");
					var paddint = parseInt(paddstr);
					paddint += 20;
					var newpadd = paddint + "px";
					$(".black${childModel.modelId?c?default(0)}").css("padding-left",newpadd);
					 //启用工具提示
					$("#modelChild${childModel.modelModelId?c?default(0)}").find("span").attr('class','glyphicon glyphicon-folder-open');
           		</#list>
		    </#if>
		
		//点击展开子栏目效果
		$("#modelTable").on('click','tr td span',function(){
			if($(this).attr("class")=="glyphicon glyphicon-folder-close"){
				$(this).attr("class","glyphicon glyphicon-folder-open");
				var modelId = $(this).parent().parent().children().eq(0).text();
				modelExpansion(modelId);
			}else{
				closeChildModel($(this).parent().parent());
			}
		});
		
		
		
		//栏目不能选择自己及其子栏目为父栏目的事件
		$("a[title='notSelectThisColumn']").on('click','span',function(event){
			alert("a");
			event.stopPropagation();
			alert("不能选择该栏目作为父栏目");
		});
		
		 //启用工具提示
		$("[data-toggle='tooltip']").tooltip();
		
		//点击删除按钮判断是否存在子模块
		$("#modelTable").on('click','a i.glyphicon-trash',function(){
			var modelId=$(this).parent().parent().parent().find("td:first").html();
			$.ajax({
				type: "post",
				url:"${base}/manager/model/"+modelId+"/isChildModel.do",
				dataType:"json",
				success:function(msg){
					if(msg==true){
						warndeleteWarn("请先删除子模块","");
					}else{
						warndeleteWarn("是否删除该模块？","删除模块","deleteModel("+msg+")");
					}
				}
			});
		});
		
		//编辑模块
		$("#editModelDialog").delegate("#editModel","click",function(){
			var formdata = $("#editModelFrom").serialize();
			$.ajax({
				type: "post",
				dataType:"json",
				url:  "${base}/manager/model/update.do",
				data: formdata,
				success:function(msg){
					if(msg.result){
						alert("更新成功");
				    	location.href="${base}/manager/model/list.do?modelId="+msg.resultMsg;
					}
				}
			});
		});
		
		//新增模块
		$("#addModel").on("click",function() {
			bindValidate();
			$("#saveBtn").html("保存");
			$("#modelFrom").attr("action","${base}/manager/model/save.do");
			$("#modelFrom input").val("");
			$("input[name='modelModelId']").val(0);				
			$("#modelTitle").html("请选择模板");		
			$("#modelForm").modal('show');
			$("#modelForm").find(".modal-title").html("新增模块");
			loadModelForSelect(0,0);
			showImgs("","modelIcon");
		});

		
		//点击编辑按钮，弹出编辑弹框
		$("#modelTable").delegate(".editModel","click",function() {
			bindValidate();
			$("#saveOrUpdateBtn").html("更新");
			$("#modelFrom").attr("action","${base}/manager/model/update.do");
			$("#modelForm").modal('show');
			$("#modelForm").find(".modal-title").html("更新模块");
			var currentModel = "";
			var currentModelId = 0;
		    var modelId=$(this).attr("data-id");
				//ajax加载表单数据
				$.ajax({
					type: "post",
					url:"${base}/manager/model/edit/"+modelId+".do",
					dataType:"json",
					success:function(msg){
						var model = msg.model;
						// 给表单赋值
						if(model!=null){
							$("#hideModelId").html("<input type='hidden' name='modelId'/>");
							currentModelId = model.modelId;
							currentModel = model.modelTitle;
							$("input[name='modelId']").val(model.modelId);
							$("input[name='modelIcon']").val(model.modelIcon);
							$("input[name='modelTitle']").val(model.modelTitle);
							$("input[name='modelCode']").val(model.modelCode);
							$("input[name='modelUrl']").val(model.modelUrl);
							$("input[name='modelModelId']").val(model.modelModelId);
							if(model.modelModelId==0){
								$("#modelTitle").html("顶级模块");
							}else{
								$("#modelTitle").html(msg.parentModel.modelTitle);
							}
							loadModelForSelect(currentModelId,currentModel);
							showImgs(model.modelIcon,"modelIcon");	
						}		
					}
				});
				$("#saveOrUpdateBtn").html("更新");
		});
		// 点击保存模块
		$("#saveOrUpdateBtn").on("click",function(){
			if(bootstrapValidator.data('bootstrapValidator').validate().isValid()){
				$.ajax({
					type: "post",
					url:$("#modelFrom").attr("action"),
					dataType:"json",
					data: $("#modelFrom").serialize(),
					success: function(msg){
				     	if (msg.result) {
				     		if($("#saveOrUpdateBtn").html()=="保存"){
				     			alert("保存成功");
				     		}else{
				     			alert("更新成功");
				     		}
				     		$("#saveOrUpdateBtn").attr("disabled",true);
				    		location.href="${base}/manager/model/list.do?modelId="+msg.resultMsg;
				    	}else{
				    		alert(msg);
				    	}
				    	
					}
				});
			}			
		});
		
					
	});
	</script>
	
</body>
</html>

