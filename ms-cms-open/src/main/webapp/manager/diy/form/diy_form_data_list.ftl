<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/>
</head>
<body>
	
	<div class="container-fluid link-style">
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-10">
				<h3 class="page-title bottomLine">
					${title?default('')}记录
					<small>记录列表</small>
				</h3>
			</div>
			<div class="col-md-2 col-xs-2 white padding0 ">
				<button class="btn btn-default pull-right" role="button" onclick="javascript:history.go(-1)">返回</button>
			</div>			
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row">
			<!--功能按键部分 开始-->
			
			<table class="table table-bordered">
				<thead>
		        	<tr>
		        		<td colspan="12" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	表单列表
		        		</td>
		        	</tr>
			        <tr>
			            <th class="text-center"  style="width:5%;">编号</th>
			           <th class="text-center"  style="width:80%;">数据</th>
						<th class="text-center" style="width:10%;">提交时间</th>			            	
			            <th class="text-center" style="width:5%;">操作</th>
			        </tr>
		        </thead>
		        <tbody>
		        	<#if list?has_content>
		           		<#list list as form>
		        	<tr> 
			           	<td class="text-center basicId">${form['id']?default(0)}</td>
			           	<td>
			           		<ul>
			           	<#list fields as field>
			            	<li><b style="color:#999">${field.diyFormFieldTipsName}:</b>${form['${field.diyFormFieldFieldName}']?default('')}</li>
			            </#list>
			            	</ul>
			            </td>
			            <td class="text-left" id="name">${form.date?default("")}</td>
			            <td class="text-left">
                    		<a class="btn btn-xs red tooltips del-btn " data-toggle="tooltip" data-original-title="删除" data-url="/manager/diy/form/${form['fromID']?default(0)}/${form['id']?default(0)}/delete.do">
	                     		<i class="glyphicon glyphicon-trash"></i>
	                    	</a>
						</td>
			        </tr>
			        </#list>
		           	<#else>
			           	<td colspan="12" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0">
			            		<span class="glyphicon glyphicon-info-sign"></span>
			            		暂无记录！！！
						  	</p>
						</td>
		          	</#if>
		        </tbody>
			</table>
		</div>
		<div class="container-fluid">
			<#if page?has_content>
				<@showPage page=page/>
			</#if>	
		</div>
	</div>
	<@warnModal modalName="Model"/>
</body>

</html>

