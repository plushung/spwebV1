package springMvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sqltest.sqlt;

@RequestMapping("/user")
@Controller
public class userController {
	@Autowired
	@Qualifier("sqlt1")
	sqlt sq=null;
	
	@RequestMapping(path="/{id}")//对/welcome的请求做响应
	public String user(Model mav,
			@PathVariable String id//将url路径中的变量id写入
			) {
	
		mav.addAttribute("spituser",sq.getSpit(Integer.parseInt(id)));
		return "welcome";//返回视图名
	}
	@RequestMapping(path="/list/{id}/pagelist")//对/welcome的请求做响应
	public String lis(Model mav,@PathVariable String id) {
		System.out.println("pagelist");
		mav.addAttribute("spituser",sq.getSpit(Integer.parseInt(id)));
		mav.addAttribute("pagelist",sq.allPage());
		return "welcome";//返回视图名
	}
	@RequestMapping(path="/{id}/getuserlist")//对/welcome的请求做响应
	public String userlist(Model mav,@PathVariable String id) {
		System.out.println("num:"+sq.num);
		mav.addAttribute("spituser",sq.getSpit(Integer.parseInt(id)));
		mav.addAttribute("userlist",sq.getSpittle());
		return "welcome";//返回视图名
	}
	@RequestMapping
	public String user() {
		//前端传回来的参数会填充sp中对应名字的属性
		System.out.println("userroot");
		return "welcome";//返回视图名
	}
}
