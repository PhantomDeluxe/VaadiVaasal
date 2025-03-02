<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu Fixtures</title>
    <link rel="stylesheet" href="ScoreboardStyle.css">
</head>
<body>
<header>
    <h1>Jallikattu Fixtures</h1>
    <nav>
        <a href="Jallikattu.jsp">Home</a>
        <a href="AdminLogin.jsp">Admin Portal</a>
    </nav>
    <button id="theme-toggle">🌙 Dark Mode</button>
</header>

<div id="matchContainer"></div>

<h2>Add New Match</h2>
<form id="addMatchForm">
    <input type="text" id="venue" placeholder="Venue" required>
    <input type="text" id="teams" placeholder="Teams" required>
    <select id="status">
        <option value="Upcoming">Upcoming</option>
        <option value="Live">Live</option>
        <option value="Completed">Completed</option>
    </select>
    <input type="date" id="date" required>
    <input type="url" id="youtubeLink" placeholder="YouTube Link" required>
    <button type="submit">Add Match</button>
</form>

<script src="ScoreboardScript.js"></script>
</body>
</html>
