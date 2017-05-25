/**
 * NotaFiscal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.governo.ws;

public class NotaFiscal  implements java.io.Serializable {
    private java.lang.String documentoEmissor;

    private java.lang.String documentoReceptor;

    private double valorTotal;

    private double valorTotalImpostos;

    public NotaFiscal() {
    }

    public NotaFiscal(
           java.lang.String documentoEmissor,
           java.lang.String documentoReceptor,
           double valorTotal,
           double valorTotalImpostos) {
           this.documentoEmissor = documentoEmissor;
           this.documentoReceptor = documentoReceptor;
           this.valorTotal = valorTotal;
           this.valorTotalImpostos = valorTotalImpostos;
    }


    /**
     * Gets the documentoEmissor value for this NotaFiscal.
     * 
     * @return documentoEmissor
     */
    public java.lang.String getDocumentoEmissor() {
        return documentoEmissor;
    }


    /**
     * Sets the documentoEmissor value for this NotaFiscal.
     * 
     * @param documentoEmissor
     */
    public void setDocumentoEmissor(java.lang.String documentoEmissor) {
        this.documentoEmissor = documentoEmissor;
    }


    /**
     * Gets the documentoReceptor value for this NotaFiscal.
     * 
     * @return documentoReceptor
     */
    public java.lang.String getDocumentoReceptor() {
        return documentoReceptor;
    }


    /**
     * Sets the documentoReceptor value for this NotaFiscal.
     * 
     * @param documentoReceptor
     */
    public void setDocumentoReceptor(java.lang.String documentoReceptor) {
        this.documentoReceptor = documentoReceptor;
    }


    /**
     * Gets the valorTotal value for this NotaFiscal.
     * 
     * @return valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }


    /**
     * Sets the valorTotal value for this NotaFiscal.
     * 
     * @param valorTotal
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    /**
     * Gets the valorTotalImpostos value for this NotaFiscal.
     * 
     * @return valorTotalImpostos
     */
    public double getValorTotalImpostos() {
        return valorTotalImpostos;
    }


    /**
     * Sets the valorTotalImpostos value for this NotaFiscal.
     * 
     * @param valorTotalImpostos
     */
    public void setValorTotalImpostos(double valorTotalImpostos) {
        this.valorTotalImpostos = valorTotalImpostos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotaFiscal)) return false;
        NotaFiscal other = (NotaFiscal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentoEmissor==null && other.getDocumentoEmissor()==null) || 
             (this.documentoEmissor!=null &&
              this.documentoEmissor.equals(other.getDocumentoEmissor()))) &&
            ((this.documentoReceptor==null && other.getDocumentoReceptor()==null) || 
             (this.documentoReceptor!=null &&
              this.documentoReceptor.equals(other.getDocumentoReceptor()))) &&
            this.valorTotal == other.getValorTotal() &&
            this.valorTotalImpostos == other.getValorTotalImpostos();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDocumentoEmissor() != null) {
            _hashCode += getDocumentoEmissor().hashCode();
        }
        if (getDocumentoReceptor() != null) {
            _hashCode += getDocumentoReceptor().hashCode();
        }
        _hashCode += new Double(getValorTotal()).hashCode();
        _hashCode += new Double(getValorTotalImpostos()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NotaFiscal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.governo.com.br/", "notaFiscal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoEmissor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentoEmissor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoReceptor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentoReceptor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTotalImpostos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorTotalImpostos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
