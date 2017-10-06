package com.emea.controller;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.junit.BeforeClass;

public class InitDb {
    public static Server server;
    private static final String H2_SER_PORT = "9990";
    private static final String TCP_PORT_STR = "-tcpPort";
    public static boolean flag =true;
    @BeforeClass
    public static void init(){
        if(flag){
            
            String[] params={TCP_PORT_STR, H2_SER_PORT};
            try {
               server = Server.createTcpServer(params);
               server.start();
               flag=false;
               System.out.println("db created");
           } catch (SQLException e) {
               e.printStackTrace();
           }
            
        }
        
    }
    
    public void shutDownDB(){
        server.shutdown();
    }
}
