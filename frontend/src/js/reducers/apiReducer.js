/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

import actionTypeConstants from '../constants/actionTypeConstants';
import _ from 'lodash';

const initialState = {
  isRequesting: false,
  numberOfRequests: 0
};

let apiReducer = (state = initialState, action)=> {
  let newState;

  switch (action.type) {
    case actionTypeConstants.API_REQUEST:
      newState = _.cloneDeep(state);
      newState.isRequesting = true;
      newState.numberOfRequests++;
      return newState;

    case actionTypeConstants.API_RESPONSE:
      newState = _.cloneDeep(state);
      newState.numberOfRequests--;

      if (newState.numberOfRequests <= 0) {
        newState.isRequesting = false;
      }
      return newState;

    case actionTypeConstants.API_CLEAR_STATE:
      newState = _.cloneDeep(state);
      newState.numberOfRequests = 0;
      newState.isRequesting = false;
      return newState;

    default:
      return state;
  }
};

export default apiReducer;

