package com.baseball.adapter.persistence;

import com.baseball.domain.BaseballGameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseballGameHistoryRepository extends JpaRepository<BaseballGameHistory, Long>{

    List<BaseballGameHistory> findAllHistoryByRoomId(Long roomId);
}
