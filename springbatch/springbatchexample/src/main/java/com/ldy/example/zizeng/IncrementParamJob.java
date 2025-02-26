//package com.ldy.example.zizeng;
//
//
//import com.ldy.until.DailyTimestampParamIncrementer;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.util.Map;
//
//@SpringBootApplication
//@EnableBatchProcessing
//public class IncrementParamJob {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Tasklet tasklet2(){
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                Map<String, Object> parameters = chunkContext.getStepContext().getJobParameters();
//                System.out.println("params---daily:" + parameters.get("daily"));
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//
//    //时间戳增量器
//    @Bean
//    public DailyTimestampParamIncrementer dailyTimestampParamIncrementer(){
//        return new DailyTimestampParamIncrementer();
//    }
//
//
//    @Bean
//    public Step  step2(){
//        return  stepBuilderFactory.get("step2")
//                .tasklet(tasklet2())
//                .build();
//    }
//
//    @Bean
//    public Job job2(){
//        return jobBuilderFactory.get("incr-params-job")
//                .start(step2())
//                //.incrementer(new RunIdIncrementer())  //参数增量器(run.id自增)
//                .incrementer(dailyTimestampParamIncrementer())  //时间戳增量器
//                .build();
//    }
//    public static void main(String[] args) {
//        SpringApplication.run(IncrementParamJob.class, args);
//    }
//}
