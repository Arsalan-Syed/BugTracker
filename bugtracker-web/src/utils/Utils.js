function isLogin() {
    return localStorage.getItem("authToken") != null;
}

function authHeader(){
    let token = localStorage.getItem("authToken");
    return "Bearer "+token;
}

export const utils = {
    isLogin,
    authHeader
}