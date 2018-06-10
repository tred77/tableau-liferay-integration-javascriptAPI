package com.liferay.tableau.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
		category="display-content",
		scope=ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(id="com.liferay.tableau.configuration.PortletConfiguration")
public interface PortletConfiguration {

	@Meta.AD(required=false)
	String reportURL();

	@Meta.AD(required=false)
	String hideTabs();
}
