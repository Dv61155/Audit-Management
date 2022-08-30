import Axios from "axios";

export const API = Axios.create({
  baseURL: "http://localhost:3340/api/",
});

export const APIRoutes = {
  AuthorizeModule:
    "http://localhost:9100/auth",
  AuditCheckList:
    "http://localhost:9300/checklist",
  AuditSeverity:
    "http://localhost:9400/severity",
};
