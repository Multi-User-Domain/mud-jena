/**
 * 
 */
package com.mackervoy.calum.mud;

import java.io.ByteArrayOutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb2.TDB2Factory;

/**
 * @author Calum Mackervoy
 * A generic endpoint for GETting datasets and resources
 */
@Path("/")
public class DataController {
	@GET
    @Produces("text/turtle")
	@Path("/{dataset}/")
    public String get(@PathParam("dataset") String datasetSubPath) {
		//TODO: check if the dataset exists and return 404 if not (rather than creating an empty one on each 'not found' request
		//	in TDB1 you would use inUseLocation(..) but this is not provided by the TDB2Factory (needs research)
		//  best to use incorporate this into the responsibilities of TDBStore
		String datasetPath = MUDApplication.getRootDirectory() + datasetSubPath;
    	Dataset dataset = TDB2Factory.connectDataset(datasetPath);
    	
    	dataset.begin(ReadWrite.READ) ;
	    Model m = dataset.getDefaultModel() ;
	        		
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        m.write(baos, "Turtle");
        dataset.end();
    	
        return baos.toString();
    }
}
