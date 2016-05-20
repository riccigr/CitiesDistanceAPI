package com.rede.jersey.jaxb.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.rede.jersey.jaxb.dao.TodoDao;
import com.rede.jersey.jaxb.model.Todo;

@Path("/todos")
public class AllTodosNewResource {
	
	@Context 
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<TodoNew> getTodosBrowser(){
		List<TodoNew> todos = new ArrayList<TodoNew>();
		todos.addAll(TodoDao.instance.getModel().values());
		return todos;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<TodoNew> getTodos(){
		List<TodoNew> todos = new ArrayList<TodoNew>();
		todos.addAll(TodoDao.instance.getModel().values());
		return todos;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount(){
		int count = TodoDao.instance.getModel().size();
		return String.valueOf(count);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public void newTodo(@FormParam("id") String id,
						@FormParam("summary") String summary,
						@FormParam("description") String description,
						@Context HttpServletResponse servletResponse) throws IOException{
		
		TodoNew todo = new TodoNew(id, summary);
		
		if(description != null){
			todo.setDescription(description);
		}
		
		TodoDao.instance.getModel().put(id, todo);
		
		servletResponse.sendRedirect("../create_todo.html");
		
	}
	
	@Path("{todo}")
	public TodoNewResource getTodo(@PathParam("todo") String id){
		return new TodoNewResource(uriInfo, request, id);
	}
	
	
	

}
