package com.min.context;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

@EnableAsync
// web.xml 
public class Initializer implements WebApplicationInitializer{

	// 메인 윈도우 표시하기
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext rootContext
		= new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		this.addDispatcherServlet(servletContext);
		this.addUtf8CharacterEncodingFilter(servletContext);
		
		
	}
	
	private void addDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext applicationContext
		= new AnnotationConfigWebApplicationContext();
		applicationContext.register(MvcConfig.class);
		
		ServletRegistration.Dynamic dispatcher
		= servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
		
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setInitParameter("dispatchOptionsRequest", "true");
	}
		
	/** * UTF-8 캐릭터 인코딩 필터를 추가한다. * @param servletContext */
	private void addUtf8CharacterEncodingFilter(ServletContext servletContext) {
		
		FilterRegistration.Dynamic filter
		= servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
		
		filter.setInitParameter("encoding", "UTF-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(null, false, "/*");
	}
		
		
		
}
	
	
	

