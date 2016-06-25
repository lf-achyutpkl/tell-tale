/**
 * Created by romit on 6/25/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/25/16
 */

import React, {Component} from 'react';
import {Modal, Button, FormControl} from 'react-bootstrap';
import Select from 'react-select';
import Toastr from 'toastr';

class SuggestionSend extends Component {

  constructor(props) {
    super(props);

    this.isValidFields = this.isValidFields.bind(this);
    this.saveSuggestion = this.saveSuggestion.bind(this);
    this.removeFeedback = this.removeFeedback.bind(this);
    this.handleMessageFieldChange = this.handleMessageFieldChange.bind(this);

    this.state = {
      errorFields: {receiver: false, label: false, suggestion: false}
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

  mapDivisionsToLabelAndValue(divisions = [{division: {title: 'Android', id: 'b'}}]) {
    let selectedDivisions = [];
    for (let divisionObj of divisions) {
      selectedDivisions.push({label: divisionObj.division.title, value: divisionObj.division.id})
    }
    return selectedDivisions;
  }

  getSelectOptions(input) {
    return {options: [{value: '123', label: 'java'}]}
  }

  validateFields() {
    let requiredFields = {
      receiver: this.refs.receiverEmail.props.value.value,
      label: this.refs.label.props.value.value,
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
      //save suggestion and if success ->
      this.props.onHide();
    } else {
      Toastr.error('Enter data in all fields');
    }
  }

  handleChange(event) {
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
                value={{value:'123',label:'Android'}}
                loadOptions={this.getSelectOptions}
                placeholder="ReceiverEmail"
                multi={false}
              />
              <span className="help-block"></span>
            </div>
          </div>
          <div className="row clearfix">
            <div className="col-md-2">
              <span className="">Label</span>
            </div>
            <div className="col-md-10 form-group">
              <Select.Async
                name="label"
                ref="label"
                value={{value:'123',label:'Android'}}
                loadOptions={this.getSelectOptions}
                placeholder="Label"
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
