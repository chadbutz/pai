package com.chadbutz.pai.rest.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chadbutz.pai.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Component
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "User resource", produces = "application/json")
public class PaiResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaiResource.class);

	@GET
	@Path("v1/hello/{name}")
	@ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User resource found"),
	    @ApiResponse(code = 404, message = "User resource not found")
	})
	public Response getHelloVersionInUrl(@ApiParam @PathParam("name") String name) {
		LOGGER.info("getHelloVersionInUrl() v1");
		return this.getHello(name, "Version 1 - passed in URL");
	}

	@GET
	@Path("hello/{name}")
	@Consumes("application/vnd.chadbutz-v1+json")
	@Produces("application/vnd.chadbutz-v1+json")
	@ApiOperation(value = "Gets a hello resource. World Version 1 (version in Accept Header)", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User resource found"),
	    @ApiResponse(code = 404, message = "User resource not found")
	})
	public Response getHelloVersionInAcceptHeader(@PathParam("name") String name) {
		LOGGER.info("getHelloVersionInAcceptHeader() v1");
		return this.getHello(name, "Version 1 - passed in Accept Header");
	}

	@POST
	@Path("v1/hello")
	@ApiOperation(value = "Creates hello resource. Version 1 - (version in URL)", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "hello resource created", responseHeaders = {
			@ResponseHeader(name = "Location", description = "The URL to retrieve created resource", response = String.class)
		})
	})
	public Response createHelloVersionInUrl(User hello, @Context UriInfo uriInfo) {
		LOGGER.info("createHelloVersionInUrl() v1");
		return this.createHelloWorld(hello, uriInfo);
	}

	@POST
	@Path("hello")
	@Consumes("application/vnd.chadbutz-v1+json")
	@ApiOperation(value = "Creates hello resource. Version 1 - (version in Accept Header)", response = User.class)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "hello resource created", responseHeaders = {
			@ResponseHeader(name = "Location", description = "The URL to retrieve created resource", response = String.class)
		})
	})
	public Response createHelloVersionInAcceptHeader(User hello, @Context UriInfo uriInfo) {
		LOGGER.info("createHelloVersionInAcceptHeader() v1");
		return this.createHelloWorld(hello, uriInfo);
	}

	private Response getHello(String name, String partialMsg) {
		if ("404".equals(name)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		User result = new User();
		result.setMsg(String.format("User %s. %s", name, partialMsg));
		return Response.status(Status.OK).entity(result).build();
	}

	private Response createHelloWorld(User hello, UriInfo uriInfo) {
		// Creates resource and return 201 with reference to new resource in Location header
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(hello.getMsg());
		return Response.created(builder.build()).build();
	}
}