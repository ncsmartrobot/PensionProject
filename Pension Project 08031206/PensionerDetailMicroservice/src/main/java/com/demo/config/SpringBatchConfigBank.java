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

import com.demo.bean.Bank;
import com.demo.itemprocessor.BankItemProcessor;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfigBank {
	
	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public Bank bank() {
		return new Bank();
	}

	@Bean
	@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
	public ItemProcessor<Bank, Bank> itemProcessor() {
		return new BankItemProcessor();
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
	public BeanWrapperFieldSetMapper<Bank> beanWrapperFieldSetMapper() {
		BeanWrapperFieldSetMapper<Bank> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setPrototypeBeanName("bank");
		return fieldSetMapper;
	}
	
	 @Bean
		public FlatFileItemReader<Bank> fileItemReader(BeanWrapperFieldSetMapper<Bank> beanWrapperFieldSetMapper) {
			FlatFileItemReader<Bank> fileItemReader = new FlatFileItemReader<>();
			fileItemReader.setResource(new ClassPathResource("bankDetailsCSVfile.CSV"));

			DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
			delimitedLineTokenizer.setNames(
					"aadhaarNumber",
					"bankAccountNumber",
					"bankName",
					"bankType"
					);

			DefaultLineMapper<Bank> defaultLineMapper = new DefaultLineMapper<>();
			defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
			defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

			fileItemReader.setLineMapper(defaultLineMapper);

			return fileItemReader;
		}
	 @Bean
		public JdbcBatchItemWriter<Bank> jdbcBatchItemWriter(DataSource dataSource,
				BeanPropertyItemSqlParameterSourceProvider<Bank> sqlParameterSourceProvider) {
			JdbcBatchItemWriter<Bank> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
			jdbcBatchItemWriter.setDataSource(dataSource);
			jdbcBatchItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);
			jdbcBatchItemWriter.setSql("insert into bank(aadhaar_number,bank_account_number,bank_name,bank_type) "
					+"values (:aadhaarNumber, :bankAccountNumber, :bankName, :bankType)");

			return jdbcBatchItemWriter;
		}

		@Bean
		public BeanPropertyItemSqlParameterSourceProvider<Bank> beanPropertyItemSqlParameterSourceProvider() {
			return new BeanPropertyItemSqlParameterSourceProvider<>();
		}

		@Bean
		public Job jobCsvMysql(JobBuilderFactory jobBuilderFactory, Step step) {
			return jobBuilderFactory.get("jobCsvMysql").incrementer(new RunIdIncrementer()).flow(step).end().build();
		}

		@Bean
		public Step step1(StepBuilderFactory stepBuilderFactory, ResourcelessTransactionManager transactionManager,
				ItemReader<Bank> reader, ItemWriter<Bank> writer, ItemProcessor<Bank, Bank> processor) {
			return stepBuilderFactory.get("step1").transactionManager(transactionManager).<Bank,Bank>chunk(3)
					.reader(reader).processor(processor).writer(writer).build();
		}

}
