package com;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.*;

public class Print {

	private static boolean fileExist(String filePath){
		boolean flag = false;
		try {
			File file = new File(filePath);
			flag = file.exists();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static boolean printFileAction(String filePath){
		boolean returnFlg = false;
		try {
			
			
			ComThread.InitSTA();
			ActiveXComponent xl = new ActiveXComponent("Excel.Application");
 
			// �����ĵ�
			Dispatch.put(xl, "Visible", new Variant(true));
			Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
 
			// win��·������(�Ѹ�Ŀ¼ǰ��б��ɾ��)
			filePath = filePath.replace("/E:/","E:/");
 
			// �ж��ļ��Ƿ����
			boolean fileExistFlg = fileExist(filePath);
			if (fileExistFlg) {
				Dispatch excel=Dispatch.call(workbooks,"Open",filePath).toDispatch();
				// ��ʼ��ӡ
				System.out.println("��ʼ��ӡ");
				Dispatch.get(excel,"PrintOut");
				returnFlg = true;
			}
		} 
		catch (Exception eer) {
			eer.printStackTrace();
		} finally {
			// ʼ���ͷ���Դ
			ComThread.Release();
		}
		System.out.println("��ӡ���");
		return returnFlg;
}
	public Print(){
		// TODO Auto-generated method stub
		printFileAction("D:\\tmp_excel.xls");
              
	}

}
