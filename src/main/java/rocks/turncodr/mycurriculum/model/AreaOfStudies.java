package rocks.turncodr.mycurriculum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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

    @NotBlank
    private String name;
    //only used for validation purposes. -1 for not set.
    private int color = -1;

    private String colorRGB;

    /**
     * Sets the color to the input value. <br> NOTE: This setter also sets the integer representation of the color, so there is no need to set it otherwise. <br>
     * If the input string is not valid this method does nothing!
     * @param color The color which should be set in RGB notation, e.g. for white "255,255,255".
     */
    public void setColorRGB(String color) {
        final int colorShades = 256;
        /* regex which ensures that the input is in rgb notation
         * \\d{1,3} checks if the input is a decimal with 1-3 digits
         * ,? checks if the comma separates the values correctly ? means that it occurs one or no time
         * (...){3} means that the expression in the parenthesis occurs 3 times
         */
        final String regex = "(\\d{1,3},?){3}";

        if (color.matches(regex)) {
            this.colorRGB = color;
            String[] colors = color.split(",");
            int r = Integer.parseInt(colors[0]);
            int g = Integer.parseInt(colors[1]);
            int b = Integer.parseInt(colors[2]);
            this.color =  ((colorShades * colorShades) * r) + (colorShades * g) + b;
        }
    }

    /**
     *
     *Shouldn't be used! Use setColorRGB instead.
     */
    @Deprecated
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
