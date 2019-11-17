package com.itkingk.springbootxfire.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author itkingk
 */
@WebService(serviceName = "robotApiService",wsdlLocation = "file://G:\\IdeaProjects\\spring-boot-xfire\\src\\main\\resources\\pms.wsdl", targetNamespace = "http://service.springbootxfire.itkingk.com")
public interface RobotApiService {
    /**
     * xfire 框架解析目前好像不支持DTO对象，的自动封装， 所以目前都使用String字符串
     * @param req
     * @return
     */
    @WebMethod
    String syncTaskType(String req);
}
