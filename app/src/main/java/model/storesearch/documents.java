package model.storesearch;

public class documents
{
    private String place_url;

    private String category_group_name;

    private String place_name;

    private String distance;

    private String address_name;

    private String road_address_name;

    private String id;

    private String phone;

    private String category_group_code;

    private String category_name;

    private String x;

    private String y;

    private String addressBCode;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getPlace_url ()
    {
        return place_url;
    }

    public void setPlace_url (String title)
    {
        this.place_url = title;
    }

    public String getCategory_group_name ()
    {
        return category_group_name;
    }

    public void setCategory_group_name (String category)
    {
        this.category_group_name = category;
    }

    public String getDistance ()
    {
        return distance;
    }

    public void setDistance (String distance)
    {
        this.distance = distance;
    }

    public String getPlace_name ()
    {
        return place_name;
    }

    public void setPlace_name (String newAddress)
    {
        this.place_name = newAddress;
    }

    public String getAddress_name ()
    {
        return address_name;
    }

    public void setAddress_name (String address)
    {
        this.address_name = address;
    }

    public String getRoad_address_name ()
    {
        return road_address_name;
    }

    public void setRoad_address_name (String imageUrl)
    {
        this.road_address_name = imageUrl;
    }

    public String getCategory_group_code ()
    {
        return category_group_code;
    }

    public void setCategory_group_code (String direction)
    {
        this.category_group_code = direction;
    }

    public String getCategory_name ()
    {
        return category_name;
    }

    public void setCategory_name (String zipcode)
    {
        this.category_name = zipcode;
    }

    public String getX ()
    {
        return x;
    }

    public void setX (String x)
    {
        this.x = x;
    }

    public String getY ()
    {
        return y;
    }

    public void setY (String longitude)
    {
        this.y = longitude;
    }

    public String getAddressBCode ()
    {
        return addressBCode;
    }

    public void setAddressBCode (String addressBCode)
    {
        this.addressBCode = addressBCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", phone = "+phone+", place_name = "+place_name+", distance = "+distance+", distance = "+distance+", place_url = "+place_url+", category_group_name = "+category_group_name+", address_name = "+address_name+", road_address_name = "+road_address_name+", category_group_code = "+category_group_code+", category_name = "+category_name+", x = "+x+", y = "+y+", category_name = "+category_name+"]";
    }
}
