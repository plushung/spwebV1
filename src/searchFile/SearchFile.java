/**
 * 
 */
/**
 * @author Hung
 *
 */
package searchFile;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class SearchFile{
	String path=null;
	String abpathRoot=null;
	public String getAbpathRoot() {
		return abpathRoot;
	}
	public void setAbpathRoot(String abpath) {
		this.abpathRoot = abpath;
	}

	int num=0;

	public SearchFile(String abpath) {
		// TODO 自动生成的构造函数存根
		this.abpathRoot=abpath;
	}
	public SearchFile() {
		// TODO 自动生成的构造函数存根
		this.abpathRoot="E:\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ROOT\\newWeb\\";
	}
	
public boolean listfile(String path,int n,OutputStream out){
		//将从f下扫描到的路径信息输出到out输出流
		File f=new File(abpathRoot+path);
		boolean y=true;
		try {
			if(f.isDirectory()){
				out.write("\n".getBytes());
				for(int i=0;i<n;i++){
					out.write("  ".getBytes());
					}
				out.write((n+"[+]"+f.getName()).getBytes());
				out.flush();
				File []list=f.listFiles();
				for(int i=0;i<list.length;i++){
					y=listfile(list[i].getPath(),n+1,out);
					if(!y)break;
				}
			}else {
				out.write("\n".getBytes());
				for(int i=0;i<n;i++){
					out.write("  ".getBytes());}
				out.write((n+"[-]"+f.getName()+"\tnum:"+(++num)+"  \t"+f.getPath()).getBytes());
				out.flush();
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return y;
	}



public Iterator<String> listfile(String path,int n,List<String> List_String) throws IOException{
	//将从f下扫描到的路径信息输出到out
	File f=new File(abpathRoot+path);
	Iterator<String> it=null;
	try {
	if(f.isDirectory()){
		for(int i=0;i<n;i++){
			}
		File []list=f.listFiles();
		for(int i=0;i<list.length;i++){
			it=listfile(list[i].getPath(),n+1,List_String);
		}
	}else {
		for(int i=0;i<n;i++){
		List_String.add(f.getName());
		it=List_String.iterator();
		}
	}
	} catch (IOException e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	return it;
}	
public List<String> listfileL(String path,int n,List<String> List_String) throws IOException{
	//将从f下扫描到的路径信息输出到out
	File f=new File(abpathRoot+path);
	try {
	if(f.isDirectory()){
		for(int i=0;i<n;i++){
			}
		File[] list=f.listFiles();
		System.out.println("len:"+list.length);
		for(int i=0;i<list.length;i++){
			List_String=listfileL("//"+list[i].getName(),n+1,List_String);
		}
	}else {
		for(int i=0;i<n;i++){
		List_String.add(f.getName());
		}
	}
	} catch (IOException e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	return List_String;
}

public static ArrayList<String> findfile(String rootpath,int n,ArrayList<String> List_String) throws IOException{
	//将从f下扫描到的路径信息输出到out
	File f=new File(rootpath);
	try {
	if(f.isDirectory()){
		for(int i=0;i<n;i++){
			}
		File[] list=f.listFiles();
		for(int i=0;i<list.length;i++){
			List_String=findfile(rootpath+"//"+list[i].getName(),n+1,List_String);
		}
	}else {
//		for(int i=0;i<n;i++){
			String[] fn=f.getName().split("\\.");
			switch(fn[fn.length-1]){
			case "mp4":
			case "mkv":
			case "rm":
			case "rmvb":
			case "avi":	List_String.add(f.getName());break;
			default :break;
			}
//		}
	}
	} catch (IOException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return List_String;
}

public static ArrayList<String> searchPathFile(String rootpath){
	ArrayList<String> list=new ArrayList<String>(); 
	try {
		list=findfile(rootpath,0,list);
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	return list;
}

private static File found(String rootpath,String filename,ArrayList<String> List_String) throws IOException{
	//将从f下扫描到的路径信息输出到out
	File f=new File(rootpath);
	File ffff=null;
	
	try {
	if(f.isDirectory()){
		File[] list=f.listFiles();
		for(int i=0;i<list.length;i++){
			if(ffff==null){
			ffff=found(rootpath+"//"+list[i].getName(),filename,List_String);
			}else{
				break;
			}
		}
	}else {
//		for(int i=0;i<n;i++){

			if(f.getName().equals(filename)){
				System.out.println(filename+":::::"+f.getName());
				ffff=f;
//			}
		}
	}
	} catch (IOException e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	return ffff;
}

public static File getFile(String rootpath,String filename){
	File f=null;
	try {
		f=found(rootpath,filename,new ArrayList<String>());
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	return f;
}
	public static void main(String[] args) {
//		String s="ss.s.s.s.s.s.s.sss.s";
//		String[] ss=s.split("\\.");
//		System.out.println(ss.length);
//		SearchFile ser=new SearchFile("E:/迅雷下/movie");
//		Iterator<String> itt=null;
//		List<String> li=searchPathFile("E:/迅雷下载/movie");
//		for(String s:li){
//			System.out.println(s);
//		}
//		try {
////			ser.listfile(ser.path, 0, System.out);
//			li=ser.listfileL(ser.path, 0, li);
//			itt=li.listIterator();
//			System.out.println("\n-------------------------------------");
//			while(itt.hasNext()){
//				System.out.println(itt.next()+"}");
//			}
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
	}
	
}