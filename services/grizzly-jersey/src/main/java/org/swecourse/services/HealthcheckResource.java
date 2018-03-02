package org.swecourse.services;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;


import org.apache.log4j.Logger;

/**
 * 
 */
@Path("healthcheck")
public class HealthcheckResource {

  private static final Logger logger = Logger.getLogger(HealthcheckResource.class);
  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getIt(@Context UriInfo uriInfo) {
    logger.info(uriInfo.getRequestUri());
    return "live";
  }
  @HEAD
  public Response getItHead(@Context UriInfo uriInfo) {
    return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
  }
}
