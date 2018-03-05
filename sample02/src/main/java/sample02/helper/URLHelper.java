package sample02.helper;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Component
public class URLHelper {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	ResourceUrlProvider resourceUrlProvider;

	public String version(String lookupPath) {
		String versionPath = this.resourceUrlProvider.getForLookupPath(lookupPath);
		if (versionPath == null)
			versionPath = lookupPath;

		return servletContext.getContextPath() + versionPath;
	}

}
