/**
 * 
 */
/**
 * @author Hung
 *
 */
package springMvcController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pageI.PageItem;
import spit.Spittle;
import sqltest.sqlt;

@Controller
@RequestMapping("/")
public class SpiController{
	@Autowired
	@Qualifier("sqlt0")//指定注入id为 sqlt0的bean
	private sqlt sq=null;
	@Autowired
	private ArrayList<PageItem> plis=null;
	public SpiController() {
		// TODO 自动生成的构造函数存根
		System.out.println("controller!!!");
	}
	
	@RequestMapping(path="/ListPage.do",method=RequestMethod.GET)//对/welcome的请求做响应
	public String welcome(Model mav,HttpServletRequest req,HttpSession s,@PathVariable(name="id",required=false) String ss) {
		Spittle sp=sq.getSpit(sq.signIn("hung", "021501199"));
		plis.addAll(sq.allPage());
		mav.addAttribute("pagelist",plis);//向model中加入元素,作用域为request
		s.setAttribute("spituser", sp);
		System.out.print(ss+"welcome........."+sp.getId());
		return "welcome";//返回视图名
	}

	@RequestMapping(path="/ListUser.do",method=RequestMethod.GET)//对/welcome的请求做响应
	public String listuser(Model mav) {
		if(mav.containsAttribute("userlist")){
			System.out.println("exist userlist");
		}
		mav.addAttribute("userlist",sq.getSpittle());//向model中加入元素
		System.out.print("listuser.........");
		return "welcome";//返回视图名
	}
	
	@RequestMapping(path="/register.do",method=RequestMethod.POST)//对/welcome的请求做响应
	public String register(Model mav
			,@RequestParam("name") String name
			,@RequestParam("pass") String pass) {
			boolean sta=sq.signUp(name, pass);
		System.out.println(name+":"+sta+":"+pass);
		return "welcome";//返回视图名
	}
	@RequestMapping(path="/login.do",method=RequestMethod.POST)//对/welcome的请求做响应
	public String login(Model mav
			,String username //请求体中对应名字的参数会自动写入
			,String password
			,Spittle sp
			,HttpSession ses) {
		//前端传回来的参数会填充sp中对应名字的属性
		String view=null;
		int id=sq.signIn(username, password);
		System.out.println(id);
		if(id==-1){
			view="/login";
			mav.addAttribute(new Spittle(100,3,username,sp.getQQ(),sp.getSex()));
		}else if(id==-2){
			view="/login";
			mav.addAttribute(new Spittle(100,3,"username err",sp.getQQ(),sp.getSex()));
		}else{
		sp=sq.getSpit(id);
		System.out.println(sp.getId()+":"+sp.getQQ()+":"+sp.getSex()+":"+sp.getType()+":"+sp.getUsername());
		mav.addAttribute("spituser", sp);
		view="forward:/user/"+sp.getId();//spring会识别到forward关键字
		}
		return view;//重定向到项目的user路径
				//返回视图名
	}
	

	@RequestMapping(path={"/view/login","/login"})//对/welcome的请求做响应
	public Spittle login() {
		return new Spittle(100,3,"游客",000,"woman");
	}
	@RequestMapping(path={"/view/welcome","/welcome"})//对/welcome的请求做响应
	public String wel() {
		System.out.println("num:"+sq.num);
		return "welcome";
	}
	
}