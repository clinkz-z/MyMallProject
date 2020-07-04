package com.zzz.project1.model.bo;

public class AdminChangePwdBO {

    private String adminToken;

    private String confirmPwd;

    private String newPwd;

    private String oldPwd;

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    @Override
    public String toString() {
        return "AdminChangePwdBO{" +
                "adminToken='" + adminToken + '\'' +
                ", confirmPwd='" + confirmPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                '}';
    }
}
