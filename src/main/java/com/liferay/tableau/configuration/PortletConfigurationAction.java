package com.liferay.tableau.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.tableau.portlet.constants.MVCPortletKeys;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component(
    configurationPid = "com.liferay.tableau.configuration.PortletConfiguration",
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
    immediate = true,
    property = {
        "javax.portlet.name=" + MVCPortletKeys.TableauMVC
    },
    service = ConfigurationAction.class
)
public class PortletConfigurationAction extends DefaultConfigurationAction {
    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
        throws Exception {
        String tabServer = ParamUtil.getString(actionRequest, "reportURL");
        String hideTabs = ParamUtil.getString(actionRequest, "hideTabs");
        setPreference(actionRequest, "reportURL", tabServer);
        setPreference(actionRequest, "hideTabs", hideTabs.toString());
        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
        PortletConfig portletConfig, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
            PortletConfiguration.class.getName(),
            _tableauConfiguration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _tableauConfiguration = ConfigurableUtil.createConfigurable(
            PortletConfiguration.class, properties);
    }

    private volatile PortletConfiguration _tableauConfiguration;
}
