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
import Dashboard from './components/dashboard/Dashboard';
import MessageMain from './components/message/MessageMain';
import MessageList from './components/message/MessageList';

const history = syncHistoryWithStore(browserHistory, store);
let routes = (
  <Router history={history}>
    <Route path="/" component={App} onEnter={checkAuthentication}>
      <Route path="messages" name="Messages" component={MessageMain}>
        <IndexRoute component={MessageList}/>
      </Route>
    </Route>
    <Route path="/tell-tale" name="Landing Page" component={LandingPage} onEnter={checkSession}/>
  </Router>
);

export default routes;