/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

//libraries
import request from 'superagent';

let suggestions = [
  {
    id: 'uuid112',
    suggestion: 'Bootstrap requires a containing element to wrap site contents and house our grid system. You may choose one of two containers to use in your projects. Note that, due to padding and more, neither container is nestable.',
    label: 'text',
    recipient: 'uuid',
    seen: false,
    starred: false,
    createdAt: ' 06/13/2016'
  },
  {
    id: 'uuid321',
    suggestion: 'Bootstrap requires a containing element to wrap site contents and house our grid system. You may choose one of two containers to use in your projects. Note that, due to padding and more, neither container is nestable.',
    label: 'text',
    recipient: 'uuid',
    seen: false,
    starred: true,
    createdAt: '06/13/2016'
  },
  {
    id: 'uuid132',
    suggestion: 'Bootstrap requires a containing element to wrap site contents and house our grid system. You may choose one of two containers to use in your projects. Note that, due to padding and more, neither container is nestable.',
    label: 'text',
    recipient: 'uuid',
    seen: false,
    starred: false,
    createdAt: '06/13/2016'
  }
];

let apiUtil = {
  fetch(url, pathParam, queryParams){
    return new Promise((resolve, reject)=> {
      request
        .get(url + pathParam + queryParams)
        .set('Authorization', 'Bearer' + ' ' + sessionStorage.getItem('tellTaleAuth'))
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
  create(url, pathParam, data){
    return new Promise((resolve, reject)=> {
      request
        .post(url + pathParam)
        .send(data)
        .set('Authorization', 'Bearer ' + sessionStorage.getItem('tellTaleAuth'))
        .set('Accept', 'application/json')
        .end((err, res)=> {
          if (err || !res) {
            reject(err);
          } else {
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
            reject(err);
          }
          else {
            resolve(res.body);
          }
        })
    });
  },

  edit(url, pathParam, data) {
    return new Promise((resolve, reject)=> {
      resolve(response);
      request
        .put(url + pathParam)
        .send(data)
        .set('Authorization', 'Bearer' + ' ' + sessionStorage.getItem('tellTaleAuth'))
        .set('Accept', 'application/json')
        .end((err, res)=> {
          if (err || !res) {
            reject(err);
          } else {
            resolve(res);
          }
        })
    });
  }


};

export default apiUtil