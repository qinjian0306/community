package org.chinamyheart.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.Doctor;

@Mapper
public interface DoctorMapper {

	Doctor selectByUserId(int id);

}
