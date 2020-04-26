package com.example.architecture.accesData.repo;

import com.example.architecture.accesData.entity.LikeObserver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author TimoteiMolcut
 * This interface is used to persiste the objects of LikeObserver for every user.
 */
@Repository
public interface LikeObserverRepository extends JpaRepository<LikeObserver, Integer> {
}
