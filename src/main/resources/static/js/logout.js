function logout(){
    localStorage.removeItem("token");
    window.location.pathname = "/";
}