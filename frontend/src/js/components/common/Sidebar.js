/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/24/16.
 */

import React from 'react';
import {Link} from 'react-router';

class Sidebar extends React.Component {

  render() {
    return (
      <div className="col-md-3 sidebar">
        <ul>
          <li><Link to="/suggestions">All Messages</Link></li>
          <li><a href="#">Starred Messages</a></li>
        </ul>
      </div>
    )
  }
}

export default Sidebar;