package com.awise.finance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 对账详细对象 aw_DetailReconciliation
 * 
 * @author awise
 * @date 2023-12-22
 */
public class AwDetailreconciliation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 对账id */
    private Long id;

    /** 对账单Id */
    @Excel(name = "对账单Id")
    private String statementOfAccountID;

    /** 订单id */
    @Excel(name = "订单id")
    private String saleorderID;

    /** 合同金额 */
    @Excel(name = "合同金额")
    private String money;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String name;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 需求数量 */
    @Excel(name = "需求数量")
    private Long number;

    /** 以供产品数 */
    @Excel(name = "以供产品数")
    private Long numberOfProductsSupplied;

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

    /** 是否通过 */
    @Excel(name = "是否通过")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStatementOfAccountID(String statementOfAccountID) 
    {
        this.statementOfAccountID = statementOfAccountID;
    }

    public String getStatementOfAccountID() 
    {
        return statementOfAccountID;
    }
    public void setSaleorderID(String saleorderID) 
    {
        this.saleorderID = saleorderID;
    }

    public String getSaleorderID() 
    {
        return saleorderID;
    }
    public void setMoney(String money) 
    {
        this.money = money;
    }

    public String getMoney() 
    {
        return money;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setNumberOfProductsSupplied(Long numberOfProductsSupplied) 
    {
        this.numberOfProductsSupplied = numberOfProductsSupplied;
    }

    public Long getNumberOfProductsSupplied() 
    {
        return numberOfProductsSupplied;
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
            .append("statementOfAccountID", getStatementOfAccountID())
            .append("saleorderID", getSaleorderID())
            .append("money", getMoney())
            .append("name", getName())
            .append("productName", getProductName())
            .append("number", getNumber())
            .append("numberOfProductsSupplied", getNumberOfProductsSupplied())
            .append("amountDue", getAmountDue())
            .append("outOfPocketAmount", getOutOfPocketAmount())
            .append("unpaidAmount", getUnpaidAmount())
            .append("notes", getNotes())
            .append("status", getStatus())
            .toString();
    }
}
