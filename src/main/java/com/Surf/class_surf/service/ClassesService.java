package com.Surf.class_surf.service;

import com.Surf.class_surf.models.Classes;
import com.Surf.class_surf.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;

@Service
public class ClassesService {
    @Autowired
    private ClassesRepository classesRepository;

    public Classes postCreateClasses(Classes classes){
        return classesRepository.save(classes);
    }

    public void deleteClassesById(Long id){
        classesRepository.deleteById(id);
    }

    public Classes getFindClassById(Long id) {
        return classesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clase no agendada"));
    }

    public Classes getFindClassByDate(LocalDate date) {
        return classesRepository.findByDate(date)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clase no agendada"));
    }

    public Classes pathChangeDate (String nameClass, Classes classes){
        Classes existingClass = classesRepository.findByNameClass(nameClass).orElse(null);

        if(existingClass == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clase no agendada");
        }

        if(classes.getDate() != null){
            existingClass.setDate(classes.getDate());
            return classesRepository.save(existingClass);
        }
        return existingClass;
    }
}
