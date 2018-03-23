package searchFile;

import java.io.File;

public class DeleteFile {
	String path=null;
	String abpathRoot=null;
	
	public DeleteFile() {
		// TODO 自动生成的构造函数存根
		this.abpathRoot="E:\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ROOT\\tiezi\\";
	}
	public DeleteFile(String abpath) {
		// TODO 自动生成的构造函数存根
		this.abpathRoot=abpath;
	}
	
	public void deleteFromDisk(String path) {
		File f=new File(abpathRoot+path);
		if(f.exists()){
			f.delete();
		}else{
			System.out.println("no such file");
		}
	}
	
//	public static void main(String[] args) {
//		DeleteFile df=new DeleteFile();
//		df.deleteFromDisk("474208155756391957.txt");
//	}

}
