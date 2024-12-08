package com.vv.nativebatch.billingjob;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class BillingJobConfig {

    private final EntityManagerFactory entityManagerFactory;

    public BillingJobConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job billingJob(JobRepository jobRepository, Step billingStep) {
        return new JobBuilder("billingJob", jobRepository)
                .start(billingStep)
                .build();
    }

    @Bean
    public Step billingStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("billingStep", jobRepository)
                .<Billing, Billing>chunk(10, transactionManager)
                .reader(billingItemReader())
                .processor(billingProcessor())
                .writer(billingItemWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<Billing> billingItemReader() {
        return new FlatFileItemReaderBuilder<Billing>()
                .name("billingItemReader")
                 .resource(new FileSystemResource("C:/_dev/foojay/nativeBatch/nativeBatch/src/main/resources/billing-2023-01.csv")) // absolute path
                .strict(false) // Turn off strict mode
                .delimited()
                .delimiter(",")
                .names("year", "month", "accountNumber", "phoneNumber", "amount", "calls", "messages")
                .targetType(Billing.class)
                .build();
    }

    @Bean
    public ItemProcessor<Billing, Billing> billingProcessor() {
        return billing -> {
            System.out.println(billing);

            // Example processing logic (optional)
            // billing.setAmount(billing.getAmount() * 1.1); // Apply a 10% increase (optional)
            return billing;
        };
    }

    @Bean
    public JpaItemWriter<Billing> billingItemWriter() {
        JpaItemWriter<Billing> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public CommandLineRunner runJob( JobLauncher jobLauncher, Job billingJob ) {
        return args -> {
            var jobParameters = new JobParametersBuilder()
                    .addString("input.file", "src/main/resources/billing-2023-01.csv")
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(billingJob, jobParameters);
        };
    }

}
