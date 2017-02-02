<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
  xmlns:ts="http://www.jsc.transcript.se" xmlns:epr="http://www.jsc.employmentRecord.se" xmlns:ci="http://www.jsc.companyInfo.se"
  xmlns:cv="http://www.jsc.shortCv.se"  xmlns:app="http://www.jsc.applicantProfile.se">
  <xsl:template match="/">
    <xsl:element name="app:applicantProfile">

      <xsl:element name="app:personalInformation">
        <xsl:element name="app:ApplicantName">
          <xsl:value-of select="concat(document('transcript.xml')//ts:StudentFirstName ,' ',document('transcript.xml')//ts:StudentLastName)"/>
        </xsl:element>
        <xsl:element name="app:City">
          <xsl:value-of select="document('shortCv.xml')//cv:City"/>
        </xsl:element>
        <xsl:element name="app:SSN">
          <xsl:value-of select="document('shortCv.xml')//cv:SSN"/>
        </xsl:element>
        <xsl:element name="app:DOB">
          <xsl:value-of select="document('transcript.xml')//ts:DOB"/>
        </xsl:element>
      </xsl:element>

      <xsl:element name="app:EducationalQualification">
        <xsl:element name="app:University">
          <xsl:value-of select="document('transcript.xml')//ts:University"/>
        </xsl:element>
          <xsl:element name="app:Degree">
              <xsl:value-of select="document('transcript.xml')//ts:Degree"/>
          </xsl:element>
          <xsl:element name="app:PassingYear">
              <xsl:value-of select="document('transcript.xml')//ts:PeriodOfProgramme"/>
          </xsl:element>
            <xsl:element name="app:NameOfProgramme">
              <xsl:value-of select="document('transcript.xml')//ts:Programme"/>
            </xsl:element>
            <xsl:element name="app:TotalGrade">
              <xsl:value-of select="round (sum(document('transcript.xml')//ts:GradesInCourse) div count(document('transcript.xml')//ts:Course) *100) div 100"/>
            </xsl:element>
            <xsl:element name="app:DurationOfProgramme">
              <xsl:value-of select="document('transcript.xml')//ts:DurationOfProgramme"/>
            </xsl:element>
        <xsl:element name="app:PeriodOfProgramme">
          <xsl:value-of select="document('transcript.xml')//ts:PeriodOfProgramme"/>
        </xsl:element>
      </xsl:element>

      <xsl:element name="app:EmploymentInfo">
          <xsl:element name="app:Employment">
          <xsl:element name="app:companyName">
            <xsl:value-of select="document('employmentRecord.xml')//epr:companyName"/>
          </xsl:element>
              <xsl:element name="app:Designation">
                  <xsl:value-of select="document('employmentRecord.xml')//epr:designation"/>
              </xsl:element>
          <xsl:element name="app:startDate">
            <xsl:value-of select="document('employmentRecord.xml')//epr:startDate"/>
          </xsl:element>
          <xsl:element name="app:endDate">
            <xsl:value-of select="document('employmentRecord.xml')//epr:endDate"/>
          </xsl:element>
          </xsl:element>
      </xsl:element>
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>
