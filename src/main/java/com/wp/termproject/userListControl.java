package com.wp.termproject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.termproject.*;

/**
 * Servlet implementation class userListControl
 */
@WebServlet("/userlist/*")
public class userListControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userListControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//step #1. get request paprameters
		request.setCharacterEncoding("UTF-8");
		
		//step #2. data processing
		//get DAO object
		userinfoDAO daou = (userinfoDAO)getServletContext().getAttribute("daou");
		noticeboardDAO daon = (noticeboardDAO)getServletContext().getAttribute("daon");
		HttpSession session = request.getSession(false);
		String viewName = null;
		if (session == null)
		{
			viewName = "redirect:/login.jsp";
		}
		else
		{
			//get routing information
			String pathInfo = request.getPathInfo();
			String action = request.getParameter("action");
			String permission = (String)session.getAttribute("permission");
			String userid = (String)session.getAttribute("userid");
			String name = (String)session.getAttribute("name");
			
			String msg = null;
			
			if(pathInfo != null && pathInfo.length() > 0)
			{
				if (pathInfo.equals("/main"))
				{
					//get boods list
					List<NoticeDO> boardList = daon.selectAllboards();
					
					//forward to list view
					request.setAttribute("board_list", boardList);
					request.setAttribute("userid", userid);
					request.setAttribute("name", name);
					request.setAttribute("permission", permission);
					
					viewName = "WEB-INF/views/main.jsp";
					
				}
				else if (pathInfo.equals("/list"))
				{
					if(permission.equals("1 "))
					{
						//get boods list
						List<userDO> userList = daou.selectAllUser();
						
						//forward to list view
						request.setAttribute("user_list", userList);
						
						viewName = "WEB-INF/views/list_userinfo.jsp";
					}
					else
					{
						msg = "접근 권한이 없습니다.";
						
						request.setAttribute("msg", msg);
						request.setAttribute("type", "del");
						
						viewName = "WEB-INF/views/result.jsp";
					}
					
				}
				else if (pathInfo.equals("/search"))
				{
					String search_type = request.getParameter("search_type");
					String search_string = request.getParameter("search_string");
					List<NoticeDO> boardList = null;
					switch(search_type)
					{
						case "title":
							boardList = daon.selectAllTitle(search_string);
							break;
						case "content":
							boardList = daon.selectAllContent(search_string);
							break;
						case "id":
							boardList = daon.selectAllId(search_string);
							break;
					}
					request.setAttribute("board_list", boardList);
					request.setAttribute("name", name);
					request.setAttribute("permission", permission);
					viewName = "WEB-INF/views/main.jsp";
				}
				else if (pathInfo.equals("/write"))
				{
					String mod = request.getParameter("mod");
					if(mod.equals("0"))
					{
						viewName = "WEB-INF/views/write.jsp";
					}
					else
					{
						String title = request.getParameter("title");
						String content = request.getParameter("content");
						
						NoticeDO board = new NoticeDO();
						board.setContent(content);
						board.setDay("sysdate");
						board.setId((String)session.getAttribute("userid"));
						board.setTitle(title);
						
						daon.insertboards(board);
						
						request.setAttribute("permission", permission);
						viewName = "userlist/main";
					}
					
				}
				else if (pathInfo.equals("/show"))
				{
					String bid = request.getParameter("bid");
					String bday = request.getParameter("bday");
					String mod = request.getParameter("mod");
					bday= bday.replace("%20", " ");
					
					NoticeDO board = new NoticeDO();
					board.setDay(bday);
					board.setId(bid);
					
					board = daon.selectboard(board);
					
					request.setAttribute("board", board);
					request.setAttribute("bid", bid);
					request.setAttribute("userid", userid);
					request.setAttribute("permission", permission);
					if(mod.equals("0"))
					{
						viewName = "WEB-INF/views/show.jsp";
					}
					else
					{
						if(permission.equals("1 ") || userid.equals(bid))
						{
							viewName = "WEB-INF/views/modify.jsp";
						}
						else
						{
							msg = "접근 권한이 없습니다.";
							
							request.setAttribute("msg", msg);
							request.setAttribute("type", "del");
							
							viewName = "WEB-INF/views/result.jsp";
						}
					}
					
				}
				else if (pathInfo.equals("/modify"))
				{
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String bid = request.getParameter("bid");
					String bday = request.getParameter("bday");
					bday= bday.replace("%20", " ");
					
					if(permission.equals("1 ") || userid.equals(bid))
					{
						Date nowDate = new Date();
			    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    		String now = sdf.format(nowDate);
						
						int result;
						NoticeDO board = new NoticeDO();
						board.setTitle(title);
						board.setContent(content);
						board.setDay(bday);
						board.setId(bid);
						board.setUpdateDay(now);
						
						result = daon.updateboards(board);
						
						viewName = "userlist/show?mod=0&bid="+bid+"&bday="+now;
					}
					else
					{
						msg = "접근 권한이 없습니다.";
						
						request.setAttribute("msg", msg);
						request.setAttribute("type", "del");
						
						viewName = "WEB-INF/views/result.jsp";
					}
				}
				else if (pathInfo.equals("/delete"))
				{
					String bid = request.getParameter("bid");
					String bday = request.getParameter("bday");
					bday= bday.replace("%20", " ");
					
					if(permission.equals("1 ") || userid.equals(bid))
					{
						int result;
						NoticeDO board = new NoticeDO();
						board.setDay(bday);
						board.setId(bid);
						
						result = daon.deleteboards(board);
						
						if(result == -1)
						{
							msg = "제거 실패 다시 시도해주세요.";
						}
						else
						{
							msg = "성공적으로 제거되었습니다.";
						}
						
						request.setAttribute("msg", msg);
						request.setAttribute("type", "del");
						viewName = "WEB-INF/views/result.jsp";
					}
					else
					{
						msg = "접근 권한이 없습니다.";
						
						request.setAttribute("msg", msg);
						request.setAttribute("type", "del");
						
						viewName = "WEB-INF/views/result.jsp";
					}
				}
				else if (pathInfo.equals("/logout"))
				{
					session.invalidate();
					viewName = "redirect:/login.jsp";
				}
				else if (pathInfo.equals("/userinfo"))
				{
					userDO user = new userDO();
					user = daou.selectUser(userid);
					
					request.setAttribute("id", user.getId());
					request.setAttribute("name", user.getName());
					request.setAttribute("password", user.getPassword());
					
					viewName = "WEB-INF/views/usermodify.jsp";
				}
				else if (pathInfo.equals("/usermodify"))
				{
					String sign_name = request.getParameter("sign_name");
					String sign_id = request.getParameter("sign_id");
					String sign_passwd1 = request.getParameter("sign_passwd1");
					String sign_passwd2 = request.getParameter("sign_passwd2");
					String id = request.getParameter("id");
					
					if(userid.equals(id))
					{
						// DB에서 같은 아이디 있는지 체크
						if(sign_passwd1.equals(sign_passwd2))
						{
							userDO user = new userDO();
						
							user.setId(id);
							user.setName(sign_name);
							user.setPassword(sign_passwd1);
							user.setModId(sign_id);
							user.setPermission("0");
							
							int result =  daou.updateUser(user);
							
							if(result == -1)
							{
								msg = "이미 사용중인 아이디입니다.";
								
								request.setAttribute("msg", msg);
								viewName = "userlist/userinfo";
							}
							else
							{
								msg = "회원정보 수정에 성공했습니다.";
								
								request.setAttribute("msg", msg);
								request.setAttribute("type", "del");
								viewName = "/WEB-INF/views/result.jsp";
							}
						}
						else
						{
							msg = "비밀번호가 일치하지 않습니다.";
							
							request.setAttribute("msg", msg);
							viewName = "userlist/userinfo";
						}
					}
					else
					{
						msg = "접근 권한이 없습니다.";
						
						request.setAttribute("msg", msg);
						request.setAttribute("type", "del");
						
						viewName = "WEB-INF/views/result.jsp";
					}
				}
			}
			else if (action != null)
			{
				if(action.equals("delete"))
				{
					int result;
					String id = request.getParameter("id");
					String mod = request.getParameter("mod");
					if(permission.equals("1 ") || userid.equals(id))
					{
						try {
							result = daou.deletUser(id);
						} catch (Exception e) {
							e.printStackTrace();
							throw new ServletException(e.getMessage());
						}
						if(mod.equals("0"))
						{
							if(result == -1)
							{
								msg = "제거 실패 다시 시도해주세요.";
							}
							else
							{
								msg = "성공적으로 제거되었습니다.";
							}
							request.setAttribute("msg", msg);
							request.setAttribute("type", "list");
						}
						else
						{
							if(result == -1)
							{
								msg = "탈퇴 실패 다시 시도해주세요.";
							}
							else
							{
								msg = "성공적으로 탈퇴 되었습니다.";
							}
							request.setAttribute("msg", msg);
							request.setAttribute("type", "regi");
						}
						
						
						viewName = "WEB-INF/views/result.jsp";
					}
					else
					{
						msg = "접근 권한이 없습니다.";
						
						request.setAttribute("msg", msg);
						request.setAttribute("type", "del");
						
						viewName = "WEB-INF/views/result.jsp";
					}
					
				}
			}
		}
		
		
		//step #3. output processing data
		if(viewName != null)
		{
			if(viewName.contains("redirect:"))
			{
				String location = viewName.split(":")[1];
				response.sendRedirect(request.getContextPath()+location);
			}
			else
			{
				RequestDispatcher view = request.getRequestDispatcher("/"+viewName);
				view.forward(request, response);
			}
			
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