package com.wp.termproject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		
		String sign_name = request.getParameter("sign_name");
		String sign_id = request.getParameter("sign_id");
		String sign_passwd1 = request.getParameter("sign_passwd1");
		String sign_passwd2 = request.getParameter("sign_passwd2");
		
		String msg = null;
		String viewName = null;
		// DB에서 같은 아이디 있는지 체크
		if(sign_passwd1.equals(sign_passwd2))
		{
			userDO user = new userDO();
		
			user.setId(sign_id);
			user.setName(sign_name);
			user.setPassword(sign_passwd1);
			user.setPermission("0");
			
			int result =  dao.insertUser(user);
			
			if(result == -1)
			{
				msg = "이미 사용중인 아이디입니다.";
				
				request.setAttribute("msg", msg);
				viewName = "/signup.jsp";
			}
			else
			{
				msg = "축하합니다. 회원가입을 성공했습니다.";
				
				request.setAttribute("msg", msg);
				request.setAttribute("type", "regi");
				viewName = "/WEB-INF/views/result.jsp";
			}
		}
		else
		{
			msg = "비밀번호가 일치하지 않습니다.";
			
			request.setAttribute("msg", msg);
			viewName = "/signup.jsp";
		}
		RequestDispatcher view = request.getRequestDispatcher(viewName);
		view.forward(request, response);
		
		
		// 있으면 다시 페이지 호출해서 메시지 출력
		
		// 아니면 DB에 내용 추가하고 회원가입 성공페이지 띄우고 로그인 화면으로가는 버튼 넣기.
		
		// DB구성 이름 아이디 비밀번호 관리자
		
		//관리자가 접속하면 게시판 글목록화면에서 회원리스트 확인 버튼 추가됨
	}

}
