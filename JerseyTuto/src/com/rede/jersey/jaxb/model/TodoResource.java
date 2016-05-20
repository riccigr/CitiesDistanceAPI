package com.rede.jersey.jaxb.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/todo")
public class TodoResource {

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Todo getXML(){
		Todo todo = new Todo();
		todo.setSummary("first summary");
		todo.setDescription("first descrption");
		return todo;
	}
	
	@GET
	@Produces({MediaType.TEXT_XML})
	public Todo getHTML(){
		Todo todo = new Todo();
		todo.setSummary("first summary");
		todo.setDescription("first descrption");
		return todo;
	}
	
}
