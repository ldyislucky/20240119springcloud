package com.ldy.until;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.util.Date;

//时间戳作业参数增量器
public class DailyTimestampParamIncrementer implements JobParametersIncrementer {
    @Override
    public JobParameters getNext(JobParameters parameters) {
        return new JobParametersBuilder(parameters)//传参parameters会继承第一次运行时传入的旧参数，删除参数库或者不传参可以不继承
                .addLong("daily", new Date().getTime())  //添加时间戳
                .toJobParameters();
    }
}
