// Get elements
const loginForm = document.getElementById("loginForm");
const registerForm = document.getElementById("registerForm");
const registerSection = document.getElementById("registerSection");
const showRegister = document.getElementById("showRegister");
const showLogin = document.getElementById("showLogin");

// Ensure elements exist before adding event listeners
if (showRegister) {
    showRegister.addEventListener("click", function () {
        document.querySelector(".admin-auth").classList.add("hidden");
        registerSection.classList.remove("hidden");
    });
}

if (showLogin) {
    showLogin.addEventListener("click", function () {
        registerSection.classList.add("hidden");
        document.querySelector(".admin-auth").classList.remove("hidden");
    });
}

// ✅ Handle Login
if (loginForm) {
    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const email = document.getElementById("loginEmail").value.trim();
        const password = document.getElementById("loginPassword").value.trim();

        if (!email || !password) {
            alert("❌ Please enter both email and password.");
            return;
        }

        console.log("DEBUG: Sending login request with email:", email);

        let formData = new URLSearchParams();
        formData.append("email", email);
        formData.append("password", password);

        fetch("/VaadiVaasal_webapp_war_exploded/AdminLoginServlet", { // ✅ Ensure correct servlet mapping
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: formData.toString()
        })
            .then(response => response.text())
            .then(data => {
                console.log("DEBUG: Server Response ->", data);

                if (data.trim() === "redirect") {
                    alert("✅ Login successful! Redirecting...");
                    window.location.href = "/VaadiVaasal_webapp_war_exploded/adminDashboard.jsp"; // ✅ Redirect to dashboard
                } else {
                    alert("🚫 Login failed: " + data);
                }
            })
            .catch(error => {
                console.error("❌ Login request failed:", error);
                alert("⚠️ Server connection issue! Please try again.");
            });
    });
}

// ✅ Handle Registration
if (registerForm) {
    registerForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const name = document.getElementById("registerName").value.trim();
        const email = document.getElementById("registerEmail").value.trim();
        const password = document.getElementById("registerPassword").value.trim();

        if (!name || !email || !password) {
            alert("❌ All fields are required.");
            return;
        }

        console.log("DEBUG: Sending registration request with email:", email);

        let formData = new URLSearchParams();
        formData.append("name", name);
        formData.append("email", email);
        formData.append("password", password);

        fetch("/VaadiVaasal_webapp_war_exploded/AdminDatabase", { // ✅ Ensure correct servlet mapping
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: formData.toString()
        })
            .then(response => response.text())
            .then(data => {
                console.log("DEBUG: Server Response ->", data);

                if (data.trim() === "Success") {
                    alert("✅ Registration successful! Redirecting to login...");
                    registerForm.reset();
                    window.location.href = "/VaadiVaasal_webapp_war_exploded/AdminLogin.jsp"; // ✅ Redirect to login
                } else {
                    alert("🚫 Registration failed: " + data);
                }
            })
            .catch(error => {
                console.error("❌ Registration request failed:", error);
                alert("⚠️ Server connection issue! Please try again.");
            });
    });
}
