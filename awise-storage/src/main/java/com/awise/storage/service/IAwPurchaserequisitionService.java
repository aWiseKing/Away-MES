package com.awise.storage.service;

import java.util.List;
import com.awise.storage.domain.AwPurchaserequisition;

/**
 * 申购单Service接口
 * 
 * @author ruoyi
 * @date 2023-10-14
 */
public interface IAwPurchaserequisitionService 
{
    /**
     * 查询申购单
     * 
     * @param subscribeID 申购单主键
     * @return 申购单
     */
    public AwPurchaserequisition selectAwPurchaserequisitionBySubscribeID(String subscribeID);

    /**
     * 查询申购单列表
     * 
     * @param awPurchaserequisition 申购单
     * @return 申购单集合
     */
    public List<AwPurchaserequisition> selectAwPurchaserequisitionList(AwPurchaserequisition awPurchaserequisition);

    /**
     * 新增申购单
     * 
     * @param awPurchaserequisition 申购单
     * @return 结果
     */
    public int insertAwPurchaserequisition(AwPurchaserequisition awPurchaserequisition);

    /**
     * 修改申购单
     * 
     * @param awPurchaserequisition 申购单
     * @return 结果
     */
    public int updateAwPurchaserequisition(AwPurchaserequisition awPurchaserequisition);

    /**
     * 批量删除申购单
     * 
     * @param subscribeIDs 需要删除的申购单主键集合
     * @return 结果
     */
    public int deleteAwPurchaserequisitionBySubscribeIDs(String[] subscribeIDs);

    /**
     * 删除申购单信息
     * 
     * @param subscribeID 申购单主键
     * @return 结果
     */
    public int deleteAwPurchaserequisitionBySubscribeID(String subscribeID);
}
