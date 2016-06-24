import React from 'react';
import {Router, Route, browserHistory, IndexRoute} from 'react-router';


import App from './components/App';

import {syncHistoryWithStore} from 'react-router-redux';
import store from './store/store';

let history = syncHistoryWithStore(browserHistory, store);

let routes = (

  <Router history={history}>
    <Route path="/" component={App}>
      <IndexRoute component={App}/>
    </Route>
  </Router>
);

export default routes;