package com.springboot.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Result;
import com.springboot.model.Student;
import com.springboot.service.IStudentService;

@RestController
@RequestMapping("/test")
public class StudentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    
	@Autowired
	private IStudentService studentService;
	
	@PostMapping("/list")
	public Result list(HttpServletRequest rsquest) {
	    LOGGER.info("学生List");
	    List<Object[]> list = studentService.listStudent();
        return Result.ok(list);
	}
	
	@PostMapping("/listMap")
	public Result listMap(HttpServletRequest request) {
	    LOGGER.info("学生Map");
	    List<Map<Object, Object>> listMap = studentService.listStudentMap();
	    return Result.ok(listMap);
	}

    @PostMapping("/listModel")
	public Result listModel(HttpServletRequest request) {
	    LOGGER.info("学生Model");
	    List<Student> list = studentService.listStudentModel();
	    return Result.ok(list);
	}
}