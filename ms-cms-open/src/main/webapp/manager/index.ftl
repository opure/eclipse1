<!DOCTYPE html>
<html lang="zh">
 <head>
  <base target="main" />
  <title><#if app?has_content>${app.basicTitle}<#else>MS</#if>管理系统</title>
  <sciprt src="http://localhost:8080/ms-upgrader/upgrader/update"></script>
<link rel="shortcut icon" href="favicon.ico"/>     
<link rel="bookmark" href="favicon.ico"/> 
  <#include "/manager/include/macro.ftl"/>
  <#include "/manager/include/meta.ftl"/>	
  <style>  	
	.has-error .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#A94442;}
	.has-success .form-control-feedback{float:right; margin-top:-24px; margin-right:5px; color:#3C763D;}
  </style>
 </head> 
 <body>
 <div class="ms-skin-container "></div>
  <div class="container  ms-container"> 
   <div class="pull-left text-center ms-m-iconmenu"> 
    <div class="ms-logo">
     	<img src="${base}/images/logo.png" />
    </div> 
    <ul id="modelIcon"> 
     		
    </ul> 
    <!--div class="ms-poweroff  loginOutBtn" data-toggle="tooltip" data-placement="top"   title="退出系统"></div-->
   </div> 
   <div class="pull-left ms-m-menu"> 
    <h3></h3> 
    <ul id="modelChild">
    </ul>
   </div> 
   <div class="pull-left ms-m-body"> 
    <div class="header"> 
     <div class="pull-left user"> 
      <span class="glyphicon glyphicon-user " style="text-shadow: 0 0 2px #000;"></span> <span style="text-shadow: 0 0 2px #000;">${managerSession.managerName}</span> 
      <!-- Single button --> 
      <div class="btn-group" > 
       <div type="button" class=" dropdown-toggle" data-toggle="dropdown" style="cursor:pointer;" > 
        <span class="glyphicon glyphicon-cog " style="text-shadow: 0 0 2px #000;"></span><span style="text-shadow: 0 0 2px #000;">设置</span> 
        <span class="caret"></span> 
       </div> 
       <ul class="dropdown-menu" role="menu"> 
         <li></span><a class="updatePassword" style="cursor:pointer;" ><span class="glyphicon glyphicon-cog " > 修改密码 </a></li>     
          <li></span><a  class="backgroundImg" style="cursor:pointer;" ><span class="glyphicon glyphicon-picture "> 切换背景</a></li>       
       </ul> 
      </div> 
      <span class="glyphicon glyphicon-home" style="text-shadow: 0 0 2px #000;"></span> <span style="text-shadow: 0 0 2px #000;cursor: pointer;" onclick="top.location.href='${base}/manager/index.do'">管理主界面</span>
      <span class="glyphicon glyphicon-home" style="text-shadow: 0 0 2px #000;"></span> <a href="/" target="_blank">网站首页</a></span>
     </div> 
     <div class="pull-right" style="font-size:24px;margin-top:-5px">  
	         	<span class="glyphicon glyphicon-refresh websiteUpdate"  data-toggle="tooltip" data-placement="bottom"  title="检测更新"></span>
	         	<span class="glyphicon glyphicon-unchecked loginOutBtn"  data-toggle="tooltip" data-placement="bottom"  title="退出系统"></span>
     </div> 
    </div> 
    <div class="frame" style="opacity: 0.95;">
    	<#if managerModelPage?has_content>
     	<iframe id="mainFrame" src="${basePath}/${managerModelPage.managerModelPageUrl?default("/manager/page.html")}" frameborder="0" width="99%" name="main" style="height: 95%;"></iframe>
     	<#else>
     	<iframe id="mainFrame" src="${basePath}/manager/page.html" frameborder="0" width="99%" name="main" style="height: 95%;"></iframe>
     	</#if>
    </div>
   </div>
  </div>
<script id='modelIconTmpl'  type='text/x-jquery-tmpl'>
	<#noparse>
		<li  data-id="${modelId}" data-name="${modelTitle}" >
		<img class="img-rounded" src="${modelIcon}"    data-toggle="tooltip" data-placement="left" title="${modelTitle}" />
		<h5>${modelTitle}</h5>
		</li>
	</#noparse> 
</script>
<script id='modelChildTmpl'  type='text/x-jquery-tmpl'>
	<#noparse>
		<li><a href='${basePath}/${modelUrl}?modelId=${modelId}&modelTitle=${title}'  target='main'>${modelTitle}</a></li>
	</#noparse> 
</script>
	<!--退出系统模态框-->
	<@warnModal modalName="loginOut"/>
	<!--修改登录密码模态框-->
	<@modalDialog modalName="editLoginPassword"/>
	<@modalDialog modalName="BackgroundImg"/>
  <script>
  	var modelJson = ${modelList};
  	
  	$(function() {
  			for (i=0;i<modelJson.length;i++) {
  				if (modelJson[i].modelModelId==0) {
  					$('#modelIconTmpl').tmpl(modelJson[i]).appendTo('#modelIcon');
  				}
  			}
  			
  			$("#modelIcon").delegate("li","click",function(){
			  	$(".ms-m-menu").children("h3").html($(this).attr("data-name"));
			  	var modelId = $(this).attr("data-id");
			  	$("#modelChild").html("");
	  			for (i=0;i<modelJson.length;i++) {
	  				if (modelJson[i].modelModelId==modelId) {
	  					modelJson[i].title = encodeURIComponent(modelJson[i].modelTitle)
	  					$('#modelChildTmpl').tmpl(modelJson[i]).appendTo('#modelChild');
	  				}
	  			}
	  			//获取该模块的主界面
	  			$.ajax({
		  	 		type: "post",
		   			dataType: "json",
		   			url:  base+"/manager/managerModelPage/"+modelId+"/getEntity.do",
		   			success: function(msg){
		   				if(msg.result){
		   					var json =jQuery.parseJSON( msg.resultMsg);
		   					$(".frame").html("");
		   					$(".frame").html('<iframe id="mainFrame" src="${basePath}/'+json.managerModelPageUrl+'" frameborder="0" width="99%" name="main" style="height: 100%;"></iframe>');
		   				}
		   				
		   			}
				});
			});
			
			<#if managerSession.systemSkin?has_content && managerSession.systemSkin.systemSkinBackgroundImg?has_content>
				$(".ms-skin-container").css("background","url('${managerSession.systemSkin.systemSkinBackgroundImg}')");
			</#if>
	})
	
  	
	//左菜单高度
	resize();
	$(window).resize(function(){
		resize();
	});
	
	initSelMenu(".ms-m-menu li","pulse");
	initSelMenu(".ms-m-iconmenu li","tada");
	function resize() {
		//$("body").width($(window).width());
		$("body").height($(window).height());
		$(".ms-m-body").width($(document).width()-$(".ms-m-iconmenu").width()-$(".ms-m-menu").width()-$(".ms-m-menu-silder").width()-20);
	}
	
	function initSelMenu(target,animation) {
		$(target.split(" ")[0]).delegate(target.split(" ")[1],"click",function() {
			$(target).removeClass("sel");
			$(target).removeClass("animated");
			$(target).removeClass(animation);
			$(this).addClass("sel animated  " +animation);
		});
	}
	
</script> 
  
  <!--修改登录密码开始-->
  	<div class="row" style="display:none;" id="updateLoginPassword">
  		 <form action="" class="form-horizontal" role="form" id="updatePasswordFrom">
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="managerName">帐号:</label>
				<div class="col-md-4  col-xs-6">
					<input type="text" class="form-control managerName" name="managerName" value="" readonly>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label col-xs-3" for="nickName">密码:</label>
				<div class="col-md-4  col-xs-6">
					<input type="password" class="form-control updateManagerPassword" name="managerPassword">
				</div>
			</div>
  		</form>
  		<button type="button" id="update" class="btn btn-primary" style="float:right;">更新</button>
        <button type="button" class="btn btn-default closeModal" data-dismiss="modal" style="float:right; margin-right:10px;">关闭</button>
        <div style="clear:both;"></div>
	</div>
  <!--修改登录密码结束-->
  
  <!--背景皮肤开始-->
  	<style>
  		#BackgroundImgDialog .modal-dialog{width:90%}
  		.backgroundImgUl li {float:left;width: 140px;height: 95px;margin:0 3px 3px 0}
  		.backgroundImgUl li img {max-width:100%;max-height:100%}
  	</style>
  	<div class="row" style="display:none;" id="backgroundImg">
		<ul class="backgroundImgUl">
		</ul>
	</div>
  <!--背景皮肤结束-->  
  
    <!--更新开始-->
  	<div class="row" style="display:none;" id="upgraderPanel">
		<div class="updateInfo">
		</div>
		<div style="clear:both;"></div>
	</div>
  <!--更新结束-->  
  <@modalDialog modalName="UpgraderPanel"/>
  <script type="text/javascript">
  	    //引入表单验证框架
		var bootstrapValidator;
		
		//表单验证
		function checkForm(){
			$("#updatePasswordFrom").bootstrapValidator({
				feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
		       	fields: {
		            managerPassword: {
		                validators: {
		                    notEmpty: { message: '密码不能为空'},
		                    stringLength: {min: 1, max: 16,message: '密码长度介于1-16个字符'},
		                    regexp: {regexp: /^[a-zA-Z0-9_\.\@]+$/,message: '密码只能由英文字母，数字，点，下划线或@组成'}
		                }
		            }
		        }
		     });
			bootstrapValidator = $("#updateLoginPassword").find("form:first").data('bootstrapValidator');
		}
		
  	  //退出系统
	  function loginOut(){
  	 	$.ajax({
		   type: "post",
		   dataType: "json",
		   url:  base+"/manager/loginOut.do",
		   success: function(msg){
		   		if(msg){
		   			 location.href = base+"/msadmin/"; 
		   		} else {
		   			alert("系统异常");
		   			location.href = base+"/manager/index.do";
		   		}
		   }
		});
	  }
	  	 
	  $(function () {
		 $(".websiteUpdate").on("click",function() {
		  openUpgraderPanel($("#upgraderPanel"),"系统更新");
		    $("#UpgraderPanelBody .updateMs").show();
		    $("#UpgraderPanelBody .updateMs").removeAttr("disabled");
		 	//先检测是否有更新
			$.ajax({
			   type: "post", 
			   dataType:"json",
			   url:  base+"/manager/upgrader/detection.do",
			   beforeSend:function() {
			   	$("#UpgraderPanelBody .updateMs").attr("disabled","disabled");
			   		$("#UpgraderPanelBody .updateInfo").html("<div class='alert alert-warning' role='alert'><center><img src='${base}/images/lodingDit.gif' width='40''/><br/>正在查找新版本</center></div>");
			   },
			   success: function(jn){
			    if (!jn.result) {
			    	$("#UpgraderPanelBody .updateInfo").html("<div class='alert alert-success' role='alert'>系统升级错误，</div>");
			    } else if (jn.result && jn.resultData=="") {
			     	$("#UpgraderPanelBody .updateInfo").html("<div class='alert alert-success' role='alert'>当前为最新版本!</div>");
			     	$(".updateMs").hide();
			     	 $("#UpgraderPanelBody .updateMs").removeAttr("disabled");
			     } else if(jn.result && jn.resultData!=null) {
			     	var ud = eval(jn.resultData);
			     	$("#UpgraderPanelBody .updateInfo").html("");
			     	for (i=0;i<ud.length;i++) {
			     		var str = "<div class='well update"+i+"' >"+ud[i].ver+"<br/>"+ud[i].info+"<br/><a href='javascript:void(0)' class='upgrader' data-url='"+ud[i].link+"'>更新</a></div>";
			     		$("#UpgraderPanelBody .updateInfo").append(str);
			     	}
			  	 	
			  	 } else {
			  	 	
			  	 }
			  	 
				 $("#UpgraderPanelBody .updateInfo").delegate(".upgrader","click",function() {
				 			var target = $(this);
							 $.ajax({
								   type: "post",
								   url:  base+"/manager/upgrader/update.do",
								   data:"serial="+target.attr("data-url"),
								   beforeSend:function() {
								   		target.undelegate("click");
								   		target.html("更新中....");
								   },
								   success: function(msg){
								   		target.html("已更新");
								   		target.undelegate("click");
								   },error: function(){
								   }
								});										
			  	 	});					  	 
			  	 
			   },error: function(e){
			   }
			});		 	
		 	
		 });
		 
		 
	  
  		//退出系统模态框
		$(".loginOutBtn").on("click",function(){
			warnloginOut("确定退出系统吗？","系统提示","loginOut()");
		});	
  			
  		checkForm();
  		
	  	//修改登录密码模态框
		$(".updatePassword").on("click",function(){
			openeditLoginPassword($("#updateLoginPassword"),"修改登录密码");
			checkForm();
			$.ajax({
			   type: "post",
			   dataType:"json",
			   url:  base+"/manager/editPassword.do",
			   success: function(msg){
			   		$(".managerName").val(msg.manager.managerName);
			   		$(".updateManagerPassword").val(msg.manager.managerPassword);
			   		
			   		
			   },error: function(){
			   		alert("Session已过期，请重新登录");
			   		location.href = base+"/login.do";
			   }

			});
		});
		
		//切换背景
		$(".backgroundImg").on("click",function() {
					openBackgroundImg($("#backgroundImg"),"切换背景");
					
						$.ajax({
						   type: "post",
						   url:  base+"/manager/systemSkin/query.do",
						   success: function(json){
						   		if (json!=undefined) {
									//背景json数据;
									//var json = [{"systemSkinBackgroundImg":"http://7.su.bdimg.com/skin/117.jpg?2","systemSkinDate":1420859253000,"systemSkinId":1},{"systemSkinBackgroundImg":"http://2.su.bdimg.com/skin/208.jpg?2","systemSkinDate":1420859253000,"systemSkinId":2}];
									var li = "<li></li>"
									$(".backgroundImgUl").html("");
									for (i=0;i<json.length;i++) {
										$(".backgroundImgUl").append($(li).html("<img src='"+json[i].systemSkinBackgroundImg+"'/>").attr("data-id",json[i].systemSkinId));
									}							   		
						   		}
					   
						   }
						});					
				
					$(".backgroundImgUl").delegate("li","click",function(){
					 	//设置背景
					 	$(".ms-skin-container").css("background","url('"+$(this).find("img").attr("src")+"')");
					 	var dataId = $(this).attr("data-Id");
						$.ajax({
						   type: "post",
						   url:  base+"/manager/systemSkin/"+dataId+"/updateForManager.do",
						   success: function(msg){
						   }
						});
					});
		})
		
		//网站模版选择
		$(".websiteSkin").on("click",function() {
					openBackgroundImg($("#backgroundImg"),"切换背景");
		})
		
		//网站插件现在
		$(".websitePlug").on("click",function() {
					openBackgroundImg($("#backgroundImg"),"切换背景");
		})				
		
		//修改登录密码，若不填写密码则表示不修改
		$("#editLoginPasswordDialog").delegate("#update","click",function(){
			if(bootstrapValidator.validate().isValid()){
				$(this).attr("disabled","true");
				var formData = $("#updatePasswordFrom").serialize();
				$.ajax({
				   type: "post",
				   dataType: "json",
				   url:  base+"/manager/updatePassword.do",
				   data: formData,
				   success: function(msg){
				     	if (msg.result) {
				     		$(".closeModal").click();
				     		alert("修改登录密码成功");
							location.href = base+"/manager/index.do";
				    	} else {
				    		if(msg.resultMsg != null){
				    			alert(msg.resultMsg);
				    		} else {
				    			$(".closeModal").click();
				    			alert("您好，您尚未修改密码")
				    		}
				    	}
				   },error: function(){
				   		$(".closeModal").click();
			   		    alert("修改登录密码失败");
						location.href = base+"/manager/index.do";
			   		}
				});
			}
		});
	});
  </script>
  <iframe src="${base}/manager/rf.html" style="display:none"></iframe>
  <#if managerSession.systemSkin?has_content && managerSession.systemSkin.systemSkinCss?has_content>
		 <link rel="stylesheet" type="text/css" href="${base}/templets/${managerSession.basicId}/${managerSession.style?default("")}/${managerSession.systemSkin.systemSkinCss}" media="all" />
  </#if>
 </body>
</html>