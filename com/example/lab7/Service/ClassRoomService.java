package com.example.lab7.Service;

import com.example.lab7.Model.ClassRoom;
import jakarta.validation.constraints.Max;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class ClassRoomService {
    ArrayList<ClassRoom> classRooms = new ArrayList<>();

    public ArrayList<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public void addClassRoom(ClassRoom classRoom) {
        classRooms.add(classRoom);
    }

    public boolean removeClassRoom(String classRoom) {
        for (int i = 0; i < classRooms.size(); i++) {

            if (classRooms.get(i).getClassRoom().equalsIgnoreCase(classRoom)) {
                classRooms.remove(i);
                return true;
            }

        }
        return false;
    }

    public boolean updateClassRoom(String classRoom, ClassRoom classRoom2) {
        for (int i = 0; i < classRooms.size(); i++) {
            if (classRooms.get(i).getClassRoom().equals(classRoom)) {
                classRooms.set(i, classRoom2);
                return true;
            }
        }
        return false;
    }

    //Display all classrooms that contains the same courses
    public ArrayList<ClassRoom> getClassRoomsBycourse(String course) {
        ArrayList<ClassRoom> searchedClassRooms = new ArrayList<>();
        for (int i = 0; i < classRooms.size(); i++) {
            if (classRooms.get(i).getCourse().equalsIgnoreCase(course)) {
                searchedClassRooms.add(classRooms.get(i));
            }
        }
        if (searchedClassRooms.isEmpty()){
            return null;
        }
        return searchedClassRooms;
    }

    //Change capacity
    public boolean changeCapacity(String classRoom, @Max(50) int newCapacity) {
        for (int i = 0; i < classRooms.size(); i++) {
            if (classRooms.get(i).getClassRoom().equalsIgnoreCase(classRoom)) {
                classRooms.get(i).setCapacity(newCapacity);
                return true;
            }
        }
        return false;
    }

    //change class time
    public boolean changeTime(String classRoom, LocalTime newTime) {
        for (int i = 0; i < classRooms.size(); i++) {
            if (classRooms.get(i).getClassRoom().equalsIgnoreCase(classRoom)) {
                classRooms.get(i).setTime(newTime);
                return true;
            }
        }
        return false;
    }

    //Display class room that have the same instructor
    public ArrayList<ClassRoom> getClassRoomsByInstructor(String instructorName) {
        ArrayList<ClassRoom> instructor = new ArrayList<>();
        for (int i = 0; i < classRooms.size(); i++) {
            if (classRooms.get(i).getInstructorName().equalsIgnoreCase(instructorName)) {
                instructor.add(classRooms.get(i));
            }
        }
        if (instructor.isEmpty()) {
            return null;
        }
        return instructor;
    }
}
