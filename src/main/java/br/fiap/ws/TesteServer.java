package br.fiap.ws;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Deprecated
@Path("/teste")
public class TesteServer {

	@GET
	@Produces(MediaType.TEXT_PLAIN+ ";charset=utf-8" )
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
	
//	@GET
//	@Produces(MediaType.TEXT_HTML+ ";charset=utf-8" )
//	@Path("/")
//	public void teste2(@Context HttpServletResponse servletResponse){
//        try {
//			servletResponse.sendRedirect("pagina/index.html");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
