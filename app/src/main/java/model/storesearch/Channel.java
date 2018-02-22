package model.storesearch;

public class Channel
{
    private Item[] item;

    private Info info;

    public Item[] getItem ()
    {
        return item;
    }

    public void setItem (Item[] item)
    {
        this.item = item;
    }

    public Info getInfo ()
    {
        return info;
    }

    public void setInfo (Info info)
    {
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [item = "+item+", info = "+info+"]";
    }
}
