package com.awise.comprehensive.service.impl;

import java.util.List;

import com.awise.comprehensive.domain.AwContacts;
import com.awise.comprehensive.mapper.AwContactsMapper;
import com.awise.comprehensive.service.IAwContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-22
 */
@Service
public class AwContactsServiceImpl implements IAwContactsService
{
    @Autowired
    private AwContactsMapper awContactsMapper;

    /**
     * 查询联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * 
     * @param id 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位主键
     * @return 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     */
    @Override
    public AwContacts selectAwContactsById(Long id)
    {
        return awContactsMapper.selectAwContactsById(id);
    }

    /**
     * 查询联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位列表
     * 
     * @param awContacts 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * @return 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     */
    @Override
    public List<AwContacts> selectAwContactsList(AwContacts awContacts)
    {
        return awContactsMapper.selectAwContactsList(awContacts);
    }

    /**
     * 新增联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * 
     * @param awContacts 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * @return 结果
     */
    @Override
    public int insertAwContacts(AwContacts awContacts)
    {
        return awContactsMapper.insertAwContacts(awContacts);
    }

    /**
     * 修改联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * 
     * @param awContacts 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * @return 结果
     */
    @Override
    public int updateAwContacts(AwContacts awContacts)
    {
        return awContactsMapper.updateAwContacts(awContacts);
    }

    /**
     * 批量删除联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * 
     * @param ids 需要删除的联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位主键
     * @return 结果
     */
    @Override
    public int deleteAwContactsByIds(Long[] ids)
    {
        return awContactsMapper.deleteAwContactsByIds(ids);
    }

    /**
     * 删除联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位信息
     * 
     * @param id 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位主键
     * @return 结果
     */
    @Override
    public int deleteAwContactsById(Long id)
    {
        return awContactsMapper.deleteAwContactsById(id);
    }
}
