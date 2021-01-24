package com.mackervoy.calum.mud;

import java.io.ByteArrayOutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.jena.rdf.model.*;

@Path("/settlements/")
public class SettlementController {
    @GET
    @Produces("text/turtle")
    public String getSettlements() {
    	Model m = ModelFactory.createDefaultModel();
        
    	m.read(MUDApplication.PATH);
    		
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        m.write(baos, "Turtle");
    	
        return baos.toString();
    }
}