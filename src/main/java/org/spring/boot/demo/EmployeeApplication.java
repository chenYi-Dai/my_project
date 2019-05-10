package org.spring.boot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.demo.filter.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("org.spring.boot.demo.mapper")
public class EmployeeApplication {
	
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeApplication.class);
	
	
    public static void main( String[] args ){
    	SpringApplication.run(EmployeeApplication.class, args);
    }
    
    @Bean
    public FilterRegistrationBean TestFilterRegBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter");
        LoginFilter loginFilter = new LoginFilter();
        registrationBean.setFilter(loginFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }
    
}
