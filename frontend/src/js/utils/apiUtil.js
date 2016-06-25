/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

//libraries
import request from 'superagent';

let apiUtil = {
  fetch(url, pathParam, queryParams){
    return new Promise((resolve, reject)=> {
      request
        .get(url + pathParam + queryParams)
        .end((err, res)=> {
          if (err || !res) {
            reject(err);
          }
          else {
            resolve(res.body);
          }
        })
    });
  },
  register(url, data){
    return new Promise((resolve, reject)=> {
      request
        .post(url)
        .send(data)
        .set('Accept', 'application/json')
        .end((err, res)=> {
          if (err || !res) {
            reject(err);
          }
          else {
            resolve(res.body);
          }
        })
    });
  },
  login(url, data){
    return new Promise((resolve, reject)=> {
      request
        .post(url)
        .send(data)
        .set('Accept', 'application/json')
        .end((err, res)=> {
          if (err || !res) {
            console.log(err, 'err');
            reject(err);
          }
          else {
            console.log(res, 'res');
            resolve(res.body);
          }
        })
    });
  }
};

export default apiUtil