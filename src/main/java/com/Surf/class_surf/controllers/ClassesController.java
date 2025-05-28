package com.Surf.class_surf.controllers;

import com.Surf.class_surf.models.Classes;
import com.Surf.class_surf.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Classes createClasses(@RequestBody Classes monitor) {
        return classesService.postCreateClasses(monitor);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.PROCESSING)
    private ResponseEntity<Void> deleteClasses(@PathVariable Long id){
        classesService.deleteClassesById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Classes getFindClassById(@PathVariable Long id){
        return classesService.getFindClassById(id);
    }

    @GetMapping("/date/{date}")
    @ResponseStatus(HttpStatus.FOUND)
    private Classes getFindClassByDate(@PathVariable LocalDate date){
        return classesService.getFindClassByDate(date);
    }

    @PatchMapping("/updateDate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Classes patchChangeDate(@PathVariable Long id, @RequestBody Classes classes) {
        return classesService.patchChangeDate(id, classes);
    }
}
