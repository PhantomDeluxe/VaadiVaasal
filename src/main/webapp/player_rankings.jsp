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
            <label for="playerNameInput">Name:</label>
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
    <div class="edit-form-container" id="editFormContainer">
        <form id="editPlayerForm">
            <h2>Edit Player</h2>
            <input type="hidden" id="editPlayerId">
            <label for="editPlayerName">Name:</label>
            <input type="text" id="editPlayerName" required>

            <label for="editPlayerImage">Image URL:</label>
            <input type="text" id="editPlayerImage" required>

            <label for="editPlayerScore">Score:</label>
            <input type="number" id="editPlayerScore" required>

            <label for="editPlayerBullsTamed">Bulls Tamed:</label>
            <input type="number" id="editPlayerBullsTamed" required>

            <label for="editPlayerAge">Age:</label>
            <input type="number" id="editPlayerAge" required>

            <label for="editPlayerRegion">Region:</label>
            <input type="text" id="editPlayerRegion" required>

            <label for="editPlayerMatches">Matches Played:</label>
            <input type="number" id="editPlayerMatches" required>

            <button type="submit">Save Changes</button>
            <button type="button" id="cancelEdit">Cancel</button>
        </form>
    </div>

</div>

<script src="PlayerCardScript.js"></script> <!-- External JavaScript -->
</body>
</html>
