<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<style>
	.container{margin:0;padding:0;width:auto}
	hr{margin-top:9px;margin-bottom:9px;padding:0;}
	.rowpadding3{padding-bottom: 3px;}
	.ms-button-group{padding:0px 0px 8px 0px}
	.row {margin-left:0;margin-right:0}
	.form-horizontal .form-group{margin-left:0;margin-right:0}
	.form-group{overflow: hidden;}
	.bs-example>.dropdown>.dropdown-menu {position: static;margin-bottom: 5px;clear: left;}
	.bs-example>.dropdown>.dropdown-toggle {float: left;}
	.padding-zero{padding:0;}
	.link-style a{color:black;}
	.form-inline .form-group {display: inline-block;margin-bottom: 0;vertical-align: middle;}
	.dedeteRight{width: 32%;margin: 0 auto;overflow: hidden;}
	/*弹出窗口样式*/
	#WindowDialog .modal-dialog{width:auto;}
	.has-error .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#A94442;}
	.has-success .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#3C763D;}
</style>
<body>
	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-10">
				<h3 class="page-title bottomLine">模版管理 <small>编辑模版文件</small> </h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default" role="button" onclick="javascript:history.go(-1)">返回上一层</button>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row">
			<form class="form-horizontal" id="editFileForm">
				<div class="form-group">
					<label class="col-md-1 control-label col-xs-1">文件名称:</label>
					<div class="col-md-4  col-xs-4">
						<input type="text" class="form-control" name="fileName" value="${fileName}">
					</div>
				</div>
				<input type="hidden" class="fileName" value="${fileName}">
				<input type="hidden" class="fileNamePrefix" value="${fileNamePrefix}">
				<div class="form-group">
					<label class="col-md-1 control-label col-xs-1">文件内容:</label>
					<div class="col-md-10  col-xs-10">
						<textarea class="form-control" rows="21" name="fileContent">${fileContent?html}</textarea>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label col-xs-1"></label>
				<button type="button"class="btn btn-success btn-md update" style="margin-left:15px;">修改&nbsp;</button>
				<button type="button" class="btn btn-default btn-md" onclick="javascript:history.go(-1)">取消</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		
		$(function () {	
			//修改文件
			$(".update").on("click",function(){
				var formData = $("#editFileForm").serialize();
				$.ajax({
				   type: "post",
				   dataType: "json",
				   url:  base+"/manager/cms/templet/writeFileContent.do",
				   data: formData + "&oldFileName=" + $(".fileName").val() + "&fileNamePrefix=" + $(".fileNamePrefix").val(),
				   success: function(msg){
				    	if(msg.result){
				    		alert("修改模版文件成功");
				    	} else {
				    		alert("修改模版文件失败");
				   	  	}
				   	  	var fileNameUrl = $(".fileNamePrefix").val();
				   	  	//location.href = base+"/manager/cms/templet/showChildFileAndFolder.do?skinFolderName=" + fileNameUrl;
				   } 
				});
			});
		});
	</script>
</body>
</html>

