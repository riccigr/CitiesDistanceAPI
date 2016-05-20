package com.rede.project.resource;

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

import com.rede.project.model.City;
import com.rede.project.provider.CityProviderEnum;

@Path("/cities")
public class CitiesResource {
	
	@Context 
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<City> getCitiesBrowser(){
		List<City> cities = new ArrayList<City>();
		cities.addAll(CityProviderEnum.instance.getModel().values());
		return cities;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<City> getCities(){
		try{
		List<City> cities = new ArrayList<City>();
		cities.addAll(CityProviderEnum.instance.getModel().values());
		return cities;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount(){
		int count = CityProviderEnum.instance.getModel().size();
		return String.valueOf(count);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public void createCity(@FormParam("id") String id,
						@FormParam("name") String name,
						@FormParam("latitude") double latitude,
						@FormParam("longitude") double longitude,
						@Context HttpServletResponse servletResponse) throws IOException{
		
		City city = new City(id, name, latitude, longitude);
		
		
		CityProviderEnum.instance.getModel().put(id, city);		
		
		
	}
	
	@Path("{city}")
	public CityResource getCities(@PathParam("city") String id){
		return new CityResource(uriInfo, request, id);
	}
	
}
