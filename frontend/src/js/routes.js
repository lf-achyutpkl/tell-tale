import React from 'react';
import {Router, Route, browserHistory, IndexRoute} from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import store from './store/store';

let history = syncHistoryWithStore(browserHistory, store);

//Component
import App from './components/App';
import Dashboard from './components/dashboard/Dashboard';
import MessageMain from './components/message/MessageMain';
import MessageList from './components/message/MessageList';

let routes = (

  <Router history={history}>
    <Route path="/" component={App}>
      <IndexRoute component={Dashboard}/>
      <Route path="messages" name="Messages" component={MessageMain}>
        <IndexRoute component={MessageList}/>
      </Route>
    </Route>
  </Router>
);

export default routes;