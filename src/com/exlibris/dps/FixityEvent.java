/**
 * FixityEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exlibris.dps;

public class FixityEvent  implements java.io.Serializable {
    private java.lang.String agentName;

    private java.lang.String algorithmName;

    private java.lang.String copyId;

    private java.util.Calendar date;

    private java.lang.String filePid;

    private java.lang.Boolean status;

    public FixityEvent() {
    }

    public FixityEvent(
           java.lang.String agentName,
           java.lang.String algorithmName,
           java.lang.String copyId,
           java.util.Calendar date,
           java.lang.String filePid,
           java.lang.Boolean status) {
           this.agentName = agentName;
           this.algorithmName = algorithmName;
           this.copyId = copyId;
           this.date = date;
           this.filePid = filePid;
           this.status = status;
    }


    /**
     * Gets the agentName value for this FixityEvent.
     * 
     * @return agentName
     */
    public java.lang.String getAgentName() {
        return agentName;
    }


    /**
     * Sets the agentName value for this FixityEvent.
     * 
     * @param agentName
     */
    public void setAgentName(java.lang.String agentName) {
        this.agentName = agentName;
    }


    /**
     * Gets the algorithmName value for this FixityEvent.
     * 
     * @return algorithmName
     */
    public java.lang.String getAlgorithmName() {
        return algorithmName;
    }


    /**
     * Sets the algorithmName value for this FixityEvent.
     * 
     * @param algorithmName
     */
    public void setAlgorithmName(java.lang.String algorithmName) {
        this.algorithmName = algorithmName;
    }


    /**
     * Gets the copyId value for this FixityEvent.
     * 
     * @return copyId
     */
    public java.lang.String getCopyId() {
        return copyId;
    }


    /**
     * Sets the copyId value for this FixityEvent.
     * 
     * @param copyId
     */
    public void setCopyId(java.lang.String copyId) {
        this.copyId = copyId;
    }


    /**
     * Gets the date value for this FixityEvent.
     * 
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this FixityEvent.
     * 
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the filePid value for this FixityEvent.
     * 
     * @return filePid
     */
    public java.lang.String getFilePid() {
        return filePid;
    }


    /**
     * Sets the filePid value for this FixityEvent.
     * 
     * @param filePid
     */
    public void setFilePid(java.lang.String filePid) {
        this.filePid = filePid;
    }


    /**
     * Gets the status value for this FixityEvent.
     * 
     * @return status
     */
    public java.lang.Boolean getStatus() {
        return status;
    }


    /**
     * Sets the status value for this FixityEvent.
     * 
     * @param status
     */
    public void setStatus(java.lang.Boolean status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FixityEvent)) return false;
        FixityEvent other = (FixityEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.agentName==null && other.getAgentName()==null) || 
             (this.agentName!=null &&
              this.agentName.equals(other.getAgentName()))) &&
            ((this.algorithmName==null && other.getAlgorithmName()==null) || 
             (this.algorithmName!=null &&
              this.algorithmName.equals(other.getAlgorithmName()))) &&
            ((this.copyId==null && other.getCopyId()==null) || 
             (this.copyId!=null &&
              this.copyId.equals(other.getCopyId()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.filePid==null && other.getFilePid()==null) || 
             (this.filePid!=null &&
              this.filePid.equals(other.getFilePid()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getAgentName() != null) {
            _hashCode += getAgentName().hashCode();
        }
        if (getAlgorithmName() != null) {
            _hashCode += getAlgorithmName().hashCode();
        }
        if (getCopyId() != null) {
            _hashCode += getCopyId().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getFilePid() != null) {
            _hashCode += getFilePid().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FixityEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "fixityEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("algorithmName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "algorithmName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("copyId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "copyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filePid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "filePid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
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
