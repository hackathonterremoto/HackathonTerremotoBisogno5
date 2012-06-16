package org.hackathon.soluzione5.web.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hackathon.soluzione5.web.dao.jpa2.OffertaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SampleRestController {
	
	@Autowired
	OffertaDAO offertaDAO;

	private final static Logger logger = Logger.getLogger(SampleRestController.class.getName());
	
	public static final String EchoPath = "/echo/{echoString}";
	

	@RequestMapping(value = EchoPath, method = RequestMethod.GET)
	@ResponseBody
	public String echo(HttpServletRequest request,@PathVariable String echoString ){
		logger.info("Received echo request");
		
		return echoString;
		
	}
	
	
	
	
	
	

}
