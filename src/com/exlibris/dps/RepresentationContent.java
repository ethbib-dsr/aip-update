/**
 * RepresentationContent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exlibris.dps;

public class RepresentationContent  implements java.io.Serializable {
    private java.lang.String fileOriginalPath;

    private com.exlibris.dps.Fixity fixity;

    private java.lang.String label;

    private java.lang.String newFile;

    private java.lang.String oldFilePid;

    private com.exlibris.dps.Operation operation;

    public RepresentationContent() {
    }

    public RepresentationContent(
           java.lang.String fileOriginalPath,
           com.exlibris.dps.Fixity fixity,
           java.lang.String label,
           java.lang.String newFile,
           java.lang.String oldFilePid,
           com.exlibris.dps.Operation operation) {
           this.fileOriginalPath = fileOriginalPath;
           this.fixity = fixity;
           this.label = label;
           this.newFile = newFile;
           this.oldFilePid = oldFilePid;
           this.operation = operation;
    }


    /**
     * Gets the fileOriginalPath value for this RepresentationContent.
     * 
     * @return fileOriginalPath
     */
    public java.lang.String getFileOriginalPath() {
        return fileOriginalPath;
    }


    /**
     * Sets the fileOriginalPath value for this RepresentationContent.
     * 
     * @param fileOriginalPath
     */
    public void setFileOriginalPath(java.lang.String fileOriginalPath) {
        this.fileOriginalPath = fileOriginalPath;
    }


    /**
     * Gets the fixity value for this RepresentationContent.
     * 
     * @return fixity
     */
    public com.exlibris.dps.Fixity getFixity() {
        return fixity;
    }


    /**
     * Sets the fixity value for this RepresentationContent.
     * 
     * @param fixity
     */
    public void setFixity(com.exlibris.dps.Fixity fixity) {
        this.fixity = fixity;
    }


    /**
     * Gets the label value for this RepresentationContent.
     * 
     * @return label
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this RepresentationContent.
     * 
     * @param label
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the newFile value for this RepresentationContent.
     * 
     * @return newFile
     */
    public java.lang.String getNewFile() {
        return newFile;
    }


    /**
     * Sets the newFile value for this RepresentationContent.
     * 
     * @param newFile
     */
    public void setNewFile(java.lang.String newFile) {
        this.newFile = newFile;
    }


    /**
     * Gets the oldFilePid value for this RepresentationContent.
     * 
     * @return oldFilePid
     */
    public java.lang.String getOldFilePid() {
        return oldFilePid;
    }


    /**
     * Sets the oldFilePid value for this RepresentationContent.
     * 
     * @param oldFilePid
     */
    public void setOldFilePid(java.lang.String oldFilePid) {
        this.oldFilePid = oldFilePid;
    }


    /**
     * Gets the operation value for this RepresentationContent.
     * 
     * @return operation
     */
    public com.exlibris.dps.Operation getOperation() {
        return operation;
    }


    /**
     * Sets the operation value for this RepresentationContent.
     * 
     * @param operation
     */
    public void setOperation(com.exlibris.dps.Operation operation) {
        this.operation = operation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RepresentationContent)) return false;
        RepresentationContent other = (RepresentationContent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fileOriginalPath==null && other.getFileOriginalPath()==null) || 
             (this.fileOriginalPath!=null &&
              this.fileOriginalPath.equals(other.getFileOriginalPath()))) &&
            ((this.fixity==null && other.getFixity()==null) || 
             (this.fixity!=null &&
              this.fixity.equals(other.getFixity()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.newFile==null && other.getNewFile()==null) || 
             (this.newFile!=null &&
              this.newFile.equals(other.getNewFile()))) &&
            ((this.oldFilePid==null && other.getOldFilePid()==null) || 
             (this.oldFilePid!=null &&
              this.oldFilePid.equals(other.getOldFilePid()))) &&
            ((this.operation==null && other.getOperation()==null) || 
             (this.operation!=null &&
              this.operation.equals(other.getOperation())));
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
        if (getFileOriginalPath() != null) {
            _hashCode += getFileOriginalPath().hashCode();
        }
        if (getFixity() != null) {
            _hashCode += getFixity().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getNewFile() != null) {
            _hashCode += getNewFile().hashCode();
        }
        if (getOldFilePid() != null) {
            _hashCode += getOldFilePid().hashCode();
        }
        if (getOperation() != null) {
            _hashCode += getOperation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RepresentationContent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "representationContent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileOriginalPath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileOriginalPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fixity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fixity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "fixity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newFile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oldFilePid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oldFilePid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "operation"));
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
