package com.rede.project.resource;

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

import com.rede.project.exception.CityNotFoundException;
import com.rede.project.provider.CityProviderEnum;

public class CityResource {
		
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;	
	String id;
	public CityResource (UriInfo uriInfo, Request request, String id){
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public City getCity(){
		
		City todo = CityProviderEnum.INSTANCE.getModel().get(id);
		
		if(todo == null){
			throw new CityNotFoundException();
		}
		
		return todo;
	}
	
	@GET
	@Produces({MediaType.TEXT_XML})
	public City getCityHTML(){
		
		City todo = CityProviderEnum.INSTANCE.getModel().get(id);
		
		if(todo == null){
			throw new CityNotFoundException();
		}
		
		return todo;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putCity(JAXBElement<City> city){
		City c = city.getValue();
		return putAndGetResponse(c);
	}
	
	@DELETE
	public void deleteCity(){
		City c = CityProviderEnum.INSTANCE.getModel().remove(id);
		if(c == null){
			throw new CityNotFoundException();
		}
	}
	
	
	public Response putAndGetResponse(City city){
		
		Response res;
		if(CityProviderEnum.INSTANCE.getModel().containsKey(city)){
			res = Response.noContent().build();
		}else{
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}		
		CityProviderEnum.INSTANCE.getModel().put(city.getId(), city);
		
		return res;
	}
}
