package database;

import logic.dataStrucures.dataModules.Answer;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.dataModules.Question;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.Instructor;
import logic.dataStrucures.userModules.PersonName;
import logic.dataStrucures.userModules.Student;
import logic.dataStrucures.userModules.User;
import sun.security.util.Password;

import java.sql.*;

public class Database {
    public void importDatabase(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db/Noel.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System .exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public static Student getStudent(String username){
        return Student.PLACE_HOLDER;
    }
    public static Instructor getInstructor(String username){
        return Instructor.PLACE_HOLDER;
    }
    public static LinkedList<User> getUserList(){
        return new LinkedList<User>(User.PLACE_HOLDER);
    }
    public static LinkedList<Student> getStudentList(){
        return new LinkedList<Student>(Student.PLACE_HOLDER);
    }
    public static LinkedList<Instructor> getInstructorList(){
        return new LinkedList<Instructor>(Instructor.PLACE_HOLDER);
    }
    public static LinkedList<Field> getFieldList(){
        /******************************************************************
         *
         *                              PLEASE
         *                           REMOVE THIS
         *
         * ****************************************************************/
        try {
            Answer answer=new Answer("Apple","Banana","Tomato","Carrot",'D');
            Question question=new Question("Which of the following is a fruit?",answer,1.0,0.5);
            Answer answer1=new Answer("Hand","Foot","Teeth","Shoulder",'C');
            Question question1=new Question("Which of the following is plural?",answer1,2.0,1.0);
            LinkedList<Question> questions=new LinkedList<>();
            questions.add(question);
            questions.add(question1);
            PersonName author=new PersonName("Harison","Fekadu");
            Exam exam=new Exam("SAT 1","Choose the best answer.",questions,author);
            Answer answer3=new Answer("Django","Knead","Phantom","Pneumonia",'C');
            Question question2=new Question("Of the following which word's first letter is read?",answer,3.5,1.0);
            Answer answer4=new Answer("Hollow","Empty","Void","All are answers",'D');
            Question question3=new Question("Which of the following is similar in meaning with vacant?",answer1,4.0,1.0);
            LinkedList<Question> questions1=new LinkedList<>();
            questions1.add(question2);
            questions1.add(question3);
            Exam exam2=new Exam("SAT 2","Choose the best answer.",questions1,author);
            Field field=new Field("Aptitude","ENG-101");
            field.addExam(exam);
            field.addExam(exam2);
            Field field2=new Field("Maths","MAT-101");
            Answer answer2=new Answer("-2","0","+2","C and D",'D');
            Question question4=new Question("What is the solution of (x-2)(x+2)?",answer2,2.0,2.0);
            Answer answer5=new Answer("-2","0","+2","C and D",'B');
            Question question5=new Question("What is the solution of (x-2)+(x+2)?",answer5,2.0,1.5);
            LinkedList<Question> questions3=new LinkedList<>();
            questions3.add(question4);
            questions3.add(question5);
            Exam exam1=new Exam("Algebra 1","Choose the correct answer.",questions3,author);
            field2.addExam(exam1);
            LinkedList<Field> fields=new LinkedList<>();
            fields.add(field);
            fields.add(field2);
            return fields;
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return new LinkedList<Field>(Field.PLACE_HOLDER);
    }
    public static LinkedList<Field> getFieldList(Student student){
        return new LinkedList<Field>(Field.PLACE_HOLDER);
    }
    public static LinkedList<Field> getFieldList(User user){
        return new LinkedList<Field>(Field.PLACE_HOLDER);
    }
    public static LinkedList<Field> getFieldList(String userId){
        return new LinkedList<Field>(Field.PLACE_HOLDER);
    }
    public static LinkedList<Exam> getExamList(Field field){
        return new LinkedList<Exam>(Exam.PLACE_HOLDER);
    }
    public static LinkedList<Exam> getExamList(Student student,Field field){
        return new LinkedList<Exam>(Exam.PLACE_HOLDER);
    }
    public static LinkedList<Exam> getExamList(Instructor instructor,Field field){
        return new LinkedList<Exam>(Exam.PLACE_HOLDER);
    }
    public static void addStudent(Student student){

    }
    public static void addInstructor(Instructor instructor){

    }
    public static void addField(Student student, Field field){

    }
    public static void addField(Instructor instructor, Field field){

    }
    public static void addField(Field field){

    }
    public static void addExam(Field field,Exam exam){

    }
    public static void addExam(Instructor instructor, Field field, Exam exam){

    }
    public static void addExam(Student student, Field field, Exam exam){

    }
    public static Student deleteStudent(Student student){
        return Student.PLACE_HOLDER;
    }
    public static Instructor deleteStudent(Instructor instructor){
        return Instructor.PLACE_HOLDER;
    }
    public static Field deleteField(Student student, Field field){
        return Field.PLACE_HOLDER;
    }
    public static Field deleteField(Instructor instructor, Field field){
        return Field.PLACE_HOLDER;
    }
    public static Exam deleteExam(Instructor instructor, Field field, Exam exam){
        return Exam.PLACE_HOLDER;
    }
    public static Exam deleteExam(Student student, Field field, Exam exam){
        return Exam.PLACE_HOLDER;
    }
    public static Exam updateExam(Field field, Exam exam){
        return Exam.PLACE_HOLDER;
    }
    public static void updateStudent(Student student){

    }
    public static void updateInstructor(Instructor instructor){

    }
    public static void updateFields(LinkedList<Field> fieldList){

    }
    public static void updateExamGrades(User user, Exam exam){
    }


}
