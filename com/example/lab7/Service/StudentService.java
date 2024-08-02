package com.example.lab7.Service;

import com.example.lab7.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<Student>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAbsents() == 7) {
                students.get(i).setDn(true);
            }
        }
    }


    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(int id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, student);
                if (students.get(i).getAbsents() == 7) {
                    students.get(i).setDn(true);
                }
                return true;
            }
        }
        return false;
    }

    // get the student by he's ID
    public Student getStudentById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return students.get(i);
            }
        }
        return null;
    }

    //change student collage
    public boolean changeCollage(int id, String newCollage) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.get(i).setCollage(newCollage);
                return true;
            }
        }
        return false;
    }

    //if the student absent the absents counter will increase by 1, or if the student present the method return present
    public String  setStudentAbsent (int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                if (students.get(i).getAttendance().equals("absent")) {
                    students.get(i).setAbsents(students.get(i).getAbsents() + 1);

                    if (students.get(i).getAbsents() == 7) {

                        students.get(i).setDn(true);//if the absents be '7' the student DN status will turn to true
                    }

                    return "absent";

                } else if (students.get(i).getAttendance().equals("present")) {
                    return "present";
                }
            }
        }
        return null;
    }

    //get absents or present students
    public ArrayList<Student> getStudentsByAttendance (String attendance) {
        ArrayList<Student> studentsByAttendance = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAttendance().equals(attendance)) {
                studentsByAttendance.add(students.get(i));
            }
        }
        if (studentsByAttendance.isEmpty()) {
            return null;
        }
        return studentsByAttendance;
    }

}
