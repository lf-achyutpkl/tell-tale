import {createStore, applyMiddleware, combineReducers, compose} from 'redux';
import thunk from 'redux-thunk';
import {routerReducer} from 'react-router-redux';

//Reducers

let reducers = combineReducers({
  routing: routerReducer
});

let store = createStore(reducers, compose(
  applyMiddleware(thunk),

  //For working redux dev tools in chrome (https://github.com/zalmoxisus/redux-devtools-extension)
  window.devToolsExtension ? window.devToolsExtension() : function (f) {
    return f;
  }
));

export default store;