package com.entor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entor.entity.Student;
import com.entor.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@RequestMapping("/add")
	public void add() {
		
	}
	@RequestMapping("/update")
	public void update() {
		
	}
	@RequestMapping("/deleteById")
	public String deleteById(int id) {
		studentService.deleteById(id);
		return "删除成功";
	}
	
	@RequestMapping("/queryById")
	public Student queryById(int id) {
		return studentService.queryById(id);
	}
	@RequestMapping("/queryAll")
	public List<Student> queryAll() {
		return studentService.queryAll();
	}
	@RequestMapping("/queryByPage")
	public List<Student> queryByPage(int currentPage,int pageSize) {
		return studentService.queryByPage(currentPage, pageSize);
	}
	@RequestMapping("/queryByPage2")
	public PageInfo<Student> queryByPage2(int currentPage,int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Student> list = studentService.queryAll();
		PageInfo<Student> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
}
