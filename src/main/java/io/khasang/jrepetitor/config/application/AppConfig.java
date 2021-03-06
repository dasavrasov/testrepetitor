package io.khasang.jrepetitor.config.application;

import io.khasang.jrepetitor.dao.*;
import io.khasang.jrepetitor.dao.impl.*;
import io.khasang.jrepetitor.entity.*;
import io.khasang.jrepetitor.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value = "classpath:util.properties")
@PropertySource(value = "classpath:auth.properties")
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.user"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());
        jdbcDao.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcDao.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcDao;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
       JdbcTemplate jdbcTemplate = new JdbcTemplate();
       jdbcTemplate.setDataSource(dataSource());
       return jdbcTemplate;
    }

    @Bean
    public CreateTable createTable(){
        return new CreateTable(jdbcTemplate());
    }

    @Bean
    public CatDao catDao(){
        return new CatDaoImpl(Cat.class);
    }

    @Bean
    public GroupDao groupDao(){
        return new GroupDaoImpl(Group.class);
    }
    @Bean
    public QuizDao quizDao(){
        return new QuizDaoImpl(Quiz.class);
    }
    @Bean
    public QuestionDao questionDao(){
        return new QuestionDaoImpl(Question.class);
    }
    @Bean
    public ItemDao itemDao(){
        return new ItemDaoImpl(Item.class);
    }
    @Bean
    public UserTryDao userTryDao(){
        return new UserTryDaoImpl(UserTry.class);
    }
    @Bean
    public UserAnswerDao userAnswerDao(){
        return new UserAnswerDaoImpl(UserAnswer.class);
    }
    @Bean
    public ItemRightAnsDao itemRightAnsDao(){
        return new ItemRightAnsDaoImpl(ItemRightAns.class);
    }
    @Bean
    public RightAnsDao rightAnsDao(){
        return new RightAnsDaoImpl(RightAns.class);
    }

}
