<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu Fixtures</title>
    <link rel="stylesheet" href="ScoreboardStyle.css">
</head>
<body>

<header class="navbar">
    <h1>Jallikattu Fixtures</h1>
    <nav>
        <a href="Jallikattu.jsp">Home</a>
        <a href="AdminLogin.jsp">Admin Portal</a>
    </nav>
</header>

<div class="container">
    <aside class="filters">
        <h2>Filter Fixtures</h2>
        <label>Team:
            <select id="teamFilter">
                <option value="all">All Teams</option>
                <option value="team1">Team 1</option>
                <option value="team2">Team 2</option>
            </select>
        </label>
        <label>Format:
            <select id="formatFilter">
                <option value="all">All Formats</option>
                <option value="traditional">Traditional</option>
                <option value="modern">Modern</option>
            </select>
        </label>
        <label>Series Type:
            <select id="seriesFilter">
                <option value="all">All Series</option>
                <option value="national">National</option>
                <option value="regional">Regional</option>
            </select>
        </label>
    </aside>

    <main class="fixtures">
        <h2>Upcoming Matches</h2>

        <div id="fixturesList">
            <!-- Matches will be loaded dynamically from JS -->
        </div>
    </main>
</div>

<script src="ScoreboardScript.js"></script>
</body>
</html>
