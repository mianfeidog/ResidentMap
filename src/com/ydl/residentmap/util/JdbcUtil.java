package com.ydl.residentmap.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
2  * jdbc工具类
3  * @author APPle
4  *
5  */
 public class JdbcUtil {
     private static String url = null;
     private static String user = null;
     private static String password = null;
     private static String driverClass = null;
     
     //声明ThreadLocal容器对象，放置多个Connection
 	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>(); 
     
     /**
      * 静态代码块中（只加载一次）
      */
    static{
         try {
             //读取db.properties文件
             Properties props = new Properties();
             /**
            *  . 代表java命令运行的目录
              *  在java项目下，. java命令的运行目录从项目的根目录开始
              *  在web项目下，  . java命令的而运行目录从tomcat/bin目录开始
             *  所以不能使用点.
              */
             //FileInputStream in = new FileInputStream("./src/db.properties");
             
             /**
              * 使用类路径的读取方式
              *  / : 斜杠表示classpath的根目录
              *     在java项目下，classpath的根目录从bin目录开始
              *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
              */
             InputStream in = JdbcUtil.class.getResourceAsStream("/config/persistence-mysql.properties");
            
             //加载文件
             props.load(in);
             //读取信息
             url = props.getProperty("jdbc.url");
            user = props.getProperty("jdbc.user");
            password = props.getProperty("jdbc.pass");
             driverClass = props.getProperty("jdbc.driverClassName");
            
             
            //注册驱动程序5             
             Class.forName(driverClass);
         } catch (Exception e) {
             e.printStackTrace();
            System.out.println("驱程程序注册出错");
         }
     }
    
    
  //返回Connection对象
  	public static Connection getConnection(){
  		Connection conn=null;
  		//从容器中获取连接对象
  		conn = tl.get();
  		
  		try{
  		//容器中没有连接
  		if(conn==null){
  			 conn = DriverManager.getConnection(url, user, password);
  			tl.set(conn);
  		}
  		}catch(Exception e){
  			System.out.println("DataSourceUtils------------getConnnection    出错");
  			e.printStackTrace();
  		}
  		return conn;
  	}
    
     /**
      * 释放资源的方法
      */
     public static void close(Connection conn,Statement stmt){
         if(stmt!=null){
             try {
                 stmt.close();
             } catch (SQLException e) {
                e.printStackTrace();
                 throw new RuntimeException(e);
             }
       }
         if(conn!=null){
             try {
                 conn.close();
          } catch (SQLException e) {
                 e.printStackTrace();
                throw new RuntimeException(e);
             }
         }
     }
     
    public static void close(Connection conn,Statement stmt,ResultSet rs){
    	if(rs!=null)
            try {
                 rs.close();
             } catch (SQLException e1) {
                 e1.printStackTrace();
                 throw new RuntimeException(e1);
             }
         if(stmt!=null){
             try {
                 stmt.close();
             } catch (SQLException e) {
                e.printStackTrace();
               throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
               throw new RuntimeException(e);
            }
       }
    }
}