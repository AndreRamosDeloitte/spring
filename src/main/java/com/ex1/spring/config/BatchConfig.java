package com.ex1.spring.config;

import javax.sql.DataSource;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.infrastructure.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.ex1.spring.model.Product;

@Configuration
public class BatchConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobRepository repository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job job() {
        return new JobBuilder("job-1", repository).flow(step()).end().build();
    }

    @Bean
    public Step step() {
        StepBuilder stepBuilder = new StepBuilder("step-1", repository);
        return stepBuilder.<Product, Product>chunk(4, transactionManager).reader(reader()).processor(processor())
                .writer(writer()).build();
    }

    @Bean
    public ItemReader<Product> reader() {

        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "name", "description", "price");

        BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Product.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        FlatFileItemReader<Product> reader = new FlatFileItemReader<>(lineMapper);
        reader.setResource(new ClassPathResource("products.csv"));

        return reader;
    }

    @Bean
    public ItemProcessor<Product, Product> processor() {
        return (p) -> {
            System.out.println(p.getName());
            p.setPrice(p.getPrice() * 0.9);
            return p;
        };
    }

    @Bean
    public ItemWriter<Product> writer() {
        JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
        writer.setSql("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES (:id, :name, :description, :price)");

        return writer;
    }
}
