/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/24/16.
 */

import React from 'react';

class Header extends React.Component {
  render() {
    return (
      <header id="header" className="navbar navbar-default navbar-fixed-top">
        <a class="navbar-brand" href="#">
          <img alt="app-logo" src="img/tell-tale-logo.png" width="70px" height="50px" />
        </a>
      </header>
    );
  }
}

export default Header;