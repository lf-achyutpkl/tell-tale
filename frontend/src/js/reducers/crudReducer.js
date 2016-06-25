/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/25/16.
 */

//constants
import actionTypeConstants from '../constants/actionTypeConstants';

//libraries
import _ from 'lodash';

const initialState = {
  suggestions: []
};

let crudReducer = function (state = initialState, action) {
  let newState;

  switch (action.type) {
    case actionTypeConstants.LIST:
      newState = _.cloneDeep(state);
      newState[action.entity] = _.cloneDeep(action.data.data);
      return newState;

    case actionTypeConstants.UPDATE:
      newState = _.cloneDeep(state);
      newState[action.entity].forEach((value,index) => {
        if(value.id == action.data.id){
          newState[action.entity][index] = action.data;
          console.log('match found')
        }
      });
      return newState;

    default:
      return state;
  }
};

export default crudReducer;