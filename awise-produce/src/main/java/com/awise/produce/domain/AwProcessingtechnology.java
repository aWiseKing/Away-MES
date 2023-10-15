package com.awise.produce.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 加工工艺信息对象 aw_processingtechnology
 * 
 * @author awise
 * @date 2023-09-27
 */
public class AwProcessingtechnology extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 加工工艺编号 */
    private String id;

    /** 创建人 */
    @Excel(name = "创建人")
    private String founder;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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
            .append("createTime", getCreateTime())
            .append("founder", getFounder())
            .append("status", getStatus())
            .toString();
    }
}
