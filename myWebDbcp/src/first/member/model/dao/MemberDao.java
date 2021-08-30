package first.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import first.member.model.vo.MemberVo;

public class MemberDao {

	public MemberDao() {
	}

	// 로그인 id, passwd 가지고 오면 sql
	// 로그인성공
	// 로그인실패 - id가 없어서, id는 있는데 passwd 틀려서
	/*
	 * return : 0 - id,pwd 일치,  1- id만일치,  2- id없음, -1 오류발생
	 * return : String passwd
	 */
	public int login(Connection conn, String id, String pwd) {
		int result = -1;
//		String sql = "select * from TEST_MEMBER where id=? and passwd=?";
		String sql = "select passwd from TEST_MEMBER where id=?";
		try {
			PreparedStatement s  = conn.prepareStatement(sql);
			s.setString(1,id);
//			s.setString(2,pwd);
			ResultSet rs = s.executeQuery();
			
			if(rs.next()) {
				String dbPwd = rs.getString(1);
				if(pwd.equals(dbPwd)) {
					// pwd 도 같음 ==> 로그인 성공
					result = 0;
				} else {
					// pwd 가 틀렸음
					result = 1;
				}
			} else {
				// id 가 없음
				result = 2;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public ArrayList<MemberVo> selectList(Connection conn) {
		ArrayList<MemberVo> volist = null;
		
		String sql = "select * from TEST_MEMBER";
		
		try {
			PreparedStatement pstmt  = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			volist = new ArrayList<MemberVo>();
			while(rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				
				volist.add(vo);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return volist;
	}
	
	// TEST_MEMBER table 에 검색한 결과 리스트 
	public ArrayList<MemberVo> searchSelectList(Connection conn, String name) {
		ArrayList<MemberVo> volist = null;
		//String sql = "select * from TEST_MEMBER where name='"+ name + "'";  // for Statement
		String sql = "select * from TEST_MEMBER where name=?";  // for PreparedStatement
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<MemberVo>();
				do {
					MemberVo vo = new MemberVo();
					vo.setId(rs.getString("id"));
					vo.setPasswd(rs.getString("passwd"));
					vo.setName(rs.getString("name"));
					vo.setEmail(rs.getString("email"));
					volist.add(vo);
				}while(rs.next());
			}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return volist;
	}
	
	// TEST_MEMBER table 에 insert
	public int inputMember(Connection conn, MemberVo vo) {
		int result = -1;
		String sql = "insert into TEST_MEMBER (ID, PASSWD, NAME, EMAIL) values (?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			result = pstmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// TEST_MEMBER table 에 delete 
	public int deleteMember(Connection conn, String id) {
		int result = -1;   // 초기 값을 음수 - why~ sql 문 정상수행후 결과가 0인 것과 오류 발생으로 인한 결과값을 구분하기 위해 오류발생시에는 음수가 return 될 수 있도록 지정함.
		String sql = "delete from TEST_MEMBER where id=?";
		try {
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, id);
			result = p.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// TEST_MEMBER table 에 update 
	public int updatePwdMember(Connection conn, String id, String pwd) {
		int result = -1;
		String sql = "update TEST_MEMBER set PASSWD=? where id=?";
		try {
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, pwd);
			p.setString(2, id);
			result = p.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
