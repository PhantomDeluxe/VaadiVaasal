<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login & Register</title>
    <link rel="stylesheet" href="AdminLogin_style.css">
</head>
<body>

<header class="navbar">
    <h1>Jallikattu Admin Panel</h1>
    <nav>
        <a href="Jallikattu.jsp">Home</a>
        <a href="Matches_and_Scoreboard.jsp">Fixtures</a>
    </nav>
</header>

<div class="container">
    <aside class="admin-auth">
        <h2>Admin Login</h2>
        <form id="loginForm" action="AdminLoginServlet" method="POST">
            <label>Email:</label>
            <input type="email" id="loginEmail" required>

            <label>Password:</label>
            <input type="password" id="loginPassword" required>

            <button type="submit" onclick= window.location.href="adminDashboard.jsp">Login</button>
        </form>

        <p>Don't have an account? <a href="#" id="showRegister">Register here</a></p>
    </aside>

    <aside class="admin-auth hidden" id="registerSection">
        <h2>Admin Register</h2>
        <form id="registerForm">
            <label>Full Name:</label>
            <input type="text" id="registerName" required>

            <label>Email:</label>
            <input type="email" id="registerEmail" required>

            <label>Password:</label>
            <input type="password" id="registerPassword" required>

            <button type="submit">Register</button>
        </form>

        <p>Already have an account? <a href="#" id="showLogin">Login here</a></p>
    </aside>
</div>

<script src="AdminLogin_Script.js"></script>
</body>
</html>

