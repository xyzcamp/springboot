package sample04.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import sample04.interceptor.AuthInterceptor;

@Configuration
@ImportResource("classpath:spring_context.xml")
@PropertySource(value = "file:/usr/home/sb/sample04.properties", encoding = "UTF-8")
@PropertySource(value = "classpath:app.properties", encoding = "UTF-8")
//property載入順序 , 後面不覆蓋前面
//application.properties
//@PropertySource("classpath:...")
//@PropertySource("file:...")
//@ImportResource("...xml")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		AuthInterceptor authInterceptor = new AuthInterceptor();
		authInterceptor.setGoIfDeny("/admin/user/index");
		registry.addInterceptor(authInterceptor).addPathPatterns("/web*", "/property1*").excludePathPatterns("/b/*");

		AuthInterceptor authInterceptor2 = new AuthInterceptor();
		authInterceptor2.setGoIfDeny("/admin/user/index2");
		registry.addInterceptor(authInterceptor2).addPathPatterns("/web*", "/property1*").excludePathPatterns("/b/*");
	}
}
