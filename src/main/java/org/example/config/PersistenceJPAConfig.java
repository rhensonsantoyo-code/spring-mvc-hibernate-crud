package org.example.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = "org.example")
public class PersistenceJPAConfig {

    private final Environment env;

    public PersistenceJPAConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        ds.setUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource());
        emf.setPackagesToScan("org.example.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        emf.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProps = new Properties();
        String dialect = env.getProperty("hibernate.dialect");
        if (dialect != null) jpaProps.put("hibernate.dialect", dialect);
        String hbm2ddl = env.getProperty("hibernate.hbm2ddl.auto");
        if (hbm2ddl != null) jpaProps.put("hibernate.hbm2ddl.auto", hbm2ddl);
        String showSql = env.getProperty("hibernate.show_sql");
        if (showSql != null) jpaProps.put("hibernate.show_sql", showSql);
        String formatSql = env.getProperty("hibernate.format_sql");
        if (formatSql != null) jpaProps.put("hibernate.format_sql", formatSql);

        emf.setJpaProperties(jpaProps);
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        return tm;
    }
}