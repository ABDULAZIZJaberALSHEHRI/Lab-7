package com.example.lab7.Controller;

import com.example.lab7.ApiResponse.ApiResponse;
import com.example.lab7.Model.ClassRoom;
import com.example.lab7.Service.ClassRoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/class-room")
@RequiredArgsConstructor
public class ClassRoomController {
    private final ClassRoomService classRoomService;


    @GetMapping("/get-class-rooms")
    public ResponseEntity getClassRooms() {
        ArrayList<ClassRoom> classRooms = classRoomService.getClassRooms();
        return ResponseEntity.status(200).body(classRooms);
    }

    @PostMapping("/add-class-room")
    public ResponseEntity addClassRoom(@Valid @RequestBody ClassRoom classRoom, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        classRoomService.addClassRoom(classRoom);
        return ResponseEntity.status(200).body(new ApiResponse("Class room added successfully"));
    }

    @DeleteMapping("/del-classroom/{classroom}")
    public ResponseEntity delClassRoom(@PathVariable String classroom) {
        boolean isDel = classRoomService.removeClassRoom(classroom);
        if (isDel) {
            return ResponseEntity.status(200).body(new ApiResponse("Class room deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Class room not found"));
    }

    //Display all classrooms that contains the same instructor
    @PutMapping("/update-classroom/{classroom}")
    public ResponseEntity updateClassRoom(@PathVariable String classroom, @Valid @RequestBody ClassRoom classRoom2, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = classRoomService.updateClassRoom(classroom,classRoom2);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Class room updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Class room not found"));
    }

    //Display all classrooms that contains the same courses
    @GetMapping("/get-by-course/{course}")
    public ResponseEntity getClassRoomBycourse(@PathVariable String course) {
        if (classRoomService.getClassRoomsBycourse(course) == null){
            return ResponseEntity.status(404).body(new ApiResponse("Class room not found"));
        }
        return ResponseEntity.status(200).body(classRoomService.getClassRoomsBycourse(course));
    }


    //Change capacity
    @PutMapping("/change-capacity/{classroom}")
    public ResponseEntity changeCapacity(@PathVariable String classroom, @Valid @RequestBody @Max(50) int newcapacity, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if (classRoomService.changeCapacity(classroom,newcapacity)) {
            return ResponseEntity.status(200).body(new ApiResponse("Class room updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Class room not found"));
    }

    //change class time
    @PutMapping("/change-time/{classroom}")
    public ResponseEntity changeTime(@PathVariable String classroom,@Valid @RequestBody LocalTime newtime) {
        boolean timeChange = classRoomService.changeTime(classroom,newtime);
        if (timeChange){
            return ResponseEntity.status(200).body(new ApiResponse("Class room updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Class room not found"));
    }



    //Display all classrooms that contains the same courses
    @GetMapping("/get-classroom-by-instructor/{instructor}")
    public ResponseEntity getClassRoomByInstructor(@PathVariable String instructor) {
        if (classRoomService.getClassRoomsByInstructor(instructor) == null){
            return ResponseEntity.status(404).body(new ApiResponse("Class room not found"));
        }
        return ResponseEntity.status(200).body(classRoomService.getClassRoomsByInstructor(instructor));
    }
}
