package com.rede.project.resource;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.rede.project.dao.CityDAO;
import com.rede.project.exception.CityNotFoundException;
import com.rede.project.log.LogHelper;
import com.rede.project.logic.DistanceLogic;
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
		List<City> cities = new ArrayList<>();
		cities.addAll(CityProviderEnum.INSTANCE.getModel().values());
		return cities;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<City> getCities(){
		List<City> cities = new ArrayList<>();
		try{
			cities.addAll(CityProviderEnum.INSTANCE.getModel().values());
		}catch(Exception e){
			e.getMessage();
		}
		return cities;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCount(){
		int count = CityProviderEnum.INSTANCE.getModel().size();
		return Response.ok(String.valueOf(count)).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public Response createCity(@FormParam("id") String id,
						@FormParam("name") String name,
						@FormParam("latitude") double latitude,
						@FormParam("longitude") double longitude) throws IOException{
		
		City city = new City(id, name, latitude, longitude);
		try{
			new CityDAO().createCity(city);			
		}catch(Exception e){
			LogHelper.log.error(e);
			return Response.serverError().build();
		}
		
		CityProviderEnum.INSTANCE.getModel().put(id, city);			
		URI createdURI = uriInfo.getAbsolutePath();
		
		return Response.created(createdURI).entity("City Created").build();		
		
	}
	
	@GET
	@Path("/distance")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getDistance(@QueryParam("cityA") String idCityA,
								@QueryParam("cityB") String idCityB,
								@QueryParam("unit") String unit) throws IOException{
		
		City cityA = CityProviderEnum.INSTANCE.getModel().get(idCityA);
		City cityB = CityProviderEnum.INSTANCE.getModel().get(idCityB);		
		
		if(cityA == null || cityB == null){
			throw new CityNotFoundException();
		}
		
		double distance = new DistanceLogic().getDistance(cityA, cityB, unit);			
		
		return Response.ok(String.valueOf(distance)).build();
		
	}
	
	
	
	@Path("{city}")
	public CityResource getCities(@PathParam("city") String id){
		return new CityResource(uriInfo, request, id);
	}
	
}
