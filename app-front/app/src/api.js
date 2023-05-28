import axios from "axios"
import {API_BASE_URL} from "./config"

export const addElevator = (data) => {
    return axios.post(`${API_BASE_URL + '/add/empty'}`, data, {
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json;charset=UTF-8"
        }
    })        
    .then(response => response.data)
    .catch(error => {
        console.log(error)
    });
}

export const addUserRequest = (data) => {
    return axios.post(`${API_BASE_URL + '/add/user/request'}`, data, {
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json;charset=UTF-8"
        }
    })        
    .then(response => response.data)
    .catch(error => {
        console.log(error)
    });
}

export const runRound = (data) => {
    return axios.post(`${API_BASE_URL + '/run/round'}`, data, {
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json;charset=UTF-8"
        }
    })        
    .then(response => response.data)
    .catch(error => {
        console.log(error)
    });
}