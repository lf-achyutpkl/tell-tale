/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

import React, {Component} from 'react';
import {Modal, Button, FormControl} from 'react-bootstrap';
import Select from 'react-select';
import Toastr from 'toastr';

import TellTaleService from '../../services/TellTaleService';

class SuggestionSend extends Component {

  constructor(props) {
    super(props);

    this.isValidFields = this.isValidFields.bind(this);
    this.saveSuggestion = this.saveSuggestion.bind(this);
    this.removeFeedback = this.removeFeedback.bind(this);
    this.handleMessageFieldChange = this.handleMessageFieldChange.bind(this);
    this.handleSelectChange = this.handleSelectChange.bind(this);
    this.state = {
      errorFields: {receiver: false, suggestion: false},
      receiver: {value: '', label: ''},
      suggestion: ''
    }
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

  mapUsersToLabelAndValue(users) {
    let selectedUsers = [];
    for (let userObj of users) {
      selectedUsers.push({label: userObj.name, value: userObj.id})
    }
    return selectedUsers;
  }

  getSelectOptions(input) {
    return (TellTaleService.fetch('users', 'users').then((response) => {
      let options = [];
      for (let responseData of response.data) {
        options.push({
          value: responseData.id,
          label: responseData.name
        });
      }
      return {options: options};
    }).catch((error) => {
      return {}
    }));
  }

  validateFields() {
    let requiredFields = {
      suggestion: this.refs.suggestion.value
    };
    for (let key in requiredFields) {
      if (requiredFields[key] == '' || requiredFields[key] == null) {
        this.state.errorFields[key] = true;
        this.setState({errorFields: this.state.errorFields});
        return false;
      }
    }
    return true;
  }

  saveSuggestion() {
    if (this.validateFields()) {
      console.log(this.state.receiver.value);
      console.log(this.refs.suggestion.value);
      //save suggestion and if success ->
      TellTaleService.create({
        recepient: {id: this.state.receiver.value},
        message: this.refs.suggestion.value
      }).then(response=> {
        Toastr.success('Successfully sent suggestion');
        this.props.onHide();
      })
        .catch(error=> {
          Toastr.error('Invalid Data! Try Again');
        });

    } else {
      Toastr.error('Enter data in all fields');
    }
  }

  handleSelectChange(value) {
    this.state.receiver['value'] = value.value;
    this.state.receiver['label'] = value.label;
    this.setState({receiver: this.state.receiver});
  }

  render() {
    return (
      <Modal {...this.props} bsSize="large" aria-labelledby="contained-modal-title-lg">
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-lg">Send Suggestion</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="row clearfix">
            <div className="col-md-2">
              <span className="">To</span>
            </div>
            <div className="col-md-10 form-group">
              <Select.Async
                name="receiverEmail"
                ref="receiverEmail"
                value={this.state.receiver}
                loadOptions={this.getSelectOptions}
                placeholder="ReceiverEmail"
                onChange={this.handleSelectChange}
                multi={false}
              />
              <span className="help-block"></span>
            </div>
          </div>
          <div className="row clearfix">
            <div className="col-md-12">
              <span className="">Suggestion</span>
            </div>
            <div className="col-md-12 form-group">
              <textarea rows="5" className="form-control" maxlength="500" ref="suggestion"
                        placeholder="Type your suggestion"></textarea>
              <span className="help-block pull-right">*500 Characters Only</span>
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.saveSuggestion}>Send</Button>
          <Button onClick={this.props.onHide}>Close</Button>
        </Modal.Footer>
      </Modal>
    );
  }
}
export default SuggestionSend
