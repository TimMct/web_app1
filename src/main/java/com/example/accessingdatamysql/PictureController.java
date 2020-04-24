package com.example.accessingdatamysql;

import com.example.accessingdatamysql.architecture.Picture;
import com.example.accessingdatamysql.architecture.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Timotei Molcut
 * class needed to manage the access to endpoints for the picture
 * for the path http://localhost:8080/picture
 */
@RestController
@RequestMapping(path = "/picture")
public class PictureController {
    /**
     * the bean for accessing the database
     */
    @Autowired
    private PictureRepository pictureRepository;

    /**
     *
     * it's a post request
     * adds a new picture into the picture table
     * @param name of the picture file
     * @return message about the result (success/fail)
     */
    @PostMapping(path = "/add")
    public String addPicture(String name){
        Picture p = new Picture();
        p.setName(name);
        pictureRepository.save(p);
        return "Picture created.";
    }

    /**
     * retrieve all pictures from database
     * @return the json file with all picture objects
     */
    @GetMapping(path = "/all")
    public Iterable<Picture> getAllPictures(){
        return pictureRepository.findAll();
    }

    /**
     * incomplete method
     * this endpoint intends to open a JFrame window with the picture
     * @return message
     */
    @PostMapping(path = "/show")
    public String show(){

        //JFrame frame = new JFrame(name);
        //ImageIcon icon = new ImageIcon("D:\\Facultate\\An3\\Sem2\\PS\\web-app\\pictures\\" + name);
        //JLabel label = new JLabel(icon);
        //frame.add(label);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        //frame.setVisible(true);
        /*if(pictureRepository.count() > 0L){
            Iterable<Picture> allPics = pictureRepository.findAll();
            for(Picture p : allPics){
                //p.openPic();
                //System.out.println(p.getName());
                break;

            }
        }*/
        return "OK";
    }


}
