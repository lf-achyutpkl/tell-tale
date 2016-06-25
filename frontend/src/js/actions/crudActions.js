/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/25/16.
 */

//Actions
import apiActions from '../actions/apiActions';

//Services
import TellTaleService from '../services/TellTaleService';

//Constants
import actionTypeConstants from '../constants/actionTypeConstants';

//Libraries
import Toastr from 'toastr';

let actions = {

  list(entity, data){
    return {
      type: actionTypeConstants.LIST,
      entity: entity,
      data: data
    }
  },

  update(entity, data) {
    return {
      type: actionTypeConstants.UPDATE,
      entity: entity,
      data: data
    }
  },
}

let crudActions = {
  fetch(entity, pathParam){
    return (dispatch, getState)=> {
      let oldRoute = getState().routing.locationBeforeTransitions.pathname;
      dispatch(apiActions.apiRequest());
      return (TellTaleService.fetch(pathParam)
        .then((response)=> {
          dispatch(apiActions.apiResponse());
          dispatch(actions.list(entity, response));
        })
        .catch((error)=> {
          Toastr.error('Error has occurred, Please Try again');
        }))
    }
  },

  updateItem(entity, data, id) {
    return function (dispatch, getState) {
      var oldRoute = getState().routing.locationBeforeTransitions.pathname;
      dispatch(apiActions.apiRequest(entity));

      return (TellTaleService.edit(entity, data, id)
        .then((response)=> {
          dispatch(apiActions.apiResponse());
          dispatch(actions.update(entity, response.data));
          console.log(response.data);
          debugger;
          Toastr.success('Successfully Updated');
        })
        .catch((error) => {
          Toastr.success('Error has occurred, Please Try again');
        }));
    }
  }
}

export default crudActions;