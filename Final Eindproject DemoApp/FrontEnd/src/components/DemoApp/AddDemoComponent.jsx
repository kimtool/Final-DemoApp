import React, {useState} from 'react'
import {useHistory} from "react-router-dom";
import '../../App.css'
import axios from 'axios';
import moment from 'moment';
import {API_URL} from "../../Constants"
import AuthenticationService from "./AuthenticationService";
import bsCustomFileInput from 'bs-custom-file-input';

function AddDemoComponent() {

    let history = useHistory();
    var maxChars = 255;
    let fileInput = "Choose Demo";
    var currentDate = moment()

function submitForm(contentType, data, setResponse, username) {    
    axios({
        url: `${API_URL}/users/${username}/demos`,
        method: 'POST',
        data: data,
        headers: {
        'Content-Type': contentType
        }
    }).then((response) => {
        setResponse(response.data);
        history.push("/demos")  
    }).catch((error) => {
        setResponse("error");
    })
}    

    const [username, setUsername] = useState("")
    const [trackname, setTrackname] = useState("");
    const [file, setFile] = useState("");
    const [description, setDescription] = useState("");
    const [errorNoMp3, setErrorNoMp3] = useState(false);
    const [errorNoTitle, setErrorNoTitle] = useState(false);
    const [charsLeft, setCharsLeft] = useState(maxChars)

    function uploadWithFormData(){
        const fi = file.type;
            if(fi === "audio/mp3"){
            setErrorNoMp3(false);
            console.log("het is " + fi);
            const formData = new FormData();
            formData.append("trackname", trackname);
            formData.append("file", file);
            formData.append("username", username);
            formData.append("date", currentDate);
            formData.append("description", description);
            if(trackname === ""){
                setErrorNoTitle(true);
            }else{
                submitForm("multipart/form-data", formData, (msg) => console.log(msg))  
            }             
        }if(fi !== "audio/mp3" && trackname === ""){
            setErrorNoMp3(true);
            setErrorNoTitle(true)
        }else if(fi !== "audio/mp3"){
            setErrorNoMp3(true);
            setErrorNoTitle(false)
        }
    }

    function inputChanged(e){
        setFile(e.target.files[0])
        bsCustomFileInput.init()
        setUsername(AuthenticationService.getLoggedInUsername())
        
    }    

    function handleChange(event) {
        var input = event.target.value;        
        setCharsLeft(maxChars-input.length);
        setDescription(event.target.value);      
    }

    return (
        <div>
            <h2 className="title">Upload Form</h2>
            {errorNoMp3 && <div className="alert alert-warning">You must select an .mp3 file</div>}
            {errorNoTitle && <div className="alert alert-warning">You must give your demo a Title</div>}
            <form className="block demo-block">
                <div className="fields">
                    <label>
                        <input className="form-control" type="text" value={trackname}
                            onChange={(e) => {setTrackname(e.target.value)}}
                            placeholder="Title"/>                    
                    </label>
                    <label className="custom-file">
                        <input type="file" className="custom-file-input" name="file" accept=".mp3" onChange={(e) => inputChanged(e)}/>
                        <label className="custom-file-label">{fileInput}</label>
                    </label>
                    <label>
                    <textarea className="form-control" value={description} placeholder="Description" maxLength="255" 
                    onChange={(e) => handleChange(e)} style={{height:"150px"}}></textarea>
                    <p className="right_align">{charsLeft}</p>
                    </label>
                    <input className="button" type="button" value="save" onClick={uploadWithFormData} />
                    {/*<input type="button" value="Upload as JSON" onClick={uploadWithJSON}/>*/}
                </div>
            </form>
        </div>
    );
}

export default AddDemoComponent;
