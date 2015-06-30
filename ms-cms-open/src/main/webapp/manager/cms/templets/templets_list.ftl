<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
x
<body>
	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					模版管理
					<small>模版列表</small>
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-md-1 control-label col-xs-1">模版上传:</label>
					<div class="col-md-4 col-xs-4">
						<@uploadFile path="templets/${websiteId}" inputName="websiteStyle" size="10" filetype="zip" msg="建议上传30M以下的zip文件"  maxSize="30" callBack="setUrl" isRename="false"/>
					</div>
				</div>
			</form>
			<table class="table table-bordered">
				<thead>
		        	<tr>
		        		<td colspan="12" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	<a href="">栏目名</a>&nbsp;>&nbsp;<a href=""><strong>模版列表</strong></a>
		        		</td>
		        	</tr>
			        <tr>
			            <th class="col-md-1 text-center">图标</th>
			            <th class="col-md-8 text-center">模版名称</th>
			            <th class="col-md-2 text-center">类型</th>
			            <th class="col-md-1 text-center">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if folderNameList?has_content>
		           		<#list folderNameList as folderName>
				        	<tr> 
					            <td class="text-center"><span class='glyphicon glyphicon-folder-close'></span></td>
					            <td class="text-left">${folderName}</td>
					            <td class="text-center">文件夹</td>
					            <td class="text-center">
					            	<a class="btn btn-xs tooltips delete" data-toggle="tooltip" data-title="${folderName}" data-original-title="删除">
				                        <i class="glyphicon glyphicon-trash" style="color:#428BCA"></i>
				                    </a>				      
			                    	<a class="btn btn-xs tooltips" href="${base}/manager/cms/templet/showChildFileAndFolder.do?skinFolderName=${folderName}" data-toggle="tooltip" data-original-title="打开文件夹" >
			                     		<i class="glyphicon glyphicon-log-in" style="color:#428BCA"></i>
			                    	</a>			                    	
								</td>
					        </tr>
			        	</#list>
		           	<#else>		           	
			           	<td colspan="6" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a href="" class="alert-link">
			            			您还没有添加模版，点击添加模版
			            		</a>
						  	</p>
						</td>
		          	</#if>
		        </tbody>
			</table>
		</div>
	</div>

	<@showPage page=page/>
	<!--删除模版模态框-->
	<@warnModal modalName="deleteModel"/>
	
	<script type="text/javascript">
	   var name;
	   var fileUrl;
       function setUrl(e){
       	   if (e<0) {
       	 	  alert(e);
       	   } else {
       		fileUrl = e;
           	  $.ajax({
				   type: "post",
				   url: base+"/manager/cms/templet/unZip.do",
				   data: "fileUrl=" + fileUrl,
				   success: function(msg){ 
				   		alert("模版上传成功！ 	");
				     	location.href="${base}/manager/cms/templet/queryTempletSkin.do"
				   }
			  });            	   
       	   }
	   }
       
       //删除模版
		function deleteField(){
			$.ajax({
			   type: "post",
			   dataType: "json",
			   url:  base+"/manager/cms/templet/"+${websiteId}+"/"+name+"/delete.do",
			   data: "name=" + name + "&websiteId=" + ${websiteId},
			   success: function(msg){
			   	  if(msg){
			      	alert("删除模版成功");
			      	location.href="${base}/manager/cms/templet/queryTempletSkin.do"
			      } else {
			      	alert("删除模版失败");
			      	$(".modal-footer button").first().click();
			      }
			   }
			});
		}
	
	   $(function(){
	       //删除模版
			$("tbody").delegate(".delete","click",function(){
				name = $(this).attr("data-title");
				warndeleteModel("确定删除该模版吗？","删除模版","deleteField()");
			});	
	                   
	   }); 
	</script>
</body>
</html>

