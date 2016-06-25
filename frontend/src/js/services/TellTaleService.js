/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

import apiUtil from '../utils/apiUtil';

//constants
import urlConstants from '../constants/urlConstants';

let url = window.location.origin + urlConstants.TELL_TALE_SERVER + '/';

let coreApiService = {
  fetch(pathParam, data){
    let queryParams = '';
    return apiUtil.fetch(url, pathParam.toLowerCase(), queryParams);
  },
  register(data){
    return apiUtil.register(url + 'users/', data);
  },
  login(data){
    return apiUtil.register(url + 'users/login/', data);
  }
};

export default coreApiService;