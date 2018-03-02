package model.storesearch;

public class meta
{
    private Same_name same_name;

    private String pageable_count;

    private String total_count;

    private String is_end;

    public Same_name getSamename ()
    {
        return same_name;
    }

    public void setSamename (Same_name samename)
    {
        this.same_name = samename;
    }

    public String getCount ()
    {
        return pageable_count;
    }

    public void setCount (String count)
    {
        this.pageable_count = count;
    }

    public String getTotalCount ()
    {
        return total_count;
    }

    public void setTotalCount (String totalCount)
    {
        this.total_count = totalCount;
    }

    public String getPage ()
    {
        return is_end;
    }

    public void setPage (String page)
    {
        this.is_end = page;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [same_name = "+same_name+", pageable_count = "+pageable_count+", total_count = "+total_count+", is_end = "+is_end+"]";
    }
}
