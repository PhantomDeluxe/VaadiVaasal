<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu Fixtures</title>
    <link rel="stylesheet" href="ScoreboardStyle.css">
    <script src="ScoreboardScript.js" defer></script>
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

<div class="container">
    <aside class="filter-panel">
        <h2>Filter Fixtures</h2>
        <label for="search">Search:</label>
        <input type="text" id="search" placeholder="Search matches...">

        <label for="team">Team:</label>
        <select id="team">
            <option value="">All Teams</option>
            <option value="Team A">Team A</option>
            <option value="Team B">Team B</option>
        </select>

        <label for="format">Format:</label>
        <select id="format">
            <option value="">All Formats</option>
            <option value="Traditional">Traditional</option>
            <option value="Modern">Modern</option>
        </select>

        <label for="series">Series Type:</label>
        <select id="series">
            <option value="">All Series</option>
            <option value="Championship">Championship</option>
            <option value="Regional">Regional</option>
        </select>
    </aside>

    <main class="match-schedule">
        <h2>Upcoming Matches</h2>
        <div id="matches"></div>
    </main>
</div>

<footer>
    <p>&copy; 2025 Jallikattu Fixtures. All rights reserved.</p>
</footer>
</body>
</html>
