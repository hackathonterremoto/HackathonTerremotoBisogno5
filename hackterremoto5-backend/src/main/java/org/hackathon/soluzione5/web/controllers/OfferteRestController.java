package org.hackathon.soluzione5.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hackathon.soluzione5.model.Offerta;
import org.hackathon.soluzione5.web.dao.jpa2.OffertaDAO;
import org.hackathon.soluzione5.web.dao.mock.MockOffertaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Controller
public class OfferteRestController {

	@Autowired
	MockOffertaDAO offertaDAO;

	private final static Logger logger = Logger.getLogger(OfferteRestController.class.getName());



	public static final String offerteListPath = "/offerte/latitude/{latitude}/longitude/{longitude}/radius/{radius}/";
	public static final String offerteAddPath = "/offerte/";


	@RequestMapping(value = offerteListPath, method = RequestMethod.GET)
	@ResponseBody
	public Offerta[] getOfferte(HttpServletRequest request,HttpServletResponse response,@PathVariable String latitude,@PathVariable String longitude,@PathVariable String radius) throws ParseException{
		logger.info("Received offerte request");

		logger.info("Spatial query on a restriction area ("+latitude+","+longitude+") "+radius);

		WKTReader fromText = new WKTReader();
		Point center = null;
		double convertedRadius;
		
		center = (Point) fromText.read("POINT("+latitude+" "+longitude+")");
			
		convertedRadius = Double.parseDouble(radius);

		response.addHeader("Access-Control-Allow-Origin", "*");
		
		List<Offerta> offerte =  offertaDAO.findItems(center, convertedRadius, "", 0, null, null, null);
		
		return offerte.toArray(new Offerta[]{});


	}
	
	
	@RequestMapping(value = offerteAddPath, method = RequestMethod.PUT)
	@ResponseBody
	public void addOfferta(HttpServletRequest request,HttpServletResponse response,@RequestBody Offerta offerta) throws ParseException{
		logger.info("Received add offerta request");

		response.addHeader("Access-Control-Allow-Origin", "*");
		
		offertaDAO.insert(offerta);

	}
	
	
	
	

    @ExceptionHandler(ParseException.class)
    public void handleException(ParseException ex, HttpServletResponse response) {
            try {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getLocalizedMessage());
            } catch (IOException e) {
                    e.printStackTrace();
            }
    }
    
    
    @ExceptionHandler(NumberFormatException.class)
    public void handleException(NumberFormatException ex, HttpServletResponse response) {
            try {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getLocalizedMessage());
            } catch (IOException e) {
                    e.printStackTrace();
            }
    }

    
    @ExceptionHandler(RuntimeException.class)
    public void handleException(RuntimeException ex, HttpServletResponse response) {
            try {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
            } catch (IOException e) {
                    e.printStackTrace();
            }
    }

    
    

	
	



}
