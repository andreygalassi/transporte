package br.fiap.ws.transportadora;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;

import org.apache.axis.client.Stub;
import org.apache.axis.message.SOAPHeaderElement;

import br.com.governo.ws.Exception;
import br.com.governo.ws.Governo;
import br.com.governo.ws.GovernoServiceLocator;
import br.com.governo.ws.Imposto;

@Path("/")
public class Transportadora {

	static List<Remessa> remessas = new ArrayList<Remessa>();
	private static double VALOR_FRETE_POR_PRODUTO = 25;
	private static double ALIQUOTA_SEGURO = 0.01;
	
	static {
		
		// TODO : Remover posteriormente a lista de remessas de teste
		
		remessas = new ArrayList();

		Remessa remessa = new Remessa();
		remessa.setCpfCnpjDestinatario("262");
		remessa.setCpfCnpjRemetente("256");
		remessa.setValorFrete(10.5);
		
		remessas.add(remessa);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarRemessas")
	public List<Remessa> getListaRemessas() {
		return remessas;
	}
	
	// Método com uso presumido para simulação em site de vendas.
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("calcularFrete")
	public Response calcularFrete(CalcularFrete calcularFrete) throws ServiceException, Exception, RemoteException, SOAPException {
		
		double valorTotalFrete = calculaFrete(calcularFrete);		
		
		FreteCalculado freteCalculado = new FreteCalculado();
		freteCalculado.setValorTotalFrete(valorTotalFrete);
		
		return Response.status(200).entity(freteCalculado).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("gerarFrete")
	public Response cadastrarFrete(Frete frete) throws ServiceException, Exception, RemoteException, SOAPException {
		
		// TODO - vari�vel somente para depurar e separar da l�gica ok dos outros grupos, remover.
		boolean gerarFreteNormalmente = true;
		
		Retorno retorno = new Retorno();
		retorno.setMensagem("Geração de frete em curso...");
		
		if( frete != null )
		{
			CalcularFrete calcularFrete = new CalcularFrete();
			calcularFrete.setQuantidadeProdutos(frete.getQuantidadeProdutos());
			calcularFrete.setValorTotalRemessa(frete.getValorTotalRemessa());
		
			double valorTotalFrete = calculaFrete(calcularFrete);
		
			// TODO ( ?? )
			//
			// if ( financeira.isDebtApproved( cpfCnpjRemetente, valorTotalFrete ) )  
			// { 
			//   if ( governo.isNotaFiscalEmitida(cpfCnpjRemetente, cpfCnpjDestinatario, valorProdutos, quantidadeProdutos, valorTotalFrete) ) 
			//   {
			//      gerarFreteNormalmente = true;
			//	 }
			//   else
			//      retorno.setMensagem("Nota fiscal n�o emitida.");
			// }
			// else
			//    retorno.setMensagem("D�bito n�o aprovado.");
			//		
			
			if( gerarFreteNormalmente )
			{
				Remessa remessa = new Remessa();
				remessa.setCpfCnpjDestinatario(frete.getCpfCnpjDestinatario());
				remessa.setCpfCnpjRemetente(frete.getCpfCnpjRemetente());
				remessa.setValorFrete(valorTotalFrete);
				remessas.add(remessa);
			
				retorno.setMensagem("Frete gerado com sucesso.");
			}
		}	
			
		return Response.status(200).entity(retorno).build();
	}
	
	public static void main(String args[]) throws Exception, RemoteException, ServiceException, SOAPException
	{
		CalcularFrete calcularFrete = new CalcularFrete();
		calcularFrete.setQuantidadeProdutos(1);
		calcularFrete.setValorTotalRemessa(10);
		new Transportadora().calculaFrete(calcularFrete);
	}
	
	private double calculaFrete(CalcularFrete calcularFrete) throws ServiceException, Exception, RemoteException, SOAPException {
		double freteCalculado = 0.0;

		if (calcularFrete != null) {
			freteCalculado = calcularFrete.getQuantidadeProdutos() * VALOR_FRETE_POR_PRODUTO;
			freteCalculado = freteCalculado + calcularFrete.getValorTotalRemessa() * ALIQUOTA_SEGURO;

			Governo port = new GovernoServiceLocator().getGovernoPort();
			
			SOAPHeaderElement authentication = new SOAPHeaderElement("http://ws.governo.com.br/","Authentication");
			SOAPHeaderElement user = new SOAPHeaderElement("http://ws.governo.com.br/","documento", "11111111111");
			SOAPHeaderElement password = new SOAPHeaderElement("http://ws.governo.com.br/","senha", "1234");
			
			authentication.addChild(user);
			authentication.addChild(password);
			
			((Stub)port).setHeader(authentication);

			Imposto[] impostos = port.listarImpostos();

			Double totalImpostos = 0d;
			for (Imposto imposto : impostos) {
				totalImpostos += imposto.getAliquota();
			}
			freteCalculado = freteCalculado + totalImpostos;
		}

		return freteCalculado;
	}
	
}
