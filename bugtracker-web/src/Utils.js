function isLogin() {
    return localStorage.getItem("authToken") != null;
}

export const utils = {
    isLogin
}