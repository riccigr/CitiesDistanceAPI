package com.rede.jersey.first;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String helloHtml() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>"
				+ "</html> ";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String helloXml() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloTextPlain() {
		return "Hello Jersey";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String helloKJson() {
		return "{Hello Jersey}";
	}

}
