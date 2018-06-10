<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.tableau.configuration.PortletConfiguration" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<portlet:defineObjects />
<liferay-theme:defineObjects />


<%
PortletConfiguration configuration = (PortletConfiguration) renderRequest.getAttribute(PortletConfiguration.class.getName());
Boolean hideTabs = Boolean.FALSE;
String reportURL = "";

if (Validator.isNotNull(configuration)) {
    reportURL = portletPreferences.getValue("reportURL", configuration.reportURL());
	if(portletPreferences.getValue("hideTabs", configuration.hideTabs()) != null){
		hideTabs =
			Boolean.valueOf(portletPreferences.getValue("hideTabs", configuration.hideTabs()));
	}
}
%>