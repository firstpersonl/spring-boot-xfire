package com.itkingk.springbootxfire;

import com.itkingk.springbootxfire.service.RobotApiService;
import com.itkingk.springbootxfire.service.impl.RobotApiServiceImpl;
import org.codehaus.xfire.DefaultXFire;
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.aegis.type.DefaultTypeMappingRegistry;
import org.codehaus.xfire.service.DefaultServiceRegistry;
import org.codehaus.xfire.service.ServiceRegistry;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.spring.remoting.XFireExporter;
import org.codehaus.xfire.transport.DefaultTransportManager;
import org.codehaus.xfire.transport.http.XFireServletController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * @author itKingk
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.itkingk.springbootxfire.xFireServlet")
public class SpringBootXfireApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringBootXfireApplication.class);
		application.addListeners(new MainBusiListeners());
		application.run(args);
	}

	@Bean("robotApiServiceImpl")
	public RobotApiServiceImpl pasService() {
		return new RobotApiServiceImpl();
	}

	@Bean
	public XFire xFire() {

		ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
		DefaultTransportManager transportManager = new DefaultTransportManager();
		transportManager.initialize();
		XFire xFire = new DefaultXFire(serviceRegistry, transportManager);
		WebService[] annotationsByType = RobotApiService.class.getAnnotationsByType(WebService.class);
		QName qName = new QName(annotationsByType[0].targetNamespace(), annotationsByType[0].serviceName());
		xFire.setProperty("objectServiceFactory.portType", qName);
		return xFire;
	}

	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		Map<String, Object> mapper = new HashMap<>();
		mapper.put("/robotApiService", xFireExporter());
		simpleUrlHandlerMapping.setUrlMap(mapper);
		return simpleUrlHandlerMapping;
	}


	@Bean
	public XFireExporter xFireExporter() {
		XFireExporter xFireExporter = new XFireExporter();
		xFireExporter.setServiceFactory(objectServiceFactory());
		xFireExporter.setXfire(xFire());
		xFireExporter.setServiceBean(pasService());
		xFireExporter.setName("robotApiService");
		xFireExporter.setNamespace("http://service.springbootxfire.itkingk.com");
		xFireExporter.setServiceClass(RobotApiServiceImpl.class);
		xFireExporter.setServiceInterface(RobotApiService.class);
		xFireExporter.setWsdlURL("file://G:\\IdeaProjects\\spring-boot-xfire\\src\\main\\resources\\pms.wsdl");
		return xFireExporter;
	}

	@Bean
	public ObjectServiceFactory objectServiceFactory() {
		return new ObjectServiceFactory(defaultTransportManager(),aegisBindingProvider());
	}

	@Bean
	public XFireServletController xFireServletController() {
		return new XFireServletController(xFire());
	}


	@Bean
	public DefaultTransportManager defaultTransportManager() {
		DefaultTransportManager defaultTransportManager = new DefaultTransportManager();
		defaultTransportManager.initialize();
		return defaultTransportManager;
	}

	@Bean
	public AegisBindingProvider aegisBindingProvider() {
		return new AegisBindingProvider(defaultTypeMappingRegistry());
	}

	private DefaultTypeMappingRegistry defaultTypeMappingRegistry() {
		DefaultTypeMappingRegistry defaultTypeMappingRegistry = new DefaultTypeMappingRegistry();
		defaultTypeMappingRegistry.createDefaultMappings();
		return defaultTypeMappingRegistry;
	}

	@Bean
	public DefaultServiceRegistry defaultServiceRegistry() {
		return new DefaultServiceRegistry();
	}

}
