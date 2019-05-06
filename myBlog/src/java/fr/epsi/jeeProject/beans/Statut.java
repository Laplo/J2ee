package java.fr.epsi.jeeProject.beans;

public class Statut {

    public Statut() {
    }

    public Statut(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String description;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
