package com.emea.controller;

import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.h2.tools.Server;
import org.junit.BeforeClass;
import org.springframework.stereotype.Component;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.ServletContextAware;
@Component
@WebAppConfiguration
public class InitDb implements  ServletContextAware{
    public static Server server;
    private static final String H2_SER_PORT = "9990";
    private static final String TCP_PORT_STR = "-tcpPort";
    public static boolean flag = true;
    private ServletContext context;

    public void setServletContext(ServletContext servletContext) {
         this.context = servletContext;
    }
   
    @BeforeClass
    public static void init() {
        if (flag) {

            String[] params = {TCP_PORT_STR, H2_SER_PORT};
            try {
                server = Server.createTcpServer(params);
                server.start();
                flag = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
    
   

    public void shutDownDB() {
        server.shutdown();
    }
}
