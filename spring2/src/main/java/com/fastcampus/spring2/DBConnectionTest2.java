
// - spring 프로그램으로 MySQL DB 연결하기 : spring-jdbc 드라이버 필요함 (Maven Repository에서 다운)

package com.fastcampus.spring2;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class DBConnectionTest2 {
    public static void main(String[] args) throws Exception {
//        // 스키마의 이름(springbasic)이 다른 경우 알맞게 변경
//        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";
//
//        // DB의 userid와 pwd를 알맞게 변경
//        String DB_USER = "Lim";
//        String DB_PASSWORD = "2461";
//        String DB_DRIVER = "com.mysql.jdbc.Driver";
//
//    	// 직접 객체를 생성.
//        DriverManagerDataSource ds = new DriverManagerDataSource();	// 스프링 jdbc가 제공하는 클래스
//        ds.setDriverClassName(DB_DRIVER);
//        ds.setUrl(DB_URL);
//        ds.setUsername(DB_USER);
//        ds.setPassword(DB_PASSWORD);
	    
    	// root-context에 위의 정보를 bean으로 등록하고 getBean으로 얻어온다.
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
//        assertTrue(conn!=null);
    }
}
