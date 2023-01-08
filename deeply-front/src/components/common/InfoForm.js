import React, {useEffect, useState} from 'react';
import {Card, Button, ListGroup} from "react-bootstrap";
import {useDispatch} from "react-redux";
import {findProfile, myProfile} from "../../actions/profile_actions";
import {displayFile} from "../../actions/file_actions";

const InfoForm = () => {

    const [profile, setProfile] = useState({})
    const [fileImage,setFileImage] = useState("");
    const [g, setG] = useState({})


    const dispatch = useDispatch()
    useEffect(() => {
        const g = dispatch(myProfile())
            .then(response => {
                if (response.payload){
                    setProfile(response.payload)
                    // setFileImage(URL.createObjectURL(response.payload.file.filePath));
                    dispatch(displayFile(response.payload.file.id))
                        .then(res => {
                            setFileImage(res.data)
                        })
                    console.log("profile:"+response.payload)
                    return
                }else{
                    console.log("Error")
                    return
                }
            })

    }, [g])



    return (
        <Card style={{width: '18rem'}}>
            <Card.Img variant="top" src="http://localhost:9000/imgs/seed.jpg"/>
            <Card.Img variant="top" src={fileImage}/>
            <Card.Title>{profile.username}</Card.Title>
            <Card.Body>
                <Card.Title>{profile.username}</Card.Title>
                <Card.Text>
                </Card.Text>
            </Card.Body>
            <ListGroup className="list-group-flush">
                <ListGroup.Item>{profile.childrenYn}</ListGroup.Item>
                <ListGroup.Item>{profile.email}</ListGroup.Item>
                <ListGroup.Item>{profile.gender}</ListGroup.Item>
                <ListGroup.Item>{profile.id}</ListGroup.Item>
                <ListGroup.Item>{profile.money}</ListGroup.Item>
                <ListGroup.Item>{profile.phoneNumber}</ListGroup.Item>
                <ListGroup.Item>{profile.role}</ListGroup.Item>
                <ListGroup.Item>{profile.phoneNumber}</ListGroup.Item>
            </ListGroup>
            <Card.Body>
                <Card.Link>Card Link</Card.Link>
                <Card.Link href="#">Another Link</Card.Link>
            </Card.Body>
        </Card>

    );
};

export default InfoForm;
