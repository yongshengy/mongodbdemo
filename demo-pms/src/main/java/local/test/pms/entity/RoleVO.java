package local.test.pms.entity;

import java.util.List;

public class RoleVO {
    private int code;
    private String mess;
    private long count;
    private List<Role> data;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMess() {
        return mess;
    }
    public void setMess(String mess) {
        this.mess = mess;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public List<Role> getData() {
        return data;
    }
    public void setData(List<Role> data) {
        this.data = data;
    }
}
