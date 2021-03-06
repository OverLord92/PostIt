package com.postit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@Import({SecurityConfig.class}) 
@ComponentScan(basePackages={"com"},
		excludeFilters={
				@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
		})
public class RootConfig {

	@Bean
	public DataSource dataSource(){
		
		JndiDataSourceLookup jndi = new JndiDataSourceLookup();
		DataSource dataSource = jndi.getDataSource("jdbc/post-it");
		
		return dataSource;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]{ "com.postit.dao" });
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.setProperty("javax.persistence.validation.group.pre-persist", 
				"com.postit.validationGroups.PersistenceValidationGroup");
		hibernateProperties.setProperty("javax.persistence.validation.group.pre-update", 
				"com.postit.validationGroups.PersistenceValidationGroup");
		hibernateProperties.setProperty("javax.persistence.validation.group.pre-remove", 
				"com.postit.validationGroups.PersistenceValidationGroup");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		
		sessionFactory.setHibernateProperties(hibernateProperties);
		
		return sessionFactory;
	}
	
	@Bean 
	public DataSourceTransactionManager transactionManager(){
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
	
		dstm.setDataSource(dataSource());
		return dstm;
	}
	
	@Bean
	PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
		PersistenceExceptionTranslationPostProcessor exceptionTranslator =
				new PersistenceExceptionTranslationPostProcessor();
		
		return exceptionTranslator;
	}
	
}
