<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ent="http://wandrell.com/example/ws/entity">
  <xsl:output method="xml" encoding="UTF-8"  indent="yes" omit-xml-declaration="yes"/>
  <xsl:param name="id"/>

  <!-- Transform to SOAP request body -->
  <xsl:template match="/">
    <ent:getEntity>
      <id><xsl:value-of select="$id"/></id>
    </ent:getEntity>
  </xsl:template> 

</xsl:stylesheet>