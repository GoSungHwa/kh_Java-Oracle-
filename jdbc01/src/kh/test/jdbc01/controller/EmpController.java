package kh.test.jdbc01.controller;

import java.util.ArrayList;

import kh.test.jdbc01.model.EmpDao;
import kh.test.jdbc01.model.EmpVo;

public class EmpController {
	public ArrayList<EmpVo> selectEmpList() {
		return new EmpDao().selectList();
	}
	public void inputEmp(EmpVo vo) {
		new EmpDao().inputEmp(vo);
	}
}
