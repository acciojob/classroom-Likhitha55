package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentDB;
    HashMap<String,Teacher> teacherDB;
    HashMap<String, List<String>> teacherStudentDB;
    public StudentRepository(){
        this.studentDB = new HashMap<>();
        this.teacherDB = new HashMap<>();
        this.teacherStudentDB = new HashMap<>();
    }
    public void addStudent(Student obj){
        studentDB.put(obj.getName(),obj);
    }
    public void addTeacher(Teacher obj){
        teacherDB.put(obj.getName(),obj);

    }
    public void addStudentTeacherPair(String student,String teacher){
        teacherStudentDB.put(teacher,new ArrayList<String>());
        List<String> list = teacherStudentDB.get(teacher);
        list.add(student);
        teacherStudentDB.put(teacher,list);
        Teacher t = teacherDB.get(teacher);
        t.setNumberOfStudents(t.getNumberOfStudents()+1);
    }
    public Student getStudentByName(String name){
        System.out.println(studentDB.get(name));
        return studentDB.get(name);
    }
    public Teacher getTeacherByName(String name){
        for(String s : teacherDB.keySet()){
            if(s.equals(name)){
                return teacherDB.get(s);
            }
        }
        return null;
    }
    public List<String> getStudentsByTeacherName(String name){
        for(String s : teacherStudentDB.keySet()){
            if(s.equals(name)){
                return teacherStudentDB.get(s);
            }
        }
        return null;
    }
    public List<String> getAllStudents(){
        List<String> list = new ArrayList<>();
        for(String s:studentDB.keySet()){
            list.add(s);
        }
        return list;
    }
    public void deleteTeacherByName(String name){
        teacherDB.remove(name);
        teacherStudentDB.remove(name);
    }
    public void deleteAllTeachers(){
        teacherDB.clear();
        teacherStudentDB.clear();

    }
}
