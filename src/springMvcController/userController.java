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
	
	@RequestMapping(path="/{id}")//��/welcome����������Ӧ
	public String user(Model mav,
			@PathVariable String id//��url·���еı���idд��
			) {
	
		mav.addAttribute("spituser",sq.getSpit(Integer.parseInt(id)));
		return "welcome";//������ͼ��
	}
	@RequestMapping(path="/list/{id}/pagelist")//��/welcome����������Ӧ
	public String lis(Model mav,@PathVariable String id) {
		System.out.println("pagelist");
		mav.addAttribute("spituser",sq.getSpit(Integer.parseInt(id)));
		mav.addAttribute("pagelist",sq.allPage());
		return "welcome";//������ͼ��
	}
	@RequestMapping(path="/{id}/getuserlist")//��/welcome����������Ӧ
	public String userlist(Model mav,@PathVariable String id) {
		System.out.println("num:"+sq.num);
		mav.addAttribute("spituser",sq.getSpit(Integer.parseInt(id)));
		mav.addAttribute("userlist",sq.getSpittle());
		return "welcome";//������ͼ��
	}
	@RequestMapping
	public String user() {
		//ǰ�˴������Ĳ��������sp�ж�Ӧ���ֵ�����
		System.out.println("userroot");
		return "welcome";//������ͼ��
	}
}
