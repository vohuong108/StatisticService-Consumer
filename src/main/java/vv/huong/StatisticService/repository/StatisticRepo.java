package vv.huong.StatisticService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vv.huong.StatisticService.model.Statistic;

public interface StatisticRepo extends JpaRepository<Statistic, Integer> {
}
