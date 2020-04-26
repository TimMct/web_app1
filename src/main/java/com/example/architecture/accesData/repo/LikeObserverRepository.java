package com.example.architecture.accesData.repo;

import com.example.architecture.accesData.entity.LikeObserver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeObserverRepository extends JpaRepository<LikeObserver, Integer> {
}
