package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;

public class PaymentCtl extends BaseCtl {
	
	@Override
	protected void preload(HttpServletRequest request) {
		HashMap payMode = new HashMap();
		payMode.put("cash", "cash");
		payMode.put("UPI", "UPI");
		payMode.put("cheque", "cheque");
		payMode.put("debit card", "debit card");
		payMode.put("credit card", "credit card");
		request.setAttribute("payMode", payMode);
		
		
		HashMap payStatus = new HashMap();
		payStatus.put("processed", "processed");
		payStatus.put("pending", "pending");
		payStatus.put("processing", "processing");
		request.setAttribute("payStatus", payStatus);
	}
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("payerName"))) {
			request.setAttribute("payerName", PropertyReader.getValue("error.require", "payer Name"));
			pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("paymentId"))) {
			request.setAttribute("paymentId", PropertyReader.getValue("error.require", "payment Id"));
			pass = false;
		} else if (DataValidator.isLong(request.getParameter("paymentId"))) {
			request.setAttribute("paymentId", "enter a valid payment id");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
			pass = false;
		} else if (DataValidator.isLong(request.getParameter("amount"))) {
			request.setAttribute("amount", "enter valid amount");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("paymentMode"))) {
			request.setAttribute("paymentMode", PropertyReader.getValue("error.require", "payment Mode"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("paymentStatus"))) {
			request.setAttribute("paymentStatus", PropertyReader.getValue("error.require", "payment Status"));
			pass = false;
		}
		
		return pass;
	}
	
//	@Override
//	protected BaseDTO populateDTO(HttpServletRequest request) {
		
//		PaymentDTO dto = new PaymentDTO();
//		dto.setId(id);
//		dto.setPayerName(payerName);
//		dto.setPaymentMode(paymentMode);
//		dto.setPaymentStatus(paymentStatus);
//		dto.set
//		populateBean(dto, request);
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PAYMENT_VIEW;
	}

}
