/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.model;

import java.util.Date;

/**
 *
 * @author anbq
 */
public class SessionUser {

    private int id;
    private int nguoiDungId;
    private Date expriceTime;
    private String sessionKey;
    private String rold;

    public SessionUser() {
    }

    public SessionUser(int id, int nguoiDungId, Date expriceTime, String sessionKey, String rold) {
        this.id = id;
        this.nguoiDungId = nguoiDungId;
        this.expriceTime = expriceTime;
        this.sessionKey = sessionKey;
        this.rold = rold;
    }

    public int getId() {
        return id;
    }

    public int getNguoiDungId() {
        return nguoiDungId;
    }

    public Date getExpriceTime() {
        return expriceTime;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public String getRold() {
        return rold;
    }

    public void setId(int sessionID) {
        this.id = sessionID;
    }

    public void setNguoiDungId(int nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }

    public void setExpriceTime(Date expriceTime) {
        this.expriceTime = expriceTime;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public void setRold(String rold) {
        this.rold = rold;
    }

}
