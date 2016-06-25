/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/25/16.
 */

//React dependencies
import React from 'react';

class SuggestionRow extends React.Component {
  render() {
    return (
      <tr>
        <td>
          {this.props.index}
        </td>
        <td>
          <a href="" className="star">
            <i className="glyphicon glyphicon-star"></i>
          </a>
        </td>
        <td>
          <a href="">
            <p className="suggestion-text">{this.props.suggestion.Suggestion}</p>
          </a>
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