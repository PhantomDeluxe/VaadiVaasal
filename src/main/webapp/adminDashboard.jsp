<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu Scoreboard</title>
    <link rel="stylesheet" href="adminDashstyle.css">
</head>
<body>
<div class="container">
    <header>
        <h1>JALLIKATTU SCOREBOARD</h1>
    </header>

    <!-- Admin Enters Region -->
    <div class="region-input">
        <label for="region">Enter Region:</label>
        <input type="text" id="region" placeholder="Type region name">
        <button onclick="fetchPlayers()">Load Players</button>
    </div>

    <div class="controls">
        <button onclick="sortTable()">Sort</button>
        <button onclick="resetScores()">Reset</button>
    </div>

    <table id="scoreboard">
        <thead>
        <tr>
            <th>Rank</th>
            <th>Player Name</th>
            <th>Bulls Tamed</th>
            <th>Time Taken (mins)</th>
            <th>Fouls</th>
            <th>Total Score</th>
        </tr>
        </thead>
        <tbody id="scoreboard-body">
        <!-- Players Data Will Be Injected Here -->
        </tbody>
    </table>

    <p id="error-message" style="color: red; display: none;"></p>
</div>

<script src="adminDashscript.js"></script>
</body>
</html>
