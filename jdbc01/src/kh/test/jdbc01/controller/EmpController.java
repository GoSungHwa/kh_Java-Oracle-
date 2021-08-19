package kh.test.jdbc01.controller;

import java.util.ArrayList;

import kh.test.jdbc01.model.EmpDao;
import kh.test.jdbc01.model.EmpVo;

public class EmpController {

	public EmpController() {
	}
	
	public  ArrayList<EmpVo> selectEmpList() {
		return new EmpDao().selectList();
	}
	
	public void inputEmp(EmpVo vo) {
//		new EmpDao().inputEmp(vo);
		new EmpDao().inputEmpPreparedStatement(vo);
	}
	public int deleteEmp(EmpVo vo) {
		return new EmpDao().deleteEmp(vo);
	}
	public int updateEmp(EmpVo vo) {
		return new EmpDao().updateEmp(vo);	
		}
}
