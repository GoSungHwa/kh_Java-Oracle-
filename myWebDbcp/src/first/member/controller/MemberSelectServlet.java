package first.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import first.member.model.dao.MemberDao;
import first.member.model.service.MemberService;
import first.member.model.vo.MemberVo;

/**
 * Servlet implementation class MemberSelectServlet
 */
@WebServlet("/memberlist")
public class MemberSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MemberSelectServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out  = response.getWriter();
		
		// member를 db에서 읽어와야함.
		ArrayList<MemberVo> volist  = new MemberService().selectList();
		
		// member 리스트를 화면에 출력
		for (MemberVo vo : volist) {
			out.println("<p>" + vo.getId() + "  |  " + vo.getEmail() + "</p>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
