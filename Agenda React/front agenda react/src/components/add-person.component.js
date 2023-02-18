import React, { Component } from "react";
import AgendaDataService from "../services/agenda.service";

export default class AddPerson extends Component {
    
    constructor(props) {
        super(props);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.publishedChange = this.publishedChange.bind(this);
        this.addPerson = this.addPerson.bind(this);

        this.state = {
            currentPerson: {
                id: null,
		"firstName": "",
		"lastName": "",
		"street": "",
		"postalCode": 0,
		"city": "",
		"birthday": null
            },
            message: ""
        };
    }

    onChangeTitle(e) {
        const title = e.target.value;

        this.setState(function (prevState) {
            return {
                currentTutorial: {
                    ...prevState.currentTutorial,
                    title: title
                }
            };
        });
    }

    onChangeDescription(e) {
        const description = e.target.value;

        this.setState(prevState => ({
            currentTutorial: {
                ...prevState.currentTutorial,
                description: description
            }
        }));
    }

    publishedChange(e) {
            const published = e.target.checked;

            this.setState(function (prevState) {
                return {
                    currentTutorial: {
                        ...prevState.currentTutorial,
                        published: published
                    }
                };
            });
    }

    addTutorial() {
        TutorialDataService.create(
            this.state.currentTutorial
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The tutorial was created successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        return (
            <div>
                    <div className="edit-form">
                        <h4>Tutorial</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Title</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="title"
                                    value={this.state.title}
                                    onChange={this.onChangeTitle}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Description</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={this.state.description}
                                    onChange={this.onChangeDescription}
                                />
                            </div>

                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" checked={this.state.published} onChange={this.publishedChange}/>
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Published
                                    </label>
                            </div>
                        </form><br></br>

                        <button
                            type="submit"
                            className="badge badge-success"
                            onClick={this.addTutorial}
                        >
                            Add
                        </button>
                        <p>{this.state.message}</p>
                    </div>

            </div>
        );
    }
}
