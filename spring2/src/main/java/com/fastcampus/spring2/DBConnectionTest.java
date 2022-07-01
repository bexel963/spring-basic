//
//// - java ���α׷����� MySQL DB �����ϱ� : JDBC(Java DB Connectivity)-API ����̹��� �ʿ���.(Maven Repository���� �ٿ�)
//
//
//package com.fastcampus.spring2;
//
//import java.sql.*;
//
//public class DBConnectionTest {
//    public static void main(String[] args) throws Exception {
//    	
//        // ��Ű���� �̸�(springbasic)�� �ٸ� ��� �˸°� �����ؾ� ��
//        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";
//
//        // DB�� userid�� pwd�� �˸°� �����ؾ� ��
//        String DB_USER = "Lim";
//        String DB_PASSWORD = "2461";
//
//        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // �����ͺ��̽��� ������ ��´�.
//        Statement stmt  = conn.createStatement(); // Statement�� �����Ѵ�.
//
//        String query = "SELECT now()"; 				// �ý����� ���� ��¥�ð��� ����ϴ� ����(query)
//        ResultSet rs = stmt.executeQuery(query); 	// query�� ������ ����� rs�� ��´�.
//        										 	// ResultSet�� ���̺� ���·� �����͸� �����´�.
//
//        // �������� ��� rs���� �� �پ� �о ���
//        while (rs.next()) {
//            String curDate = rs.getString(1);  // �о�� ���� ù��° �÷��� ���� String���� �о curDate�� ����
//            System.out.println(curDate);       // 2022-01-11 13:53:00.0
//        }
//    } // main()
//}
