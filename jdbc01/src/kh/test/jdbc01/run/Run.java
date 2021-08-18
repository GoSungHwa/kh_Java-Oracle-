package kh.test.jdbc01.run;

import kh.test.jdbc01.view.EmpView;

public class Run {

	public static void main(String[] args) {
		//1. 사원 정보 전체 출력
		//2. 사원 1명 추가
		//3. 사원 1명 삭제(사원번호를 입력하고  그 사원을 삭제)
		//4. 사원정보 수정 (사원번호 입력하고 해당하는 사원의 sal를 100증가)
		
		//emp 테이블에서  사원들 정보를 읽어서 화면에 출력
		new EmpView().mainMenu();
	}

}
