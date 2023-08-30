package com.awise.produce.mapper;

import java.util.List;
import com.awise.produce.domain.AwProcessingtechnology;

/**
 * 加工工艺信息Mapper接口
 * 
 * @author awise
 * @date 2023-08-21
 */
public interface AwProcessingtechnologyMapper 
{
    /**
     * 查询加工工艺信息
     * 
     * @param id 加工工艺信息主键
     * @return 加工工艺信息
     */
    public AwProcessingtechnology selectAwProcessingtechnologyById(String id);

    /**
     * 查询加工工艺信息列表
     * 
     * @param awProcessingtechnology 加工工艺信息
     * @return 加工工艺信息集合
     */
    public List<AwProcessingtechnology> selectAwProcessingtechnologyList(AwProcessingtechnology awProcessingtechnology);

    /**
     * 查询生产任务对应加工工艺信息
     *
     * @param productionTasksID 加工工艺信息主键
     * @return 加工工艺信息
     */
    public AwProcessingtechnology selectAwProcessingtechnologyByProductionTasksID(String productionTasksID);

    /**
     * 新增加工工艺信息
     * 
     * @param awProcessingtechnology 加工工艺信息
     * @return 结果
     */
    public int insertAwProcessingtechnology(AwProcessingtechnology awProcessingtechnology);

    /**
     * 修改加工工艺信息
     * 
     * @param awProcessingtechnology 加工工艺信息
     * @return 结果
     */
    public int updateAwProcessingtechnology(AwProcessingtechnology awProcessingtechnology);

    /**
     * 删除加工工艺信息
     * 
     * @param id 加工工艺信息主键
     * @return 结果
     */
    public int deleteAwProcessingtechnologyById(String id);

    /**
     * 批量删除加工工艺信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAwProcessingtechnologyByIds(String[] ids);
}
