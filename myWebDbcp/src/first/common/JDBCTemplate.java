package first.common;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	// JDBCTemplate 내부 모양은 수십가지~
	
	public JDBCTemplate() {
	}

	public static Connection getConnection() {
		Connection con = null;
//		String dr = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";  //TODO: 설명
//		String uid = "scott";
//		String pwd = "TIGER";
		
		try {
			Context initContext=new InitialContext();
			Context envContext=(Context)initContext.lookup("java:/comp/env");  //Tomcat resource 설정을 찾는다server.(xml, context.sml)
			DataSource ds=(DataSource)envContext.lookup("jdbc/mylocaloracle");
			con=ds.getConnection();
			if(con!=null) {
				System.out.println("2021 08 30 DBCP JNDI 연결 성공");
			}else {
				System.out.println("2021 08 30 DBCP JNDI 연결 실패");		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return con;
	}
	public static void close(Connection con) {
		try {
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
