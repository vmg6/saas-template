package org.swecourse.services.api.v1;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PATCH;
import javax.ws.rs.DELETE;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.PathSegment;

import org.apache.log4j.Logger;

import org.swecourse.services.Utils;
import org.swecourse.services.ApiResponse;
import org.swecourse.services.api.Contacts;
import org.swecourse.services.api.ContactInfo;

/**
 * 
 */
@Path("api/v1/contacts")
public class ContactsResource {

  private static final Logger logger = Logger.getLogger(ContactsResource.class);

  /**
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllContacts(@Context UriInfo uriInfo,
      @DefaultValue(".*") @QueryParam("firstName") String firstName,
      @DefaultValue(".*") @QueryParam("lastName") String lastName,
      @DefaultValue(".*") @QueryParam("email") String email
 ) {
    logger.info(uriInfo.getRequestUri());
    return Response.ok().entity(ApiResponse.buildList(new ArrayList<Object>(Contacts.getAll(firstName, lastName, email)))).build();
  }

  /**
   */
  @GET
  @Path("/{contactId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getContactById(@Context UriInfo uriInfo, @PathParam("contactId") Integer contactId) {
    logger.info(uriInfo.getRequestUri());
    Contacts.Contact contact = Contacts.find(contactId);
    if (contact != null) {
      return Response.ok().entity(ApiResponse.build(contact)).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }
  @OPTIONS
  @Path("/{contactId}")
  public Response getContactByIdOptions(@Context UriInfo uriInfo, @PathParam("contactId") Integer contactId) {
    logger.info(uriInfo.getRequestUri());
    return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
  }

  /**
   * Create contact
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createContact(@Context UriInfo uriInfo, ContactInfo contactInfo) {
    logger.info(uriInfo.getRequestUri());
    return Response.status(Response.Status.CREATED).entity(ApiResponse.build(Contacts.add(contactInfo))).build();
  }

  /**
   * Delete contact
   */
  @DELETE
  @Path("/{contactId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteContactById(@Context UriInfo uriInfo, @PathParam("contactId") Integer contactId) {
    logger.info(uriInfo.getRequestUri());
    Contacts.Contact contact = Contacts.delete(contactId);
    //
    Utils.dumpObject(logger, "Delete contact", contact);
    if (contact != null) {
      return Response.ok().entity(ApiResponse.build(contact)).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  /**
   * Update entire contact
   */
  @PUT
  @Path("/{contactId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteContactById(@Context UriInfo uriInfo, @PathParam("contactId") Integer contactId, ContactInfo contactInfo) {
    logger.info(uriInfo.getRequestUri());
    Contacts.Contact contact = Contacts.update(contactId, contactInfo);
    if (contact != null) {
      return Response.ok().entity(ApiResponse.build(contact)).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  /**
   * Update sub set of  contact parameters
   */
  @PATCH
  @Path("/{contactId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response patchContactById(@Context UriInfo uriInfo, @PathParam("contactId") Integer contactId, ContactInfo contactInfo) {
    logger.info(uriInfo.getRequestUri());
    Utils.dumpObject(logger, "Patch contact", contactInfo);
    //
    Contacts.Contact contact = Contacts.patch(contactId, contactInfo);
    if (contact != null) {
      return Response.ok().entity(ApiResponse.build(contact)).build();
    }
    //
    return Response.status(Response.Status.NOT_FOUND).build();
  }

}
