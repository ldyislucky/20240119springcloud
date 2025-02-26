package com.ldy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private JobLauncher launcher;

    @Autowired
    private JobLauncher launcher1;

    @Autowired
    private Job job;
    @Autowired
    private JobExplorer jobExplorer;  //job 展示对象

    @Autowired
    private Job jobweb;

    @GetMapping("/job/start")
    public ExitStatus startJob(String name) throws Exception {
        //启动job作业
        JobParameters jp = new JobParametersBuilder(jobExplorer)
                .getNextJobParameters(job)
                .addString("name", name)
                .toJobParameters();
        JobExecution jobExet = launcher.run(job, jp);
        return jobExet.getExitStatus();
    }

    @GetMapping("/job/start1")
    public ExitStatus startJob1(String name) throws Exception {
        //启动job作业
        JobParameters jp = new JobParametersBuilder(jobExplorer)
                .getNextJobParameters(jobweb)
                .addString("name", name)
                .toJobParameters();
        JobExecution jobExet = launcher.run(jobweb, jp);
        return jobExet.getExitStatus();
    }

    @GetMapping("/job/start2")
    public ExitStatus startJob2(String name) throws Exception {
        boolean isSameInstance = (launcher == launcher1);
        log.warn("isSameInstance: " + isSameInstance);
        return null;
    }
}
