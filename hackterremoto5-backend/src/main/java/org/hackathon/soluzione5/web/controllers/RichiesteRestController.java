package org.hackathon.soluzione5.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hackathon.soluzione5.model.Offerta;
import org.hackathon.soluzione5.model.Richiesta;
import org.hackathon.soluzione5.web.dao.jpa2.RichiestaDAO;
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
public class RichiesteRestController {

	@Autowired
	RichiestaDAO richiestaDAO;
	

	private final static Logger logger = Logger.getLogger(RichiesteRestController.class.getName());


	public static final String richiesteListPath = "/richieste/latitude/{latitude}/longitude/{longitude}/radius/{radius}/";

	public static final String richiesteAddPath = "/richieste/";


	
	
	
	
	@RequestMapping(value = richiesteAddPath, method = RequestMethod.POST)
	@ResponseBody
	public void addRichiesta(HttpServletRequest request,@RequestBody Richiesta richiesta){
		logger.info("Received addRichiesta request");



	}
	
	
	
	
	@RequestMapping(value = richiesteListPath, method = RequestMethod.GET)
	@ResponseBody
	public Richiesta[] getRichieste(HttpServletRequest request,@PathVariable String latitude,@PathVariable String longitude,@PathVariable String radius) throws ParseException{
		logger.info("Received richieste request");

		logger.info("Spatial query on a restriction area ("+latitude+","+longitude+") "+radius);

		WKTReader fromText = new WKTReader();
		Point center = null;
		double convertedRadius;
		
		center = (Point) fromText.read("POINT("+latitude+" "+longitude+")");
			
		convertedRadius = Double.parseDouble(radius);


		List<Richiesta> richieste =  richiestaDAO.findItems(center, convertedRadius, "", 0, null, null);
		
		return richieste.toArray(new Richiesta[]{});


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
