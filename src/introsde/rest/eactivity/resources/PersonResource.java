package introsde.rest.eactivity.resources;

import introsde.rest.eactivity.model.*;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.derby.impl.sql.catalog.SYSFOREIGNKEYSRowFactory;



@Stateless
@LocalBean
public class PersonResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	EntityManager entityManager;
	
	int id;

	public PersonResource(UriInfo uriInfo, Request request,int id, EntityManager em) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		this.entityManager = em;
	}
	
	public PersonResource(UriInfo uriInfo, Request request,int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	
	// Request#2
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getPerson() {
		Person person = Person.getPersonById(this.id);
		System.out.println("Person exist: " + person);
		if (person == null) {
			 return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(person).build();
	}
	
	//Request#3 
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putPerson(Person person) {
		System.out.println("Person with id: " +this.id+" has been updated");
		System.out.println(person.toString());
		
		Response response;
		
		Person existing = Person.getPersonById(this.id);
		
		if (existing == null) {
			response = Response.noContent().build();
		} else {
			response = Response.created(uriInfo.getAbsolutePath()).build();
			person.setpersonId(this.id);
			Person.updatePerson(person);
		}

		return response;

		
	}
	
	//Request#5
	@DELETE
	public void deletePerson() {
		Person person = Person.getPersonById(id);
		if (person == null)
			throw new RuntimeException("Person with id:  " + id + " not found");

		Person.removePerson(person);
	}


	
	//Request7 and Request#11 EXTRA
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{activity_Type}")
	public List<Activity> getActivityBetweenDates(@PathParam("activity_Type") ActivityType type, 
			@DefaultValue("")@QueryParam("before") String beforeDateString, 
			@DefaultValue("")@QueryParam("after") String afterDateString) {
		Date beforeDate = getDateFromString(beforeDateString);
		Date afterDate = getDateFromString(afterDateString);
		Person person = Person.getPersonById(id);
		List<Activity> activities;

		if (!(beforeDate==null) ||!(afterDate==null)) {
			activities = person.getActivitiesInRange(type, beforeDate, afterDate);
		} else {
			activities = person.getActivitiesType(type);
		}
		return activities;
	}	

	//Request#8
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{activity_Type}/{activity_Id}")
	public List<Activity> getActivityWithTypeAndId(@PathParam("activity_Type") ActivityType type,@PathParam("activity_Id") int activityId ) {
		Person person = Person.getPersonById(id);
		List<Activity> activitiesWithTypeAndId = person.getActivitiesTypeId(type, activityId);
		return activitiesWithTypeAndId;
	}	
	
	//Request#9
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{activity_Type}")
	public Activity addActivityWithType(@PathParam("activity_Type") ActivityType type, Activity activity) throws IOException {
		Person person = Person.getPersonById(id);
		activity.setPerson(person);
		Activity activitySaved = Activity.saveActivity(activity);
		return person.addActivityType(type, activitySaved);
	}

	//Request#10 EXTRA
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{activity_Type}/{activity_Id}")
	public Activity updateActivityWithType(@PathParam("activity_Type") ActivityType type, Activity activity) throws IOException {
		Person person = Person.getPersonById(id);
		activity.setPerson(person);
		return Activity.updateActivity(activity);
	}
	
	
	
	private Date getDateFromString(String dateString) {
	    try {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	        Date date = df.parse(dateString);
	        return date;
	    } catch (ParseException e) {
	       System.out.println("Exception: No date obtained from string.");
	    	return null;
	    }
		
	}
			
	
	
	
}
