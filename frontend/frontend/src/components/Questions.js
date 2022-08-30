import React from "react";
import {Button, Form} from "react-bootstrap";
import {AuditRequestContext} from "../AuditRequestContext";

export const Questions = ({checkLists}) => {


    const {
        checkList,
        setCheckList
    } = React.useContext(AuditRequestContext);

   

    return (
        <>
            {checkList.data &&
                checkList.data.map((item) => (
                    <Form.Group className="mb-3" key={item.questionId}>
                        <Form.Label>- {item.question}</Form.Label>
                        <Form.Group >
                                <Button size="sm" variant={item.response==="No"?"light":"success"} active={item.response==="Yes"?true:false} onClick={(e)=>{
                                   
                                       item.response="Yes";
                                       setCheckList({...checkList});
                                       console.log(checkList)
                                      
                                }} >Yes</Button>
                                 <Button size="sm" variant={item.response==="Yes"?"light":"success"} active={item.response==="No"?true:false}  style={{marginLeft:"5px"}} onClick={(e)=>{
                                      
                                            item.response="No";
                                            setCheckList({...checkList});
                                            console.log(checkList)
                                 }} >No</Button>
                        </Form.Group>
                        {/* <Form.Check
                            label={`${item.question}`}
                            name={item.questionId}
                            onChange={(e) => {
                                item.response=e.target.checked?"Yes":"No";
                                setCheckList({...checkList});
                                console.log(checkList)
                            }}
                           //checked={item.response==="false"?false:true}
                        /> */}
                    </Form.Group>
                ))}
        </>
    );
};
