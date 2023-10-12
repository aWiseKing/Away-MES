import request from '@/utils/request'

// 查询联系人信息
// 存储合作方的联系人的联系信息
// 存储  联系人姓名，电话，部门，职位列表
export function listContacts(query) {
  return request({
    url: '/system/contacts/list',
    method: 'get',
    params: query
  })
}

// 查询联系人信息
// 存储合作方的联系人的联系信息
// 存储  联系人姓名，电话，部门，职位详细
export function getContacts(id) {
  return request({
    url: '/system/contacts/' + id,
    method: 'get'
  })
}

// 新增联系人信息
// 存储合作方的联系人的联系信息
// 存储  联系人姓名，电话，部门，职位
export function addContacts(data) {
  return request({
    url: '/system/contacts',
    method: 'post',
    data: data
  })
}

// 修改联系人信息
// 存储合作方的联系人的联系信息
// 存储  联系人姓名，电话，部门，职位
export function updateContacts(data) {
  return request({
    url: '/system/contacts',
    method: 'put',
    data: data
  })
}

// 删除联系人信息
// 存储合作方的联系人的联系信息
// 存储  联系人姓名，电话，部门，职位
export function delContacts(id) {
  return request({
    url: '/system/contacts/' + id,
    method: 'delete'
  })
}
