package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import com.Payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Payment paymentObj = new Payment(); 

    public PaymentAPI() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// NOT USED
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String output = paymentObj.insertPayment(request.getParameter("paymentID"),
				request.getParameter("paymentDate"),
				request.getParameter("paymentType"),
				request.getParameter("Amount"));

response.getWriter().write(output);
	}
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
	    {
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
						scanner.useDelimiter("\\A").next() : "";
				scanner.close();
		 
				String[] params = queryString.split("&");
				for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
			    }
			 }
					
			 catch (Exception e)
		     {
			 }
			 
			return map;
		}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = paymentObj.updatePayment(paras.get("hidPaymentIDSave").toString(),
										   paras.get("paymentDate").toString(),
										   paras.get("paymentType").toString(),
										   paras.get("Amount").toString(),
										   
			
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = paymentObj.deletePayment(paras.get("paymentID").toString());
		response.getWriter().write(output);
	}

}
