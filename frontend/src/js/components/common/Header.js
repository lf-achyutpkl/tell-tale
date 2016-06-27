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
        <a href="#">
          <img alt="app-logo" src="img/tell-tale-logo.png" width="70px" height="50px"/>
        </a>
        <button type="button" className="btn btn-default btn-sm pull-right"
                onClick={()=>{
                sessionStorage.tellTaleAuth = '';
                location.href = '/tell-tale';
                }}>
          <span className="glyphicon glyphicon-log-out"></span> Log out
        </button>
      </header>
    );
  }
}

export default Header;