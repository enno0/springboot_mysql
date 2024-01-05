package com.enno.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.enno.DAO.StudentDAO;
import com.enno.DAO.StudentDaoImp;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.enno.controllers.StudentController" })
@PropertySource("classpath:application.properties")
public class Appconfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    // @Autowired
    // private ApplicationContext applicationContext;

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver ivr = new InternalResourceViewResolver();
        ivr.setPrefix(env.getProperty("spring.mvc.view.prefix"));
        ivr.setSuffix(env.getProperty("spring.mvc.view.suffix"));
        return ivr;
    }

    @Bean
    DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    public StudentDAO getUserDao() {
        return new StudentDaoImp(getDataSource());
    }
}
