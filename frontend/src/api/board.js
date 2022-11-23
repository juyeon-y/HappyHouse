import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.post(`/board`, null, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board/write`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(param, success, fail) {
  api.get(`/board/view`, { params: param }).then(success).catch(fail);
}

function getComment(param, success, fail) {
  api.get(`/comment/list`, { params: param }).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.post(`/board/edit`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(code, success, fail) {
  api.post(`/board/${code}`).then(success).catch(fail);
}

export {
  listArticle,
  writeArticle,
  getArticle,
  getComment,
  modifyArticle,
  deleteArticle,
};
