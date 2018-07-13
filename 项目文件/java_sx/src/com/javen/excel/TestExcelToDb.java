package com.javen.excel;

import java.util.List;

import com.javen.db.DBhepler;
import com.javen.entity.StuEntity;
import com.javen.service.StuService;

public class TestExcelToDb {
	public static void main(String[] args) {
		//�õ���������е�����
		List<StuEntity> listExcel=StuService.getAllByExcel("C://book2.xls");
		/*//�õ����ݿ�������е�����
		List<StuEntity> listDb=StuService.getAllByDb();*/
		
		DBhepler db=new DBhepler();
		
		for (StuEntity stuEntity : listExcel) {
			int id=stuEntity.getId();
			if (!StuService.isExist(id)) {
				//�����ھ����
				String sql="insert into stu (name,sex,num) values(?,?,?)";
				String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+""};
				db.AddU(sql, str);
			}else {
				//���ھ͸���
				String sql="update stu set name=?,sex=?,num=? where id=?";
				String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+"",id+""};
				db.AddU(sql, str);
			}
		}
	}
}
