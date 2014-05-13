<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	xmlns:gsd="http://www.genesyslab.com/cs"
	version="1.0">

<xsl:output method="xml" 
            media-type="text" 
            indent="no"
	          encoding="UTF-8"
            omit-xml-declaration="no"/>

  <xsl:template match="/">
    <types xmlns="http://www.w3.org/2001/XMLSchema">
    <xsl:apply-templates select="/xsd:schema/xsd:complexType"/>
    </types>
  </xsl:template>
  
  <xsl:template match="xsd:attribute">
	<xsl:copy>
	<xsl:copy-of select="@*"/>
	</xsl:copy>
  </xsl:template>

  <xsl:template match="xsd:extension|xsd:restriction">
	<xsl:attribute name="base">
	  <xsl:value-of select="@base"/>
	</xsl:attribute>
  </xsl:template>

  <xsl:template match="*">
	<xsl:copy >
	<xsl:copy-of select="@*"/>
	  <xsl:apply-templates select="xsd:complexContent/xsd:restriction[@base]|xsd:complexContent/xsd:extension[@base]"/>
	  <xsl:apply-templates select="xsd:complexContent/xsd:extension"/>
	  <xsl:apply-templates select="xsd:attribute|xsd:complexContent/xsd:extension/xsd:attribute|xsd:complexContent/xsd:restriction/xsd:attribute"/>
	</xsl:copy>
  </xsl:template>
  
  <xsl:template match="@*">
	<xsl:copy/>
  </xsl:template>
  
</xsl:stylesheet>
