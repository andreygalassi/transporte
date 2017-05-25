/**
 * GovernoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.governo.ws;

public class GovernoServiceLocator extends org.apache.axis.client.Service implements br.com.governo.ws.GovernoService {

    public GovernoServiceLocator() {
    }


    public GovernoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GovernoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GovernoPort
    private java.lang.String GovernoPort_address = "http://52.15.126.233:80/fiap-governo-ws/Governo";

    public java.lang.String getGovernoPortAddress() {
        return GovernoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GovernoPortWSDDServiceName = "GovernoPort";

    public java.lang.String getGovernoPortWSDDServiceName() {
        return GovernoPortWSDDServiceName;
    }

    public void setGovernoPortWSDDServiceName(java.lang.String name) {
        GovernoPortWSDDServiceName = name;
    }

    public br.com.governo.ws.Governo getGovernoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GovernoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGovernoPort(endpoint);
    }

    public br.com.governo.ws.Governo getGovernoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            br.com.governo.ws.GovernoServiceSoapBindingStub _stub = new br.com.governo.ws.GovernoServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getGovernoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGovernoPortEndpointAddress(java.lang.String address) {
        GovernoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.governo.ws.Governo.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.governo.ws.GovernoServiceSoapBindingStub _stub = new br.com.governo.ws.GovernoServiceSoapBindingStub(new java.net.URL(GovernoPort_address), this);
                _stub.setPortName(getGovernoPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GovernoPort".equals(inputPortName)) {
            return getGovernoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.governo.com.br/", "GovernoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.governo.com.br/", "GovernoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GovernoPort".equals(portName)) {
            setGovernoPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
