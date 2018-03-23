package searchFile;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Tested {

	@Test
	public void tses() throws Exception {
		SearchFile s=new SearchFile();
		List<String> lis=SearchFile.searchPathFile("E:/Ñ¸À×ÏÂÔØ/movie");
		for(String i : lis){
			System.out.println(i);
		}
	}
}
