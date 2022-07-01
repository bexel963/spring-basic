package com.fastcampus.spring2;

public interface UserDao {

	int deleteUser(String id);

	User selectUser(String id);

	// ����� ������ user_info���̺� �����ϴ� �޼���
	int insertUser(User user);

	// �Ű������� ���� ����� ������ user_info���̺��� update�ϴ� �޼���
	int updateUser(User user);
	
	void deleteAll() throws Exception;
}