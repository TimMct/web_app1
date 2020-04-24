package com.example.accessingdatamysql.architecture;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Timotei Molcut
 * interface needed to acces the database and performe crud opperations for pictures
 */
public interface PictureRepository extends CrudRepository<Picture, Integer> {

}