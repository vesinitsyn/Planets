package com.gmail.vesinitsyn.planets.web.configuration;

import com.gmail.vesinitsyn.planets.model.Planet;
import com.gmail.vesinitsyn.planets.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.gmail.vesinitsyn")
@EnableTransactionManagement
public class PlanetsConfiguration {

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/planets");
        dataSource.setUsername("postgres");
        dataSource.setPassword("SinitsynPOSTSQL");


        return dataSource;
    }

    @Bean
    @Autowired
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addAnnotatedClasses(User.class, Planet.class);
        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    @Autowired
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
