package com.example.architecture.accesData.repo;

import com.example.architecture.accesData.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timotei Molcut
 * Interface needed to acces the database and performe crud opperations for pictures
 * The methods are implemented automatically by the hibernate api.
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    /**
     * Get the picture stored by providing it's name.
     * @param name
     * @return
     */
    public Picture getPictureByName(String name);

    /**
     * Get the picture stored by providing the name of it's owner.
     * @param name
     * @return
     */
    public Picture getPictureByOwnerName(String name);
}