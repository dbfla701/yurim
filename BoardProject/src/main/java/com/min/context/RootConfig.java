package com.min.context;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

@PropertySource("classpath:properties/db.properties")
public class RootConfig {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Autowired
	private ResourcePatternResolver resolver;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(this.driverClassName);
		dataSource.setUrl(this.url);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		return dataSource;
	}

//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(this.driverClassName);
//		dataSource.setUrl(this.url);
//		dataSource.setUsername(this.username);
//		dataSource.setPassword(this.password);
//		return dataSource;
//	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean ss = new SqlSessionFactoryBean();
		ss.setDataSource(dataSource);
		ss.setTypeAliasesPackage("com.min.dto");
		ss.setMapperLocations(resolver.getResources("classpath:/mappers/*.xml"));
		return ss;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactorybean) throws Exception {
		SqlSessionTemplate s = new SqlSessionTemplate(sqlSessionFactorybean.getObject());
		return s;
	}

	@Bean
	public TransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager d = new DataSourceTransactionManager(dataSource);
		return d;
	}

}
