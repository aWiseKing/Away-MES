package com.awise.finance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 外协对账详细对象 aw_OutsourcingReconciliation
 * 
 * @author awise
 * @date 2023-12-26
 */
public class AwOutsourcingreconciliation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 外协对账d */
    @Excel(name = "外协对账d")
    private Long id;

    /** 外协对账单编号 */
    @Excel(name = "外协对账单编号")
    private String outsourcingStatementsID;

    /** 外协订单id */
    @Excel(name = "外协订单id")
    private String outsourcingOrderID;

    /** 外协数量 */
    @Excel(name = "外协数量")
    private Long outsourcingQuantity;

    /** 外协完成数量 */
    @Excel(name = "外协完成数量")
    private Long completedQuantity;

    /** 未完成数量 */
    @Excel(name = "未完成数量")
    private Long unfinishedQuantity;

    /** 应付金额 */
    @Excel(name = "应付金额")
    private String amountDue;

    /** 实付金额 */
    @Excel(name = "实付金额")
    private String outOfPocketAmount;

    /** 未付金额 */
    @Excel(name = "未付金额")
    private String unpaidAmount;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOutsourcingStatementsID(String outsourcingStatementsID) 
    {
        this.outsourcingStatementsID = outsourcingStatementsID;
    }

    public String getOutsourcingStatementsID() 
    {
        return outsourcingStatementsID;
    }
    public void setOutsourcingOrderID(String outsourcingOrderID) 
    {
        this.outsourcingOrderID = outsourcingOrderID;
    }

    public String getOutsourcingOrderID() 
    {
        return outsourcingOrderID;
    }
    public void setOutsourcingQuantity(Long outsourcingQuantity) 
    {
        this.outsourcingQuantity = outsourcingQuantity;
    }

    public Long getOutsourcingQuantity() 
    {
        return outsourcingQuantity;
    }
    public void setCompletedQuantity(Long completedQuantity) 
    {
        this.completedQuantity = completedQuantity;
    }

    public Long getCompletedQuantity() 
    {
        return completedQuantity;
    }
    public void setUnfinishedQuantity(Long unfinishedQuantity) 
    {
        this.unfinishedQuantity = unfinishedQuantity;
    }

    public Long getUnfinishedQuantity() 
    {
        return unfinishedQuantity;
    }
    public void setAmountDue(String amountDue) 
    {
        this.amountDue = amountDue;
    }

    public String getAmountDue() 
    {
        return amountDue;
    }
    public void setOutOfPocketAmount(String outOfPocketAmount) 
    {
        this.outOfPocketAmount = outOfPocketAmount;
    }

    public String getOutOfPocketAmount() 
    {
        return outOfPocketAmount;
    }
    public void setUnpaidAmount(String unpaidAmount) 
    {
        this.unpaidAmount = unpaidAmount;
    }

    public String getUnpaidAmount() 
    {
        return unpaidAmount;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
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
            .append("id", getId())
            .append("outsourcingStatementsID", getOutsourcingStatementsID())
            .append("outsourcingOrderID", getOutsourcingOrderID())
            .append("outsourcingQuantity", getOutsourcingQuantity())
            .append("completedQuantity", getCompletedQuantity())
            .append("unfinishedQuantity", getUnfinishedQuantity())
            .append("amountDue", getAmountDue())
            .append("outOfPocketAmount", getOutOfPocketAmount())
            .append("unpaidAmount", getUnpaidAmount())
            .append("notes", getNotes())
            .append("status", getStatus())
            .toString();
    }
}
