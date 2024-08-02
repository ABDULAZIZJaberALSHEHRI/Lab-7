package com.example.lab7.Controller;

import com.example.lab7.ApiResponse.ApiResponse;
import com.example.lab7.Model.Student;
import com.example.lab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-students")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add-student")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity updateStudent(@PathVariable int id,@Valid @RequestBody Student student, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = studentService.updateStudent(id, student);
        if(isUpdated){
            return ResponseEntity.status(201).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("/del-student/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id){
        boolean isDel = studentService.deleteStudent(id);
        if(isDel){
            return ResponseEntity.status(201).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getStudentById(@PathVariable int id){
        if (studentService.getStudentById(id) == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        }
        return ResponseEntity.status(200).body(studentService.getStudentById(id));
    }

    @PutMapping("/change-collage/{id}/{newcollage}")
    public ResponseEntity changeCollage(@PathVariable int id, @PathVariable String newcollage){
        boolean isChanged = studentService.changeCollage(id, newcollage);
        if(isChanged){
        return ResponseEntity.status(201).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @PutMapping("/set-absent/{id}")
    public ResponseEntity setAbsent(@PathVariable int id){
        String isAbsent = studentService.setStudentAbsent(id);

        if (isAbsent.equalsIgnoreCase("absent")){
            return ResponseEntity.status(200).body(new ApiResponse("Student absents counter updated successfully"));
        }else if (isAbsent.equalsIgnoreCase("present")){
            return ResponseEntity.status(200).body(new ApiResponse("Student is present"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }


    @GetMapping("/get-students-by-attendance/{attendance}")
    public ResponseEntity getStudentsByAttendance(@PathVariable String attendance){
        if (studentService.getStudentsByAttendance(attendance) == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Students not found"));
        }
        return ResponseEntity.status(200).body(studentService.getStudentsByAttendance(attendance));
    }
}
