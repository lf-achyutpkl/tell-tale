/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/24/16.
 */

import React from 'react';

import SuggestionRow from './SuggestionRow';

let suggestions = [
  {
    Id: 'uuid',
    Suggestion: 'Bootstrap requires a containing element to wrap site contents and house our grid system. You may choose one of two containers to use in your projects. Note that, due to padding and more, neither container is nestable.',
    Label: 'text',
    Recipient: 'uuid',
    Seen: false,
    Starred: false,
    createdAt: ' 06/13/2016'
  },
  {
    Id: 'uuid',
    Suggestion: 'Bootstrap requires a containing element to wrap site contents and house our grid system. You may choose one of two containers to use in your projects. Note that, due to padding and more, neither container is nestable.',
    Label: 'text',
    Recipient: 'uuid',
    Seen: false,
    Starred: false,
    createdAt: '06/13/2016'
  },
  {
    Id: 'uuid',
    Suggestion: 'Bootstrap requires a containing element to wrap site contents and house our grid system. You may choose one of two containers to use in your projects. Note that, due to padding and more, neither container is nestable.',
    Label: 'text',
    Recipient: 'uuid',
    Seen: false,
    Starred: false,
    createdAt: '06/13/2016'
  }
];


class SuggestionList extends React.Component {

  renderSuggestion(key) {
    let index = parseInt(key) + 1;
    return(
      <SuggestionRow index={index} key={key} suggestion={suggestions[key]} />
    )
  }

  render() {
    return (
      <div>
        <div>
          <h2>Suggestions</h2>
        </div>
        <div className="table-container">
          <table className="table table-suggestions">
            <tbody>
            {Object.keys(suggestions).map(this.renderSuggestion)}
            </tbody>
          </table>
        </div>
      </div>
    )
  }
}

export default SuggestionList;