package com.awise.storage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 申购单对象 aw_purchaserequisition
 * 
 * @author ruoyi
 * @date 2023-10-14
 */
public class AwPurchaserequisition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申购单编号 */
    private String subscribeID;

    /** 申购人 */
    @Excel(name = "申购人")
    private String requisitioner;

    /** 申购日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申购日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subscriptionDate;

    /** 核准人 */
    @Excel(name = "核准人")
    private String approver;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setSubscribeID(String subscribeID) 
    {
        this.subscribeID = subscribeID;
    }

    public String getSubscribeID() 
    {
        return subscribeID;
    }
    public void setRequisitioner(String requisitioner) 
    {
        this.requisitioner = requisitioner;
    }

    public String getRequisitioner() 
    {
        return requisitioner;
    }
    public void setSubscriptionDate(Date subscriptionDate) 
    {
        this.subscriptionDate = subscriptionDate;
    }

    public Date getSubscriptionDate() 
    {
        return subscriptionDate;
    }
    public void setApprover(String approver) 
    {
        this.approver = approver;
    }

    public String getApprover() 
    {
        return approver;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("subscribeID", getSubscribeID())
            .append("requisitioner", getRequisitioner())
            .append("subscriptionDate", getSubscriptionDate())
            .append("approver", getApprover())
            .append("note", getNote())
            .append("status", getStatus())
            .toString();
    }
}
