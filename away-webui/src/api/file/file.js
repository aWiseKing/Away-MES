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
export async function fileDownload(query) {
  let response = await request({
    url: '/awise/file/download/' + query,
    method: 'get',
    responseType: 'blob'

  })
  let blob = response
  let tmp_url = window.URL.createObjectURL(blob)
  return tmp_url
}

// 删除文件
export function fileDelete(query) {
  return request({
    url: '/awise/file/delete',
    method: 'get',
    params: query
  })
}

