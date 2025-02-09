<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu Player Rankings</title>
    <link rel="stylesheet" href="PlayerCardStyles.css"> <!-- External CSS -->
</head>
<body>
<div class="ranking-container">
    <div class="ranking-header">
        <h1>Jallikattu Player Rankings</h1>
        <button id="addPlayerBtn">Add New Player</button>
    </div>

    <div class="player-grid" id="playerGrid">
        <!-- Player cards will be dynamically inserted here -->
    </div>

    <!-- Add Player Form -->
    <div class="form-container" id="playerFormContainer">
        <form id="addPlayerForm">
            <%--@declare id="playernationalityinput"--%><label for="playerNameInput">Name:</label>
            <input type="text" id="playerNameInput" name="name" required>

            <label for="playerImageInput">Image URL:</label>
            <input type="text" id="playerImageInput" required>

            <label for="playerScoreInput">Score:</label>
            <input type="number" id="playerScoreInput" required>

            <label for="playerBullsTamedInput">Bulls Tamed:</label>
            <input type="number" id="playerBullsTamedInput" required>

            <label for="playerAgeInput">Age:</label>
            <input type="number" id="playerAgeInput" required>

            <label for="playerRegionInput">Region:</label>
            <input type="text" id="playerRegionInput" required>

            <label for="playerNationalityInput">Nationality:</label>
            <input type="text" id="playerNationalityInput" required>

            <label for="playerMatchesInput">Matches Played:</label>
            <input type="number" id="playerMatchesInput" required>

            <button type="submit">Add Player</button>
        </form>
    </div>
    <!-- Edit Player Form -->
    <div class="player-grid" id="playerGrid">
        <!-- Player cards will be dynamically added here -->
    </div>

    <!-- Edit Player Form -->
    <div id="editFormContainer" style="display: none;">
        <form id="editPlayerForm">
            <input type="hidden" id="editPlayerId">
            <label>Name:</label> <input type="text" id="editPlayerName">
            <label>Image URL:</label> <input type="text" id="editPlayerImage">
            <label>Score:</label> <input type="number" id="editPlayerScore">
            <label>Bulls Tamed:</label> <input type="number" id="editPlayerBullsTamed">
            <label>Age:</label> <input type="number" id="editPlayerAge">
            <label>Region:</label> <input type="text" id="editPlayerRegion">
            <label>Nationality:</label> <input type="text" id="editPlayerNationality">
            <label>Matches Played:</label> <input type="number" id="editPlayerMatches">

            <button type="button" onclick="submitEditPlayer()">Save Changes</button>
            <button type="button" id="cancelEdit">Cancel</button>
        </form>
    </div>

</div>

<script src="PlayerCardScript.js"></script> <!-- External JavaScript -->
</body>
</html>
