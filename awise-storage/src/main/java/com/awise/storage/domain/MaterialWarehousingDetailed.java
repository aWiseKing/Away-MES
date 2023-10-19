package com.awise.storage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 材料入库详细对象 materialWarehousingDetailed
 * 
 * @author awise
 * @date 2023-10-19
 */
public class MaterialWarehousingDetailed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 入库单编号 */
    @Excel(name = "入库单编号")
    private String warehouseEntryID;

    /** 入库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warehousingDate;

    /** 制单人 */
    @Excel(name = "制单人")
    private String creator;

    /** 库管员 */
    @Excel(name = "库管员")
    private String warehouseKeeper;

    /** 经办人 */
    @Excel(name = "经办人")
    private String operator;

    /** 备注 */
    private String notes;

    /** 入库数量 */
    @Excel(name = "入库数量")
    private Long receiptQuantity;

    /** 附样 */
    private String sampleURL;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String name;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String typeName;

    /** 规格类型 */
    @Excel(name = "规格类型")
    private String specificationsType;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specificationModel;

    /** 材料密度#校验大于0，用于工艺下料后自动计算重量。规格类型确定了重量计算公式。材料计数单位都以kg计，材料密度单位为kg/mm^3，材料尺寸单位为mm# */
    private String materialDensity;

    public void setWarehouseEntryID(String warehouseEntryID) 
    {
        this.warehouseEntryID = warehouseEntryID;
    }

    public String getWarehouseEntryID() 
    {
        return warehouseEntryID;
    }
    public void setWarehousingDate(Date warehousingDate) 
    {
        this.warehousingDate = warehousingDate;
    }

    public Date getWarehousingDate() 
    {
        return warehousingDate;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setWarehouseKeeper(String warehouseKeeper) 
    {
        this.warehouseKeeper = warehouseKeeper;
    }

    public String getWarehouseKeeper() 
    {
        return warehouseKeeper;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }
    public void setReceiptQuantity(Long receiptQuantity) 
    {
        this.receiptQuantity = receiptQuantity;
    }

    public Long getReceiptQuantity() 
    {
        return receiptQuantity;
    }
    public void setSampleURL(String sampleURL) 
    {
        this.sampleURL = sampleURL;
    }

    public String getSampleURL() 
    {
        return sampleURL;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setSpecificationsType(String specificationsType) 
    {
        this.specificationsType = specificationsType;
    }

    public String getSpecificationsType() 
    {
        return specificationsType;
    }
    public void setSpecificationModel(String specificationModel) 
    {
        this.specificationModel = specificationModel;
    }

    public String getSpecificationModel() 
    {
        return specificationModel;
    }
    public void setMaterialDensity(String materialDensity) 
    {
        this.materialDensity = materialDensity;
    }

    public String getMaterialDensity() 
    {
        return materialDensity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("warehouseEntryID", getWarehouseEntryID())
            .append("warehousingDate", getWarehousingDate())
            .append("creator", getCreator())
            .append("warehouseKeeper", getWarehouseKeeper())
            .append("operator", getOperator())
            .append("notes", getNotes())
            .append("receiptQuantity", getReceiptQuantity())
            .append("sampleURL", getSampleURL())
            .append("name", getName())
            .append("typeName", getTypeName())
            .append("specificationsType", getSpecificationsType())
            .append("specificationModel", getSpecificationModel())
            .append("materialDensity", getMaterialDensity())
            .toString();
    }
}
