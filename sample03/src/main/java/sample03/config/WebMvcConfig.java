package sample03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// Content Negotiation
	// ref: https://junq.io/spring-mvc%E5%AE%9E%E7%8E%B0http%E5%86%85%E5%AE%B9%E5%8D%8F%E5%95%86-content-negotiation.html
	// ref: http://www.javainuse.com/spring/spring-boot-content-negotiation
	// ref: https://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc
	// 
	// Message Converters
	// ref: http://www.baeldung.com/spring-httpmessageconverter-rest
	
	// 1.URL postfix，例如 xxx.xml or xxx.json
	// 2.URL QueryString, 如?format=json
	// 3.HTTP Header: Accept
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		// configurer.favorPathExtension(true);	// 預設true
		configurer.favorParameter(true);	// 預設false
		// configurer.ignoreAcceptHeader(false);	// 預設false
		
		//configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
}
