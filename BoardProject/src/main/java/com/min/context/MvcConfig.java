package com.min.context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@ComponentScan(basePackages= {"com.min.controller","com.min"})
public class MvcConfig implements WebMvcConfigurer{
	
// InternalResourceViewResolver ( ViewResolver 구현 클래스중 하나)
// = 뷰이름으로부터 JSP나 Tiles 연동을 위한 View 객체를 리턴
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver
		= new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	
	// 파일업로드 할때 쓰는거
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver c = new CommonsMultipartResolver();
		c.setMaxUploadSize(1000000000);
		c.setMaxInMemorySize(1000000000);
		return c;
	}
	
	// resources들 매핑
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**")
		.addResourceLocations("classpath:/static/js/");
	}
	

}
