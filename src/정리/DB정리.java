package ����;

public class DB���� {
	// DB : �����ͺ��̽�, ǥ(���̺�) �����
	// ���� : ������ ����, ����
	// data : ��
	// base : ���� - > ǥ
	
	// ���� : �����͸� ���� �ִ� ��ǻ�� / cafe24�� �̿��ϸ� ������ �Ҵ���� �� ����, �Ƹ���(aws)�� ����
	// ������ local�� �Ұ���
	// DB(���������̺�) <---------- DBMS(DB�����ý���) <------------------- DBA(DB������)
	//												* Mysql
	// MySQL Installer for Windows(470MB) -> MYSQL ������  ���� ����Ʈ���� ����
	// server 8.0.27 x64
	// workbench 8.0.27
	// connector/j 8.0.27
	// Connector/J(Platform independent)(4.8MB)
	
	// ip : pc �ּ�(= ���ּ�)
	// port : ��Ʈ��ũ���� ������ ������ �� 
	// ��Ʈ ��ȣ Ȯ��(cmd : netstat -ano) (3307)
	// - DB�� ���� ��(***port, pw �� ���)
	
	// ��ũ��ġ ���� -> ���� ����
	
	// SQL : DML(���۾�)
	// insert : ����
	//																			(��, ��, ��)
	// Ư���ʵ� : insert into ���̺��(�ʵ��1, �ʵ��2, �ʵ��3) values (?, ?, ?);
	// ����ʵ� : insert into ���̺�� values (?, ?, ?);
	
	// �˻�(select)
	// Ư���ʵ� : select �ʵ�� from ���̺�� where ����
	// ����ʵ� : select * from ���̺�� where ����
	
	// ����(insert)
	// insert
	
	// ����(delete)
	
	// ����(update)
	// update ���̺�� set �����ʵ� 
	
	// ����(�񱳿�����, Ű����, �Լ�)
	// and : �̸鼭, �鼭, �̰�, �׸���
	//		- ����1 and ����2
	// or : �̰ų�, �ų�, �Ǵ�, �ϳ���
	//		- ����1 or ����2
	
	// select�� executeQuery() �������� executeUpdate()
	
	// next�� ���ڵ带 �����°�(�� ��)
	// get�� �ʵ带 ������ ��(���� ���ڵ��� �ʵ�)
	
	// SQL : �����ͺ��̽� ���� ���
	// DDL ���Ǿ�
	// 1. Create : ����
	// 
	
	// 2. Drop : ����
	// 3. Alter : ����
	
	
	// ���̺��
	// ObserverList<> : javafx�� ����ϴ� �÷��� �����ӿ�ũ
		// 1. ���̺� ����Ʈ ���� : Tableview�� ����Ʈ�� ���� �� setItems(ObservableList)
		// 2. ���̺� �ʵ忡 ����Ʈ �� ��ü ����
		// 2-1. ���̺� �� �� ��������
		// 2-2. �ش翭�� ��ü �� �ֱ�
	
	// ���̺�� �̸�.getSelection
	
	
	
	// 1.primary key : �⺻ Ű / �ĺ��� �ʵ�(�ߺ���x null) : �ֹι�ȣ, ���, �й�
	// 2. foreign key : �ܷ� Ű(�ٸ� ���̺��� �⺻Ű�� ����� Ű)
	// 3. auto_increment: �ڵ���ȣ ����
	// 4. not null : null�� ����(null�ϰ�� ���� �߻�)
	// 5. 
	
	
	
}
