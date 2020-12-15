package sample;

public class ModelTableCity {
    private Integer cityId;
    private String city, province;

    public ModelTableCity(Integer cityId, String city, String province) {
        this.cityId = cityId;
        this.city = city;
        this.province = province;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
