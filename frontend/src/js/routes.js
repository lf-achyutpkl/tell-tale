import React from 'react';
import {Router, Route, browserHistory, IndexRoute} from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import store from './store/store';

//Component
import App from './components/App';
import LoginPage from './components/loginRegister/LoginRegisterPage';
import SuggestionMain from './components/suggestion/SuggestionMain';
import SuggestionList from './components/suggestion/SuggestionList';

import SuggestionSend from './components/suggestion/SuggestionSend';

const checkAuthentication = (nextState, transition) => {
  if (!sessionStorage.tellTaleAuth) {
    transition('/tell-tale');
  }
};

const checkSession = (nextState, transition) => {
  if (sessionStorage.tellTaleAuth) {
    transition('suggestions');
  }
};

const history = syncHistoryWithStore(browserHistory, store);
let routes = (
  <Router history={history}>
    <Route path="/" component={App} onEnter={checkAuthentication}>
      <Route path="suggestions" name="Suggestions" component={SuggestionMain}>
        <IndexRoute component={SuggestionList}/>
        <Route path="new" name="Send Suggestions" component={SuggestionSend}/>
      </Route>
    </Route>
    <Route path="/tell-tale" name="Login Page" component={LoginPage} onEnter={checkSession}/>
  </Router>
);

export default routes;