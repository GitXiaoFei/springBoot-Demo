package com.springboot.service;

import java.util.List;
import java.util.Map;

import com.springboot.model.Student;


public interface IStudentService {
    /**
     * 返回list<Object[]>
     * @return
     */
    List<Object[]> listStudent();
    
    List<Student> listStudentModel();
    
    List<Map<Object, Object>> listStudentMap();
}