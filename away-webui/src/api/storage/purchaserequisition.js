import request from '@/utils/request'

// 查询申购单列表
export function listPurchaserequisition(query) {
  return request({
    url: '/storage/purchaserequisition/list',
    method: 'get',
    params: query
  })
}

// 查询申购单详细
export function getPurchaserequisition(subscribeID) {
  return request({
    url: '/storage/purchaserequisition/' + subscribeID,
    method: 'get'
  })
}

// 新增申购单
export function addPurchaserequisition(data) {
  return request({
    url: '/storage/purchaserequisition',
    method: 'post',
    data: data
  })
}

// 修改申购单
export function updatePurchaserequisition(data) {
  return request({
    url: '/storage/purchaserequisition',
    method: 'put',
    data: data
  })
}

// 删除申购单
export function delPurchaserequisition(subscribeID) {
  return request({
    url: '/storage/purchaserequisition/' + subscribeID,
    method: 'delete'
  })
}
