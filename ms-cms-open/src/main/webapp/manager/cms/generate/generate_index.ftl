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
	.form-group{overflow: hidden;}
	.link-style a:hover{color:#000;}
	.link-style a:visited{color:#000;}
	.padding0{padding:0} 
	label{
		padding: 0;
		margin: 0;
		line-height: 33px;
		font-weight: 300;
	}
</style>
、
<body>
	<!----------------------------------- bootstarp 布局容器 开始-------------------------------->	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					更新主页
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
				            		更新主页，如果系统存在引导页面可以手动修改主页位置文件名,default.html引导页面index.html主页。
				            	</a>
						</p>
					</div>
					<div class="col-md-7 col-xs-7" style="padding: 0;" >
						<div class="form-group text-left">
								<label for="" class="control-label  col-md-2 col-xs-2" style="padding: 0;">选择主页模板：</label>
								<div class="col-md-4 col-xs-4" style="padding: 0;">
									<select class="form-control" id="select_id"></select>
									<input type="hidden" name="url"/>
								</div>
								<div class="col-md-8 col-xs-8" style="padding: 0;"></div>
						</div>
						 
						<div class="form-group">
							<label for="" class="control-label  col-md-2 col-xs-2" style="padding: 0;">选择主页位置：</label>
							<div class="col-md-2 col-xs-2" style="padding: 0;">
									<input type="text" class="form-control" value="index.html" name="position">
							</div>
							<div class="col-md-1 col-xs-1 "  style="padding: 0;height:20px;"></div>
							<div role="alert" class="col-md-7 col-xs-7" style="line-height:30px;">注:主页位置htm文件名一般为index.html或default.html</div>
						</div>
						<div class="form-group text-left">
								<div class="col-md-2 " style="padding: 0;">
									
								</div>
								<div class="col-md-2" style="padding: 0;">
								<button type="button" class="btn btn-primary" id="updateIndex">更新主页</button>
								</div>
								<div class="col-md-6" style="padding:0; display:none;" id="view">
									<button type="button" class="btn btn-success" id="viewIndex">预览主页</button>
								</div>
						</div>
						
						
						
					</div>
					<div class="col-md-5 col-xs-5" style="padding:0;" ></div>
				</div>
			</div>
		</div>
		<script>
			$(function(){
				//点击一键更新主页时，进行主页更新
				$("#updateIndex").click(function(){
					var url = $("#select_id").val();
					$("#view").hide();
					var position =$("input[name='position']").val();
					$(this).html("更新中..")
					$.ajax({
						type: "post",
						url:"${base}/manager/cms/generate//generateIndex.do",
						data:"url="+url+"&position="+position,
						dataType:"json",
						success:function(msg){
							if(msg){
								$("#view").show();
								alert("更新成功");
							}else{
								alert("更新失败,模版路径不存在");
							}
							$("#updateIndex").html("更新主页")
						}
					});
					
				});
				//点击预览时，进行预览
				$("#viewIndex").click(function(){
					var position =$("input[name='position']").val();
					window.open("${base}/manager/cms/generate/"+position+"/viewIndex.do");
				});
				
				//获取模板下的htm文件
				$.ajax({
						type: "post",
						url:"${base}/manager/cms/templet/queryTempletFileForColumn.do",
						dataType:"json",
						success:function(msg){
							if(msg.length==0){
								$("#select_id").append("<option value='' >暂无文件</option>")
							}
							for(var i = 0;i<msg.length;i++){
								$("#select_id").append("<option value="+msg[i]+">"+msg[i]+"</option>");
								//如果存在index.html
								if(msg[i]=="index.html" || msg[i]=="index.htm"){
									$("#select_id").find("option[value='"+msg[i]+"']").attr("selected",true);
								}
							}
							
						}
				});
			});
		</script>
</body>
</html>