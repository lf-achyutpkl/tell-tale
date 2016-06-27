/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/25/16.
 */

//React dependencies
import React from 'react';
import {Modal} from 'react-bootstrap';

class SuggestionRow extends React.Component {

  constructor(props) {
    super(props);
    this.displayFullSuggestion = this.displayFullSuggestion.bind(this);
    this.close = this.close.bind(this);
    this.state = {
      isShowModal: false,
      suggestion: {suggestion: '', createdAt: ''}
    }
  }

  displayFullSuggestion(suggestion) {
    console.log('suggestion', suggestion);
    this.setState({isShowModal: true});
    this.setState({suggestion: suggestion});
  }

  close() {
    this.setState({isShowModal: false});
  }

  render() {
    let isStarred = this.props.suggestion.starred;
    let fullMessage = this.props.suggestion.message;
    let shortMessage = fullMessage.substr(0, 100);
    let starStyle = isStarred ? {color: '#f0ad4e'} : {color: '#ccc'};
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
          <span style={starStyle} onClick={() => {
            this.displayFullSuggestion(this.props.suggestion);
          }}>
            <p className="suggestion-text">{shortMessage}</p>
          </span>
        </td>
        <td>
          <span className="date pull-right">{this.props.suggestion.createdAt}</span>
        </td>

        <td>
          <Modal show={this.state.isShowModal} onHide={this.close}>
            <Modal.Header closeButton>
              <Modal.Title>Suggestion</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <p>{this.state.suggestion.message}</p>
              <span>Created At {this.state.suggestion.createdAt}</span>
            </Modal.Body>
          </Modal>
        </td>
      </tr>
    );
  }
}

export default SuggestionRow;