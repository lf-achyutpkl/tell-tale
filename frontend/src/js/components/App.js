//React dependencies
import React, {Component} from 'react';

//component
import Sidebar from './common/Sidebar';
import Header from './common/Header';

export default class App extends Component {
  render() {
    return (
      <div id="page-container">
        <Header />

        <div className="container-fluid">
          <div className="row">
            <Sidebar />

            <div className="col-md-8">
              {this.props.children}
            </div>
          </div>
        </div>
      </div>
    );
  }
}