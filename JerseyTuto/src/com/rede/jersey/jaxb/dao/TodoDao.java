package com.rede.jersey.jaxb.dao;

import java.util.HashMap;
import java.util.Map;

import com.rede.jersey.jaxb.database.dao.MyDAO;
import com.rede.jersey.jaxb.model.Todo;
import com.rede.jersey.jaxb.resource.TodoNew;

public enum TodoDao {

	instance;

	private Map<String, TodoNew> contentProvider = new HashMap<String, TodoNew>();

	private TodoDao() {
		TodoNew todo = new TodoNew("1", "keep learning");
		todo.setDescription("Test description");
		contentProvider.put("1", todo);
		
		todo = new TodoNew("2", "continue it");
		todo.setDescription("Test description continue");
		contentProvider.put("2", todo);
		
		MyDAO m = new MyDAO();
		m.select();
	}
	
	public Map<String, TodoNew> getModel(){
		return contentProvider;
	} 

}
