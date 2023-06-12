package com.baseball.adapter.persistence;

import com.baseball.domain.BaseballGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseballGameRepository extends JpaRepository<BaseballGame, Long> {
}
