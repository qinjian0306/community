package org.chinamyheart.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.Doctor;

@Mapper
public interface DoctorMapper {

	Doctor selectByUserId(int id);

}
