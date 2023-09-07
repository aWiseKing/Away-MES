export default class FileDown {
  constructor(url, file) {
    this.url = url;
    this.file = file;
    return this.url;
  }
  getFile() {
    return this.file;
  }
  getUrl() {
    return this.url;
  }
}
