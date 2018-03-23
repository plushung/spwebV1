package springMvcController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spit.Spittle;
import sqltest.sqlt;

//@RestController ==@Controller+@ResponseBody
@org.springframework.web.bind.annotation.RestController
@RequestMapping(path="/REST/")
//@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	sqlt sq=null;
/*	@ResponseBody �ѷ���ֱֵ����Ϊ��Դ��json/xml���������ظ��ͻ��ˣ����ý�����ͼ��������Converter������Դ��ʽ
		@ResponseBody����@RestController����
			ͨ���ͻ�����·���ĺ�׺��ȷ�����ص���Դ��ʽjson/xml������
			*/
	@RequestMapping(path="/*.json",produces="application/json")//produces����ָ��Ҫ��Ӧ��accept����
	public Spittle spi(
			@RequestBody //�����ĵ�contentType ������application/json
			Spittle spittle) throws IOException{
		System.out.println(spittle.getUsername()+" json request");
//		BufferedReader br=req.getReader();
//		String s=null;
//		while((s=br.readLine())!=null){
//			System.out.println(s);
//		}
		HashMap<String, Spittle> hm=new HashMap<String, Spittle>();
		hm.put("C1", new Spittle(123,2,"hung",6199,"man"));
		System.out.println(hm.get("C1").hashCode());
		return hm.get("C1")/*new Spittle(id,type,name,qq,sex)*/;
	}
	@RequestMapping(path="/*.xml"
			,produces="application/xml")
	public @ResponseBody 
	List<Spittle> spis(HttpServletResponse spo) throws IOException{
//		BufferedReader br=req.getReader();
//		String s=null;
//		while((s=br.readLine())!=null){
		System.out.println("xml");
//		}
		
		return sq.getSpittle();
	}
	@RequestMapping(path="/spit"
			,produces="text/html")
	public @ResponseBody 
	List<Spittle> spihtml(HttpServletResponse spo) throws IOException{
//		BufferedReader br=req.getReader();
//		String s=null;
//		while((s=br.readLine())!=null){
//			System.out.println(s);
//		}
		System.out.println("html");
		return sq.getSpittle();
	}
}
