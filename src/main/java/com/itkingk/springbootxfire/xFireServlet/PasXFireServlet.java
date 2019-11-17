package com.itkingk.springbootxfire.xFireServlet;

import com.itkingk.springbootxfire.dto.ApplicationContextUtils;
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireException;
import org.codehaus.xfire.transport.http.XFireConfigurableServlet;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * @author 93633
 */

@WebServlet(name="myServlet",urlPatterns="/webservice/*")
public class PasXFireServlet extends XFireConfigurableServlet {

    public PasXFireServlet() {
        super();
    }

    @Override
    public XFire createXFire() throws ServletException {
        return super.createXFire();
    }

    @Override
    public XFire loadConfig(String configPath) throws XFireException {
        return ApplicationContextUtils.getBean(XFire.class);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
