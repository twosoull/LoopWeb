package com.loopcreative.web.scheduler;

import com.loopcreative.web.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerFile {

   private final FileService fileService;

    /**
     * 1. 매일 새벽 2시 불필요한 파일 삭제(물리,db 데이터)
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void fileDeleteAndDataScheduler(){
      log.info("스케쥴러 발동!");
      fileService.removeFileUseYnN();
    }
}
