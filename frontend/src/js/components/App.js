import React, {Component} from 'react';

import Header from './common/Header'

export default class App extends Component {
  render() {
    return (
      <div>
        {this.props.children}
      </div>
    );
  }
}