package kh.test.jdbc01.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
	private String dr = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";  //TODO: 설명
	private String uid = "scott";
	private String pwd = "TIGER";

	public EmpDao() {
	}
	
	public ArrayList<EmpVo> selectList() {
		ArrayList<EmpVo> voList = null;
		
		try {
			// 1. Oracle 연결 : driver load -> connect 
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");
		
			// 2. query 문을 날릴수 있는 리소스(Statement) 마련
			Statement stmt = conn.createStatement();
			//String sql = "select * from emp join dept using (deptno)";
			//String sql = "select * from emp join dept on emp.deptno=dept.deptno";
			String sql = "select emp.*, dept.dname from emp join dept on emp.deptno=dept.deptno";
			// 3. query 문을 실행하고 그 결과를 받아옴
			ResultSet rs = stmt.executeQuery(sql);   // 위 연결된 곳으로 select query 문을 날려줌
			
			voList = new ArrayList<EmpVo>();
			while(rs.next()) {         // rs.next() 1행을 읽어올것이 없다면 false, 읽어나올 것이 있다면 읽어 나온 값을 가지고 있음.
				EmpVo vo = new EmpVo();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setComm(rs.getInt(7));
				vo.setDeptno(rs.getInt(8));
				vo.setDname(rs.getString(9));
				vo.setSal(rs.getInt(6));
				voList.add(vo);
				//System.out.println(vo.toString());
			}
			//System.out.println(voList);
			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("연결실패");
			e.printStackTrace();
		}
		
		return voList;		
	}

	public void inputEmp(EmpVo vo) {
		System.out.println("DAO  inputEmp 메소드");
		System.out.println(vo.toString());
		try {
			// 1. Oracle 연결 : driver load -> connect 
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");
		
			// 2. query 문을 날릴수 있는 리소스(Statement) 마련
			Statement stmt = conn.createStatement();
			
			// 3. query 문 실행하고 그 결과를 받아옴
			String sql = "insert into emp (empno, ename) values (" + vo.getEmpno() + ", '" + vo.getEname() + "' )";
			int result = stmt.executeUpdate(sql);     // 위 연결된 곳으로 insert/delete/update query 문을 날려줌
			if(result > 0) {
				System.out.println("추가 성공");
			} else {
				System.out.println("추가 실패");
			}
		}catch (Exception e) {
			System.out.println("연결실패");
			e.printStackTrace();
		}
	}
	public int deleteEmp(EmpVo vo) {
		int result=0;
		try {
			// 1. Oracle 연결 : driver load -> connect 
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");
		
			// 2. query 문을 날릴수 있는 리소스(Statement) 마련
			String sql="delete from emp where empno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			
			 result = pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateEmp(EmpVo vo) {
		int result=0;
		try {
			// 1. Oracle 연결 : driver load -> connect 
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");
		
			// 2. query 문을 날릴수 있는 리소스(Statement) 마련
			String sql="update emp set sal=nvl(sal,0)+100 where empno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			
			result=pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void inputEmpPreparedStatement(EmpVo vo) {
		System.out.println("DAO  inputEmp 메소드");
		System.out.println(vo.toString());
		try {
			// 1. Oracle 연결 : driver load -> connect 
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");
		
			// 2. query 문을 날릴수 있는 리소스(PreparedStatement) 마련
			// PreparedStatement 로 변경
			String sql = "insert into emp (empno, ename, deptno) values ( ? , ? , ? )";   //  ? : 위치홀더. 값이 들어가야 하는 위치에 사용. 테이블명컬럼명 사용안됨.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			pstmt.setString(2, vo.getEname());   // setString 사용하면 ' ' 을 넣어주는 기능까지 있음. 
			pstmt.setInt(3, vo.getDeptno());
			//pstmt.setDate(parameterIndex, x);
			
			// 3. query 문 실행하고 그 결과를 받아옴
			int result = pstmt.executeUpdate();     // 위 연결된 곳으로 insert/delete/update query 문을 날려줌
			if(result > 0) {
				System.out.println("추가 성공");
			} else {
				System.out.println("추가 실패");
			}
		}catch (Exception e) {
			System.out.println("연결실패");
			e.printStackTrace();
		}
	}
}
