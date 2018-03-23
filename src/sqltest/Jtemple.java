//package sqltest;
//
//import static org.junit.Assert.*;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.stereotype.Repository;
//
//
////import configure.Configure;
//import spit.Spittle;
//
//@Repository
//public class Jtemple {
//	public static final String SELECT_BY_ID = "select * from usertable";
//	@Autowired
//	private JdbcOperations jo=null;
//	public Jtemple() {
//		// TODO 自动生成的构造函数存根
//	}
//	public void getById(int id) { 
//		Spittle d=null;
//		List<Spittle> sl=null;
//		d=jo.queryForObject(SELECT_BY_ID
//				,this::maps
//				,id);
//		for(Spittle s : sl){
//			System.out.println(d.getUsername());
//		}
//			
//	}
//	private Spittle maps(ResultSet rs,int row) throws SQLException{
//		return new Spittle(rs.getInt("id")
//				,rs.getInt("type")
//				,rs.getString("username")
//				,rs.getInt("QQ")
//				,rs.getString("sex")
//				);
//	}
////	@Test
////	public void testName() throws Exception {
////		ApplicationContext ct=new AnnotationConfigApplicationContext(Configure.class);
////		Jtemple bds=ct.getBean(Jtemple.class);
////		bds.getById(101);
////	}
//}
