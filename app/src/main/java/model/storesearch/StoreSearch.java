package model.storesearch;

public class StoreSearch
{
    private documents[] documents;

    private meta meta;

    public documents[] getDocuments()
    {
        return documents;
    }

    public void getDocuments (documents[] documents)
    {
        this.documents = documents;
    }

    public meta getMeta()
    {
        return meta;
    }

    public void setMeta(meta meta)
    {
        this.meta = meta;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [documents = "+documents+", meta = "+ meta +"]";
    }
}
