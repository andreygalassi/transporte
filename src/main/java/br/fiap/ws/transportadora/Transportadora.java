package br.fiap.ws.transportadora;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.apache.axis.client.Stub;
import org.apache.axis.message.SOAPHeaderElement;

import br.com.fiap.ws.financeiro.cobrar.client.Cobranca;
import br.com.fiap.ws.financeiro.cobrar.client.CobrarCliente;
import br.com.fiap.ws.financeiro.cobrar.client.CobrarClienteService;
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
	public Response cadastrarFrete(Frete frete) {
		
		boolean gerarFreteNormalmente = false;
		Retorno retorno = new Retorno();
		retorno.setMensagem("Geração de frete em curso...");
		
		try
		{
		
			if( frete != null )
			{
				CalcularFrete calcularFrete = new CalcularFrete();
				calcularFrete.setQuantidadeProdutos(frete.getQuantidadeProdutos());
				calcularFrete.setValorTotalRemessa(frete.getValorTotalRemessa());
			
				double valorTotalFrete = calculaFrete(calcularFrete);
			
				// Tenta cobrar do cliente de cpfCnpj o valor total do frete na financeira.
				if (cobrarCliente( frete.getCpfCnpjDestinatario(), valorTotalFrete ) )
					gerarFreteNormalmente = true;
				else
				{
					gerarFreteNormalmente = false;
					retorno.setMensagem("Débito não aprovado.");
				}
					
				// TODO
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
		
		}
		catch(RemoteException ex)
		{
			retorno.setMensagem("Erro durante a geração do frete :" + ex.getMessage());
			return Response.status(500).entity(retorno).build();
		}
		catch(ServiceException ex)
		{
			retorno.setMensagem("Erro durante a geração do frete :" + ex.getMessage());
			return Response.status(500).entity(retorno).build();
		}
		catch(SOAPException ex)
		{
			retorno.setMensagem("Erro durante a geração do frete :" + ex.getMessage());
			return Response.status(500).entity(retorno).build();
		}
		
		return Response.status(200).entity(retorno).build();
	}
	
	private boolean cobrarCliente( String cpfCnpj, double valor ) throws Exception
	{
        URL url = null;
        boolean retorno = false;
        long cpfCnpjL = 0;
        
		try {
			
			cpfCnpjL = converterNumeroInStringToLong(cpfCnpj);
			
			if( cpfCnpjL == -1 )
				throw new Exception("Erro de conversão de String para long");
			
			Properties propriedades = getPropriedades();
			
			String enderecoEndPointServico = propriedades.getProperty("enderecoEndPointServico");
			String enderecoWsdlServico = propriedades.getProperty("enderecoWsdlServico");
			String cargoAuthHeaderKey = propriedades.getProperty("cargoAuthHeaderKey");
			String authHeaderValue = propriedades.getProperty("authHeaderValue");
			
			url = new URL(enderecoWsdlServico);
			
	        CobrarCliente port = new CobrarClienteService(url).getCobrarClientePort();
			
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, enderecoEndPointServico);
			
			Map<String,Object> req_ctx = bindingProvider.getRequestContext();
			Map<String,List<String>> headers = new HashMap<String,List<String>>();
			headers.put(cargoAuthHeaderKey, Collections.singletonList(authHeaderValue));
			req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
			
			Cobranca cobranca = new Cobranca();
	        cobranca.setCpf(cpfCnpjL);
	        cobranca.setValor(valor);
	        
	        retorno = port.cobrar(cobranca);

	        System.out.println("Resultado:" + retorno);
			
		} catch (Exception e ) {
			throw e;
		}
	    catch (IOException e ) {
			throw new Exception(e.getMessage());
	    }
		
		
		return retorno;
	}
	
	private long converterNumeroInStringToLong(String cpfCnpj)
	{
		long retorno = -1;
		
		String digitos = "";
	    char[] letras  = cpfCnpj.toCharArray();
	    for (char letra : letras) {
	        if(Character.isDigit(letra)) {
	            digitos += letra;
	        }
	    }
	    
	    retorno = Long.parseLong(digitos);
	    
		return retorno;
	}
	
	private Properties getPropriedades() throws IOException {
		Properties propers = new Properties();
		FileInputStream file = new FileInputStream(
				"./config/config.properties");
		propers.load(file);
		return propers;
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
