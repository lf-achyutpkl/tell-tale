/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/24/16.
 */

import React from 'react';

class Sidebar extends React.Component {

  render() {
    return (
      <div className="col-md-3 sidebar">
        <ul>
          <li><a href="/messages">All Messages</a></li>
          <li><a href="#">Starred Messages</a></li>
          <li><a href="#">All Messages</a></li>
          <li><a href="#">Starred Messages</a></li>
          <li><a href="#">All Messages</a></li>
          <li><a href="#">Starred Messages</a></li>
        </ul>
      </div>
    )
  }
}

export default Sidebar;