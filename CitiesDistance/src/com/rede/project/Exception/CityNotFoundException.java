package com.rede.project.Exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class CityNotFoundException extends WebApplicationException  {
	
	public CityNotFoundException(){		
		super(Response.status(404).entity("City not Found").build());		
	}

}
