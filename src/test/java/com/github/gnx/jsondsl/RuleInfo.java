package com.github.gnx.jsondsl;

import com.github.gnx.jsondsl.annotation.CompareByVersion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/19 22:10
 */
public class RuleInfo {

    /**
     * 客户端类型
     */
    private Integer clientType;

    /**
     * 版本号
     */
    private String version;

    /**
     * vip等级
     */
    private int vip;

    /**
     * 用户余额
     */
    private BigDecimal balance;

    /**
     * 当前时间
     */
    private final Date time = new Date();


    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    /**
     * 使用 版本比较器
     */
    @CompareByVersion
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getTime() {
        return time;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
}
