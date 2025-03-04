package com.ldy.controller;

import com.ldy.service.IEmployeeService;
import com.ldy.service.IEmployeeTempService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/job")
public class PartitionController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    IEmployeeTempService employeeTempService;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobExplorer jobExplorer;  //job 展示对象
    @Autowired
    @Qualifier("csvToDBJob")
    private Job csvToDBJob;
    @Autowired
    @Qualifier("dbToDBJob")
    private Job dbToDBJob;


    @GetMapping("/dataInit")
    public String startJob(String name) throws Exception {
        employeeService.dataInit();
        return "ok";
    }

    @GetMapping("/csvToDB")
    public String csvToDB() throws Exception {
        employeeTempService.truncateTemp(); //清空数据运行多次执行

        //需要多次执行，run.id 必须重写之前，再重构一个新的参数对象
        JobParameters jobParameters = new JobParametersBuilder(new JobParameters(),jobExplorer)
                .addLong("time", new Date().getTime())
                .getNextJobParameters(csvToDBJob).toJobParameters();
        JobExecution run = jobLauncher.run(csvToDBJob, jobParameters);
        return run.getId().toString();
    }


    @GetMapping("/DBToDB")
    public String DBToDB() throws Exception {
        employeeService.truncateAll(); //清空数据运行多次执行

        //需要多次执行，run.id 必须重写之前，再重构一个新的参数对象
        JobParameters jobParameters = new JobParametersBuilder(new JobParameters(),jobExplorer)
                .addLong("time", new Date().getTime())
                .getNextJobParameters(dbToDBJob).toJobParameters();
        JobExecution run = jobLauncher.run(dbToDBJob, jobParameters);
        return run.getId().toString();
    }





}
