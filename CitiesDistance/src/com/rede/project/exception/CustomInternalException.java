package com.rede.project.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class CustomInternalException extends WebApplicationException{
	
	public CustomInternalException(){		
		super(Response.status(500).entity("An error has occurred during request. We are investigating what happened.").build());		
	}


}
