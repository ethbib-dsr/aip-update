/**
 * LockedIeException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exlibris.dps;

public class LockedIeException  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private java.lang.String lifeCycle;

    private java.lang.String lockedBy;

    public LockedIeException() {
    }

    public LockedIeException(
           java.lang.String lifeCycle,
           java.lang.String lockedBy) {
        this.lifeCycle = lifeCycle;
        this.lockedBy = lockedBy;
    }


    /**
     * Gets the lifeCycle value for this LockedIeException.
     * 
     * @return lifeCycle
     */
    public java.lang.String getLifeCycle() {
        return lifeCycle;
    }


    /**
     * Sets the lifeCycle value for this LockedIeException.
     * 
     * @param lifeCycle
     */
    public void setLifeCycle(java.lang.String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }


    /**
     * Gets the lockedBy value for this LockedIeException.
     * 
     * @return lockedBy
     */
    public java.lang.String getLockedBy() {
        return lockedBy;
    }


    /**
     * Sets the lockedBy value for this LockedIeException.
     * 
     * @param lockedBy
     */
    public void setLockedBy(java.lang.String lockedBy) {
        this.lockedBy = lockedBy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LockedIeException)) return false;
        LockedIeException other = (LockedIeException) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lifeCycle==null && other.getLifeCycle()==null) || 
             (this.lifeCycle!=null &&
              this.lifeCycle.equals(other.getLifeCycle()))) &&
            ((this.lockedBy==null && other.getLockedBy()==null) || 
             (this.lockedBy!=null &&
              this.lockedBy.equals(other.getLockedBy())));
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
        if (getLifeCycle() != null) {
            _hashCode += getLifeCycle().hashCode();
        }
        if (getLockedBy() != null) {
            _hashCode += getLockedBy().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LockedIeException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lifeCycle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lifeCycle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lockedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lockedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
