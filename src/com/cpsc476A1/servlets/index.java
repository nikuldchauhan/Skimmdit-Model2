package com.cpsc476A1.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.cpsc476A1.bean.linkUser;


/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() throws ServletException {
		// TODO Auto-generated method stub
		 Map user = new ConcurrentHashMap();
		 ServletConfig conf = getServletConfig();
		 ServletContext context = conf.getServletContext();
		 context.setAttribute("hashmapuser", user);
		 Map<String, linkUser> m = new ConcurrentHashMap();
		 context.setAttribute("link", m);
		 Map<String,List<String>> l=new ConcurrentHashMap();
		 Map<String,List<String>> u=new ConcurrentHashMap();
		 context.setAttribute("l", l);
		 context.setAttribute("u", u);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		String var = request.getParameter("var");
		if(var!=null)
		{	
		 if(var.equals("signup"))
		 {
			rd = request.getRequestDispatcher("/jsp/signup.jsp");
			rd.forward(request, response);
			return;
		 }
		 if(var.equals("back"))
		 {
			 rd = request.getRequestDispatcher("/jsp/login.jsp");
		     rd.forward(request, response);
		     return;
		 }	 
		} 
		HttpSession h = request.getSession();
		String s = (String) h.getAttribute("user");
		if(s!=null)
		{
			rd = request.getRequestDispatcher("/jsp/linklist.jsp");
			rd.forward(request, response);
			return;
		}	
		else
		{
		  rd = request.getRequestDispatcher("/jsp/login.jsp");
		  rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		HttpSession h = request.getSession();
		String var = request.getParameter("var");
		if(var!=null)
		{
			if(var.equals("login"))
			 {
				String un = request.getParameter("un");
				String pw = request.getParameter("pw");
				//
				String md5 = null;
				try {
		            
			        //Create MessageDigest object for MD5
			        MessageDigest digest = MessageDigest.getInstance("MD5");
			         
			        //Update input string in message digest
			        digest.update(pw.getBytes(), 0, pw.length());
			 
			        //Converts message digest value in base 16 (hex)
			        md5 = new BigInteger(1, digest.digest()).toString(16);
			        pw = md5;
			        } catch (NoSuchAlgorithmException e) {
			 
			            System.out.println(e);
			        }
				//
				Map hs = (Map) request.getServletContext().getAttribute("hashmapuser");
				if(hs.containsKey(un) && pw.equals(hs.get(un)))
				{
					h.setAttribute("user", un);
					rd = request.getRequestDispatcher("/jsp/linklist.jsp");
					rd.forward(request, response);
					return;
				}
				else
				{
					request.setAttribute("err", "Username or Password is incorrect");
					rd = request.getRequestDispatcher("/jsp/login.jsp");
					rd.forward(request, response);
					return;
				}	
			 }
			
			if(var.equals("signup"))
			 {
				String un = request.getParameter("un");
				String pw = request.getParameter("pw");
				String md5 = null;
				try {
		            
			        //Create MessageDigest object for MD5
			        MessageDigest digest = MessageDigest.getInstance("MD5");
			         
			        //Update input string in message digest
			        digest.update(pw.getBytes(), 0, pw.length());
			 
			        //Converts message digest value in base 16 (hex)
			        md5 = new BigInteger(1, digest.digest()).toString(16);
			        pw = md5;
			        } catch (NoSuchAlgorithmException e) {
			 
			            System.out.println(e);
			        }
				Map hs = (Map) request.getServletContext().getAttribute("hashmapuser");
				if(hs.containsKey(un))
				{
					request.setAttribute("err", "Username is already exist please try another");
					rd = request.getRequestDispatcher("/jsp/signup.jsp");
					rd.forward(request, response);
					return;
				}
				else
				{
					hs.put(un, pw);
					//h.setAttribute("user", un);
					rd = request.getRequestDispatcher("/jsp/login.jsp");
					rd.forward(request, response);
					return;
				}	
			 }
		}
	}

}
