package com.mackervoy.calum.mud.world;

import com.mackervoy.calum.mud.AbstractMUDController;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.apache.jena.rdf.model.Model;

/**
 * @author Calum Mackervoy
 * Provides endpoints to help generate objects into a scene 
 * (e.g. a character walks into a bar and expects to see occupants)
 */
@Path("/mud/scene/")
public class SceneController extends AbstractMUDController {
	//NOTE: the ContentContoller POST must receives objects with RDF type set, or it will ignore them
	@POST
	public Response post(String requestBody) {
		// the request object will contain the scene as passed by the client
		// e.g. the clientside characters and the selected building
		// TODO: context and social context passed in with the scene
		Model scene = this.serializeTurtleRequestToModel(requestBody);
		
		// TODO: for now we just add a hard-coded character to the scene
		// ...
		
		return Response.ok(serializeModelToTurtle(scene)).build();
	}
}
