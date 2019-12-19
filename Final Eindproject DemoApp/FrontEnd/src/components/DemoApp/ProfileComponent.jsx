import React, {Component} from 'react'
import UserDataService from '../api/UserDataService.js'
import AuthenticationService, {USER_NAME_SESSION_ATTRIBUTE_NAME} from "./AuthenticationService";

class ProfileComponent extends Component {

    constructor(props){             //gets called when component is being initialized
        super(props)
        this.state = {
            user:[]
        }
    }

    componentDidMount() { 
        this.refreshDemos();
    }    

    refreshDemos = () => {
        console.log(AuthenticationService.isUserAdmin())
        const username = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        UserDataService.retrieveUserByUsername(username)
        .then(
            response => {
            this.setState({user : response.data})
        })
    }

    render(){
        return (
            <div className="App">
                    <h1 className="title">Your profile</h1>
                    <div className="box">                        
                        <div style={{display:"inline-block"}}>
                        <ul>
                            <li className="user_li">Username</li>
                            <li className="user_li">E-mail</li>
                            <li className="user_li">Role</li>                       
                        </ul>
                        </div>
                        <div style={{display:"inline-block"}}>
                        <ul>
                            <li className="user_li">{this.state.user.username}</li>
                            <li className="user_li">{this.state.user.email}</li>
                            <li className="user_li">{this.state.user.role}</li>                       
                        </ul>
                        </div>
                    </div>
                </div>
        );
    }
}

export default ProfileComponent