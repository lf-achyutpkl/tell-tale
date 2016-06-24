/**
 * Created by romit on 6/24/16.
 * Romit Amgai <romitamgai@lftechnology.com>
 * 6/24/16
 */

import React, {Component} from 'react';
import {Modal, Button} from 'react-bootstrap';

class LandingPage extends Component {

  constructor() {
    super();
    this.renderLoginModal = this.renderLoginModal.bind(this);
    this.login = this.login.bind(this);
    this.handleLoginChange = this.handleLoginChange.bind(this);
    this.handleRegisterChange = this.handleRegisterChange.bind(this);
    this.isValidFields = this.isValidFields.bind(this);
    this.removeFeedback = this.removeFeedback.bind(this);
    this.renderRegisterModal = this.renderRegisterModal.bind(this);
    this.clearState = this.clearState.bind(this);

    this.state = {
      showLogin: false,
      showRegister: false,
      loginFields: {userName: '', password: ''},
      registerFields: {userName: '', password: '', fullName: ''},
      errorFields: {userName: false, password: false, fullName: false}
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

  renderLoginModal(event) {
    event.preventDefault();
    this.setState({showLogin: true});
  }

  renderRegisterModal(event) {
    event.preventDefault();
    this.setState({showRegister: true});
  }

  handleLoginChange(event) {
    let key = event.target.name;
    let value = event.target.value;
    this.state.loginFields[key] = value;
    this.setState({loginFields: this.state.loginFields});
  }

  handleRegisterChange(event) {
    let key = event.target.name;
    let value = event.target.value;
    this.state.registerFields[key] = value;
    this.setState({registerFields: this.state.registerFields});
  }

  login(event) {
    event.preventDefault();

    if (this.isValidFields(this.state.loginFields)) {
      //call the api and set the session storage
      console.log('successfully logged in');
      sessionStorage.telltaleAuth = 'telltaleauth';
      location.href = '/';
    }
  }

  register(event) {
    event.preventDefault();

    if (this.isValidFields(this.state.registerFields)) {
      //call the api to add user
      console.log('successfully registered');
    }
  }

  clearState() {
    for (let key in this.state.loginFields) {
      this.state.loginFields[key] = '';
    }
    for (let key in this.state.errorFields) {
      this.state.loginFields[key] = '';
    }
    for (let key in this.state.registerFields) {
      this.state.loginFields[key] = '';
    }

    this.setState({
      showLogin: false,
      showRegister: false,
      errorFields: this.state.errorFields,
      formFields: this.state.formFields
    });
  }

  render() {
    return (
      <div className="user">
        <a className="btn btn-default btn-lg" onClick={this.renderLoginModal}>Log in</a>
        <a className="btn btn-default btn-lg" onClick={this.renderRegisterModal}>Register</a>
        <div className="modal-container">
          <div className="jumbotron text-center">
            <img src="img/tell-tale-logo.png" width="100px" height="100px"/>
            <h1>Tell Tale</h1>
            <p>Anytime, Anywhere, Anonymously</p>
          </div>
          <Modal
            show={this.state.showLogin}
            onHide={this.clearState}
            container={this}
            aria-labelledby="login-modal-title"
          >
            <Modal.Header closeButton>
              <Modal.Title id="login-modal-title">Log In</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <div className={this.state.errorFields.userName?"form-group has-error has-feedback":"form-group"}>
                <label>Email:</label>
                <input type="text" className="form-control" name="userName" value={this.state.loginFields.userName}
                       onChange={this.handleLoginChange} onBlur={()=>this.isValidFields(this.state.loginFields)}
                       onFocus={this.removeFeedback}/>
              </div>
              <div className={this.state.errorFields.password?"form-group has-error has-feedback":"form-group"}>
                <label>Password:</label>
                <input type="password" className="form-control" name="password" value={this.state.loginFields.password}
                       onChange={this.handleLoginChange} onBlur={()=>this.isValidFields(this.state.loginFields)}
                       onFocus={this.removeFeedback}/>
              </div>
            </Modal.Body>
            <Modal.Footer>
              <Button onClick={this.login}>Login</Button>
              <Button onClick={this.clearState}>Close</Button>
            </Modal.Footer>
          </Modal>
          <Modal
            show={this.state.showRegister}
            onHide={this.clearState}
            container={this}
            aria-labelledby="register-modal-title"
          >
            <Modal.Header closeButton>
              <Modal.Title id="register-modal-title">Register</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <div className={this.state.errorFields.fullName?"form-group has-error has-feedback":"form-group"}>
                <label>Full Name:</label>
                <input type="text" className="form-control" name="fullName" value={this.state.registerFields.fullName}
                       onChange={this.handleRegisterChange} onBlur={()=>this.isValidFields(this.state.registerFields)}
                       onFocus={this.removeFeedback}/>
              </div>
              <div className={this.state.errorFields.userName?"form-group has-error has-feedback":"form-group"}>
                <label>Email:</label>
                <input type="text" className="form-control" name="userName" value={this.state.registerFields.userName}
                       onChange={this.handleRegisterChange} onBlur={()=>this.isValidFields(this.state.registerFields)}
                       onFocus={this.removeFeedback}/>
              </div>
              <div className={this.state.errorFields.password?"form-group has-error has-feedback":"form-group"}>
                <label>Password:</label>
                <input type="password" className="form-control" name="password"
                       value={this.state.registerFields.password}
                       onChange={this.handleRegisterChange} onBlur={()=>this.isValidFields(this.state.registerFields)}
                       onFocus={this.removeFeedback}/>
              </div>
            </Modal.Body>
            <Modal.Footer>
              <Button onClick={this.register}>Register</Button>
              <Button onClick={this.clearState}>Close</Button>
            </Modal.Footer>
          </Modal>
        </div>
      </div>
    );
  }
}

export default LandingPage;