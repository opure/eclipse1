<#if label!="">
	<#if name?? && name!="">
		<label for="${name}" class=" control-label text-right ms-form-label">${label}</label>
	<#else>
		<label class=" control-label text-right ms-form-label" >${label}</label>		
	</#if>
 
</#if>
