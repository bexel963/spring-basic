
// ���α׷� ���, URL�� �޼ҵ� ���� - static - x , private - x

package com.fastcampus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	
public class _01_Hello {
	int iv = 10;
	static int cv = 20;
	
	@RequestMapping("/hello")
	private void main() {
		System.out.println("���������ڸ� private�� �ص� �ܺ�(������)���� ȣ�� ����. (���ο����� private����� �Ȱ��� �Ѵ�.");
		System.out.println("Hello - main�޼��带 static���� �������� �ʾƵ� ȣ���.");
		System.out.println("����ƽ ������� ��� : " + cv);
		System.out.println("�ν��Ͻ� ������� ��� : " + iv);
		System.out.println("�޼��带 �ν��Ͻ� ����� �����ϸ� iv, cv �� �� ��� �� ���ֱ� ������ �޼��忡 static ������ �ʴ´�.");
	}
}
