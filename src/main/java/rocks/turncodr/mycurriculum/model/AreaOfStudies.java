package rocks.turncodr.mycurriculum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * Maps a color to an area of studies. So that modules have different colors,
 * depending on their area of studies.
 *
 */
@Entity
public class AreaOfStudies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    //only used for validation purposes
    private int color;
    
    private String colorRGB;

    public void setColorRGB(String color) {
        this.colorRGB = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
    
    public String getColorRGB() {
        return colorRGB;
    }
}
