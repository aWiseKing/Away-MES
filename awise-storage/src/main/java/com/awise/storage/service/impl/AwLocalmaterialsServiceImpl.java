package com.awise.storage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awise.storage.mapper.AwLocalmaterialsMapper;
import com.awise.storage.domain.AwLocalmaterials;
import com.awise.storage.service.IAwLocalmaterialsService;

/**
 * 本地材料实时库存Service业务层处理
 * 
 * @author awise
 * @date 2023-08-03
 */
@Service
public class AwLocalmaterialsServiceImpl implements IAwLocalmaterialsService 
{
    @Autowired
    private AwLocalmaterialsMapper awLocalmaterialsMapper;

    /**
     * 查询本地材料实时库存
     * 
     * @param id 本地材料实时库存主键
     * @return 本地材料实时库存
     */
    @Override
    public AwLocalmaterials selectAwLocalmaterialsById(Long id)
    {
        return awLocalmaterialsMapper.selectAwLocalmaterialsById(id);
    }

    /**
     * 查询本地材料实时库存列表
     * 
     * @param awLocalmaterials 本地材料实时库存
     * @return 本地材料实时库存
     */
    @Override
    public List<AwLocalmaterials> selectAwLocalmaterialsList(AwLocalmaterials awLocalmaterials)
    {
        return awLocalmaterialsMapper.selectAwLocalmaterialsList(awLocalmaterials);
    }

    /**
     * 新增本地材料实时库存
     * 
     * @param awLocalmaterials 本地材料实时库存
     * @return 结果
     */
    @Override
    public int insertAwLocalmaterials(AwLocalmaterials awLocalmaterials)
    {
        return awLocalmaterialsMapper.insertAwLocalmaterials(awLocalmaterials);
    }

    /**
     * 修改本地材料实时库存
     * 
     * @param awLocalmaterials 本地材料实时库存
     * @return 结果
     */
    @Override
    public int updateAwLocalmaterials(AwLocalmaterials awLocalmaterials)
    {
        return awLocalmaterialsMapper.updateAwLocalmaterials(awLocalmaterials);
    }

    /**
     * 批量删除本地材料实时库存
     * 
     * @param ids 需要删除的本地材料实时库存主键
     * @return 结果
     */
    @Override
    public int deleteAwLocalmaterialsByIds(Long[] ids)
    {
        return awLocalmaterialsMapper.deleteAwLocalmaterialsByIds(ids);
    }

    /**
     * 删除本地材料实时库存信息
     * 
     * @param id 本地材料实时库存主键
     * @return 结果
     */
    @Override
    public int deleteAwLocalmaterialsById(Long id)
    {
        return awLocalmaterialsMapper.deleteAwLocalmaterialsById(id);
    }
}
