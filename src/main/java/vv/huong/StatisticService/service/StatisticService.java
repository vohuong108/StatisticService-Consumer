package vv.huong.StatisticService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import vv.huong.StatisticService.model.Statistic;
import vv.huong.StatisticService.repository.StatisticRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {
    private final StatisticRepo statisticRepo;

    @KafkaListener(id = "statisticGroup", topics = "statistic")
    public void listen(Statistic statistic) {
        log.info("Received: {}", statistic.getMessage());
//        statisticRepo.save(statistic);
        throw new RuntimeException("Error DLT");
    }

//    @KafkaListener(id = "dltGroup", topics = "statistic.DLT")
//    public void dltListen(Statistic statistic) {
//        log.info("Received statistic.DLT: {}", statistic.getMessage());
//        statisticRepo.save(statistic);
//    }

    @RetryableTopic(attempts = "5", dltTopicSuffix = "-dlt",
        backoff = @Backoff(delay = 2_000, multiplier = 2)
    )
    @KafkaListener(id = "dltGroup", topics = "statistic.DLT")
    public void dltListen(Statistic statistic) {
        log.info("Received statistic.DLT: {}", statistic.getMessage());
        statisticRepo.save(statistic);
    }
}
