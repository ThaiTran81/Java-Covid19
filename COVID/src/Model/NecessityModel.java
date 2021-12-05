package Model;

public class NecessityModel {
    private String name;
    private int limit;
    private String price;
    private int time_limit;
    private String type;

    public NecessityModel() {
    }

    public NecessityModel(String name, int limit, String price, int time_limit, String type) {
        this.name = name;
        this.limit = limit;
        this.price = price;
        this.time_limit = time_limit;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
