package com.awise.storage.service.impl;

import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.awise.storage.domain.AwLocalmaterials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awise.storage.mapper.AwLocaltoolMapper;
import com.awise.storage.domain.AwLocaltool;
import com.awise.storage.service.IAwLocaltoolService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 刀具库存Service业务层处理
 *
 * @author awise
 * @date 2023-12-18
 */
@Service
public class AwLocaltoolServiceImpl implements IAwLocaltoolService
{
    @Autowired
    private AwLocaltoolMapper awLocaltoolMapper;

    /**
     * 查询刀具库存
     *
     * @param id 刀具库存主键
     * @return 刀具库存
     */
    @Override
    public AwLocaltool selectAwLocaltoolById(Long id)
    {
        return awLocaltoolMapper.selectAwLocaltoolById(id);
    }

    /**
     * 查询刀具库存列表
     *
     * @param awLocaltool 刀具库存
     * @return 刀具库存
     */
    @Override
    public List<AwLocaltool> selectAwLocaltoolList(AwLocaltool awLocaltool)
    {
        return awLocaltoolMapper.selectAwLocaltoolList(awLocaltool);
    }

    /**
     * 新增刀具库存
     *
     * @param awLocaltool 刀具库存
     * @return 结果
     */
    @Override
    public int insertAwLocaltool(AwLocaltool awLocaltool)
    {
        return awLocaltoolMapper.insertAwLocaltool(awLocaltool);
    }

    /**
     * 修改刀具库存
     *
     * @param awLocaltool 刀具库存
     * @return 结果
     */
    @Override
    public int updateAwLocaltool(AwLocaltool awLocaltool)
    {
        return awLocaltoolMapper.updateAwLocaltool(awLocaltool);
    }

    /**
     * 批量删除刀具库存
     *
     * @param ids 需要删除的刀具库存主键
     * @return 结果
     */
    @Override
    public int deleteAwLocaltoolByIds(Long[] ids)
    {
        return awLocaltoolMapper.deleteAwLocaltoolByIds(ids);
    }

    /**
     * 删除刀具库存信息
     *
     * @param id 刀具库存主键
     * @return 结果
     */
    @Override
    public int deleteAwLocaltoolById(Long id)
    {
        return awLocaltoolMapper.deleteAwLocaltoolById(id);
    }



    /**
     * 刀具库存入库
     */

    @Override
    @Transactional
    public boolean addByNumber(List<Map<String, String>> lmlist) {
        ArrayList<AwLocaltool> cache_awLocaltools = new ArrayList<AwLocaltool>();
        for (Map<String, String> line : lmlist) {
            String toolInformationID  = line.get("key");
            Integer num = Integer.valueOf(line.get("value"));
            AwLocaltool awLocaltool = new AwLocaltool();
            awLocaltool.setToolInformationID(toolInformationID);
            List<AwLocaltool> list = selectAwLocaltoolList(awLocaltool);
            if (!list.isEmpty()){
                awLocaltool =list.get(0);
                awLocaltool.setNumber(awLocaltool.getNumber()+num);
                cache_awLocaltools.add(awLocaltool);
            }else {
                try {
                    awLocaltool.setNumber(Long.valueOf(0));
                    insertAwLocaltool(awLocaltool);
                    list = selectAwLocaltoolList(awLocaltool);
                    awLocaltool = list.get(0);
                    awLocaltool.setNumber(awLocaltool.getNumber()+num);
                    cache_awLocaltools.add(awLocaltool);
                }catch (Exception e){

                    return false;
                }
            }
        }
        for (AwLocaltool cacheAwLocaltool : cache_awLocaltools) {
            updateAwLocaltool(cacheAwLocaltool);
        }
        return true;
    }



    /**
     * 刀具库存出库
     */
    @Override
    @Transactional
    public boolean reduceByNumber(List<Map<String, String>> lmlist) {
        List<AwLocaltool> cache_awLocaltools = new ArrayList<AwLocaltool>();
        for (Map<String, String> line : lmlist) {
            String toolInformationID  = line.get("key");
            Integer num = Integer.valueOf(line.get("value"));
            AwLocaltool awLocaltool = new AwLocaltool();
            awLocaltool.setToolInformationID(toolInformationID);
            List<AwLocaltool> list = selectAwLocaltoolList(awLocaltool);
            if (!list.isEmpty()) {
                awLocaltool = list.get(0);
                if (awLocaltool.getNumber()-num>0){
                    awLocaltool.setNumber(awLocaltool.getNumber()-num);
                    cache_awLocaltools.add(awLocaltool);
                }else {

                    return false;
                }
            }else {
                return  false;
            }

        }
        for (AwLocaltool awLocaltool : cache_awLocaltools) {
            updateAwLocaltool(awLocaltool);
        }
        return true;
    }
}
