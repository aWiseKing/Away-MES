package com.awise.produce.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 产品出库详单对象 detailproductoutbound
 * 
 * @author awise
 * @date 2023-10-16
 */
public class Detailproductoutbound extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 出库单编号 */
    @Excel(name = "出库单编号")
    private String deliveryNoteID;

    /** 产品图号 */
    @Excel(name = "产品图号")
    private String productID;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productname;

    /** 出货检验编号 */
    @Excel(name = "出货检验编号")
    private String shippingInspectionID;

    /** 出货数量 */
    @Excel(name = "出货数量")
    private Long shipmentQuantity;

    /** 检测数量 */
    @Excel(name = "检测数量")
    private Long detectionQuantity;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private Long qualifiedQuantity;

    /** 不合格数量 */
    @Excel(name = "不合格数量")
    private Long unqualifiedQuantity;

    /** 检测结果 */
    @Excel(name = "检测结果")
    private String testResult;

    /** 检测日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date testDate;

    /** 检测人员 */
    @Excel(name = "检测人员")
    private String testingPersonnel;

    /** 出库数量 */
    @Excel(name = "出库数量")
    private Long receiptQuantity;

    /** 客户编号 */
    @Excel(name = "客户编号")
    private String contractID;

    /** 实体姓名 */
    @Excel(name = "实体姓名")
    private String customname;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDeliveryNoteID(String deliveryNoteID) 
    {
        this.deliveryNoteID = deliveryNoteID;
    }

    public String getDeliveryNoteID() 
    {
        return deliveryNoteID;
    }
    public void setProductID(String productID) 
    {
        this.productID = productID;
    }

    public String getProductID() 
    {
        return productID;
    }
    public void setProductname(String productname) 
    {
        this.productname = productname;
    }

    public String getProductname() 
    {
        return productname;
    }
    public void setShippingInspectionID(String shippingInspectionID) 
    {
        this.shippingInspectionID = shippingInspectionID;
    }

    public String getShippingInspectionID() 
    {
        return shippingInspectionID;
    }
    public void setShipmentQuantity(Long shipmentQuantity) 
    {
        this.shipmentQuantity = shipmentQuantity;
    }

    public Long getShipmentQuantity() 
    {
        return shipmentQuantity;
    }
    public void setDetectionQuantity(Long detectionQuantity) 
    {
        this.detectionQuantity = detectionQuantity;
    }

    public Long getDetectionQuantity() 
    {
        return detectionQuantity;
    }
    public void setQualifiedQuantity(Long qualifiedQuantity) 
    {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public Long getQualifiedQuantity() 
    {
        return qualifiedQuantity;
    }
    public void setUnqualifiedQuantity(Long unqualifiedQuantity) 
    {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public Long getUnqualifiedQuantity() 
    {
        return unqualifiedQuantity;
    }
    public void setTestResult(String testResult) 
    {
        this.testResult = testResult;
    }

    public String getTestResult() 
    {
        return testResult;
    }
    public void setTestDate(Date testDate) 
    {
        this.testDate = testDate;
    }

    public Date getTestDate() 
    {
        return testDate;
    }
    public void setTestingPersonnel(String testingPersonnel) 
    {
        this.testingPersonnel = testingPersonnel;
    }

    public String getTestingPersonnel() 
    {
        return testingPersonnel;
    }
    public void setReceiptQuantity(Long receiptQuantity) 
    {
        this.receiptQuantity = receiptQuantity;
    }

    public Long getReceiptQuantity() 
    {
        return receiptQuantity;
    }
    public void setContractID(String contractID) 
    {
        this.contractID = contractID;
    }

    public String getContractID() 
    {
        return contractID;
    }
    public void setCustomname(String customname) 
    {
        this.customname = customname;
    }

    public String getCustomname() 
    {
        return customname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deliveryNoteID", getDeliveryNoteID())
            .append("productID", getProductID())
            .append("productname", getProductname())
            .append("shippingInspectionID", getShippingInspectionID())
            .append("shipmentQuantity", getShipmentQuantity())
            .append("detectionQuantity", getDetectionQuantity())
            .append("qualifiedQuantity", getQualifiedQuantity())
            .append("unqualifiedQuantity", getUnqualifiedQuantity())
            .append("testResult", getTestResult())
            .append("testDate", getTestDate())
            .append("testingPersonnel", getTestingPersonnel())
            .append("receiptQuantity", getReceiptQuantity())
            .append("contractID", getContractID())
            .append("customname", getCustomname())
            .toString();
    }
}
