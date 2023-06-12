package com.baseball.adapter.persistence;

import com.baseball.domain.BaseballGameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseballGameHistoryRepository extends JpaRepository<BaseballGameHistory, Long>{}
