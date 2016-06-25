/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

//React dependencies
import React from 'react';

//libraries
import DocumentTitle from 'react-document-title';

class PageNotFoundError extends React.Component {

  render() {
    let requestedPage = window.location.href;
    return (
      <DocumentTitle title='Not Found'>
        <div className="error">
          <div className="error-code m-b-10 m-t-20">404 <i className="fa fa-warning"></i></div>
          <h3 className="font-bold">We couldn't find the page..</h3>

          <div className="error-desc">
            Sorry, but the page {requestedPage} was either not found or does not exist. <br/>
          </div>
        </div>
      </DocumentTitle>
    );
  }
}

export default PageNotFoundError;
