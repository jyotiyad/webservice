package com.jsc.webservices;

import java.util.List;

public class Transcript {
    private String studentFirstName;
    private String studentLastName;
    private String studentId;
    private String dob;
    private String programme;
    private String mediumOfInstruction;
    private String periodOfProgramme;

    private int durationOfProgramme;
    private String issueDate;
    private String university;
    private String totalGrade;
    private float gpa;
    private List<Course> courses;

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getMediumOfInstruction() {
        return mediumOfInstruction;
    }

    public void setMediumOfInstruction(String mediumOfInstruction) {
        this.mediumOfInstruction = mediumOfInstruction;
    }

    public String getPeriodOfProgramme() {
        return periodOfProgramme;
    }

    public void setPeriodOfProgramme(String periodOfProgramme) {
        this.periodOfProgramme = periodOfProgramme;
    }

    public int getDurationOfProgramme() {
        return durationOfProgramme;
    }

    public void setDurationOfProgramme(int durationOfProgramme) {
        this.durationOfProgramme = durationOfProgramme;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(String totalGrade) {
        this.totalGrade = totalGrade;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Transcript{" +
            "studentFirstName='" + studentFirstName + '\'' +
            ", studentLastName='" + studentLastName + '\'' +
            ", studentId='" + studentId + '\'' +
            ", dob='" + dob + '\'' +
            ", programme='" + programme + '\'' +
            ", mediumOfInstruction='" + mediumOfInstruction + '\'' +
            ", periodOfProgramme='" + periodOfProgramme + '\'' +
            ", durationOfProgramme=" + durationOfProgramme +
            ", issueDate='" + issueDate + '\'' +
            ", university='" + university + '\'' +
            ", totalGrade='" + totalGrade + '\'' +
            ", gpa=" + gpa +
            ", courses=" + courses +
            '}';
    }
}

class Course {
    private String nameOfCourse;
    private float gradesInCourse;
    private String codeForCourse;

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public float getGradesInCourse() {
        return gradesInCourse;
    }

    public void setGradesInCourse(float gradesInCourse) {
        this.gradesInCourse = gradesInCourse;
    }

    public String getCodeForCourse() {
        return codeForCourse;
    }

    public void setCodeForCourse(String codeForCourse) {
        this.codeForCourse = codeForCourse;
    }


    @Override
    public String toString() {
        return "Course{" +
            "nameOfCourse='" + nameOfCourse + '\'' +
            ", gradesInCourse=" + gradesInCourse +
            ", codeForCourse='" + codeForCourse + '\'' +
            '}';
    }
}
