package searchFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteFile {
	String abpathRoot=null;
public String getAbpathRoot() {
		return abpathRoot;
	}
	public void setAbpathRoot(String abpath) {
		this.abpathRoot = abpath;
	}
public WriteFile() {
	// TODO 自动生成的构造函数存根
	this.abpathRoot="E:\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ROOT\\";
}
public WriteFile(String rootpath) {
	// TODO 自动生成的构造函数存根
	this.abpathRoot=rootpath;
}
public void saveFile(String sav,String content){
	File save=new File(abpathRoot+sav);
	FileOutputStream out=null;
	if(save.exists()){
		save.delete();
		try {
			save.createNewFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.print("存在的");
	}else{
		try {
			save.createNewFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
		try {
			out=new FileOutputStream(save);
			out.write(content.getBytes());
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

}
public void buildFileNotOverWrite(String savpath,String content){
	File save=new File(abpathRoot+savpath);
	FileOutputStream out=null;
	if(save.exists()){
		System.out.print("存在的");
	}else{
		try {
			save.createNewFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			out=new FileOutputStream(save);
			out.write(content.getBytes());
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
}
public void appendWrite(String savpath,String content){
	File save=new File(abpathRoot+savpath);
	BufferedWriter out=null;
//	FileOutputStream out=null;
	if(save.exists()){
		System.out.print("存在的");
	}else{
		try {
			save.createNewFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	try {
		out=new BufferedWriter(new FileWriter(save,true));
		
		out.append(content);
		out.newLine();
		out.flush();
		out.close();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally {
		try {
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
public void copyFileNotOverWrite(String savpath,String tarpath){
	File save=new File(abpathRoot+savpath);
	File source=new File(abpathRoot+tarpath);
	FileOutputStream out=null;
	FileInputStream in=null;
	byte[] b=new byte[32];
	int bb=0;
	if(save.exists()){
		save.delete();
		try {
			save.createNewFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.print("存在的,覆盖。。。");
	}else{
		try {
			save.createNewFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
		try {
			out=new FileOutputStream(save);
			in=new FileInputStream(source);
			while((bb=in.read())!=-1){
				out.write(bb);
				b=new byte[32];
			}
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	

}
}
