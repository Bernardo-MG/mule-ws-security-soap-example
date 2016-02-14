<?xml version="1.0" encoding="UTF-8"?> 
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:weat="http://ws.cdyne.com/WeatherWS/">
  <xsl:output method="xml" encoding="UTF-8"  indent="yes" omit-xml-declaration="yes"/>
  <xsl:param name="zip"/>

  <!-- Transform from outer to inner -->
  <xsl:template match="/">
    <weat:GetCityForecastByZIP>
      <weat:ZIP><xsl:value-of select="$zip"/></weat:ZIP>
    </weat:GetCityForecastByZIP>
  </xsl:template> 

</xsl:stylesheet>