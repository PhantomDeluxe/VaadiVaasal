<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu Rankings</title>
    <link rel="stylesheet" href="Bulls_tamers.css">
</head>
<body>
<div class="ranking-container">
    <div class="ranking-header">
        <h1>Jallikattu Tamer & Bull Rankings</h1>
        <button id="addTamerBullBtn">Add New Tamer & Bull</button>
    </div>

    <div class="player-grid" id="tamerBullGrid">
        <!-- Cards will be dynamically inserted here -->
    </div>

    <!-- Add Tamer & Bull Form -->
    <div class="form-container" id="tamerBullFormContainer">
        <form id="addTamerBullForm">
            <!-- Tamer Details -->
            <h2>Tamer Details</h2>
            <label for="tamerName">Name:</label>
            <input type="text" id="tamerName" required>

            <label for="tamerExperience">Experience (Years):</label>
            <input type="number" id="tamerExperience" required>

            <label for="tamerRegion">Region:</label>
            <input type="text" id="tamerRegion" required>

            <label for="tamerNationality">Nationality:</label>
            <input type="text" id="tamerNationality" required>

            <label for="matchesPlayed">Matches Played:</label>
            <input type="number" id="matchesPlayed" required>

            <!-- Bull Details -->
            <h2>Bull Details</h2>
            <label for="bullName">Bull Name:</label>
            <input type="text" id="bullName" required>

            <label for="bullStrength">Strength (1-100):</label>
            <input type="number" id="bullStrength" min="1" max="100" required>

            <label for="bullSpeed">Speed (1-100):</label>
            <input type="number" id="bullSpeed" min="1" max="100" required>

            <label for="bullBreed">Breed:</label>
            <input type="text" id="bullBreed" required>

            <button type="submit">Add Tamer & Bull</button>
        </form>
    </div>

    <!-- Edit Form (Pop-up) -->
    <div id="editFormContainer" class="popup" style="display: none;">
        <form id="editTamerBullForm">
            <input type="hidden" id="editTamerBullId">

            <h2>Edit Tamer Details</h2>
            <label>Name:</label> <input type="text" id="editTamerName">
            <label>Experience:</label> <input type="number" id="editTamerExperience">
            <label>Region:</label> <input type="text" id="editTamerRegion">
            <label>Nationality:</label> <input type="text" id="editTamerNationality">
            <label>Matches Played:</label> <input type="number" id="editMatchesPlayed">

            <h2>Edit Bull Details</h2>
            <label>Name:</label> <input type="text" id="editBullName">
            <label>Strength:</label> <input type="number" id="editBullStrength">
            <label>Speed:</label> <input type="number" id="editBullSpeed">
            <label>Breed:</label> <input type="text" id="editBullBreed">

            <button type="button" onclick="submitEditTamerBull()">Save Changes</button>
            <button type="button" id="cancelEdit">Cancel</button>
        </form>
    </div>

</div>

<script src="Bulls_tamers.js"></script>
</body>
</html>