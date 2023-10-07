// 取出key对应的value
export function getValue(dict, key) {
  let num = 0
  for (num in dict) {
    if (dict[num]["key"] == key) {
      return dict[num]["value"]
    }
  }
  return "未知状态"
}
