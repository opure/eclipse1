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
				<h3 class="page-title bottomLine">模版管理 <small>模版文件列表</small> </h3>
			</div>
			<div class="col-md-2 col-xs-2 text-right">
				<button class="btn btn-default " onclick="javascript:history.go(-1)">返回上一层</button>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row">
			<div class="form-group">
				<label class="col-md-1 control-label col-xs-1">文件上传:</label>
				<div class="col-md-4 col-xs-4">
					<@uploadFile path="templets/${uploadFileUrl}" inputName="file" size="20" filetype="*.htm;*.html;*.jpg;*.gif;*.png;*.css;*.js;*.ico;*.swf" msg="建议上传5M以下htm/html/css/js/jpg/gif/png/swf文件"  maxSize="5" callBack="test" isRename="false"/>
				</div>
			</div>
			<!--功能按键部分 结束-->
			<table class="table table-bordered">
				<thead>
		        	<tr>
		        		<td colspan="12" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	<a href="">栏目名</a>&nbsp;>&nbsp;<a href=""><strong>模版文件列表</strong></a>
		        		</td>
		        	</tr>
			        <tr>
			            <th class="col-md-1 text-center">图标</th>
			            <th class="col-md-8 text-center">文件名称</th>
			            <th class="col-md-2 text-center">类型</th>
			            <th class="col-md-1 text-center">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if fileNameList?has_content>
			           		<#list fileNameList as fileName>
					        	<tr> 
						            <td class="text-center pic"></td>
						            <td class="text-left name">${fileName}</td>
						            <td class="text-center type"></td>
						            <td class="text-center">			      
				                    	<a class="btn btn-xs tooltips delete" data-toggle="tooltip" data-title="${fileName}" data-original-title="删除">
					                        <i class="glyphicon glyphicon-trash" style="color:#428BCA"></i>
					                    </a>
					                    <a class="btn btn-xs tooltips editFileBtn" data-toggle="tooltip" data-title="${fileName}" data-original-title="编辑">
				                     		<i class="glyphicon glyphicon-pencil" style="color:#428BCA"></i>
				                    	</a>			                    	
									</td>
						        </tr>
				        	</#list>	  
			        	<#else>         	
			           	<td colspan="6" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a href="" class="alert-link">
			            			暂无模版文件
			            		</a>
						  	</p>
						</td>
					</#if>
		        </tbody>
			</table>
		</div>
	</div>
	
	<!--删除管理员模态框-->
	<@warnModal modalName="deleteModel"/>
	
	
	<script type="text/javascript">
		var fileName;
		var isSuccess = false;
		var fileUrl;
		var skinFolderName = "${uploadFileUrl?replace("\\","/")}";
		
	   var interval;  
       function run() {  
          interval = setInterval(chat, "1000");  
       }  
       function test(e){
            isSuccess = true;
            fileUrl = e;
			alert("模版文件上传成功 ");
	   }
       function chat() {  
       	  if(isSuccess){
       	  	  clearTimeout(interval);  //关闭定时器  
       	  	  var temp = $(".delete").attr("data-title");
       	  	  if(temp == null || temp == ""){
       	  	  	temp = $(".editFileBtn").attr("data-title");
       	  	  }
       	  	  if(temp != null && temp != ""){
       	  	  	temp = temp.substring(0,temp.lastIndexOf("/"));
       	  	  } else {
       	  	  	temp = skinFolderName.substring(skinFolderName.indexOf("/"));
       	  	  	temp = temp.substring(temp.indexOf("/"));
       	  	  }
       	  	  //若上传的为zip压缩文件则直接解压
       	  	  if($("#file").val().substring($("#file").val().lastIndexOf(".")+1) == "zip"){
	       	  	  $.ajax({
					   type: "post",
					   url: base+"/manager/cms/templet/unZip.do",
					   data: "fileUrl=" + fileUrl,
					   success: function(msg){ 
					     	location.href="${base}/manager/cms/templet/showChildFileAndFolder.do?skinFolderName="+temp;
					     	fileName = temp + fileUrl.substring(fileUrl.lastIndexOf("/"));
					     	deleteTempletsFile();
					   }
				  });
			  } else {
	     	 	 location.href="${base}/manager/cms/templet/showChildFileAndFolder.do?skinFolderName="+temp;
	     	  }
           }
       }
		
		//删除模版文件
		function deleteTempletsFile(){
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  base+"/manager/cms/templet/deleteTempletsFile.do",
			   data: "fileName=" + fileName,
			   success: function(msg){
			    	if(msg != 0) {
				    	$(".closeModal").click();
						alert("删除模版文件成功");
						fileName = fileName.substring(0,fileName.lastIndexOf("/"));
			    		location.href = base+"/manager/cms/templet/showChildFileAndFolder.do?skinFolderName="+fileName;
			    	} else {
						alert("删除模版文件失败");
				    	$(".closeModal").click();
			    	}
			   }
			});
		}
		
		$(function () {	
			//加载页面时启动定时器  
	        run(); 
	        
	        //若为文件夹，则左侧显示文件夹图标；若为文件，则左侧显示文件图标，且稍向右偏移
	       $(".name").each(function(i){
	        	var temp = $(this).html();
	        	if(temp.indexOf(".") >= 0){
	        		$(this).parent().find(".pic").prepend("<span class='glyphicon glyphicon-file'></span>")
	        		$(this).parent().find(".type").prepend("文件")
	        	} else {
	        		$(this).parent().find(".pic").prepend("<span class='glyphicon glyphicon-folder-close'></span>")
	        		$(this).parent().find(".type").prepend("文件夹")
	        		$(this).parent().find(".editFileBtn").attr("data-original-title","打开文件夹");
	        		$(this).parent().find(".editFileBtn").find(".glyphicon").removeClass("glyphicon-pencil");
	        		$(this).parent().find(".editFileBtn").find(".glyphicon").addClass("glyphicon-log-in");	        		
	        		$(this).parent().find(".delete").remove();
	        	}
	        	var suffix = temp.substring(temp.lastIndexOf(".")+1);
				if(suffix == "jpg" || suffix == "gif" || suffix == "png"){
					$(this).parent().find(".type").prepend("图像")
					$(this).parent().find(".editFileBtn").remove();
				}
	        });

		/*
	        //动态为返回上一层按钮添加链接地址；若不存在文件，则返回上上层；若不存在文件，则返回上上层
	        if($("tbody").find(".name").size() != 0 || $("tbody").find(".delete").size() != 0){
	        	//若上一层为模版文件夹层，测直接返回至模版列表页面；否则返回至当前层的上一层
		        if($(".name").first().text().substring(0,$(".name").first().text().indexOf("/")) == skinFolderName.substring(skinFolderName.lastIndexOf("/")+1)){
		        	$(".back").parent().attr("href",'${base}/manager/cms/templet/queryTempletSkin.do');
		        } else if($(".name").text().substring(1,$(".name").text().indexOf("/")) == skinFolderName.substring(skinFolderName.lastIndexOf("/")+1)){
		        	$(".back").parent().attr("href",'${base}/manager/cms/templet/queryTempletSkin.do');
		        } else if($("tbody").find(".delete").size() == 0){     //仅剩下文件夹
		        	fileName = $(".editFileBtn").attr("data-title").substring(0,$(".editFileBtn").attr("data-title").lastIndexOf("/"));
		        	fileName = fileName.substring(0,fileName.lastIndexOf("/"));
		        	$(".back").parent().attr("href",'${base}/manager/cms/templet/showChildFileAndFolder.do?skinFolderName='+fileName);
		        }else {
		        	fileName = $(".delete").attr("data-title").substring(0,$(".delete").attr("data-title").lastIndexOf("/"));
		        	if(fileName.substring(0,fileName.lastIndexOf("/")).length != 0){
		        		fileName = fileName.substring(0,fileName.lastIndexOf("/"));
		        	}
		        	$(".back").parent().attr("href",'${base}/manager/cms/templet/showChildFileAndFolder.do?skinFolderName='+fileName);
		       }
	        } else {
	        	//若不存在文件，则返回上上层
	        	fileName = skinFolderName.substring(0,skinFolderName.lastIndexOf("/"));
	        	fileName = fileName.substring(fileName.indexOf("/")+1);
	        	$(".back").parent().attr("href",'${base}/manager/cms/templet/showChildFileAndFolder.do?skinFolderName='+fileName);
	        }
	    */	
	    	
	        
	        //编辑模版文件，若为文件夹，则跳至显示子文件和子文件夹action；若为文件，则直接读取代码内容，且跳至模版文件修改页面
			$("tbody").delegate(".editFileBtn","click",function(){
				fileName = $(this).attr("data-title");
		        if(fileName.indexOf(".") >= 0){
		        	location.href = base+"/manager/cms/templet/readFileContent.do?fileName="+fileName;
		        } else {
					location.href="${base}/manager/cms/templet/showChildFileAndFolder.do?skinFolderName="+fileName;
				}
			});
	        
			//删除模版文件
			$("tbody").delegate(".delete","click",function(){
				fileName = $(this).attr("data-title");
				warndeleteModel("确定删除该模版文件吗？","删除模版文件","deleteTempletsFile()");
			});
		});
	</script>
</body>
</html>

