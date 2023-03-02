import React, { Component } from "react";
import AgendaDataService from "../services/agenda.service"; //importa axios
import { Link } from "react-router-dom";
import { Alert } from "bootstrap";

export class AddContacto extends Component {

    constructor(props) {
        super(props);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangeStreet = this.onChangeStreet.bind(this);
        this.onChangePostalCode = this.onChangePostalCode.bind(this);
        this.onChangeCity = this.onChangeCity.bind(this);
        this.onChangeBirthday = this.onChangeBirthday.bind(this);
        this.createPerson = this.createPerson.bind(this);

        this.state = {
            currentPerson: {
                firstName: "",
                lastName: "",
                street: "",
                postalCode: 0,
                city: "",
                birthday: ""
            },
            message: ""
        }
    }

    componentDidMount() { }

    onChangeFirstName(e) {
        const firstName = e.target.value;

        this.setState(function (prevState) {
            return {
                currentPerson: {
                    ...prevState.currentPerson,
                    firstName: firstName
                }
            };
        });
    }

    onChangeLastName(e) {
        const lastName = e.target.value;

        this.setState(function (prevState) {
            return {
                currentPerson: {
                    ...prevState.currentPerson,
                    lastName: lastName
                }
            };
        });
    }

    onChangeStreet(e) {
        const street = e.target.value;

        this.setState(function (prevState) {
            return {
                currentPerson: {
                    ...prevState.currentPerson,
                    street: street
                }
            };
        });
    }

    onChangePostalCode(e) {
        const postalCode = e.target.value;

        this.setState(function (prevState) {
            return {
                currentPerson: {
                    ...prevState.currentPerson,
                    postalCode: postalCode
                }
            };
        });
    }

    onChangeCity(e) {
        const city = e.target.value;

        this.setState(function (prevState) {
            return {
                currentPerson: {
                    ...prevState.currentPerson,
                    city: city
                }
            };
        });
    }

    onChangeBirthday(e) {
        const birthday = e.target.value;

        this.setState(function (prevState) {
            return {
                currentPerson: {
                    ...prevState.currentPerson,
                    birthday: birthday
                }
            };
        });
    }

    createPerson() {

        AgendaDataService.create(
            this.state.currentPerson
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "¡¡CONTACTO CREADO CON ÉXITO!!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }


    render() {
        const { currentPerson, message } = this.state;


        return (
            <div className="list row justify-content-center mt-5" >

                {/*-- Card --*/}
                <div className="card col-5">

                    {/*-- Card body --*/}
                    <div className="card-body">

                        {/*-- Formulario editar contacto --*/}
                        <form className="form-group">
                            <p className="h4 text-center py-4">Nuevo Contacto</p>

                            {/*-- Nombre input text --*/}
                            <div>
                                <div className="input-group">
                                    <span className="input-group-text">
                                        <strong>Nombre &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</strong>
                                    </span>
                                    <input type="text"
                                        className="form-control"
                                        value={currentPerson.firstName}
                                        onChange={this.onChangeFirstName}
                                        required="required" />
                                </div>
                            </div>
                            {/*-- Apellido input text --*/}
                            <div className="mt-4">
                                <div className="input-group">
                                    <span className="input-group-text">
                                        <strong>Apellido &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</strong>
                                    </span>
                                    <input type="text"
                                        className="form-control"
                                        value={currentPerson.lastName}
                                        onChange={this.onChangeLastName}
                                        required />
                                </div>
                            </div>
                            {/*-- Calle input text --*/}
                            <div className="mt-4">
                                <div className="input-group">
                                    <span className="input-group-text">
                                        <strong>Calle &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;</strong>
                                    </span>
                                    <input type="text"
                                        className="form-control"
                                        value={currentPerson.street}
                                        onChange={this.onChangeStreet}
                                        required />
                                </div>
                            </div>
                            {/*-- Código Postal input text --*/}
                            <div className="mt-4">
                                <div className="input-group">
                                    <span className="input-group-text"><strong>Código Postal &nbsp; &nbsp;&nbsp;</strong></span>
                                    <input type="number"
                                        className="form-control"
                                        value={currentPerson.postalCode}
                                        onChange={this.onChangePostalCode}
                                        required />
                                </div>
                            </div>
                            {/*-- Ciudad input text --*/}
                            <div className="mt-4">
                                <div className="input-group">
                                    <span className="input-group-text">
                                        <strong>Ciudad &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</strong>
                                    </span>
                                    <input type="text"
                                        className="form-control"
                                        value={currentPerson.city}
                                        onChange={this.onChangeCity}
                                        required />
                                </div>
                            </div>
                            {/*-- Fecha Nacimiento input text --*/}
                            <div className="mt-4">
                                <div className="input-group">
                                    <span className="input-group-text">
                                        <strong>Fecha nacimiento</strong>
                                    </span>
                                    <input type="date"
                                        className="form-control"
                                        value={currentPerson.birthday}
                                        onChange={this.onChangeBirthday}
                                        required />
                                </div>
                            </div>


                            <div className="text-center py-4 mt-3">
                                <button className="btn btn-success mx-5" type="button" onClick={this.createPerson} to={'/'}>Guardar</button>
                                <Link className="btn btn-danger mx-5" type="button" to={'/persons'}>Cancelar</Link>
                            </div>

                            {/*-- Material form register --*/}
                        </form>

                        {/*-- Card body --*/}
                    </div>
                    <div className={"" + (message ? "bg-warning" : "") + " text-dark row rounded"} role="alertdialog">
                        <p className="text-center align-middle"><strong>{message}</strong></p>
                    </div>
                </div>
                {/*-- Fin Card --*/}
            </div>
        );
    }


}