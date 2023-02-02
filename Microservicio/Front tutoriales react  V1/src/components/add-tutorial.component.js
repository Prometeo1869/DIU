import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class AddTutorial extends Component {
    constructor(props) {
        super(props);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.updatePublished = this.updatePublished.bind(this);
        this.addTutorial = this.addTutorial.bind(this);

        this.state = {
            currentTutorial: {
                id: null,
                title: "",
                description: "",
                published: false
            },
            message: ""
        };
    }

    //   componentDidMount() {
    //     this.getTutorial(this.props.match.params.id);
    //   }

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

    publishedChange(status) {
        var data = {
            title: this.state.currentTutorial.title,
            description: this.state.currentTutorial.description,
            published: status
        };

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
        const { currentTutorial } = this.state;

        return (
            <div>
                {currentTutorial ? (
                    <div className="edit-form">
                        <h4>Tutorial</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Title</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="title"
                                    value={currentTutorial.title}
                                    onChange={this.onChangeTitle}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Description</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="description"
                                    value={currentTutorial.description}
                                    onChange={this.onChangeDescription}
                                />
                            </div>

                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" onChange={publishedChange}/>
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Published
                                    </label>
                            </div>
                        </form>

                        {currentTutorial.published ? (
                            <button
                                className="badge badge-primary mr-2"
                                onClick={() => this.updatePublished(false)}
                            >
                                UnPublish
                            </button>
                        ) : (
                            <button
                                className="badge badge-primary mr-2"
                                onClick={() => this.updatePublished(true)}
                            >
                                Publish
                            </button>
                        )}

                        <button
                            type="submit"
                            className="badge badge-success"
                            onClick={this.create}
                        >
                            Add
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on a Tutorial...</p>
                    </div>
                )}
            </div>
        );
    }
}
