
package com.emea;
import org.apache.log4j.Logger;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.emea.util.ApplicationContextUtil;


@Configuration
@SpringBootApplication 
@ComponentScan
public class Application  {
    private static final Logger LOG =   Logger.getLogger(Application.class);
    
	public static void main(String[] args) {
	    LOG.info("application started");
	    
	   
       
      	
		ConfigurableApplicationContext configurableApplicationContext = null;
        try {
            ApplicationContextUtil.init();
            String[] params={"-tcpPort", System.getProperty("server.h2.port")};
            Server server = Server.createTcpServer(params);
            server.start();
            
      configurableApplicationContext = SpringApplication.run(Application.class, args);
    //  CommonUtil.createMetaData();
        } catch (Exception e) {
            LOG.error(e);
            e.printStackTrace();
        }
	}
}
