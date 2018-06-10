<%@ include file="init.jsp" %>

<script type="text/javascript"
        src="https://public.tableau.com/javascripts/api/tableau-2.min.js"></script>
<div id="<portlet:namespace/>_Viz" style="width:800px; height:700px;"></div>
<div>
    <button onclick="<portlet:namespace/>.goNextTab()">Next Tab</button>
    <button onclick="<portlet:namespace/>.getActiveTabData()">Get Data</button>
</div>
<script>
    var <portlet:namespace/> = _Tableau.create("<portlet:namespace/>_Viz", "<%=reportURL%>", {hideTabs: <%=hideTabs%>});
</script>