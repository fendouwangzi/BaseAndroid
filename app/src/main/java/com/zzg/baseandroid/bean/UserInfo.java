package com.zzg.baseandroid.bean;

/**
 * @author zhongzhigang
 * @Title: ${FILE_NAME}
 * @Description:
 * @package_name: sanocare.minute.clinic.entity
 * @date 2017/6/6
 */
public class UserInfo {

    private Long createTime;
    private String scc_token;
    private String customerCode;

    private Long id;

    private boolean isAdmin;

    private String name;

    private String password;

    private String phone;

    private int positionId;

    private Long pwdLockDay;

    private int status;

    private String token;

    private int tryLoginTimes;

    private Long updateTime;

    public UserInfo() {
    }

    public String getScc_token() {
        return scc_token;
    }

    public void setScc_token(String scc_token) {
        this.scc_token = scc_token;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getPositionId() {
        return this.positionId;
    }

    public void setPwdLockDay(Long pwdLockDay) {
        this.pwdLockDay = pwdLockDay;
    }

    public Long getPwdLockDay() {
        return this.pwdLockDay;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setTryLoginTimes(int tryLoginTimes) {
        this.tryLoginTimes = tryLoginTimes;
    }

    public int getTryLoginTimes() {
        return this.tryLoginTimes;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "createTime=" + createTime +
                ", scc_token='" + scc_token + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", id=" + id +
                ", isAdmin=" + isAdmin +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", positionId=" + positionId +
                ", pwdLockDay=" + pwdLockDay +
                ", status=" + status +
                ", token='" + token + '\'' +
                ", tryLoginTimes=" + tryLoginTimes +
                ", updateTime=" + updateTime +
                '}';
    }
}
