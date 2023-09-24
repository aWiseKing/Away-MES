package com.awise.comprehensive.service;

import java.util.List;
import com.awise.comprehensive.domain.AwContacts;

/**
 * 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位Service接口
 * 
 * @author ruoyi
 * @date 2023-09-22
 */
public interface IAwContactsService 
{
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
    public AwContacts selectAwContactsById(Long id);

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
存储  联系人姓名，电话，部门，职位集合
     */
    public List<AwContacts> selectAwContactsList(AwContacts awContacts);

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
    public int insertAwContacts(AwContacts awContacts);

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
    public int updateAwContacts(AwContacts awContacts);

    /**
     * 批量删除联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     * 
     * @param ids 需要删除的联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位主键集合
     * @return 结果
     */
    public int deleteAwContactsByIds(Long[] ids);

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
    public int deleteAwContactsById(Long id);
}
