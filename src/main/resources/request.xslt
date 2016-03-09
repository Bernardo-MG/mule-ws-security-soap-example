<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:sam="http://sample.endpoint.swss.mule.example.wandrell.com">
  <xsl:output method="xml" encoding="UTF-8"  indent="yes" omit-xml-declaration="yes"/>
  <xsl:param name="cod1"/>
  <xsl:param name="cod2"/>

  <!-- Transform from outer to inner -->
  <xsl:template match="/">
    <sam:getSample>
      <cod1><xsl:value-of select="$cod1"/></cod1>
      <cod2><xsl:value-of select="$cod2"/></cod2>
    </sam:getSample>
  </xsl:template> 

</xsl:stylesheet>