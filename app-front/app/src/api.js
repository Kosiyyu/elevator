import axios from "axios"
import {API_BASE_URL} from "./config"

export const getData = () => {
    return axios.get(`${API_BASE_URL}`)
        .then(response => response.data)
        .catch(error => {
            console.log(error)
        });
}

export const addElevator = (data) => {
    return axios.post(`${API_BASE_URL + '/add'}`, data, {
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