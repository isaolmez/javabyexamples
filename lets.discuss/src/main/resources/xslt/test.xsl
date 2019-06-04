<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/ | *">
    <xsl:apply-templates select="node()"/>
  </xsl:template>

  <xsl:template match="product[@sku='70000']">
    <xsl:element name="last"/>
  </xsl:template>
  <xsl:template match="processing-instruction() | comment()">
    <xsl:comment/>
  </xsl:template>
</xsl:stylesheet>