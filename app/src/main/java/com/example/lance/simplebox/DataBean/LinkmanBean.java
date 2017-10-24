package com.example.lance.simplebox.DataBean;

import java.util.List;

/**
 * Created by Lance on 2017/10/24.
 */

public class LinkmanBean {

    //联系人的名字;
    private String name;
    //联系人的ID索引值;
    private String contactId;
    //联系人的电话;
    public List<String> phone;

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getPhone() {
        return phone;
    }*/

    public String getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }
}
