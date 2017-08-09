/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.matercero.spc.utils;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author mangel.tercero
 */
@ManagedBean
public class ImagesView {
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 0; i <= 12; i++) {
            images.add("cocina" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
    
}
