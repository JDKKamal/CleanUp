package com.jdkgroup.cleanup.model.api;

/**
 * Created by kamlesh on 9/2/2017.
 */

public class ModelHome
{
    private long id;
    private String modulename;
    private int total, mytotal;

    public ModelHome(long id, String modulename, int total, int mytotal) {
        this.id = id;
        this.modulename = modulename;
        this.total = total;
        this.mytotal = mytotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMytotal() {
        return mytotal;
    }

    public void setMytotal(int mytotal) {
        this.mytotal = mytotal;
    }
}
