package org.chinamyheart.community.service.impl;

import org.chinamyheart.community.mapper.DoctorMapper;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

	@Autowired
	private DoctorMapper doctorMapper;


	@Override
	public int insert(Doctor doctor) {
		doctor.setCreateTime(new Date());
		try {
			int result = doctorMapper.insert(doctor);
			if(result > 0){
				return 1;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Doctor selectById(int id) {
		Doctor doctor = doctorMapper.selectById(id);
		return doctor;
	}

	@Override
	public Doctor selectByUserId(int id) {
		Doctor doctor = doctorMapper.selectByUserId(id);
		return doctor;
	}

	// 所有医生列表
	@Override
	public List<Doctor> selectAll() {
		List<Doctor>  doctors = doctorMapper.selectAll();
		return doctors;
	}

	// 待审核医生列表
	@Override
	public List<Doctor> selectAllPend(Integer status) {
		List<Doctor>  doctors = doctorMapper.selectAllPend(status);
		return doctors;
	}

	@Override
	public void updateById(Doctor doctor) {
		doctor.setCreateTime(new Date());
		try {
			doctorMapper.updateById(doctor);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateByUserId(Doctor doctor) {
		doctor.setCreateTime(new Date());
		try {
			doctorMapper.updateByUserId(doctor);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
