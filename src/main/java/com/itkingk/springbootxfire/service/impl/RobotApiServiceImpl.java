package com.itkingk.springbootxfire.service.impl;

import com.itkingk.springbootxfire.service.RobotApiService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author itKingk
 */
@WebService(targetNamespace = "http://service.springbootxfire.itkingk.com",
        serviceName = "robotApiService",
        portName = "robotApiService",
        wsdlLocation = "file://G:\\IdeaProjects\\spring-boot-xfire\\src\\main\\resources\\pms.wsdl",
        endpointInterface = "com.itkingk.springbootxfire.service.RobotApiService")
public class RobotApiServiceImpl implements RobotApiService {

    @WebMethod
    @Override
    public String syncTaskType(String req) {
        System.out.println(" 同步任务请求：" + req);
        return "ok";
    }
}
