/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/25/16.
 */

let converter = {

  serialize(data) {
    if (typeof(data) != 'object') {
      return '?' + data;
    }
    let str = [];
    for (let p in data) {
      if (data[p] && data.hasOwnProperty(p)) {
        str.push(encodeURIComponent(p) + '=' + encodeURIComponent(data[p]));
      }
    }
    return '?' + str.join('&');
  },

  getPathParam() {
    let args = arguments;
    let params = [];

    for (let a in args) {
      params.push(args[a]);
    }
    return params.join('/');
  }
};

export default converter;