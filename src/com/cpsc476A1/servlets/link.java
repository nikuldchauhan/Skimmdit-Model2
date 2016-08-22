package com.cpsc476A1.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.cpsc476A1.bean.LinkBean;
import com.cpsc476A1.bean.linkUser;

/**
 * Servlet implementation class link
 */
@WebServlet("/link")
public class link extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public link() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException {
		// TODO Auto-generated method stub
    	;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String var = request.getParameter("signout");
		HttpSession h = request.getSession();
		h.invalidate();
	    RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
	    rd.forward(request, response);
	    return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		HttpSession h = request.getSession();
		String var = request.getParameter("var");
		LinkBean lb = new LinkBean();
		Map hs = (Map) request.getServletContext().getAttribute("link");
		HttpSession s = request.getSession();
		String ss = (String) s.getAttribute("user");
		if(var!=null)
		{
			if(var.equals("new"))
			 { 
				String ln = request.getParameter("ln");
				String la = request.getParameter("la");
				boolean b = true;
				String str = null;
				while(b)
				{	
				    try {
				    		str = lb.uniqueID();
				    		if(!(hs.containsKey(str)))
				    		{
				    			b = false;
				    		}
				    	} catch (NoSuchAlgorithmException e) {
				    		// TODO Auto-generated catch block
				    		e.printStackTrace();
				    
				    			}
				}    
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss");
				String formattedDate = sdf.format(date);
				linkUser lu = new linkUser();
				lu.setLinkd(ln);
				lu.setLinka(la);
				lu.setUser(ss);
				lu.setVote(1);
				lu.setDate(formattedDate);
				hs.put(str, lu);
				rd = request.getRequestDispatcher("/jsp/linklist.jsp");
				rd.forward(request, response);
				return;
			 }
		}
	}
}
