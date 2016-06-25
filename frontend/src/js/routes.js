import React from 'react';
import {Router, Route, browserHistory, IndexRoute} from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import store from './store/store';

//Component
import App from './components/App';
import LoginPage from './components/loginRegister/LoginRegisterPage';
import SuggestionMain from './components/suggestion/SuggestionMain';
import SuggestionList from './components/suggestion/SuggestionList';
import PageNotFound from './components/common/PageNotFound';

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
      <IndexRoute component={SuggestionList}/>
      <Route path="suggestions" name="suggestions" component={SuggestionMain}>
        <IndexRoute component={SuggestionList}/>
      </Route>
    </Route>
    <Route path="/tell-tale" name="Login Page" component={LoginPage} onEnter={checkSession}/>
    <Route path="*" name="Page Not Found" component={PageNotFound}/>
  </Router>
);

export default routes;