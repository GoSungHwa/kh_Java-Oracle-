package first.member.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static first.common.JDBCTemplate.*;
import first.member.model.dao.MemberDao;
import first.member.model.vo.MemberVo;

public class MemberService  {

	public MemberService() {
	}
	
	
	/*
	 * return : 0 - id,pwd 일치,  1- id만일치,  2- id없음, -1 오류발생
	 * return : String passwd
	 */
	public int login(String id, String pwd) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().login(conn, id, pwd);
		close(conn);
		return result;
	}
	
	public ArrayList<MemberVo> selectList() {
		ArrayList<MemberVo> volist = null;
		Connection conn = null;
		
		conn = getConnection();
		
		volist = new MemberDao().selectList(conn);
		
		close(conn);
		return volist;
	}
	
	public ArrayList<MemberVo> searchSelectList(String name) {
		ArrayList<MemberVo> volist = null;
		Connection conn = getConnection();
		volist = new MemberDao().searchSelectList(conn, name);
		close(conn);
		return volist;
	}
	
	public int inputMember(MemberVo vo) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().inputMember(conn, vo);
		close(conn);
		return result;
	}
	
	public int deleteMember(String id) {
		int result = -1;   // 초기 값을 음수 - why~ sql 문 정상수행후 결과가 0인 것과 오류 발생으로 인한 결과값을 구분하기 위해 오류발생시에는 음수가 return 될 수 있도록 지정함.
		Connection conn = getConnection();
		result = new MemberDao().deleteMember(conn, id);
		close(conn);
		return result;
	}
	
	public int updatePwdMember(String id, String pwd) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().updatePwdMember(conn, id, pwd);
		close(conn);
		return result;
	}
	
	
	
	
}
