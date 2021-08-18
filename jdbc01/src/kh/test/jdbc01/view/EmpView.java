package kh.test.jdbc01.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.test.jdbc01.controller.EmpController;
import kh.test.jdbc01.model.EmpVo;

public class EmpView {
	private Scanner sc=new Scanner(System.in);
	
	public void mainMenu() {
		//1. 사원 정보 전체 출력
		//2. 사원 1명 추가
		//3. 사원 1명 삭제(사원번호를 입력하고  그 사원을 삭제)
		//4. 사원정보 수정 (사원번호 입력하고 해당하는 사원의 sal를 100증가)
		boolean loop=true;
		while(loop) {
		System.out.println("1.사원 정보 전체 출력 : ");
		System.out.println("2.사원 1명 추가 : ");
		System.out.println("3.사원 삭제(사원번호를 입력하고  그 사원을 삭제)");
		System.out.println("4.사원 정보 수정(사원 번호를 입력하고 해당하는 사원의 sal를  100추가)");
		System.out.println("0. 끝내기");
		
		int inputMenu=sc.nextInt();
		switch(inputMenu) {
		case 0:
			loop=false;
			break;
		case 1:
			displayEmpList();
			break;
		case 2:
			inputEmp();
			break;
		case 3:
			
			break;
		case 4:
			
			break;
			default:
				System.out.println("메뉴 번호를 눌러주세요.");
				break;
		}
		}
	}
	public void displayEmpList() {
		ArrayList<EmpVo> voList=new EmpController().selectEmpList();
		System.out.println("===================");
		System.out.println("사원 총:" + voList.size()+"명");
		System.out.println("===================");
		int totalSal=0;
		for(EmpVo vo:voList) {
			System.out.println(vo.getEname()+" : "+vo.getJob()+"업무중"+vo.getDname());
			totalSal+=vo.getSal();
		}
		System.out.println("===============");
		System.out.println("사원 총급여 : "+totalSal);
		System.out.println("================");
	}
	public void inputEmp() {
		EmpVo vo=new EmpVo();
		System.out.println("사원명을 입력해주세요:");
		String name=sc.next();
		System.out.println("사번 입력해주세요:");
		String empnoStr=sc.next();
		int empno=0;
		try {
			 empno=Integer.parseInt(empnoStr); //문자열을 int로 바꿔줌
		}catch(Exception e) {
			System.out.println("사번은 숫자만 가능합니다.");
			e.printStackTrace();
			System.out.println("프로그램을 다시 수행해 주세요");
			return;
		}
		vo.setEname(name);
		vo.setEmpno(empno);
		new EmpController().inputEmp(vo);
	}
}
