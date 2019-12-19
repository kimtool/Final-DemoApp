import React, {Component} from 'react'
import {Container, Table } from 'reactstrap';
import UserDataService from "../api/UserDataService";

class MembersComponenten extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [], 
            isLoading: true
        }
    }

    componentDidMount() {
        this.refreshMembers()
    }

    refreshMembers = () => {
        UserDataService.retrieveAllUsers()
        .then(
            response => {
            this.setState({users : response.data, isLoading: false})
        })
    }

    deleteUserClicked = (id) => {
        UserDataService.deleteUser(id)
            .then(response =>{
                // this.setState({message:`Delete of user ${id} Succesful`});
                this.refreshMembers();
            }
        )
    }

    addUserClicked = () => {
        this.props.history.push("/adduser")
    } 

    render() {
        if (this.state.isLoading) {
            return <p className="title">Loading...</p>;
        }

        const userList = this.state.users.map(user => {
            return <tr key={user.id}>
                <td style={{whiteSpace: 'nowrap'}}>{user.id}</td>
                <td>{user.email}</td>
                <td>{user.username}</td>
                <td>{user.role}</td>
                <td>
                    <button className="button button_small" onClick={() => this.deleteUserClicked(user.id)}>Delete</button>
                </td>
            </tr>
        });

        return (
            <div>
                <Container fluid>
                    <h3 className="title">List of members</h3>
                    <div className="float-left">
                        <button className="button" style={{margin:"11px"}} onClick={() => this.addUserClicked()}>+Member</button>
                    </div>
                    
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>E-mail</th>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {userList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default MembersComponenten