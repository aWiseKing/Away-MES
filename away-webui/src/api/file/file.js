import request from '@/utils/request'

// 上传文件
export function listContract(query) {
  return request({
    url: '/order/contract/list',
    method: 'get',
    params: query
  })
}
