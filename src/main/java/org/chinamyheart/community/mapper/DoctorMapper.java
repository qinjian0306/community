package org.chinamyheart.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.Doctor;

import java.util.List;

@Mapper
public interface DoctorMapper {

	int insert(Doctor doctor);

	Doctor selectById(int id);

	Doctor selectByUserId(int id);

	// 所有医生列表
	List<Doctor> selectAll();

	// 待审核医生列表
	List<Doctor> selectAllPend(Integer status);

	void updateById(Doctor doctor);

	void updateByUserId(Doctor doctor);

}
