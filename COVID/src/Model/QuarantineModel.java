package Model;

public class QuarantineModel {
    private int id;
    private String name;
    private int province;
    private int district;
    private int capicity;
    private int deleted;

    public QuarantineModel(int id, String name, int province, int district, int capicity, int deleted) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.district = district;
        this.capicity = capicity;
        this.deleted = deleted;
    }

    public String toString(String namepro, String namedist) {
        String temp = null;
        if (deleted == 1){
            temp = "Open";
        }
        else if (deleted == 0){
            temp = "Closed";
        }
        return " Name: " + name + "\n" +
                " Province: " + namepro + "\n"+
                " District: " + namedist +"\n"+
                " Capacity: " + capicity +"\n" +
                " Activate: " + temp;
    }

    public QuarantineModel(){
        id=0;
        name=null;
        province=0;
        district=0;
        capicity=0;
        deleted=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getCapicity() {
        return capicity;
    }

    public void setCapicity(int capicity) {
        this.capicity = capicity;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
