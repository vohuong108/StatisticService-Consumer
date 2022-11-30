package vv.huong.StatisticService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
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
        statisticRepo.save(statistic);
    }
}
