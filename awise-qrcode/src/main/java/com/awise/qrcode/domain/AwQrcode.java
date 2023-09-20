package com.awise.qrcode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.away.common.annotation.Excel;
import com.away.common.core.domain.BaseEntity;

/**
 * 二维码对象 aw_qrcode
 * 
 * @author awise
 * @date 2023-09-18
 */
public class AwQrcode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 二维码唯一标识符 */
    private Long id;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String fileurl;

    /** 工序对应编号 */
    @Excel(name = "工序对应编号")
    private Long processingprocessID;

    /** 有效时长 */
    @Excel(name = "有效时长")
    private String effectiveDuration;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFileurl(String fileurl) 
    {
        this.fileurl = fileurl;
    }

    public String getFileurl() 
    {
        return fileurl;
    }
    public void setProcessingprocessID(Long processingprocessID) 
    {
        this.processingprocessID = processingprocessID;
    }

    public Long getProcessingprocessID() 
    {
        return processingprocessID;
    }
    public void setEffectiveDuration(String effectiveDuration) 
    {
        this.effectiveDuration = effectiveDuration;
    }

    public String getEffectiveDuration() 
    {
        return effectiveDuration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileurl", getFileurl())
            .append("processingprocessID", getProcessingprocessID())
            .append("createTime", getCreateTime())
            .append("effectiveDuration", getEffectiveDuration())
            .toString();
    }
}
