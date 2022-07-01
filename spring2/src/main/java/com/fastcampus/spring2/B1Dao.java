package com.fastcampus.spring2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class B1Dao {

	@Autowired
	DataSource ds;
	
	
	public int insert(int key, int value) throws Exception {
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		 
		try {
//			conn = ds.getConnection();
			conn = DataSourceUtils.getConnection(ds);
			System.out.println("conn = " + conn);
			String sql = "insert into b1 values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			pstmt.setInt(2, value);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
//			close(conn, pstmt);
			close(pstmt);
			DataSourceUtils.releaseConnection(conn, ds);
		}
	}
	
	private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }

}