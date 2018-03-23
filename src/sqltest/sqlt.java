package sqltest;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import pageI.PageItem;
import spit.Spittle;

//@Component
//@Qualifier("sqlt")
//@Primary
//@Scope
@Repository
public class sqlt {
	static int a=0;
	Connection con=null;
	Statement datas=null;
	ResultSet res=null;
	public int num=-1;
	@Autowired
	public sqlt(DataSource das){
		System.out.println("the:"+a);
		num=a++;
		// TODO 自动生成的构造函数存根
		//----访问数据库的模版
		try {
//-1-----加载驱动
			//使用从Microsoft下载的驱动
			/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");*/
			//使用系统库自带的驱动    
//			Class.forName("java.sql.Driver");
			/*System.out.println("加载驱动");*/
//-2-----创建链接
			/*con=DriverManager.getConnection("jdbc:sqlserver://"
					+ "localhost:49183;DatabaseName=UserManager","plus","123456");*/
			//------------------:数据库端口-----------:数据库名-:登录名--:密码----
//			Context context=new InitialContext();
//			DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/DataSource");
//			con=ds.getConnection();
			System.out.println("从数据源获取连接");
			con=das.getConnection();
//-3-----创建SQL语句对象，用于发送数据
			datas=con.createStatement();
			System.out.println("创建SQL语句对象，用于发送数据");
//-4-----关闭连接和Sql语句对象closeall()
		} catch (SQLException /*| ClassNotFoundException */e) {
			e.printStackTrace();
		}
	}
	
	synchronized 
	public void closeall(){
		try {
			con.close();
//			con.prepareStatement("insert into usertable valuessss(sd,sd,sd,sfd,d)");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			datas.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
//-5------可以进行数据库的操作了
	ResultSet excuteQ(String sql) throws SQLException{
		//ResultSet指向返回标的第一行，即属性行
		res=datas.executeQuery(sql);
		return res;
		
	}
	void excuteU(String sql) throws SQLException{
		datas.execute(sql);
		
	}
	int maxID(){
		int ID=0;
		ResultSet res=null;
		try {
			res=excuteQ("select * from usertable");
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		try {
			while(res.next()){
				if(ID<res.getInt("id")){
					ID=res.getInt("id");
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ID;
	}
	int getNewID(){//给新注册用户一个新可用ID
		Random ran=null;
		ran=new Random();
		int id=Math.abs(ran.nextInt()+101);
		while(searchUserId(id)!=-1){id=Math.abs(ran.nextInt()+101);}
		return id;
	}	
	
	public List<Spittle> getSpittle(){
		List<Spittle> spittles=new ArrayList<Spittle>();
		ResultSet res=null;
		try {
			res = excuteQ("select * from usertable");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			while(res.next()){
				spittles.add(
						new Spittle(
							res.getInt("id"),
							res.getInt("type"),
							res.getString("username"),
							res.getInt("QQ"),
							res.getNString("sex")
							)
						);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return spittles;
	}
	int printUserInfo() throws SQLException{
		List<Spittle> splist=getSpittle();
		Iterator<Spittle> iteratorSpi=splist.iterator();
		Spittle spitt;
		int n=0;
		while(iteratorSpi.hasNext()){
			spitt=iteratorSpi.next();
			System.out.println("id:"+spitt.getId());
			System.out.println("username:"+spitt.getUsername());
			System.out.println("type:"+spitt.getType());	
			System.out.println("QQ:"+spitt.getQQ());
			System.out.println("sex:"+spitt.getSex());
			System.out.println("-----------------------------------");
			n++;
		}
		return n;
	}
	
	public Spittle getSpit(int id){
				List<Spittle> splist=null;
					splist = getSpittle();
				Iterator<Spittle> iteratorSpi=splist.iterator();
				Spittle spitt=null;
				Spittle spitt1=null;
				int n=0;
				while(iteratorSpi.hasNext()){
					spitt1=iteratorSpi.next();
					if(spitt1.getId()==id){
					spitt=spitt1;
					}
					n++;
				}
		return spitt;
	}
	public void updateSpit(Spittle spit){
		setQQ(spit.getId(), spit.getQQ());
		setSex(spit.getId(), spit.getSex());
		changeUserName(spit.getId(), spit.getUsername());
}
	boolean matchName(String name){
		ResultSet res=null;
		boolean state=false;
		try {
			res=excuteQ("select * from usertable where username='"+name+"'");
			state=res.next();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return state;
	}
	public boolean DestoryUser(int id){//删除用户
		boolean state=true;
		try {
			excuteU("delete from password where id="+id+"");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			state=false;
		}
		try {
			excuteU("delete from usertable where id="+id+"");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			state=false;
		}
		return state;
	}
	public int getID(String username){
		ResultSet res=null;
		int id=0;
		if(matchName(username)){
		try {
			res = excuteQ("select id from usertable where username='"+username+"'");
			res.next();
			id=res.getInt("id");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}}else{
			System.out.println("no this user:"+username);
		}
		
		return id;
	}
	public int signIn(String username,String password){
		int id=0;
		ResultSet res=null;
		if(matchName(username)){
		try {
			res=excuteQ("select password from password where id in "
					+ "(select id from usertable where username='"+username+"')");
			res.next();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			
		}
		try {
			if(res.getString("password").equals(password)){
				id=getID(username);
			}else {
				System.out.println("err password");
				id=-1;
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		}else{
			System.out.println("no this username:"+username);
			id=-2;
		}
		return id;
	}
	public boolean signUp(String username,String password){
		int id=getNewID();
		int type=3;
		boolean state=true;
		if(!matchName(username)){
		try {
			excuteU("insert into usertable values("+id+",'"+username+"',"+type+",null,null"+")");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			state=false;
			
		}
		try {
			excuteU("insert into password values("+id+",'"+password+"')");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			try {
				excuteU("delete from password where id="+id+")");
				excuteU("delete from usertable where id="+id+")");
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			state=false;
		}}else{
			System.out.println("username:"+username+" have been used");
			state=false;
		}
		
		return state;
	}
	public int changeType(int id,int type){
		try {
			excuteU("update usertable set type="+type+" where id="+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return type;
	}
	private int changeId(int id,int id1){
		try {
			excuteU("update usertable set id="+id1+" where id="+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return id1;
	}
	private int changeUserName(int id,String name){
		try {
			excuteU("update usertable set username='"+name+"' where id="+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return id;
	}
	public int setQQ(int id,int QQ){
		try {
			excuteU("update usertable set QQ="+QQ+" where id="+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return QQ;
	}
	public String setSex(int id,String sex){
		try {
			excuteU("update usertable set sex='"+sex+"' where id="+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return sex;
	}
	public int changePassword(String name,String curpassword,String newpassword){
		int id=0;
		id=signIn(name,curpassword);
		try {
			excuteU("update password set password='"+newpassword+"' where id="+id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(id!=0){
			System.out.println("change password sucessful");
		}else {
			System.out.println("change password false");
		}
		return id;
	}
	public int searchUserId(int userid){
		ResultSet resset=null;
		boolean st=false;
		int pid=-1;
		try {
			resset=excuteQ("select id from usertable where id="+userid);
			st=resset.next();
			System.out.println("have:"+st);
			if(st){
				pid=resset.getInt("id");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pid;
	}

	public long addPage(int ownerid,String title,int readLevel){
		Random ran=null;
		ran=new Random();
		long newpid=Math.abs(ran.nextLong());
		System.out.println(newpid);
		while(searchPageId(newpid)!=-1){newpid=Math.abs(ran.nextLong());}
		try {
			excuteU("insert into pagetable values("
					+newpid+","
					+ownerid+",'"
					+title+"',"
					+readLevel
					+",getdate()"
					+")");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return newpid;
	}
	public long searchPageId(long pageid){
		ResultSet resset=null;
		boolean st=false;
		long pid=-1;
		try {
			resset=excuteQ("select PageId from pagetable where PageId="+pageid);
			st=resset.next();
			System.out.println("have:"+st);
			if(st){
				pid=resset.getBigDecimal("PageId").longValue();
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pid;
	}
	public List<PageItem> searchPageByOwnerId(int OwnerId){
		ResultSet res=null;
		List<PageItem> pageList=new ArrayList<>();
		try {
			res=excuteQ("select * from pagetable where OwnerId="+OwnerId+"");
			while(res.next()){
				PageItem temp=new PageItem();
				temp.setEditTime(res.getTimestamp("EditTime"));
				temp.setOwnerId(res.getInt("OwnerId"));
				temp.setReadLevel(res.getInt("ReadLevel"));
				temp.setPageId(res.getLong("PageId"));
				temp.setTitle(res.getString("Title"));
				pageList.add(temp);
				System.out.println(temp.getPageId());
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pageList;
	}
	public List<PageItem> searchPageByTitle(String Title){
		ResultSet res=null;
		List<PageItem> pageList=new ArrayList<>();
		try {
			res=excuteQ("select * from pagetable where Title='"+Title+"'");
			while(res.next()){
				PageItem temp=new PageItem();
				temp.setEditTime(res.getTimestamp("EditTime"));
				temp.setOwnerId(res.getInt("OwnerId"));
				temp.setReadLevel(res.getInt("ReadLevel"));
				temp.setPageId(res.getLong("PageId"));
				temp.setTitle(Title);
				pageList.add(temp);
				System.out.println(temp.getPageId());
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pageList;
	}
	public List<PageItem> searchPage(int ownerId,String Title){
		ResultSet res=null;
		List<PageItem> pageList=new ArrayList<>();
		try {
			res=excuteQ("select * from pagetable where Title='"+Title+"' and OwnerId="+ownerId);
			while(res.next()){
				PageItem temp=new PageItem();
				temp.setEditTime(res.getTimestamp("EditTime"));
				temp.setOwnerId(ownerId);
				temp.setReadLevel(res.getInt("ReadLevel"));
				temp.setPageId(res.getLong("PageId"));
				temp.setTitle(Title);
				pageList.add(temp);
				System.out.println(temp.getPageId());
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pageList;
	}
	public List<PageItem> searchPageByType(int ReadLevel){
		ResultSet res=null;
		List<PageItem> pageList=new ArrayList<>();
		try {
			res=excuteQ("select * from pagetable where ReadLevel="+ReadLevel);
			while(res.next()){
				PageItem temp=new PageItem();
				temp.setEditTime(res.getTimestamp("EditTime"));
				temp.setOwnerId(res.getInt("ownerId"));
				temp.setReadLevel(res.getInt("ReadLevel"));
				temp.setPageId(res.getLong("PageId"));
				temp.setTitle(res.getString("Title"));
				pageList.add(temp);
				System.out.println(temp.getPageId());
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pageList;
	}
	public List<PageItem> allPage(){
		ResultSet res=null;
		List<PageItem> pageList=new ArrayList<>();
		try {
			res=excuteQ("select * from pagetable");
			while(res.next()){
				PageItem temp=new PageItem();
				temp.setEditTime(res.getTimestamp("EditTime"));
				temp.setOwnerId(res.getInt("OwnerId"));
				temp.setReadLevel(res.getInt("ReadLevel"));
				temp.setPageId(res.getLong("PageId"));
				temp.setTitle(res.getString("Title"));
				pageList.add(temp);
				System.out.println(temp.getPageId());
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pageList;
	}
	public boolean delPageById(long pageid){
		boolean sta=false;
		try {
			excuteU("delete from pagetable where PageId="+pageid);
			System.out.println("pageid:"+pageid+" delete sucessful");
			sta=true;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			sta=false;
		}
		return sta;
	}
	public void updatePageEditTime(long pageid){
		try {
			excuteU("update pagetable set EditTime=getdate() where PageId="+pageid);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public Timestamp getPageLastEditTime(long pageid){
		ResultSet res=null;
		Timestamp timestamp=null;
		try {
			res=excuteQ("select EditTime from pagetable where PageId="+pageid);
			while(res.next()){
				System.out.println(res.getTimestamp("EditTime"));
				timestamp=res.getTimestamp("EditTime");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return timestamp;
	}
	public void listPage(List<PageItem> lis){
		Iterator<PageItem> pit=lis.iterator();
		while(pit.hasNext()){
			PageItem temp=pit.next();
			System.out.println("PageId:"+temp.getPageId());
			System.out.println("OwnerId:"+temp.getOwnerId());
			System.out.println("Title:"+temp.getTitle());
			System.out.println("EditTime:"+temp.getEditTime());
			System.out.println("ReadLevel:"+temp.getReadLevel());
			System.out.println("--------------------------------------");
		}
		System.out.println("total "+lis.size()+" items");
		
	}
	public void changePageLevel(long pageid,int readlevel){
		try {
			excuteU("update pagetable set ReadLevel="+readlevel+" where PageId="+pageid);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		try {
			
			sqlt s=new sqlt();
			String g="";
			List<PageItem> plist=null;
			BigInteger pid=new BigInteger("7699721721319488650");
			//System.out.println("id="+s.signIn("yourBofss","02150111299"));
			//s.DestoryUser(108);
			//s.res=s.excuteQ("select * from usertable");
			//s.changeId(108,100);
			//s.signUp("该罚的", "123456");
			//s.searchPageId(1234563);
//			s.addPage(102, "sdhfnsdf", 3);
//			s.getPageLastEditTime(123456);
//			s.getPageLastEditTime(12345678);
//			s.delPageById(pid.longValue());
//			plist=s.allPage();
//			s.updatePageEditTime(plist.get(2).getPageId());
//			s.listPage(plist);
//			System.out.println("::idnew"+s.getNewID());
			int id=s.signIn("hung","021501199");
			//s.changePassword("hung", "61251", "021501199");
			//s.signIn("hung","021501199");
//			s.changeType(101, 1);
			//s.setQQ(101, 619904620);
			System.out.println("totally:"+s.printUserInfo()+" items"+id);
			
			System.out.println("finally:"+g);
			s.closeall();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
