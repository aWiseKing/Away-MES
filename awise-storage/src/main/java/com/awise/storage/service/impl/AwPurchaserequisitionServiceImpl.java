package com.awise.storage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awise.storage.mapper.AwPurchaserequisitionMapper;
import com.awise.storage.domain.AwPurchaserequisition;
import com.awise.storage.service.IAwPurchaserequisitionService;

/**
 * 申购单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-14
 */
@Service
public class AwPurchaserequisitionServiceImpl implements IAwPurchaserequisitionService 
{
    @Autowired
    private AwPurchaserequisitionMapper awPurchaserequisitionMapper;

    /**
     * 查询申购单
     * 
     * @param subscribeID 申购单主键
     * @return 申购单
     */
    @Override
    public AwPurchaserequisition selectAwPurchaserequisitionBySubscribeID(String subscribeID)
    {
        return awPurchaserequisitionMapper.selectAwPurchaserequisitionBySubscribeID(subscribeID);
    }

    /**
     * 查询申购单列表
     * 
     * @param awPurchaserequisition 申购单
     * @return 申购单
     */
    @Override
    public List<AwPurchaserequisition> selectAwPurchaserequisitionList(AwPurchaserequisition awPurchaserequisition)
    {
        return awPurchaserequisitionMapper.selectAwPurchaserequisitionList(awPurchaserequisition);
    }

    /**
     * 新增申购单
     * 
     * @param awPurchaserequisition 申购单
     * @return 结果
     */
    @Override
    public int insertAwPurchaserequisition(AwPurchaserequisition awPurchaserequisition)
    {
        return awPurchaserequisitionMapper.insertAwPurchaserequisition(awPurchaserequisition);
    }

    /**
     * 修改申购单
     * 
     * @param awPurchaserequisition 申购单
     * @return 结果
     */
    @Override
    public int updateAwPurchaserequisition(AwPurchaserequisition awPurchaserequisition)
    {
        return awPurchaserequisitionMapper.updateAwPurchaserequisition(awPurchaserequisition);
    }

    /**
     * 批量删除申购单
     * 
     * @param subscribeIDs 需要删除的申购单主键
     * @return 结果
     */
    @Override
    public int deleteAwPurchaserequisitionBySubscribeIDs(String[] subscribeIDs)
    {
        return awPurchaserequisitionMapper.deleteAwPurchaserequisitionBySubscribeIDs(subscribeIDs);
    }

    /**
     * 删除申购单信息
     * 
     * @param subscribeID 申购单主键
     * @return 结果
     */
    @Override
    public int deleteAwPurchaserequisitionBySubscribeID(String subscribeID)
    {
        return awPurchaserequisitionMapper.deleteAwPurchaserequisitionBySubscribeID(subscribeID);
    }
}
