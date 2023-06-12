package com.baseball.adapter.persistence;

import com.baseball.domain.BaseballGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseballGameRepository extends JpaRepository<BaseballGame, Long> {

    Optional<BaseballGame> findByRoomId(long roomId);
}
