package br.com.governo.ws;

public class GovernoProxy implements br.com.governo.ws.Governo {
  private String _endpoint = null;
  private br.com.governo.ws.Governo governo = null;
  
  public GovernoProxy() {
    _initGovernoProxy();
  }
  
  public GovernoProxy(String endpoint) {
    _endpoint = endpoint;
    _initGovernoProxy();
  }
  
  private void _initGovernoProxy() {
    try {
      governo = (new br.com.governo.ws.GovernoServiceLocator()).getGovernoPort();
      if (governo != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)governo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)governo)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (governo != null)
      ((javax.xml.rpc.Stub)governo)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.governo.ws.Governo getGoverno() {
    if (governo == null)
      _initGovernoProxy();
    return governo;
  }
  
  public br.com.governo.ws.NotaFiscal[] listarNotaFiscal() throws java.rmi.RemoteException, br.com.governo.ws.Exception{
    if (governo == null)
      _initGovernoProxy();
    return governo.listarNotaFiscal();
  }
  
  public br.com.governo.ws.NotaFiscal emitirNotaFiscal(java.lang.String documentoReceptor, java.lang.Double valorNota) throws java.rmi.RemoteException, br.com.governo.ws.Exception{
    if (governo == null)
      _initGovernoProxy();
    return governo.emitirNotaFiscal(documentoReceptor, valorNota);
  }
  
  public br.com.governo.ws.Imposto[] listarImpostos() throws java.rmi.RemoteException, br.com.governo.ws.Exception{
    if (governo == null)
      _initGovernoProxy();
    return governo.listarImpostos();
  }
  
  
}