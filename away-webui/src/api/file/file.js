import request from '@/utils/request'

// 上传文件
export function fileUpdate(query) {
  return request({
    url: '/awise/file/update',
    method: 'post',
    data: query
  })
}
// 下载文件
export function fileDownload(query) {
  return request({
    url: '/awise/file/download/'+query,
    method: 'get',
    responseType: 'blob'

  })
}

// 删除文件
export function fileDelete(query) {
  return request({
    url: '/awise/file/delete',
    method: 'get',
    params: query
  })
}

