package com.entor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entor.entity.Student;
import com.entor.mapper.StudentMapper;
import com.entor.service.IStudentService;

@Service
@CacheConfig(cacheNames="students")//缓存键值对
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentMapper studentMapper;
	@Override
	public void add(Student student) {
		studentMapper.add(student);
	}

	@Override
	public void update(Student student) {
		studentMapper.update(student);
	}

	@Override
	@CacheEvict(allEntries=true)//清除该组的所有缓存
	public void deleteById(int id) {
		studentMapper.deleteById(id);
	}

	@Override
	@Cacheable(key="'studenId_'+#p0")//@Cacheable 作用：先从缓存中查询是否有数据，把方法返回值保存到缓存 id=222 保存到redis中的key的名称是studentId:222 #p0指的是方法的第一个参数
	public Student queryById(int id) {
		return studentMapper.queryById(id);
	}

	@Override
	@Cacheable(key="'student_'+#p0+'_'+#p1")//student_1_10
	public List<Student> queryByPage(int currentPage, int pageSize) {
		return studentMapper.queryByPage((currentPage-1)*pageSize, pageSize);
	}

	@Override
	public List<Student> queryAll() {
		return studentMapper.queryAll();
	}

}
