package com.awise.produce.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 加工工艺信息对象 aw_processingtechnology
 * 
 * @author awise
 * @date 2023-09-26
 */
public class AwProcessingtechnology extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 加工工艺编号 */
    private String id;

    /** 创建人 */
    @Excel(name = "创建人")
    private String founder;

    /** 下料尺寸 */
    @Excel(name = "下料尺寸")
    private Long cuttingSize;

    /** 可制件数 */
    @Excel(name = "可制件数")
    private String numberProducibleParts;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFounder(String founder) 
    {
        this.founder = founder;
    }

    public String getFounder() 
    {
        return founder;
    }
    public void setCuttingSize(Long cuttingSize) 
    {
        this.cuttingSize = cuttingSize;
    }

    public Long getCuttingSize() 
    {
        return cuttingSize;
    }
    public void setNumberProducibleParts(String numberProducibleParts) 
    {
        this.numberProducibleParts = numberProducibleParts;
    }

    public String getNumberProducibleParts() 
    {
        return numberProducibleParts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("founder", getFounder())
            .append("cuttingSize", getCuttingSize())
            .append("numberProducibleParts", getNumberProducibleParts())
            .toString();
    }
}
