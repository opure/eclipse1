<!DOCTYPE html>
<html lang="en">
<head>
<#include "/manager/include/meta.ftl"/> <!--调用head内样式信息-->
<script type="text/javascript" src="${base}/jquery/zTree_v3/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="${base}/jquery/zTree_v3/zTreeStyle.css" type="text/css">
</head>
<body>
	<!----------------------------------- bootstarp 布局容器 开始-------------------------------->	
	<div class="container-fluid link-style">
		<div class="row">
			<div></div>
			
			<!--右侧内容编辑区域开始-->
			<diiv class="col-">
			
		<!--顶部   开始-->
		<div class="row rowpadding3">
			<div class="col-md-12">
				<h3 class="page-title bottomLine">
					<#if categoryTitle?has_content>${categoryTitle}<#else>全部</#if><small><#if articleShow?has_content>列表<#else>查询结果</#if></small>		            
				</h3>
			</div>
		</div>
		<!--顶部  结束-->
		<hr>
		<!--------内容 部分  开始-------->
		<div class="row" id="articlePage">
			<!--功能按键部分 开始-->
			<div class="form-group">
				<button type="button" class="btn btn-success col-md" id="addArticleButton">新增文章&nbsp;<span class="glyphicon glyphicon-plus"></span></button>
				<button type="button" class="btn btn-danger btn-md" id="allDelete">批量删除 <span class="glyphicon glyphicon-trash"></button>
			</div>
			<!--功能按键部分 结束-->
			<!--搜索框  开始-->
			<div class="form-inline form-group searchForm" role="form">

				<div class="form-group">
					<select class="form-control attribute" >
						<option value="" >默认属性</option>
						<#if articleTypeList?has_content>
		        			<#list articleTypeList as at>
		        				<option value="${at.key}" <#if articleType?has_content && articleType==at.key>selected</#if>>${at.value}</option>
		        			</#list>
		        		</#if>
					</select>
				</div>
				<div class="form-group">
					<label for="kayword" class="sr-only">关键字</label>
					<input type="text" class="form-control" name="keyword" placeholder="输入关键字" value="${keyword?default('')}">
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-primary" id="search">搜索</button>
				</div>
			 </div>	
			<!--搜索框的使用 结束-->
			
			<!--列表 开始-->
			<table class="table table-bordered">
				<!--表格栏目属性 开始-->
		        <thead>
		        	<tr>
		        		<td colspan="8" class="text-left">
	                     	<i class="glyphicon glyphicon-pushpin"></i>
	                     	<a href="">栏目名</a>&nbsp;>&nbsp;<a href=""><strong>文章列表</strong></a>
		        		</td>
		        	</tr>
			        <tr>
			        	<th class="col-md-1 text-center">
			        			<input type="checkbox" id="allCheck" value="全选" data-original-title="点击全选" data-toggle="tooltip">
			        	</th>
			        	<th class="col-md-1 text-center" >ID</th>
			        	<th class="col-md-2 text-center" >栏目名</th>
			            <th class="col-md-4 text-left" >文章标题</th>
			            <th class="col-md-1 text-center" >作者</th>
			            <th class="col-md-1 text-center" >点击</th>
			            <th class="col-md-1 text-center" >排序</th>
			            <th class="col-md-1 text-center">操作</th>
			        </tr>
		        </thead>
		        <!--表格栏目属性 结束-->
		        <!--表格内容  开始-->
		        <tbody>
		        <#if listArticle?has_content>
		        	<#list listArticle as listArticle>
		        	<tr id="tableArticle">
		        	<td class="text-center">
							<input type="checkbox" name="checkbox" value="${listArticle.basicId?c?default(0)}">
		            </td>
		        	<td class="text-center articleId" >${listArticle.basicId?c?default(0)}</td>
		        	<td class="text-center" ><#if listArticle.column?exists>${listArticle.column.categoryTitle?default("")}</#if></td>
		            <td class="text-left" >
		            	<span class="updateArticle" data-toggle="tooltip"  data-original-title="点击修改文章" data-id="${listArticle.basicId?c?default(0)}">
		            		<#if keyword?has_content>
		            			${listArticle.basicTitle?default("无标题")?replace(keyword,"<font color='red'>"+keyword+"</font>")}
		            		<#else>
		            			${listArticle.basicTitle?default("无标题")}
		            		</#if>
		            	<span style="color:red">
		            	<#if articleTypeList?has_content>
		        			<#list articleTypeList as at>
		        				
		        				<#if at.key?default("")?string?trim == listArticle.articleType?default("")?string?trim?replace(","," ")>
		        					[${at.value}]
  									<#break>
  								</#if>
		        			</#list>
		        		</#if>
		        		</span>
		            	</span>
		            </td>
		            <td class="text-center" >${listArticle.articleAuthor?default("无作者")}</td>
		            <td class="text-center" >${listArticle.basicHit?c?default(0)}</td>
		             <td class="text-center" >${listArticle.articleFreeOrder?c?default(0)}</td>
		            <td class="text-center" >
	                    <a class="btn btn-xs red tooltips del-btn deleteArticle" data-toggle="tooltip" data-original-title="删除" data-id="${listArticle.basicId?c?default(0)}">
	                     <i class="glyphicon glyphicon-trash"></i>
	                    </a>
	                    <a class="btn btn-xs red tooltips  updateArticle" data-toggle="tooltip"  data-original-title="修改" data-id="${listArticle.basicId?c?default(0)}">
	                     <i class="glyphicon glyphicon-pencil"></i>
	                    </a>
	                    <!--<a class="btn btn-xs red tooltips del-btn preview" data-toggle="tooltip"  data-original-title="预览" data-id="${listArticle.basicId?c?default(0)}">
	                     <i class=" glyphicon glyphicon-globe"></i>
	                    </a>-->
					</td>
		          </tr>
		           </#list>
		           <#else>
		            <#if articleShow?has_content>
		             	<tr>
			            <td colspan="12" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		<a href="${base}/manager/cms/article/add.do" class="alert-link">
			            		您还没有添加文章，点击添加文章
			            		</a>
						  	</p>
						</td>
			          </tr>
			           <#else>
			          <tr>
			            <td colspan="7" class="text-center">
			            	<p class="alert alert-info" role="alert" style="margin:0">
			            		<span class="glyphicon glyphicon-plus"></span>
			            		没有搜索到文章！  
						  	</p>
						</td>
			          </tr>
		         	</#if>	
		        </#if>
			</tbody>
		</table>
	</div>
	<!--列表结束-->
	<!--分页样式 开始-->
	<div class="container-fluid">
		<#if page?has_content>
			<@showPage page=page/>
		</#if>	
	</div>
	<!--分页样式 结束-->			
			
			</div>
			<!--右侧内容编辑区域结束-->
		
		</div>
	
	</div>

	
	<!--引用弹出框插件-->
	<@warnModal modalName="Model"/>
	<!--JQ特效部分-->
	<script>
				
		//有关下拉列表的函数
		function beforeClick(treeId, treeNode) {
			var check = (treeNode);
			return check;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v="";
			var parentId;
			var modelObj= $("input[name='basicCategoryId']");
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v = nodes[i].name;
				parentId=nodes[i].id;
			}		
			var cityObj = $("#menuBtn");
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
		//确认删除
		function right(id){
			$.ajax({ 
				type: "POST", 
				url: base+"/manager/cms/article/"+id+"/delete.do",
				dataType:"Json",
    			success: function(msg){
    				if(msg.result){
						location.href="${basePath}/"+msg.resultData;    				
    				}else{
    					alert("没有找到该文章！")
    				}
    			},
    		});
		} 
		
		$(function(){	
			//预览文章
			$("#articlePage").delegate("table tr .preview","click",function(){
				var articleId = $(this).attr("data-id");
				window.open(base+"/manager/cms/generate/"+articleId+"/article.do");
			});
			
			//删除文章
			$("#articlePage").delegate("table tr .deleteArticle","click",function(){
				var articleId = $(this).attr("data-id");
				warnModel("确定要删除该文章？","删除文章","right("+articleId+")");
			});
			
			//添加文章
			$("#articlePage").delegate("#addArticleButton","click",function(){
				location.href = base+"/manager/cms/article/add.do?categoryId=${categoryId?default(0)}&categoryTitle=${categoryTitle?default(0)}"; 
			});
			
			//编辑文章
			$("#articlePage").delegate("table tr td .updateArticle","click",function(){
				var id = $(this).attr("data-id");
				location.href = base+"/manager/cms/article/"+id+"/edit.do";
			});
			
			//返回文章列表
			$("#articlePage").delegate("#returnButton","click",function(){
				location.href = base+"/manager/cms/article/list.do";
			});
			
			//点击搜索进行文章搜索
			$("#articlePage").delegate("#search","click",function(){
				var columnId=$('[name="basicCategoryId"]').val();
				var articleType=$(".searchForm .attribute").val();
				var keyword =  $('[name="keyword"]').val();
				
				location.href = base+"/manager/cms/article/${categoryId?default(0)}/list.do?keyword="+keyword+"&articleType="+articleType;
			});
		    //启用工具提示
		   	$("[data-toggle='tooltip']").tooltip();
		   	
		   	//全选
		   	$("#allCheck").click(function(){  
				if(this.checked){   
					$("input[name='checkbox']").each(function(){this.checked=true;});
				}else{   
					$("input[name='checkbox']").each(function(){this.checked=false;});   
				}
			}); 
			
			//多选删除
			$("#allDelete").click(function(){
					warnModel("确定要删除这些文章？","批量删除文章","allRight()");
			});
		});
		
		//批量删除
		function allRight(){
		//将checkbox序列化
				var checkboxData = $("input[name='checkbox']").serialize();
				if(checkboxData!=""){
					$.ajax({ 
						type: "POST", 
						url: base+"/manager/cms/article/allDelete.do",
						data: checkboxData,
						dataType:"Json",
		    			success: function(msg){
		    				
		    				if (msg.result) {
		    					alert("批量删除成功！")
		    					location.href="${basePath}/"+msg.resultData; 
		    				} else {
		    					alert("批量删除失败");
		    				}
		    				    				
		    			},
		    		});
		    	}else{
		        	alert("请选择文章！");
		        }
		} 
	</script>
</body>
</html>
