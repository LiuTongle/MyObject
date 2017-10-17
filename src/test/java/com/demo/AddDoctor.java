package com.demo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mavenMVC.entity.Department;
import com.mavenMVC.entity.Doctor;
import com.mavenMVC.entity.Hospital;
import com.mavenMVC.entity.Title;
import com.mavenMVC.service.IDepartmentService;
import com.mavenMVC.service.IDoctorService;
import com.mavenMVC.service.IHospitalService;
import com.mavenMVC.service.ITitleService;
import com.mavenMVC.util.HttpRequestUtil;
import com.mavenMVC.util.MD5;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })*/
public class AddDoctor {
	@Autowired
	IDoctorService doctorService;
	@Autowired
	private IHospitalService iHospitalService;

	@Autowired
	private IDepartmentService iDepartmentService;

	@Autowired
	private ITitleService iTitleService;

	@Test
	public void addDoctor() {

		try {
			String sourceFile = "D:\\333.xls"; // 源文件
			Workbook book = Workbook.getWorkbook(new File(sourceFile));
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			for (int i = 0; i < rows; i++) {
				String name = null;
				String doctorTitle = null;
				String doctorHospital = null;
				String doctorDepartment = null;
				String phone = null;
				for (int j = 0; j < cols; j++) {
					String content = sheet.getCell(j, i).getContents();
					switch (j) {
					case 0:
						name = content;
						break;
					case 1:
						doctorTitle = content;
						break;
					case 2:
						doctorDepartment = content;
						break;
					case 3:
						doctorHospital = content;
						break;
					case 4:
						phone = content;
						break;
					}
				}
				System.out.println(name+","+doctorTitle+","+doctorDepartment+","+doctorHospital+","+phone);
				if (doctorService.ifDoctorCellphoneRegisted(phone)) {
					System.out.println("用户====" + phone + "====已经被注册了！");
					continue;
				}
				String password = new MD5().GetMD5Code(phone);
				Doctor doc = doctorService.registerDoctor(name, password, phone);
				String param = "name="+name+"&cellphone="+phone+"&password="+password;
				HttpRequestUtil.sendGet("http://122.114.52.243:8088/publicCAS/user/doctor/sync",param);
				if ((doctorHospital != null) && (!doctorHospital.trim().equals(""))) {
					Hospital hospital = iHospitalService.searchHospitalByName(doctorHospital);
					if (hospital != null) {
						doc.setDoctorHospital(hospital.getHospitalId());
					} 
				}
				if ((doctorDepartment != null) && (!doctorDepartment.trim().equals(""))) {
					Department department = iDepartmentService.searchDepartmentByName(doctorDepartment);
					if (department != null) {
						doc.setDoctorDepartment(department.getDepartmentId());
					} 
				}
				if ((doctorTitle != null) && (!doctorTitle.trim().equals(""))) {
					Title title = iTitleService.searchTitleByName(doctorTitle);
					if (title != null) {
						doc.setDoctorTitle(title.getTitleId());
					}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d =sdf.parse("2016-09-19 09:00:00");
				long moing = d.getTime();
				d =sdf.parse("2016-09-19 18:00:00");
				long after = d.getTime();
				long random = (long)(moing+(Math.random()*(after-moing+1)));
				doc.setCreateTime(random);
				doc.setLastModTime(random);
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
