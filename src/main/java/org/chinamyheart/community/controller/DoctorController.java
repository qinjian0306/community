package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.service.CaseService;
import org.chinamyheart.community.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController extends BaseController{

	@Autowired
	private DoctorService doctorService;

    @Autowired
    private CaseService caseService;

	/**
	 * 所有医生
	 * @return
	 */
	@RequestMapping("/all")
	public ReturnResult doctorAll() {
		List<Doctor> doctorList = doctorService.selectAll();
		if(doctorList.size() > 0){
			return ReturnResult.SUCCESS("获取所有医生列表成功",doctorList);
		}
		return ReturnResult.FAILUER("获取所有医生列表失败");
	}

	/**
	 * 所有待审核医生
	 * @param status
	 * @return
	 */
	@RequestMapping("/allPend")
	public ReturnResult doctorAllPend(@RequestParam(required = true,defaultValue = "0") Integer status) {
		List<Doctor> doctorList = doctorService.selectAllPend(status);
		if(doctorList.size() > 0){
			return ReturnResult.SUCCESS("获取所有待审核医生列表成功",doctorList);
		}
		return ReturnResult.FAILUER("没有待审核医生");
	}

	/**
	 * 医生认证
	 * @param hospital
	 * @param realName
	 * @param mobile
	 * @param detials
	 * @return
	 */
	@RequestMapping("/verify")
	public ReturnResult doctorVerify(@RequestParam(required = true) Integer userId,
									 @RequestParam(required = true) String hospital,
									 @RequestParam(required = true) String realName,
									 @RequestParam(required = true) String mobile,
									 @RequestParam(required = false) String detials) {
		Doctor doctor = new Doctor();
		doctor.setUserId(userId);
		doctor.setHospital(hospital);
		doctor.setRealName(realName);
		doctor.setDetials(detials);
		doctor.setStatus(0);// 默认 待审核状态
		try {
			int result = doctorService.insert(doctor);
			if(result > 0){
				return ReturnResult.SUCCESS("添加认证成功");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ReturnResult.FAILUER("添加认证失败");
	}

	/**
	 * 批准/拒绝
	 * @param doctorId
	 * @param action
	 * @return
	 */
	@RequestMapping("/pend")
	public ReturnResult doctorPend(@RequestParam(required = true) Integer doctorId,
									@RequestParam(required = true) Integer action) {
		Doctor doctor = doctorService.selectById(doctorId);
		// 批准
		if(doctor !=null && doctorId !=null && action == 1){
			doctor.setStatus(1);
		}
		// 拒绝
		if(doctor !=null && doctorId !=null && action == 2){
			doctor.setStatus(2);
		}
		try {
			doctorService.updateById(doctor);
			if(doctor.getStatus() == 1){
				return ReturnResult.SUCCESS("已批准");
			}
			if(doctor.getStatus() == 2){
				return ReturnResult.SUCCESS("已拒绝");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ReturnResult.FAILUER("审核失败");
	}

    @RequestMapping(path = "/getCaseList")
    public Object getAllCases(@RequestParam(required = true) Integer userId) {
        List<Case> cases = caseService.getCasesByUserId(userId);
        return ReturnResult.SUCCESS(cases);
    }

    @RequestMapping(path = "/downloadCase")
    public Object downloadCase(@RequestParam(required = true) String url) {

        File file = new File(url);
        if (!file.exists()) {
            return ReturnResult.FAILUER("文件不存在，下载失败");
        }
        return caseService.downloadCase(file);
    }
}
