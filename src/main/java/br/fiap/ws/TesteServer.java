package br.fiap.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TesteServer {

	@GET
	@Produces(MediaType.TEXT_PLAIN )
	@Path("/teste")
	public String teste(){
		System.out.println("teste");
		return "O WebServer está em pé";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/testeJson")
	public String testeJson(){
		return new String("teste");
	}
}
