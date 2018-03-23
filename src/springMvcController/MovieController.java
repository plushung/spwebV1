package springMvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import searchFile.SearchFile;

@RequestMapping("/movie/")
@Controller
public class MovieController {
	
	@Value("${moviepath}")
	String moviepath=null;
	
	@RequestMapping(path="/{movielist}",method=RequestMethod.GET)
	public String supplymovie(@PathVariable String movielist,Model mov){
		System.out.println(moviepath);
		mov.addAttribute("movielist", SearchFile.searchPathFile(moviepath));
		return "welcome";
	}
}
