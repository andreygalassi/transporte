package br.com.fiap.ws.financeiro.cobrar.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

public class TestarCobrancaClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// CobrarCliente port = new CobrarClienteService().getCobrarClientePort(); 
		
        URL url = null;
        
		try {
			url = new URL("http://54.205.89.34:8080/FinanciamentoMBA/Cobrar_Cliente?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        CobrarCliente port = new CobrarClienteService(url).getCobrarClientePort();
		
		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://54.205.89.34:8080/FinanciamentoMBA/Cobrar_Cliente");
		
		Map<String,Object> req_ctx = bindingProvider.getRequestContext();
		Map<String,List<String>> headers = new HashMap<String,List<String>>();
		headers.put("cargo", Collections.singletonList("estabelecimento"));
		req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		
		Cobranca cobranca = new Cobranca();
        cobranca.setCpf(123);
        cobranca.setValor(10.0);
        boolean resultado = port.cobrar(cobranca);

        System.out.println("Resultado:" + resultado);
	}

}
