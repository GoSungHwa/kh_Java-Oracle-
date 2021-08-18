package kh.test.jdbc01.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpDao {
	private String dr = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "scott";
	private String pwd = "TIGER";

	public ArrayList<EmpVo> selectList() {
		// 1. Oracle 연결 :driver 로드
		// 2. Connection , query문을 날릴 수 있는 리소스(Statement) 마련
		ArrayList<EmpVo> voList = null;
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");

			Statement stmt = conn.createStatement();
			String sql = "select e.*,d.dname from emp e join dept d " + "on e.deptno=d.deptno";

			// 3. query 문을 실행하고 그 결과를 받아옴
			ResultSet rs = stmt.executeQuery(sql); // 위 연결된 db로 select query문을 날려줌 

			voList = new ArrayList<EmpVo>();
			while (rs.next()) { // 1행을 읽어올것이 없다면 false, 읽어나올게 있다면 읽음
				EmpVo vo = new EmpVo();
				vo.setEmpno(rs.getInt(1)); // get 꺼내는거임
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setComm(rs.getInt(7));
				vo.setSal(rs.getInt(6));
				vo.setDeptno(rs.getInt(8));
				vo.setDname(rs.getString(9));
				voList.add(vo);

//			System.out.println(vo);
			}
//			System.out.println(voList);
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}

		return voList;
	}

	public void inputEmp(EmpVo vo) {
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");

			Statement stmt = conn.createStatement();

			String sql = "insert into emp(empno,ename) values(" + vo.getEmpno() + ", '" + vo.getEname() + "')";
			int result=stmt.executeUpdate(sql); //insert,delete,update 쿼리 날려줌
			if(result>0) {
				System.out.println("추가 성공");
			}else {
				System.out.println("추가 실패");
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
	}
}
