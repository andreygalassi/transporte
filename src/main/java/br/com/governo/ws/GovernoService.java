/**
 * GovernoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.governo.ws;

public interface GovernoService extends javax.xml.rpc.Service {
    public java.lang.String getGovernoPortAddress();

    public br.com.governo.ws.Governo getGovernoPort() throws javax.xml.rpc.ServiceException;

    public br.com.governo.ws.Governo getGovernoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
