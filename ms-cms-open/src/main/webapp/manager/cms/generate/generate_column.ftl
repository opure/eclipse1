<!DOCTYPE html>
<html lang="en">
<head>
<!--调用head内样式信息-->
<#include "/manager/include/meta.ftl"/>
</head>
<style>
	.container{margin:0;padding:0;width:auto}
	hr{margin-top:9px;margin-bottom:9px;padding:0;}
	.rowpadding3{padding-bottom: 3px;}
	.ms-button-group{padding:0px 0px 8px 0px}
	.row {margin-left:0;margin-right:0}
	.link-style a:hover{color:#000;}
	.link-style a:visited{color:#000;} 
	.padding0{padding:0}
		label{padding: 0;
				margin: 0;
				line-height: 33px;
				font-weight: 300;
	}
	body{
		min-height: 600px;
	}
	div #menuBtn{
		cursor: pointer;
		background: white;
		}
	#menuContent {
		overflow: auto;
		max-height: 240px;
		width: 88%;
		display: none;
		z-index: 999;
		position: absolute;
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
</style>

<body>
	<!----------------------------------- bootstarp 布局容器 开始-------------------------------->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					一键更新栏目
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<div class="row ">
			<div class="col-md-12 col-xs-12" >
					<div class="form-group">
						<p class="alert alert-info" role="alert" style="margin:0">
				            		<span class="glyphicon glyphicon-pushpin text-lef "></span>
				            		<a  class="alert-link text-lef"  style="margin-left: 12px;">
				            			更新栏目列表，推荐使用指定栏目更新。系统提示“更新中...”请不要刷新页面或点击其他菜单。
				            		</a>
						</p>
					</div>
					<div class="form-group" style="height: 37px;">
							<label for="" class="control-label  col-md-1 col-xs-1" style="padding: 0;">选择栏目：</label>
							<div class="col-md-2 col-xs-2" style="padding: 0;">
									<ul class="list">
											<li class="title">
												<div style="position: relative;">
													<input id="menuBtn" role="button" data-toggle="dropdown"  placeholder="请选择栏目"  maxlength="20" class="form-control" onClick="showMenu(); return false;"  value="" readonly/>
													<span class="caret" style="margin-top: 8px;position: absolute;top: 8px;right: 14px;"></span>
												</div>
												<input type="hidden" name="columnId" value="0" />
												<div id="menuContent" class="menuContent" style="display:none;">
													<ul id="treeDemo" class="ztree" style="margin-top:0; ">
													</ul>
												</div>
											</li>
									</ul>
							</div>
							<div class="col-md-9 col-xs-9" style="padding: 0;">
							</div>
					</div>
					
	
					<div class="form-group text-left">
							<label for="" class="control-label  col-md-1 col-xs-1" style="padding: 0;"></label>
							<div class="col-md-2 col-xs-2" style="padding: 0;">					
								<button type="button" class="btn btn-primary" id="updateColumn">更新栏目</button>
								<label class="showMessage " role="alert" for="startUpdate" style="display:none">更新完成！</label>
							</div>
					</div>					
					
			</div>
<script>
		//有关下拉列表的函数
		function beforeClick(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			var parentId;
			var modelObj= $("input[name='columnId']");
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				parentId=nodes[i].id;
				type=nodes[i].type;
			}
			
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			//if(parentId.length>0) parentId=parentId.substring(0, parentId.length-1);
			var cityObj = $("#menuBtn");
			//栏目类型
			modelObj.attr("value",parentId);
			cityObj.attr("value",v);
		}

		function showMenu(){
			var cityObj = $("#modelSel");
			var cityOffset = $("#modelSel").offset();
			$("#menuContent").slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
</script>
<script>
	$(function() {
	
			
		// ajax 给选择父模块下拉框赋值
		$.ajax({
			type: "post",
			url:base+"/manager/cms/column/queryJsonAll.do",
			dataType:"json",
			success:function(msg){
				var modelList =  $.parseJSON(msg.resultData);
				//获取栏目名
				setting = {
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
						onClick: onClick
					}
				};
				var zNodes=new Array();
				for(var i=0;i<modelList.length;i++){
					zNodes[i]={id:modelList[i].categoryId, pId:modelList[i].categoryCategoryId, name:modelList[i].categoryTitle,type:modelList[i].columnType ,open:true}
				}
		 		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			},
		});
		$("#updateColumn").click(function(){
				var columnId=$("input[name='columnId']").val();
				$('.showMessage').hide();
				$(this).html("更新中..").attr("disabled","disabled");
				$.ajax({
					type: "post",
					url:"${base}/manager/cms/generate/"+columnId+"/genernateColumn.do",
					dataType:"json",
					success:function(msg){
						if(msg){
							alert("更新栏目成功");
						}else{
							alert("更新栏目失败");
						}
						$("#updateColumn").html("更新栏目").removeAttr("disabled");
						$('.showMessage').hide();
					}
				});
		});
		
	});
</script>
</body>
</html>