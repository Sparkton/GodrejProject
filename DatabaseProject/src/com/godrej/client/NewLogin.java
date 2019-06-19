package com.godrej.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.godrej.dao.UserDao;
import com.godrej.daoimpl.UserDaoImpl;
import com.godrej.model.User;


/**
 * Servlet implementation class newLogin
 */
@WebServlet("/newLogin")
public class NewLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Try POST");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDaoImpl();
		String uName = request.getParameter("uName");
		String uPass = request.getParameter("pass");
		List<User> tempList = new ArrayList<User>();
		tempList = userDao.search(1,uName);
		if(tempList.isEmpty() || tempList == null)
			response.sendRedirect("jsp/Login.jsp");
		for(User user:tempList) {
			if(user.getuPass().equals(uPass)) {
				
				  request.setAttribute("ID", user.getUserId()); 
				  request.setAttribute("uName",user.getuName()); 
				  request.setAttribute("uPass", user.getuPass());
				  request.setAttribute("State", user.getAddress().getState());
				  request.setAttribute("City", user.getAddress().getCity());
				  request.setAttribute("Pincode", user.getAddress().getPinCode());
				  RequestDispatcher rd = request.getRequestDispatcher("DisplaySingle");
				  rd.forward(request, response);
			}
			else {
				user=null;
				response.getWriter().print("UserName/Password incorrect");
				break;
			}
			
		}
	}
}

/*
 * out.println("<html><body><form action='UserUpdate'>");
 * out.println("<label>ID : <input type='text' name='serialIn'>"+i.getUserId()+
 * "</input></label>");
 * out.println("<label>Name: <input type='text' name='nameIn' id='nameIn'>\r\n "
 * +i.getuName()+"</label>");
 * out.println("<label>Password: <input type='text' name='passIn' id='passIn'>"
 * +i.getuPass()+"</input></label>");
 * out.println("<label>State : <input type='text' name='stateIn' id='stateIn'>"
 * +i.getAddress().getState()+"</input></label>");
 * out.println("<label>City: <input type='text' name='cityIn' id='cityIn'>\r\n"
 * +i.getAddress().getCity()+"</input></label>");
 * out.println("<label>PinCode: <input type='number' name='pinIn' id='pinIn'>"+i
 * .getAddress().getPinCode()+"</input></label>");
 * out.println("<button type='submit' class='button'>Update</button>");
 * out.println("</form></body></html>");
 */