/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import apiActions from '../../actions/apiActions';

class SuggestionSend extends Component {

  constructor(props) {
    super(props);

    this.isValidFields = this.isValidFields.bind(this);
    this.removeFeedback = this.removeFeedback.bind(this);
    this.handleMessageFieldChange = this.handleMessageFieldChange.bind(this);

    this.state = {
      messageFields: {receiver: '', label: '', messageBody: ''},
      errorFields: {receiver: false, label: false, messageBody: false}
    };
  }

  componentWillUnmount() {
    this.props.actions.apiClearState();
  }

  isValidFields(field) {
    let isValid = true;
    for (let key in field) {
      if (field[key] == '' || field[key] == null) {
        this.state.errorFields[key] = true;
        this.setState({errorFields: this.state.errorFields});
        isValid = false;
      }
    }
    return isValid;
  }

  removeFeedback(event) {
    this.state.errorFields[event.target.name] = false;
    this.setState({errorFields: this.state.errorFields});
  }

  handleMessageFieldChange(event) {
    let key = event.target.name;
    let value = event.target.value;
    this.state.messageFields[key] = value;
    this.setState({messageFields: this.state.messageFields});
  }

  render() {
    return (
      <div>
        <form className="form-horizontal" role="form" method="post" action="">
          <fieldset disabled={this.props.apiState.isRequesting}>
            <div className={this.state.errorFields.receiver?"form-group has-error has-feedback":"form-group"}>
              <label className="col-md-6">To:</label>
              <input type="text" className="form-control col-md-6" name="receiver" value={this.state.messageFields.receiver}
                     onChange={this.handleMessageFieldChange} onBlur={()=>this.isValidFields(this.state.messageFields)}
                     onFocus={this.removeFeedback}/>
            </div>
          </fieldset>
        </form>
      </div>
    )
  }
}

let mapStateToProps = (state)=> {
  return {
    apiState: state.apiReducer
  }
};

let mapDispatchToProps = (dispatch)=> {
  return {
    actions: bindActionCreators(_.assign({}, apiActions), dispatch)
  }
};


export default connect(mapStateToProps, mapDispatchToProps)(SuggestionSend);
