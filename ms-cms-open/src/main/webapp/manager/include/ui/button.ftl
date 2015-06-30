<#--
表单相关按钮存放区域
-->
<#macro buttonarea id="" label="" class="form-group" style="clear: both;" size="">
<div class="${class} ms-form-group" 
	<#if id!=""> id="${id}"</#if><#rt/>
	<#if style?? && style?string!=""> style="${style}"</#if><#rt/>
	>
	<#if label!="">
		<label class=" control-label text-right ms-form-label">${label}</label>
	</#if>
	 <div  class="ms-form-control">
		<#nested/><#rt/>
	 </div>
</div>
</#macro>

<#--
保存按钮
-->
<#macro savebutton value="" id="" name="" class="" style=""   disabled=false
onclick=""
>
	<button type="button"  class="btn btn-success"
	<#include "common-attributes.ftl"/><#rt/>
	<#if id!=""> id="${id}"</#if><#rt/>
	>
	
	<#if value!="">${value}<#else>保存</#if>
	</button><#rt/>
	<#if onclick?? && onclick!="" && id?? && id!="">
		<script>
			$(function() {
				$("#${id}").click(function() {
					eval(${onclick});
				});
			})
		</script>
	</#if>
</#macro>

<#--
重置按钮
-->
<#macro resetbutton value="" id="" name="" class="" style="" size="" title="" disabled=false>
	<button type="reset"  class="btn reset"
	<#include "common-attributes.ftl"/><#rt/>
	>
	<#if id!=""> id="${id}"</#if><#rt/>
	<#if value!="">${value}<#else>保存</#if>
	</button><#rt/>
</#macro>