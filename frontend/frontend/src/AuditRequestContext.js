import React from "react";

export const AuditRequestContext = React.createContext();

export const AuditRequestProvider = (props) => {
  const [show, setShow] = React.useState(false);

  const [checkList, setCheckList] = React.useState({
    data: null,
  });

  const [projectDetails, setProjectDetails] = React.useState({
    projectName: "",
    managerName: "",
    date: "",
  });

  const [auditDetails, setAuditDetails] = React.useState({
    type: "Internal",
    
  });

  
  const [auditResponse, setAuditResponse] = React.useState({
    data: null,
  });

  const stateValue = {
    show,
    setShow,
    checkList,
    setCheckList,
    auditDetails,
    setAuditDetails,
    projectDetails,
    setProjectDetails,
    auditResponse,
    setAuditResponse,
  };
  return (
    <AuditRequestContext.Provider value={stateValue}>
      {props.children}
    </AuditRequestContext.Provider>
  );
};
