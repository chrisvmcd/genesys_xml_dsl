<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	xmlns:gsd="http://www.genesyslab.com/cs"
	version="1.0"
	exclude-result-prefixes="xmlns"
	xmlns="http://www.w3.org/2001/XMLSchema">

<xsl:output method="xml" 
            media-type="text" 
            indent="no"
	          encoding="UTF-8"
            omit-xml-declaration="no"/>

  <xsl:template match="/">
    <types xmlns="http://www.w3.org/2001/XMLSchema">
      <xsl:apply-templates select="/xsd:schema/xsd:complexType[@name != 'CfgRoot']|/xsd:schema/xsd:element[@name != 'CfgData' and (not(@gsd:backLink) or @gsd:backLink != 'yes')]"/>                            
    </types>
  </xsl:template>

  <xsl:template match="xsd:complexType">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <xsl:choose>
      <xsl:when test="@name">
        <xsl:copy>
          <xsl:copy-of select="@name"/>
          <xsl:apply-templates select="xsd:complexContent|xsd:choice|xsd:sequence|xsd:group|xsd:all">
            <xsl:with-param name="maxOcc">
              <xsl:value-of select="$maxOcc"/>
            </xsl:with-param>
            <xsl:with-param name="minOcc">
              <xsl:value-of select="$minOcc"/>
            </xsl:with-param>
          </xsl:apply-templates>    
          
        </xsl:copy>
        <xsl:value-of select="'&#xa;'"/>
      </xsl:when>
      <xsl:otherwise>
          <xsl:copy-of select="@name"/>
          <xsl:apply-templates select="xsd:complexContent|xsd:choice|xsd:sequence|xsd:group|xsd:all">
            <xsl:with-param name="maxOcc">
              <xsl:value-of select="$maxOcc"/>
            </xsl:with-param>
            <xsl:with-param name="minOcc">
              <xsl:value-of select="$minOcc"/>
            </xsl:with-param>
          </xsl:apply-templates>    
          
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="xsd:element">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <xsl:copy>
      <xsl:copy-of select="@name"/>     
      <xsl:choose>
        <xsl:when test="@minOccurs">
          <xsl:copy-of select="@minOccurs"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:if test="string-length($minOcc) != 0">
            <xsl:attribute name="minOccurs">
              <xsl:value-of select="$minOcc"/>
            </xsl:attribute> 
          </xsl:if>
        </xsl:otherwise>
      </xsl:choose>

      <xsl:choose>
        <xsl:when test="@maxOccurs">
          <xsl:copy-of select="@maxOccurs"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:if test="string-length($maxOcc) != 0">
            <xsl:attribute name="maxOccurs">
              <xsl:value-of select="$maxOcc"/>
            </xsl:attribute> 
          </xsl:if>
        </xsl:otherwise>
      </xsl:choose>
      
      <xsl:apply-templates select="xsd:complexType"/>
      <xsl:if test="@type">
        <merge-with ref="{@type}"/>
      </xsl:if>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="xsd:complexContent">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <xsl:apply-templates select="xsd:restriction|xsd:extension">
      <xsl:with-param name="maxOcc">
        <xsl:value-of select="$maxOcc"/>
      </xsl:with-param>
      <xsl:with-param name="minOcc">
        <xsl:value-of select="$minOcc"/>
      </xsl:with-param>
    </xsl:apply-templates>    
  </xsl:template>

  <xsl:template match="xsd:restriction">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <merge-with ref="{@base}"/>
    <xsl:apply-templates select="xsd:group|xsd:all|xsd:choice|xsd:sequence">
      <xsl:with-param name="maxOcc">
        <xsl:value-of select="$maxOcc"/>
      </xsl:with-param>
      <xsl:with-param name="minOcc">
        <xsl:value-of select="$minOcc"/>
      </xsl:with-param>
    </xsl:apply-templates>    
  </xsl:template>

  <xsl:template match="xsd:extension">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <merge-with ref="{@base}"/>
    <xsl:apply-templates select="xsd:group|xsd:all|xsd:choice|xsd:sequence">
      <xsl:with-param name="maxOcc">
        <xsl:value-of select="$maxOcc"/>
      </xsl:with-param>
      <xsl:with-param name="minOcc">
        <xsl:value-of select="$minOcc"/>
      </xsl:with-param>
    </xsl:apply-templates>    
    
  </xsl:template>

  <xsl:template match="xsd:group">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <xsl:apply-templates select="xsd:all|xsd:choice|xsd:sequence">
      <xsl:with-param name="maxOcc">
        <xsl:choose>
          <xsl:when test="@maxOccurs">
            <xsl:value-of select="@maxOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$maxOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      <xsl:with-param name="minOcc">
        <xsl:choose>
          <xsl:when test="@minOccurs">
            <xsl:value-of select="@minOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$minOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      
    </xsl:apply-templates>    
        
  </xsl:template>

  <xsl:template match="xsd:all">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <xsl:apply-templates select="xsd:element[not(@gsd:backLink) or @gsd:backLink != 'yes']">

      <xsl:with-param name="maxOcc">
        <xsl:choose>
          <xsl:when test="@maxOccurs">
            <xsl:value-of select="@maxOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$maxOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      <xsl:with-param name="minOcc">
        <xsl:choose>
          <xsl:when test="@minOccurs">
            <xsl:value-of select="@minOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$minOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      
    </xsl:apply-templates>    
    
  </xsl:template>

  <xsl:template match="xsd:choice">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <xsl:apply-templates select="xsd:choice|xsd:element[not(@gsd:backLink) or @gsd:backLink != 'yes']|xsd:sequence|xsd:group">
    
      <xsl:with-param name="maxOcc">
        <xsl:choose>
          <xsl:when test="@maxOccurs">
            <xsl:value-of select="@maxOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$maxOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      <xsl:with-param name="minOcc">
        <xsl:choose>
          <xsl:when test="@minOccurs">
            <xsl:value-of select="@minOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$minOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      
    </xsl:apply-templates>    
    
  </xsl:template>

  <xsl:template match="xsd:sequence">
    <xsl:param name="maxOcc"/>
    <xsl:param name="minOcc"/>
    <xsl:apply-templates select="xsd:choice|xsd:element[not(@gsd:backLink) or @gsd:backLink != 'yes']|xsd:sequence|xsd:group">
    
      <xsl:with-param name="maxOcc">
        <xsl:choose>
          <xsl:when test="@maxOccurs">
            <xsl:value-of select="@maxOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$maxOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      <xsl:with-param name="minOcc">
        <xsl:choose>
          <xsl:when test="@minOccurs">
            <xsl:value-of select="@minOccurs"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="$minOcc"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:with-param>
      
    </xsl:apply-templates>    
  </xsl:template>
  
</xsl:stylesheet>
