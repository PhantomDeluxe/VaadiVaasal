<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jallikattu Player Rankings</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .ranking-container {
            max-width: 1200px;
            margin: 50px auto;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .ranking-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .ranking-header h1 {
            font-size: 1.8rem;
            color: #333;
        }

        .ranking-header button {
            background: #d32f2f;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 1rem;
            border-radius: 6px;
            cursor: pointer;
        }

        .ranking-header button:hover {
            background: #b71c1c;
        }

        .player-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }

        .player-card {
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 12px;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            cursor: pointer;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }

        .player-card:hover {
            transform: scale(1.05);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }

        .player-card img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-bottom: 15px;
        }

        .form-container {
            display: none;
            margin-top: 20px;
            padding: 20px;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
    </style>
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
            <input type="text" id="playerNameInput" required>

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
</div>

<script>
    // Function to fetch and display players
    const renderGrid = () => {
        fetch('FetchPlayersServlet')
            .then(response => response.json())
            .then(players => {
                const playerGrid = document.getElementById('playerGrid');
                playerGrid.innerHTML = '';
                players.forEach(player => {
                    const card = document.createElement('div');
                    card.classList.add('player-card');
                    card.innerHTML = `
                        <img src="${player.image}" alt="${player.name}">
                        <h2>${player.name}</h2>
                        <p>Score: ${player.score}</p>
                        <p>Bulls Tamed: ${player.bullsTamed}</p>
                    `;
                    playerGrid.appendChild(card);
                });
            })
            .catch(error => console.error('Error fetching players:', error));
    };

    // Show form when "Add New Player" button is clicked
    document.getElementById('addPlayerBtn').addEventListener('click', () => {
        document.getElementById('playerFormContainer').style.display = 'block';
    });

    document.getElementById('addPlayerForm').addEventListener('submit', (event) => {
        event.preventDefault();

        const playerName = document.getElementById('playerNameInput').value.trim();

        if (!playerName) {
            alert("Error: Player name cannot be empty!");
            return; // Stop form submission
        }

        const formData = new FormData();
        formData.append("name", playerName);
        formData.append("image", document.getElementById('playerImageInput').value || "");
        formData.append("score", document.getElementById('playerScoreInput').value || "0");
        formData.append("bullsTamed", document.getElementById('playerBullsTamedInput').value || "0");
        formData.append("age", document.getElementById('playerAgeInput').value || "0");
        formData.append("region", document.getElementById('playerRegionInput').value || "");
        formData.append("matches", document.getElementById('playerMatchesInput').value || "0");

        fetch('PlayerServlet', {
            method: 'POST',
            body: formData
        })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "Success") {
                    alert("Player added successfully!");
                    document.getElementById('addPlayerForm').reset();
                    document.getElementById('playerFormContainer').style.display = 'none';
                    renderGrid();
                } else {
                    alert("Error: " + data);
                }
            })
            .catch(error => console.error('Error:', error));
    });


    // Load players when the page loads
    window.onload = renderGrid;
</script>
</body>
</html>
