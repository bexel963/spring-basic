
// - 년, 월, 일을 입력하면 해당 날짜가 무슨 요일인지 알려주는 프로그램.
// 컴퓨터(로컬)에 있는 프로그램을 실행시킨다. 

package com.fastcampus.spring;

import java.util.Calendar;

public class _02_YoilTeller_LocalCall {

	public static void main(String[] args) {
		
		// < 입력 >
		
		String year = args[0];
		String month = args[1];
		String day = args[2];
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// < 작업 >
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:일요일, 2:월요일 ....
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		// < 출력 >
		
		System.out.println(year + "년 " + month + "월 " + day + "일은 ");
		System.out.println(yoil + "요일 입니다.");
		
		// target -> classes 폴더에 class 파일들이 있다. (여기서 실행)
		// target폴더 우클릭 -> show in local terminal 클릭 -> terminal 클릭해서 cd classes 입력 후 java 패키지명.클래스명 매개변수 입력후 앤터
	}
}
