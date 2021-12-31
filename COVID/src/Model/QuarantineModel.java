package Model;

public class QuarantineModel {
    private int id;
    private String name;
    private int id_province;
    private String province;
    private int district;
    private int capicity;
    private int deleted;

    public int getId_province() {
        return id_province;
    }

    public void setId_province(int id_province) {
        this.id_province = id_province;
    }

    public QuarantineModel(int id, String name, String province, int district, int capicity, int deleted) {
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
        province=null;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
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
    
    @Override
    public String toString() {
        return name+", "+province;
    }
    
}
