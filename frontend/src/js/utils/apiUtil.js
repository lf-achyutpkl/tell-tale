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
      let response = { data : suggestions };
      resolve(response);
      // request
      //   .get(url + pathParam + queryParams)
      //   .end((err, res)=> {
      //     if (err || !res) {
      //       reject(err);
      //     }
      //     else {
      //       resolve(res.body);
      //     }
      //   })
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
  },

  edit(url, pathParam, data) {
    return new Promise((resolve, reject)=> {
      console.log('url',url);
      console.log('pathParam', pathParam);
      let response = { data : suggestions };
      response.data.forEach((value,index) => {
        if(value.id == data.id){
          response.data[index] = data;
          console.log('updated')
          debugger;
        }
      });
      resolve(response);
      // request
      //   .put(url + pathParam)
      //   .send(data)
      //   .set('Accept', 'application/json')
      //   .end((err, res)=> {
      //     if (err || !res) {
      //       reject(err);
      //     } else {
      //       resolve(res);
      //     }
      //   })
    });
  }


};

export default apiUtil