package com.wp.termproject;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		DBConnectionInfo dbInfo = (DBConnectionInfo)context.getAttribute("db_info");
		userinfoDAO dao = new userinfoDAO(dbInfo);
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		String msg = null;
		
		userDO user = dao.selectUser(userid);
		
		if(user != null && passwd.equals(user.getPassword())) // DB에서 체크
		{
			// authentication succeeded
			
			// 게시판 글목록으로 이동.
			HttpSession session = request.getSession(true);
			if (session.isNew())
			{
				session.setAttribute("userid", userid);
				session.setAttribute("permission", user.getPermission());
				session.setAttribute("name", user.getName());
			}
			else
			{
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return;
			}			
			
			request.setAttribute("userid", userid);
			request.setAttribute("permission", user.getPermission());
			
			
			RequestDispatcher view = request.getRequestDispatcher("/userlist/main");  // 게시판 글 목록
			view.forward(request, response);
		}
		else
		{
			// authentication failed
			msg = "로그인에 실패하였습니다! 사용자 아이디 또는 비밀번호가 틀렸습니다.";
			
			request.setAttribute("msg", msg);
			
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");  // 게시판 글 목록
			view.forward(request, response);
		}
	}

}
