// Get elements
const loginForm = document.getElementById("loginForm");
const registerForm = document.getElementById("registerForm");
const registerSection = document.getElementById("registerSection");
const showRegister = document.getElementById("showRegister");
const showLogin = document.getElementById("showLogin");

// Toggle to Register Form
showRegister.addEventListener("click", function () {
    document.querySelector(".admin-auth").classList.add("hidden");
    registerSection.classList.remove("hidden");
});

// Toggle to Login Form
showLogin.addEventListener("click", function () {
    registerSection.classList.add("hidden");
    document.querySelector(".admin-auth").classList.remove("hidden");
});

// Handle Login
loginForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;

    alert(`Logged in as ${email}`);
});

// Handle Registration
registerForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const name = document.getElementById("registerName").value;
    const email = document.getElementById("registerEmail").value;
    const password = document.getElementById("registerPassword").value;

    alert(`Registered as ${name}`);
});
