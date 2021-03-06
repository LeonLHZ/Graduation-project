package com.dms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dms.constant.Constant;
import com.dms.entity.Admin;
import com.dms.entity.Building;
import com.dms.entity.Employer;
import com.dms.entity.Student;
import com.dms.entity.User;
 
/**
 *
 * Description: Excel操作
 * 
 * CreateTime: 2017年12月11日  下午3:08:09
 *
 * Change History:
 *
 *        Date             CR Number              Name              Description of change
 *
 */
public class ExcelBuilding {
 
	private static final String EXCEL_XLS = "xls";  
    private static final String EXCEL_XLSX = "xlsx";  
  
    /** 
     * 判断Excel的版本,获取Workbook 
     * @param in 
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public static Workbook getWorkbok(InputStream in,File file) throws IOException{  
        Workbook wb = null;  
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003  
            wb = new HSSFWorkbook(in);  
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
            wb = new XSSFWorkbook(in);  
        }  
        return wb;  
    }  
  
    /** 
     * 判断文件是否是excel 
     * @throws Exception  
     */  
    public static void checkExcelVaild(File file) throws Exception{  
        if(!file.exists()){  
            throw new Exception("文件不存在");  
        }  
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){  
            throw new Exception("文件不是Excel");  
        }  
    }  
    
    /** 
     * 读取Excel测试，兼容 Excel 2003/2007/2010 
     * @throws Exception  
     */  
    public static List<User> AddStudentByExcel() throws Exception {  
    	List<User> list = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
        try {  
            // 同时支持Excel 2003、2007  
            File excelFile = new File("D:\\img\\employer.xlsx"); // 创建文件对象  
            FileInputStream in = new FileInputStream(excelFile); // 文件流  
            checkExcelVaild(excelFile);  
            Workbook workbook = getWorkbok(in,excelFile);  
            //Workbook workbook = WorkbookFactory.create(excelFile); // 这种方式 Excel2003/2007/2010都是可以处理的  
  
            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量  
            /** 
             * 设置当前excel中sheet的下标：0开始 
             */  
          Sheet sheet = workbook.getSheetAt(0);   // 遍历第一个Sheet  
            //Sheet sheet = workbook.getSheetAt(2);   // 遍历第三个Sheet  
            
            //获取总行数
          System.out.println(sheet.getLastRowNum());
            
            // 为跳过第一行目录设置count  
            int count = 0;
           
            for (Row row : sheet) {
            	try {
            		// 跳过第一和第二行的目录  
                    if(count < 1 ) {
                        count++;  
                        continue;  
                    }
                    
                    //如果当前行没有数据，跳出循环  
                    if(row.getCell(0).toString().equals("")){  
                    	return null;
                    }
                    
                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                
                    System.out.println("总列数：" + columnTotalNum);
                    
                    System.out.println("最大列数：" + row.getLastCellNum());
                    
                    //for循环的，不扫描空格的列
//                    for (Cell cell : row) { 
//                    	System.out.println(cell);
//                    }
                    int end = row.getLastCellNum();
                    User user = new User();
                    Admin admin=new Admin();
                    Employer employer =new Employer();
                    String id=UUIDUtils.getId();
                    
                    user.setUid(id);
                    user.setPassword(MD5Utils.md5("123456"));
                    
                    
                   
                    admin.setAid(id);
                   
                   employer.setEid(id);
                    for (int i = 0; i < end; i++) {
                    	Cell cell = row.getCell(i);
                    	if(cell == null) {
                    		System.out.print("null" + "\t");
                    		continue;
                    	}
                    
                    	Object obj = getValue(cell);
                    	
                    	//add
                    	if(i==0) {
                    		switch (obj.toString()) {
							case "宿舍管理员":
								user.setType(Constant.ADMIN);
								user.setAdmin(admin);
								break;
                          case "维修工":
                        	  user.setType(Constant.EMPLOYEE);
                        	  user.setEmployer(employer);
								break;
							default:
								break;
							}
                    		
                    	}
                    	if(i==1) {
                    		user.setUsername(obj.toString());
                    		admin.setUsername(obj.toString());
                    		employer.setUsername(obj.toString());
         
                    	}
                    	if(i==2) {
                    		
                    		admin.setName(obj.toString());
                    		employer.setName(obj.toString());
                    	}
                    	
                       if(i==3) {
                    		
                    		admin.setSex(obj.toString());
                    		employer.setSex(obj.toString());
                    	}
                       if(i==4) {
                   		
                   		admin.setTelephone(obj.toString());
                   		employer.setTelephone(obj.toString());
                   	}
                      
                    	System.out.print(obj + "\t");
					}
                    
                    list.add(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        System.out.println(list);
		return list;
    }
    
    private static Object getValue(Cell cell) {
    	Object obj = null;
    	switch (cell.getCellTypeEnum()) {
	        case BOOLEAN:
	            obj = cell.getBooleanCellValue(); 
	            break;
	        case ERROR:
	            obj = cell.getErrorCellValue(); 
	            break;
	        case NUMERIC:
	            Double b = cell.getNumericCellValue(); 
	            obj=b.longValue();
	         
	            break;
	        case STRING:
	            obj = cell.getStringCellValue(); 
	            break;
	        default:
	            break;
    	}
    	return obj;
    }
}



