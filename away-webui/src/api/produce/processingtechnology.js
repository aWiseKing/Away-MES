import request from '@/utils/request'

// 查询加工工艺信息列表
export function listProcessingtechnology(query) {
  return request({
    url: '/produce/processingtechnology/list',
    method: 'get',
    params: query
  })
}

// 查询加工工艺信息详细
export function getProcessingtechnology(id) {
  return request({
    url: '/produce/processingtechnology/productionTasks',
    method: 'get',
    params: id
  })
}

// 新增加工工艺信息
export function addProcessingtechnology(data) {
  return request({
    url: '/produce/processingtechnology',
    method: 'post',
    data: data
  })
}

// 修改加工工艺信息
export function updateProcessingtechnology(data) {
  return request({
    url: '/produce/processingtechnology',
    method: 'put',
    data: data
  })
}

// 删除加工工艺信息
export function delProcessingtechnology(id) {
  return request({
    url: '/produce/processingtechnology/' + id,
    method: 'delete'
  })
}
