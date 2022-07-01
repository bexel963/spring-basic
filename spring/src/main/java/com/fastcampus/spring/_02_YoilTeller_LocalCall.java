
// - ��, ��, ���� �Է��ϸ� �ش� ��¥�� ���� �������� �˷��ִ� ���α׷�.
// ��ǻ��(����)�� �ִ� ���α׷��� �����Ų��. 

package com.fastcampus.spring;

import java.util.Calendar;

public class _02_YoilTeller_LocalCall {

	public static void main(String[] args) {
		
		// < �Է� >
		
		String year = args[0];
		String month = args[1];
		String day = args[2];
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// < �۾� >
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:�Ͽ���, 2:������ ....
		char yoil = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		
		// < ��� >
		
		System.out.println(year + "�� " + month + "�� " + day + "���� ");
		System.out.println(yoil + "���� �Դϴ�.");
		
		// target -> classes ������ class ���ϵ��� �ִ�. (���⼭ ����)
		// target���� ��Ŭ�� -> show in local terminal Ŭ�� -> terminal Ŭ���ؼ� cd classes �Է� �� java ��Ű����.Ŭ������ �Ű����� �Է��� ����
	}
}
