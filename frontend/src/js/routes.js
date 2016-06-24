import React from 'react';
import {Router, Route, browserHistory, IndexRoute} from 'react-router';

//Components
import App from './components/App';
import LandingPage from './components/loginRegister/LandingPage';

import {syncHistoryWithStore} from 'react-router-redux';
import store from './store/store';

const checkAuthentication = (nextState, transition) =>{
  if(!sessionStorage.telltaleAuth){
    transition('/tell-tale');
  }
};

const checkSession = (nextState, transition) => {
  if(sessionStorage.telltaleAuth){
    transition('/');
  }
};

const history = syncHistoryWithStore(browserHistory, store);
let routes = (

  <Router history={history}>
    <Route path="/" component={App} onEnter={checkAuthentication}>
      <IndexRoute component={App}/>
    </Route>
    <Route path="/tell-tale" name="Landing Page" component={LandingPage} onEnter={checkSession}/>
  </Router>
);

export default routes;