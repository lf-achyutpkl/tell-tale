import React from 'react';
import {Router, Route, browserHistory, IndexRoute} from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import store from './store/store';

const checkAuthentication = (nextState, transition) => {
  if (!sessionStorage.telltaleAuth) {
    transition('/tell-tale');
  }
};

const checkSession = (nextState, transition) => {
  if (sessionStorage.telltaleAuth) {
    transition('/');
  }
};

//Component
import App from './components/App';
import LandingPage from './components/loginRegister/LandingPage';
import SuggestionMain from './components/suggestion/SuggestionMain';
import SuggestionList from './components/suggestion/SuggestionList';

import SuggestionSend from './components/suggestion/SuggestionSend';

const history = syncHistoryWithStore(browserHistory, store);
let routes = (
  <Router history={history}>
    <Route path="/" component={App} onEnter={checkAuthentication}>
      <Route path="suggestions" name="Suggestions" component={SuggestionMain}>
        <IndexRoute component={SuggestionList}/>
        <Route path="new" name="Send Suggestions" component={SuggestionSend}/>
      </Route>
    </Route>
    <Route path="/tell-tale" name="Landing Page" component={LandingPage} onEnter={checkSession}/>
  </Router>
);

export default routes;