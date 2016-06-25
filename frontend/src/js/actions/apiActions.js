/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

import actionTypeConstants from '../constants/actionTypeConstants';

let apiActions = {
  apiRequest(){
    return{
      type: actionTypeConstants.API_REQUEST
    }
  },

  apiResponse(){
    return{
      type: actionTypeConstants.API_RESPONSE
    }
  },

  apiClearState(){
    return{
      type: actionTypeConstants.API_CLEAR_STATE
    }
  }
};

export default apiActions
