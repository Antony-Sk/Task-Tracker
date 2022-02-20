<div>
    <#if errorMessage??>
        ${errorMessage}
    <#else>
        error
    </#if>
    <form><input type="button" value="Go back!" onclick="history.back()"></form>
</div>