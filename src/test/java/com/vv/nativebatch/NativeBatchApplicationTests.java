package com.vv.nativebatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest
@ExtendWith( OutputCaptureExtension.class )
class BillingJobApplicationTests {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

//    @Test
//    void testJobExecution( CapturedOutput output ) throws Exception {
//        // given
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString( "input.file", "/some/input/file" )
//                .toJobParameters();
//
//        // when
//        JobExecution jobExecution = this.jobLauncher.run( this.job, jobParameters );
//
//        Assertions.assertEquals( ExitStatus.COMPLETED, jobExecution.getExitStatus() );
//    }

}