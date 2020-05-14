package com.dms.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.dms.entity.ExcelBean;
import com.dms.entity.Student;

public class ExcelOut {
public static void make(ExcelBean excelBean,HttpServletResponse response) throws IOException {
	
	//创建HSSFWorkbook对象(excel的文档对象)
     HSSFWorkbook wb = new HSSFWorkbook();
       //建立新的sheet对象（excel的表单）
HSSFSheet sheet=wb.createSheet(excelBean.getSheelName());
//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
HSSFRow row1=sheet.createRow(0);
//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
HSSFCell cell=row1.createCell(0);
    //设置单元格内容
cell.setCellValue(excelBean.getExcelName());
//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
//在sheet里创建第二行
HSSFRow row2=sheet.createRow(1);    
    //创建单元格并设置单元格内容
if(excelBean.getList().get(0) instanceof Student) {
	creatStudent(excelBean, sheet);
}
/*
Map<String,Object> map = list.get(0);
int x = 0;
for (String key : map.keySet()) {
	row2.createCell(x).setCellValue(key);
	x++;
}*/
    
   /* row2.createCell(1).setCellValue("班级");    
    row2.createCell(2).setCellValue("笔试成绩");
row2.createCell(3).setCellValue("机试成绩");    */



    //在sheet里创建第三行
/*int y = 0;
for (int t = 0 ; t<list.size();t++) {
	 HSSFRow row3=sheet.createRow(t+2);
	 Map<String,Object> m = list.get(t);
	
		 row3.createCell(y).setCellValue((String) m.get(key));
		 y++;
	
}*/
   
    
   /* row3.createCell(1).setCellValue("As178");
    row3.createCell(2).setCellValue(87);    
    row3.createCell(3).setCellValue(78);   */ 
//.....省略部分代码


//输出Excel文件
  OutputStream output=response.getOutputStream();
  response.reset();
  response.setHeader("Content-disposition", "attachment; filename=Excel.xls");
  response.setContentType("application/msexcel");        
  wb.write(output);
  output.close();



              }

public static void creatStudent(ExcelBean excelBean,HSSFSheet sheet) {
	HSSFRow row2=sheet.createRow(1); 
   String[] strings = excelBean.getStudent();
	int x = 0;
	for (String string : strings) {
		row2.createCell(x).setCellValue(string);
		x++;
	}

	    
	   /* row2.createCell(1).setCellValue("班级");    
	    row2.createCell(2).setCellValue("笔试成绩");
	row2.createCell(3).setCellValue("机试成绩");    */


   List<Object> list = excelBean.getList();
	    //在sheet里创建第三行
	
	for (int t = 0 ; t<list.size();t++) {
		 HSSFRow row3=sheet.createRow(t+2);
		Student student = (Student) list.get(t);
		String username = student.getUsername();
		
			row3.createCell(0).setCellValue(username);
		
			row3.createCell(1).setCellValue(student.getName());
			row3.createCell(2).setCellValue(student.getSex());
			
				row3.createCell(3).setCellValue(student.getBuilding().getNumber());
			
				
			
				row3.createCell(4).setCellValue(student.getDormitory().getNum());
		
		
			
		
				row3.createCell(5).setCellValue(student.getPosition().getNumbering());
			
			
			
			row3.createCell(6).setCellValue(student.getTelephone());
		
				
			
				
				
	}
}

}
