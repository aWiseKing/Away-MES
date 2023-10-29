package com.awise.storage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awise.storage.mapper.AwLocalmaterialsMapper;
import com.awise.storage.domain.AwLocalmaterials;
import com.awise.storage.service.IAwLocalmaterialsService;
import org.springframework.transaction.annotation.Transactional;

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

    /***
     *  本地实时库存新增
     * @param lmlist 需要新增库存的材料列表
     * @return 结果
     */
    @Override
    @Transactional
    public boolean addByNumber(List<Map<String,Integer>> lmlist){
        for(Map<String, Integer> line : lmlist) {
            String materialID = line.keySet().iterator().next();
            Integer num = line.get(materialID);
            AwLocalmaterials awLocalmaterials = new AwLocalmaterials();
            awLocalmaterials.setMaterialID(materialID);
            List<AwLocalmaterials> list = selectAwLocalmaterialsList(awLocalmaterials);

            if (!list.isEmpty()) {
                awLocalmaterials = list.get(0);
                awLocalmaterials.setNumber(awLocalmaterials.getNumber() + num);
                updateAwLocalmaterials(awLocalmaterials);
            } else {
                try {
                    awLocalmaterials.setNumber(Long.valueOf(num));
                    insertAwLocalmaterials(awLocalmaterials);
                }catch (Exception e){
                    return false;
                }

            }
        }
        return true;
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
