/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

//Utils
import apiUtil from '../utils/apiUtil';
import converter from '../utils/converter';

//constants
import urlConstants from '../constants/urlConstants';

let url = window.location.origin + urlConstants.TELL_TALE_SERVER + '/';

let coreApiService = {
  fetch(pathParam, data){
    let queryParams = '';
    return apiUtil.fetch(url, pathParam.toLowerCase(), queryParams);
  },
  create(data){
    return apiUtil.create(url,'suggestions',data);
  },
  register(data){
    return apiUtil.register(url + 'users/', data);
  },
  login(data){
    return apiUtil.register(url + 'users/login/', data);
  },

  edit(resourceName, data, dataId) {
    return apiUtil.edit(url, converter.getPathParam(resourceName.toLowerCase(), dataId), data);
  }
};

export default coreApiService;