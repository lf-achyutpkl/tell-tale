/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/24/16.
 */

import React from 'react';

import EntityHeader from '../common/EntityHeader';

class MessageList extends React.Component {
  
  render() {
    return (
      <div>
        <EntityHeader />

        <div className="list-group">
          <a href="#" className="list-group-item">
            <h4 className="list-group-item-heading">List group item heading</h4>
            <p className="list-group-item-text">While not always necessary, sometimes you need to put your DOM in a box.
              For those situations, try the panel component.</p>
          </a>
        </div>
      </div>
    )
  }
}

export default MessageList;