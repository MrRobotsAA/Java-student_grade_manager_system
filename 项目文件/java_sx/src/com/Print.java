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
 
			// 不打开文档
			Dispatch.put(xl, "Visible", new Variant(true));
			Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
 
			// win下路径处理(把根目录前的斜杠删掉)
			filePath = filePath.replace("/E:/","E:/");
 
			// 判断文件是否存在
			boolean fileExistFlg = fileExist(filePath);
			if (fileExistFlg) {
				Dispatch excel=Dispatch.call(workbooks,"Open",filePath).toDispatch();
				// 开始打印
				System.out.println("开始打印");
				Dispatch.get(excel,"PrintOut");
				returnFlg = true;
			}
		} 
		catch (Exception eer) {
			eer.printStackTrace();
		} finally {
			// 始终释放资源
			ComThread.Release();
		}
		System.out.println("打印完毕");
		return returnFlg;
}
	public Print(){
		// TODO Auto-generated method stub
		printFileAction("D:\\tmp_excel.xls");
              
	}

}
