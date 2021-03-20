package sample;

import javafx.beans.property.SimpleStringProperty;

import java.text.DecimalFormat;

public class StudentRecord {
    public String studentID;
    public float assignmentGrade;
    private float midtermGrade;
    private float examGrade;
    private float finalGrade;
    private String letterGrade;

    // Constructor
    public StudentRecord(String student_ID, float assignments, float midterm, float exam) {
        studentID = student_ID;
        assignmentGrade = assignments;
        midtermGrade = midterm;
        examGrade = exam;
        finalGrade = assignmentGrade * 0.2f + midtermGrade * 0.3f + examGrade * 0.5f;

        if (finalGrade < 50.0){
            letterGrade = "F";
        }
        else if (finalGrade < 60.0){
            letterGrade = "D";
        }
        else if (finalGrade < 70.0){
            letterGrade = "C";
        }
        else if (finalGrade < 80.0){
            letterGrade = "B";
        }
        else {
            letterGrade = "A";
        }
    }

    // Member Function
    public String getStudentID() { return studentID; }
    public void setStudentID(String s) { studentID = s; }

    public float getAssignmentGrade() { return assignmentGrade; }
    public void setAssignmentGrade(float f) {
        assignmentGrade = f;
        setFinalGrade();
        setLetterGrade();
    }

    public float getMidtermGrade() { return midtermGrade; }
    public void setMidtermGrade(float f) {
        midtermGrade = f;
        setFinalGrade();
        setLetterGrade();
    }

    public float getExamGrade() { return examGrade; }
    public void setExamGrade(float f) {
        examGrade = f;
        setFinalGrade();
        setLetterGrade();
    }

    public float getFinalGrade() { return finalGrade; }
    public void setFinalGrade() { finalGrade = assignmentGrade*0.2f + midtermGrade*0.3f + examGrade*0.5f;}

    public String getLetterGrade() { return letterGrade;}
    public void setLetterGrade() {
        if (finalGrade < 50.0){
            letterGrade = "F";
        }
        else if (finalGrade < 60.0){
            letterGrade = "D";
        }
        else if (finalGrade < 70.0){
            letterGrade = "C";
        }
        else if (finalGrade < 80.0){
            letterGrade = "B";
        }
        else {
            letterGrade = "A";
        }
    }
}
