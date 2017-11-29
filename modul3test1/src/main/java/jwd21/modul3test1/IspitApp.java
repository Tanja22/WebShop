package jwd21.modul3test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class IspitApp extends SpringBootServletInitializer{
	
	@Autowired 
	private TestData td;

	public static void main(String[] args) {
		 SpringApplication.run(IspitApp.class, args);
	}

}
