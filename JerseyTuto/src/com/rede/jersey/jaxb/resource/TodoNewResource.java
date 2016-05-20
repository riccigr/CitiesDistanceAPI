package com.rede.jersey.jaxb.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.rede.jersey.jaxb.dao.TodoDao;

public class TodoNewResource {

	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;	
	String id;
	public TodoNewResource (UriInfo uriInfo, Request request, String id){
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public TodoNew getTodo(){
		
		TodoNew todo = TodoDao.instance.getModel().get(id);
		
		if(todo == null){
			throw new RuntimeException(id + " not found");
		}
		
		return todo;
	}
	
	@GET
	@Produces({MediaType.TEXT_XML})
	public TodoNew getTodoHTML(){
		
		TodoNew todo = TodoDao.instance.getModel().get(id);
		
		if(todo == null){
			throw new RuntimeException(id + " not found");
		}
		
		return todo;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<TodoNew> todo){
		TodoNew c = todo.getValue();
		return putAndGetResponse(c);
	}
	
	@DELETE
	public void deleteTodo(){
		TodoNew c = TodoDao.instance.getModel().remove(id);
		if(c == null){
			throw new RuntimeException(id + " not found");
		}
	}
	
	public Response putAndGetResponse(TodoNew todo){
		
		Response res;
		if(TodoDao.instance.getModel().containsKey(todo.getId())){
			res = Response.noContent().build();
		}else{
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}		
		TodoDao.instance.getModel().put(todo.getId(), todo);
		
		return res;
	}
	
	
}
