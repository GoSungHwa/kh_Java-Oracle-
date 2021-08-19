package kh.test.jdbc01.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kh.test.jdbc01.controller.EmpController;
import kh.test.jdbc01.model.EmpVo;

public class EmpView {
	private Scanner sc = new Scanner(System.in);

	public EmpView() {
	}

	public void mainMenu() {
		// 1. 사원 정보 전체 출력
		// 2. 사원 1명 추가
		// 3. 사원 삭제 (사원번호를 입력하고 그 사원을 삭제)
		// 4. 사원 정보 수정 (사원 번호를 입력하고 해당하는 사원의 sal를 100증가)
		boolean loop = true;
		while (loop) {
			System.out.println("1. 사원 정보 전체 출력");
			System.out.println("2. 사원 1명 추가");
			System.out.println("3. 사원 삭제 (사원번호를 입력하고 그 사원을 삭제)");
			System.out.println("4. 사원 정보 수정 (사원 번호를 입력하고 해당하는 사원의 sal를 100증가) ");
			System.out.println("0. 끝내기");

			int inputMenu = sc.nextInt();
			sc.nextLine();   // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 줌.
			switch (inputMenu) {
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
				deleteEmp();
				break;
			case 4:
				updateEmp();
				break;
			default:
				System.out.println("메뉴 번호만 눌러주세요!");
				break;
			}
		}
	}

	public void displayEmpList() {
		ArrayList<EmpVo> voList = new EmpController().selectEmpList();
		System.out.println("==========================");
		System.out.println("사원 총 : " + voList.size() + "명");
		System.out.println("==========================");
		int totalSal = 0;
		for (EmpVo vo : voList) {
			System.out.println(vo.getEname() + " : " + vo.getDname()+":"+vo.getEmpno()+":"+vo.getSal());
			totalSal += vo.getSal();
		}
		System.out.println("==========================");
		System.out.println("사원 총급여 : " + totalSal);
		System.out.println("==========================");
	}

	public void inputEmp() {
		EmpVo vo = new EmpVo();
		System.out.print("사원명을 입력해주세요:");
		String name = sc.next();
		sc.nextLine();   // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 줌.
		System.out.print("사번 입력해주세요:");
		String empnoStr = sc.next();
		sc.nextLine();   // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 줌.
		System.out.print("부서번호 입력해주세요:");
		String deptnoStr = sc.next();
		sc.nextLine();   // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 줌.
		int empno = 0;
		int deptno = 0;
		try {
			empno = Integer.parseInt(empnoStr);
			deptno = Integer.parseInt(deptnoStr);
		} catch (Exception e) {
			System.out.println("사번과 부서번호는 숫자만 가능합니다.");
			e.printStackTrace();
			System.out.println("프로그램을 다시 수행해 주세요.");
			return;
		}
		vo.setEname(name);
		vo.setEmpno(empno);
		vo.setDeptno(deptno);
		new EmpController().inputEmp(vo);
	}

	public int deleteEmp() {
		EmpVo vo = new EmpVo();
		System.out.print("삭제할 사원의 사번 입력해주세요:");
		String empnoStr = sc.next();
		sc.nextLine();   // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 줌.
		int empno = 0;
		try {
			empno = Integer.parseInt(empnoStr);
		} catch (Exception e) {
			System.out.println("사번과 부서번호는 숫자만 가능합니다.");
			e.printStackTrace();
			System.out.println("프로그램을 다시 수행해 주세요.");
			return empno;
		}
		vo.setEmpno(empno);
		int result=new EmpController().deleteEmp(vo);
		if(result==0) {
			System.out.println("사원을 찾지 못하여 삭제하지 못했습니다.");
		}else if(result==1) {
			System.out.println(empno+"사원을 삭제하였습니다.");
		}else{ //2,3,4,5,6,7
			System.out.println(empno+"사원이 여러명있었습니다."+result+"명 삭제되었습니다.");
		}
		return result;
		
	}
	public int updateEmp() {
		EmpVo vo = new EmpVo();
		System.out.print("sal를 100증가할 사원의 사번 입력해주세요:");
		String empnoStr = sc.next();
		sc.nextLine();   // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 줌.
		int empno = 0;
		try {
			empno = Integer.parseInt(empnoStr);
		} catch (Exception e) {
			System.out.println("사번은 숫자만 가능합니다.");
			e.printStackTrace();
			System.out.println("프로그램을 다시 수행해 주세요.");
			return empno;
		}
		vo.setEmpno(empno);
		int result=new EmpController().updateEmp(vo);
		
		if(result==0) {
			System.out.println("사원을 찾지 못하여 수정하지 못했습니다.");
		}else if(result==1) {
			System.out.println(empno+"사원을수정하였습니다.");
		}else{ //2,3,4,5,6,7
			System.out.println(empno+"사원이 여러명있었습니다."+result+"명 수정되었습니다.");
		}
		return result;
	}
}
