package com.demo.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

import com.demo.bean.Pensioner;
import com.demo.itemprocessor.PensionerItemProcessor;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public Pensioner pensioner() {
		return new Pensioner();
	}

	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public ItemProcessor<Pensioner, Pensioner> itemProcessor() {
		return new PensionerItemProcessor();
	}
	
	@Bean
	public ResourcelessTransactionManager txManager() {
		return new ResourcelessTransactionManager();
	}
	

	@Bean
	public JobRepository jbRepository(DataSource dataSource, ResourcelessTransactionManager transactionManager)
			throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDatabaseType(DatabaseType.MYSQL.getProductName());
		factory.setDataSource(dataSource);
		factory.setTransactionManager(transactionManager);
		return factory.getObject();
	}
	

	@Bean
	public JobLauncher jbLauncher(JobRepository jobRepository) {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		return jobLauncher;
	}

	
	@Bean
	public BeanWrapperFieldSetMapper<Pensioner> beanWrapperFieldSetMapper() {
		BeanWrapperFieldSetMapper<Pensioner> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setPrototypeBeanName("pensioner");
		return fieldSetMapper;
	}
	
	@Bean
	public FlatFileItemReader<Pensioner> fileItemReader(BeanWrapperFieldSetMapper<Pensioner> beanWrapperFieldSetMapper) {
		FlatFileItemReader<Pensioner> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setResource(new ClassPathResource("pensionDetailsCSVfile.csv"));

		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames(
				"aadhaarNumber",
				"name",
				"dob",
				"salaryEarned",
				"allowance",
				"typeSelfORFamilypension",
				"basicSalary",
				"pensionerAccountNumber",
				"pancard",
				"userName",
				"password",
				"bankAccountNumber",
				"bankName",
				"bankType"
				);

		DefaultLineMapper<Pensioner> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

		fileItemReader.setLineMapper(defaultLineMapper);

		return fileItemReader;
	}
	
	
	@Bean
	public JdbcBatchItemWriter<Pensioner> jdbcBatchItemWriter(DataSource dataSource,
			BeanPropertyItemSqlParameterSourceProvider<Pensioner> sqlParameterSourceProvider) {
		JdbcBatchItemWriter<Pensioner> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);
		jdbcBatchItemWriter.setSql("insert into pensioners(aadhaar_number,name,dob,salary_earned,allowance,type_selforfamilypension,basic_salary,pensioner_account_number,pancard,user_name,password,bank_account_number,bank_name,bank_type) "
				+"values (:aadhaarNumber, :name, :dob, :salaryEarned, :allowance, :typeSelfORFamilypension, :basicSalary, :pensionerAccountNumber, :pancard, :userName, :password, :bankAccountNumber, :bankName, :bankType)");

		return jdbcBatchItemWriter;
	}
	

	@Bean
	public BeanPropertyItemSqlParameterSourceProvider<Pensioner> beanPropertyItemSqlParameterSourceProvider() {
		return new BeanPropertyItemSqlParameterSourceProvider<>();
	}
	

	@Bean
	public Job jobCsvMysql(JobBuilderFactory jobBuilderFactory, Step step) {
		return jobBuilderFactory.get("jobCsvMysql").incrementer(new RunIdIncrementer()).flow(step).end().build();
	}
	
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ResourcelessTransactionManager transactionManager,
			ItemReader<Pensioner> reader, ItemWriter<Pensioner> writer, ItemProcessor<Pensioner, Pensioner> processor) {
		return stepBuilderFactory.get("step1").transactionManager(transactionManager).<Pensioner,Pensioner>chunk(20)
				.reader(reader).processor(processor).writer(writer).build();
	}
	
}
