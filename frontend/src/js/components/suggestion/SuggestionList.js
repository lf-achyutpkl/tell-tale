/**
 * Created by kiran
 * Kiran Pariyar <kiranpariyar@lftechnology.com>
 * on 6/24/16.
 */

import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import SuggestionRow from './SuggestionRow';
import SuggestionSend from './SuggestionSend';

import crudActions from '../../actions/crudActions';
import apiActions from '../../actions/apiActions';


class SuggestionList extends React.Component {

  constructor(props) {
    super(props);
    this.renderSuggestion = this.renderSuggestion.bind(this);
    this.starSuggestion = this.starSuggestion.bind(this);
    this.closeModal = this.closeModal.bind(this);
    this.state = {
      show: false
    }
  }

  componentWillMount() {
    this.props.actions.fetch('suggestions', 'suggestions');
    this.props.actions.fetch('users', 'users');
  }

  starSuggestion(suggestion) {
    if (suggestion.starred) {
      suggestion.starred = false;
      this.props.actions.updateItem('suggestions', suggestion, suggestion.id);
    } else if (!suggestion.starred) {
      suggestion.starred = true;
      this.props.actions.updateItem('suggestions', suggestion, suggestion.id);
    }
  }

  closeModal(event) {
    this.setState({show: false})
  }

  renderSuggestion(key) {
    let index = parseInt(key) + 1;
    return (
      <SuggestionRow index={index}
                     key={key}
                     id={this.props.suggestions[key].id}
                     suggestion={this.props.suggestions[key]}
                     starSuggestion={this.starSuggestion}
      />
    )
  }

  render() {
    console.log(this.props.suggestions);
    return (
      <div>
        <a title="Send Suggestion" className="btn btn-sm btn-primary text-uppercase pull-right"
           onClick={()=>this.setState({show:true})}><i
          className="fa fa-plus"></i>Send Suggestion</a>
        <div>
          <h2>Suggestions</h2>
        </div>
        <div className="table-container">
          <table className="table table-suggestions">
            <tbody>
            {this.props.suggestions && this.props.suggestions.length > 0 && Object.keys(this.props.suggestions).map(this.renderSuggestion)}
            </tbody>
          </table>
        </div>
        <SuggestionSend show={this.state.show} users={this.props.users} onHide={this.closeModal}/>
      </div>
    )
  }
}

let mapStateToProps = function (state) {
  return {
    suggestions: state.crudReducer.suggestions,
    users: state.crudReducer.users,
    apiState: state.apiReducer
  }
};

let mapDispatchToProps = function (dispatch) {
  return {
    actions: bindActionCreators(_.assign({}, crudActions, apiActions), dispatch)
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(SuggestionList);

