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

// Handle Login
if (loginForm) {
    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const email = document.getElementById("loginEmail").value;
        const password = document.getElementById("loginPassword").value;

        if (!email || !password) {
            alert("❌ Please enter both email and password.");
            return;
        }

        // Send login data to servlet
        let formData = new FormData();
        formData.append("email", email);
        formData.append("password", password);

        fetch("AdminLoginServlet", { // Use the correct servlet name
            method: "POST",
            body: formData
        })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "Success") {
                    alert("✅ Login successful!");
                    window.location.href = "dashboard.html"; // Redirect after login
                } else {
                    console.error("❌ Server Response:", data);
                    alert("Error: " + data);
                }
            })
            .catch(error => console.error("❌ Error logging in:", error));
    });
}

// Handle Registration
if (registerForm) {
    registerForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const name = document.getElementById("registerName").value;
        const email = document.getElementById("registerEmail").value;
        const password = document.getElementById("registerPassword").value;

        if (!name || !email || !password) {
            alert("❌ All fields are required.");
            return;
        }

        // Send data to the servlet
        let formData = new FormData();
        formData.append("name", name);
        formData.append("email", email);
        formData.append("password", password);

        fetch("AdminDatabase", { // Correct servlet name
            method: "POST",
            body: formData
        })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "Success") {
                    alert("✅ Registration successful!");
                    registerForm.reset(); // Clear form after submission
                    window.location.href = "login.html"; // Redirect to login page
                } else {
                    console.error("❌ Server Response:", data);
                    alert("Error: " + data);
                }
            })
            .catch(error => console.error("❌ Error registering admin:", error));
    });
}
