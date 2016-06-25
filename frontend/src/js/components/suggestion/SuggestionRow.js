/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/25/16.
 */

//React dependencies
import React from 'react';

class SuggestionRow extends React.Component {
  render() {
    let isStarred = this.props.suggestion.Starred;
    let starStyle = isStarred ? { color : '#f0ad4e' } : { color : '#ccc'};
    return (
      <tr>
        <td>
          {this.props.index}
        </td>
        <td>
          <span style={starStyle} onClick={() => {
            this.props.starSuggestion(this.props.suggestion);
          }}>
            <i className="glyphicon glyphicon-star"></i>
          </span>
        </td>
        <td>
          <span>
            <p className="suggestion-text">{this.props.suggestion.Suggestion}</p>
          </span>
        </td>
        <td>
          <span className="label label-warning pull-left">{this.props.suggestion.Label}</span>
        </td>
        <td>
          <span className="date pull-right">{this.props.suggestion.createdAt}</span>
        </td>

      </tr>
    );
  }
}

export default SuggestionRow;