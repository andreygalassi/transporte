/**
 * Governo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.governo.ws;

public interface Governo extends java.rmi.Remote {
    public br.com.governo.ws.NotaFiscal[] listarNotaFiscal() throws java.rmi.RemoteException, br.com.governo.ws.Exception;
    public br.com.governo.ws.NotaFiscal emitirNotaFiscal(java.lang.String documentoReceptor, java.lang.Double valorNota) throws java.rmi.RemoteException, br.com.governo.ws.Exception;
    public br.com.governo.ws.Imposto[] listarImpostos() throws java.rmi.RemoteException, br.com.governo.ws.Exception;
}
