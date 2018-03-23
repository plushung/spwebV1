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
	@Qualifier("sqlt0")//ָ��ע��idΪ sqlt0��bean
	private sqlt sq=null;
	@Autowired
	private ArrayList<PageItem> plis=null;
	public SpiController() {
		// TODO �Զ����ɵĹ��캯�����
		System.out.println("controller!!!");
	}
	
	@RequestMapping(path="/ListPage.do",method=RequestMethod.GET)//��/welcome����������Ӧ
	public String welcome(Model mav,HttpServletRequest req,HttpSession s,@PathVariable(name="id",required=false) String ss) {
		Spittle sp=sq.getSpit(sq.signIn("hung", "021501199"));
		plis.addAll(sq.allPage());
		mav.addAttribute("pagelist",plis);//��model�м���Ԫ��,������Ϊrequest
		s.setAttribute("spituser", sp);
		System.out.print(ss+"welcome........."+sp.getId());
		return "welcome";//������ͼ��
	}

	@RequestMapping(path="/ListUser.do",method=RequestMethod.GET)//��/welcome����������Ӧ
	public String listuser(Model mav) {
		if(mav.containsAttribute("userlist")){
			System.out.println("exist userlist");
		}
		mav.addAttribute("userlist",sq.getSpittle());//��model�м���Ԫ��
		System.out.print("listuser.........");
		return "welcome";//������ͼ��
	}
	
	@RequestMapping(path="/register.do",method=RequestMethod.POST)//��/welcome����������Ӧ
	public String register(Model mav
			,@RequestParam("name") String name
			,@RequestParam("pass") String pass) {
			boolean sta=sq.signUp(name, pass);
		System.out.println(name+":"+sta+":"+pass);
		return "welcome";//������ͼ��
	}
	@RequestMapping(path="/login.do",method=RequestMethod.POST)//��/welcome����������Ӧ
	public String login(Model mav
			,String username //�������ж�Ӧ���ֵĲ������Զ�д��
			,String password
			,Spittle sp
			,HttpSession ses) {
		//ǰ�˴������Ĳ��������sp�ж�Ӧ���ֵ�����
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
		view="forward:/user/"+sp.getId();//spring��ʶ��forward�ؼ���
		}
		return view;//�ض�����Ŀ��user·��
				//������ͼ��
	}
	

	@RequestMapping(path={"/view/login","/login"})//��/welcome����������Ӧ
	public Spittle login() {
		return new Spittle(100,3,"�ο�",000,"woman");
	}
	@RequestMapping(path={"/view/welcome","/welcome"})//��/welcome����������Ӧ
	public String wel() {
		System.out.println("num:"+sq.num);
		return "welcome";
	}
	
}