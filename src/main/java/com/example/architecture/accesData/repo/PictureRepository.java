package com.example.architecture.accesData.repo;

import com.example.architecture.accesData.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timotei Molcut
 * interface needed to acces the database and performe crud opperations for pictures
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    public Picture getPictureByName(String name);
}