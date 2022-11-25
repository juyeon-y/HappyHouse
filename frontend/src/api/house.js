import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function yearList(params, success, fail) {
  api.get(`/map/year`, { params: params }).then(success).catch(fail);
}

function monthList(params, success, fail) {
  api.get(`/map/month`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function interest(params, success, fail) {
  api.post(`/interest`, null, { params: params }).then(success).catch(fail);
}

function interestList(params, success, fail) {
  api.post(`/interest/list`, null, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, yearList, monthList, houseList, interest, interestList };
