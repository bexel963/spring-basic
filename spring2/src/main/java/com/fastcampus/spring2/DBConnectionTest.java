//
//// - java 프로그램으로 MySQL DB 연결하기 : JDBC(Java DB Connectivity)-API 드라이버가 필요함.(Maven Repository에서 다운)
//
//
//package com.fastcampus.spring2;
//
//import java.sql.*;
//
//public class DBConnectionTest {
//    public static void main(String[] args) throws Exception {
//    	
//        // 스키마의 이름(springbasic)이 다른 경우 알맞게 변경해야 함
//        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";
//
//        // DB의 userid와 pwd를 알맞게 변경해야 함
//        String DB_USER = "Lim";
//        String DB_PASSWORD = "2461";
//
//        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // 데이터베이스의 연결을 얻는다.
//        Statement stmt  = conn.createStatement(); // Statement를 생성한다.
//
//        String query = "SELECT now()"; 				// 시스템의 현재 날짜시간을 출력하는 쿼리(query)
//        ResultSet rs = stmt.executeQuery(query); 	// query를 실행한 결과를 rs에 담는다.
//        										 	// ResultSet은 테이블 형태로 데이터를 가져온다.
//
//        // 실행결과가 담긴 rs에서 한 줄씩 읽어서 출력
//        while (rs.next()) {
//            String curDate = rs.getString(1);  // 읽어온 행의 첫번째 컬럼의 값을 String으로 읽어서 curDate에 저장
//            System.out.println(curDate);       // 2022-01-11 13:53:00.0
//        }
//    } // main()
//}
