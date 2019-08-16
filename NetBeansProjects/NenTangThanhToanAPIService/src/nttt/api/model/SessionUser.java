/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.model;

/**
 *
 * @author anbq
 */
public class SessionUser {

    private int id;
    private String partnerCode;
    private String username;
    private long expriceTime;
    private String sessionKey;
    private int role;

    public SessionUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public long getExpriceTime() {
        return expriceTime;
    }

    public void setExpriceTime(long expriceTime) {
        this.expriceTime = expriceTime;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
